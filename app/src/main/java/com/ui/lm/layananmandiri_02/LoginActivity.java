package com.ui.lm.layananmandiri_02;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;



import static com.ui.lm.layananmandiri_02.Constants.URL_LOGIN;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private BantuanMain bantuanMain;
    private LayananMain layananMain;
    private LaporActivity laporActivity;
    private DashboardActivity dasboardActivity;
    private EditText editTextNIK, editTextPassword;
    private Spinner editTextDesa, getTextKecamatan;
    private Button buttonMasuk;
    private ProgressDialog progressDialog;
    private String link;
//    public String desa = "";

    AutoCompleteTextView suggestion_box;
    public Spinner itemKecamatan, itemDesa;

    ArrayList<String> namaDesa = new ArrayList<>();
    ArrayList<String> namaKecamatan = new ArrayList<>();
    public String url;

   @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        if(SharedPreManager.getInstance(this).isLoggedIn()){
           finish();
           startActivity(new Intent(this, DashboardActivity.class));
           return;
        }

        editTextNIK = (EditText) findViewById(R.id.nikid);
        editTextPassword = (EditText) findViewById(R.id.noTknid);
        buttonMasuk = (Button) findViewById(R.id.masuktombol);
        editTextDesa = (Spinner) findViewById(R.id.desaid);
        getTextKecamatan = (Spinner) findViewById(R.id.kecamatanid);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait...");

        buttonMasuk.setOnClickListener(this);

        itemKecamatan = (Spinner)findViewById(R.id.kecamatanid);
        itemDesa = (Spinner)findViewById(R.id.desaid);

//        Digunakaan untuk menambahkan alamat kecamatan
        final String[] kecamatan = {"-- Pilih Kecamatan --","Kecamatan Ketapang","Kecamatan Ciparay","Kecamatan Margaasih","Kecamatan Pameungpeuk",
                                "Kecamatan Cangkuang","Kecamatan Nagreg","Kecamatan Paseh","Kecamatan Solokanjeruk","Kecamatan Cikancung","Kecamatan Arjasari"
                                ,"Kecamatan Banjaran","Kecamatan Cimaung","Kecamatan Pangalengan","Kecamatan Baleendah","Kecamatan Bojongsoang"
                                ,"Kecamatan Dayeuhkolot","Kecamatan Margahayu","Kecamatan Ibun","Kecamatan Pacet","Kecamatan Kertasari"
                                ,"Kecamatan Majalaya","Kecamatan Rancaekek","Kecamatan Cicalengka","Kecamatan Cileunyi","Kecamatan Cimenyan"
                                ,"Kecamatan Cilengkrang","Kecamatan Soreang","Kecamatan Pasirjambu","Kecamatan Ciwidey","Kecamatan Rancabali","Kecamatan Kutawaringin"};
        final String[] ketapang = {"Desa Banyusari","Desa Cilampeni","Desa Gandasari","Desa Katapang","Desa Pangauban","Desa Sangkanhurip","Desa Sukamukti"};
        final String[] ciparay = {"Desa Babakan","Desa Bumiwangi","Desa Ciheulang","Desa Cikoneng","Desa Ciparay","Desa Gunungleutik",
                                    "Desa Manggungharja","Desa Mekar Laksana","Desa Mekarsari","Desa Pakutandang","Desa Sarimahi",
                                        "Desa Serangmekar","Desa Sagaracipta","Desa Sumbersari"};
        final String[] margaasih = {"Desa Cigondewah Hilir","Desa Margaasih","Desa Lagadar","Desa Nanjung","Desa Mekarrahayu","Desa Rahayu"};
        final String[] pameungpeuk = {"Desa Bojongkunci","Desa Bojongmanggu","Desa Langonsari","Desa Rancamulya","Desa Rancatungku","Desa Sukasari"};
        final String[] cangkuang = {"Desa Bandasari","Desa Cangkuang","Desa Ciluncat","Desa Jatisari","Desa Nagrak","Desa Pananjung","Desa Tanjungsari"};
        final String[] nagrak = {"Desa Bojong","Desa Ciaro","Desa Ciherang","Desa Citaman","Desa Ganjar Sabar","Desa Mandalawangi","Desa Nagreg","Desa Nagreg Kendan"};
        final String[] paseh = {"Desa Cigentur","Desa Cijagra","Desa Cipaku","Desa Cipedes","Desa Drawati","Desa Karangtunggal","Desa Loa"
                                ,"Desa Mekarpawitan","Desa Sindangsari","Desa Sukamanah","Desa Sukamantri","Desa Tangsimekar"};
        final String[] solokanjeruk = {"Desa Bojongemas","Desa Cibodas","Desa Langensari","Desa Padamukti","Desa Panyadap","Desa Rancakasumba","Desa Solokanjeruk"};
        final String[] cikancung = {"Desa Cihanyir","Desa Cikancung","Desa Cikasungka","Desa Ciluluk","Desa Hegarmanah","Desa Mandalasari","Desa Mekarlaksana",
                                    "Desa Srirahayu","Desa Tanjunglaya"};
        final String[] arjasari = {"Desa Ancolmekar","Desa Arjasari","Desa Baros","Desa Batukarut","Desa Lebakwangi","Desa Mangunjaya","Desa Mekarjaya Banjaran"
                                    ,"Desa Patrolsari","Desa Pinggirsari","Desa Rancakole","Desa Wargaluyu" };
        final String[] banjaran = {"Desa Banjaran","Desa Banjaran Wetan","Desa Ciapus","Desa Kamasan","Desa Kiangroke","Desa Margahurip","Desa Mekarjaya","Desa Neglasari"
                                    ,"Desa Pasirmulya","Desa Sindangpanon","Desa Tarajusari" };
        final String[] cimaung = {"Desa Campakamulya","Desa Cikalong","Desa Cimaung","Desa Cipinang","Desa Jagabaya","Desa Malasari"
                                    ,"Desa Pasirhuni","Desa Sukamaju","Desa Mekarsari Cimaung","Desa Warjabakti"};
        final String[] pangalengan = {"Desa Banjarsari","Desa Lamajang","Desa Margaluyu","Desa Margamekar","Desa Margamukti","Desa Margamulya","Desa Pangalengan"
                                    ,"Desa Pulosari","Desa Sukaluyu","Desa Sukamanah Pengalengan","Desa Tribaktimulya","Desa Wanasuka","Desa Warnasari" };
        final String[] baleendah ={"Desa Bojongmalaka","Desa Malakasari","Desa Rancamanyar","Kelurahan Andir","Kelurahan Baleendah"
                                    ,"Kelurahan Jelekong","Kelurahan Manggahang","Kelurahan Wargamekar"};
        final String[] bojongsoang ={"Desa Bojongsari","Desa Bojongsoang","Desa Buahbatu","Desa Cipagalo","Desa Lengkong","Desa Tegalluar" };
        final String[] dayeuhkolot ={"Desa Cangkuang Kulon","Desa Cangkuang Wetan","Desa Citeureup","Desa Dayeuhkolot","Desa Sukapura","Kelurahan Pasawahan" };
        final String[] margahayu = {"Desa Margahayu Tengah","Desa Margahayu Selatan","Desa Sukamenak","Desa Sayati","Kelurahan Sulaiman" };
        final String[] ibun = {"Desa Cibeet","Desa Dukuh","Desa Ibun","Desa Karyalaksana","Desa Laksana","Desa Lampegan","Desa Mekarwangi",
                                "Desa Neglasari Ibun","Desa Pangguh","Desa Sudi","Desa Talun","Desa Tanggulun"};
        final String[] pacet = {"Desa Cinanggela","Desa Cikawao","Desa Cikitu","Desa Cipeujeuh","Desa Girimulya","Desa Mandalahaji","Desa Maruyung",
                                "Desa Mekarjaya Pacet","Desa Mekarsari Pacet","Desa Nagrak Pacet","Desa Pangauban Pacet","Desa Sukarame","Desa Tanjungwangi"};
        final String[] kertasari = {"Desa Cibeureum","Desa Cihawuk","Desa Cikembang","Desa Neglawangi","Desa Resmi Tingal",
                                    "Desa Santosa","Desa Sukapura Kertasari","Desa Tarumajaya"};
        final String[] majalaya = {"Desa Biru","Desa Bojong Majalaya","Desa Majakerta","Desa Majalaya","Desa Majasetra","Desa Neglasari Majalaya","Desa Padamulya",
                                    "Desa Padaulun","Desa Sukamaju Majalaya","Desa Sukamukti Majalaya","Desa Wangisagara"};
        final String[] rancaekek ={"Desa Bojongloa","Desa Bojongsalam","Desa Cangkuang Rancaekek","Desa Haurpugur","Desa Jelegong","Desa Linggar","Desa Nanjung Mekar",
                "Desa Rancaekek Kulon","Desa Rancaekek Wetan","Desa Sangiang","Desa Sukamanah Rancaekek","Desa Sukamulya","Desa Tegal Sumedang","Kelurahan Kencana"};
        final String[] cicalengka = {"Desa Babakan Peuteuy","Desa Cicalengka Kulon","Desa Cicalengka Wetan","Desa Cikuya","Desa Dampit","Desa Margaasih Cicalengka"
                                     ,"Desa Nagrog","Desa Narawita","Desa Panenjoan","Desa Tanjungwangi Cicalengka","Desa Tenjolaya","Desa Waluya"};
        final String[] cileunyi = {"Desa Cibiru Hilir","Desa Cibiru Wetan","Desa Cileunyi Kulon","Desa Cileunyi Wetan","Desa Cimekar","Desa Cinunuk"};
        final String[] cimenyan = {"Desa Ciburial","Desa Cikadut","Desa Cimenyan","Desa Mandalamekar","Desa Mekarmanik","Desa Mekarsaluyu"
                                    ,"Desa Sindanglaya","Kelurahan Cibeunying","Kelurahan Padasuka"};
        final String[] cilengkrang ={"Desa Cilengkrang","Desa Cipanjalu","Desa Ciporeat","Desa Girimekar","Desa Jatiendah","Desa Melatiwangi"};
        final String[] soreang ={"Desa Cingcin","Desa Karamatmulya","Desa Pamekaran","Desa Panyirapan","Desa Parungserab","Desa Sadu"
                                    ,"Desa Sekarwangi","Desa Soreang","Desa Sukajadi","Desa Sukanagara"};
        final String[] pasirjambu ={"Desa Cibodas Pasirjambu","Desa Cikoneng Pasirjambu","Desa Cisondari","Desa Cukanggenteng","Desa Margamulya Pasirjambu","Desa Mekarmaju","Desa Mekarsari Pasirjambu"
                                    ,"Desa Pasirjambu","Desa Sugihmukti","Desa Tenjolaya Pasirjambu"};
        final String[] ciwiday ={"Desa Ciwidey","Desa Lebakmuncang","Desa Nengkelan","Desa Panundaan","Desa Panyocokan","Desa Rawabogo","Desa Sukawening"};
        final String[] rancabali ={"Desa Alamendah","Desa Cipelah","Desa Indragiri","Desa Patengan","Desa Sukaresmi"};
        final String[] kutawaringin ={"Desa Buninagara","Desa Cibodas Kutawaringin","Desa Cilame","Desa Gajah Mekar","Desa Jatisari","Desa Jelegong"
                                        ,"Desa Kopo","Desa Kutawaringin","Desa Padasuka","Desa Pameuntasan","Desa Sukamulya"};

        Arrays.sort(kecamatan);
        for(int i = 0;i<kecamatan.length;i++) {
                namaKecamatan.add(kecamatan[i]);
        }

       ArrayAdapter<String> adapterKecamatan = new ArrayAdapter<String>(LoginActivity.this,android.R.layout.simple_spinner_dropdown_item,namaKecamatan);

       itemKecamatan.setAdapter(adapterKecamatan);


       namaDesa.add("-- Pilih Desa --");
       itemKecamatan.setOnItemSelectedListener(new OnItemSelectedListener() {
           @Override
           public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
               if(getTextKecamatan.getSelectedItem() == "Kecamatan Margahayu") {
                   namaDesa.clear();
                   for(int i = 0;i<margahayu.length;i++) {
                           namaDesa.add(margahayu[i]);
                   }
               }else if(getTextKecamatan.getSelectedItem() == "Kecamatan Ketapang"){
                   namaDesa.clear();
                   for(int i = 0;i<ketapang.length;i++) {
                       namaDesa.add(ketapang[i]);
                   }
               }else if(getTextKecamatan.getSelectedItem() == "Kecamatan Ciparay"){
                   namaDesa.clear();
                   for(int i = 0;i<ciparay.length;i++) {
                       namaDesa.add(ciparay[i]);
                   }
               }else if(getTextKecamatan.getSelectedItem() == "Kecamatan Margaasih"){
                   namaDesa.clear();
                   for(int i = 0;i<margaasih.length;i++) {
                       namaDesa.add(margaasih[i]);
                   }
               }else if(getTextKecamatan.getSelectedItem() == "Kecamatan Pameungpeuk"){
                   namaDesa.clear();
                   for(int i = 0;i<pameungpeuk.length;i++) {
                       namaDesa.add(pameungpeuk[i]);
                   }
               }else if(getTextKecamatan.getSelectedItem() == "Kecamatan Cangkuang"){
                   namaDesa.clear();
                   for(int i = 0;i<cangkuang.length;i++) {
                       namaDesa.add(cangkuang[i]);
                   }
               }else if(getTextKecamatan.getSelectedItem() == "Kecamatan Nagreg"){
                   namaDesa.clear();
                   for(int i = 0;i<nagrak.length;i++) {
                       namaDesa.add(nagrak[i]);
                   }
               }else if(getTextKecamatan.getSelectedItem() == "Kecamatan Paseh"){
                   namaDesa.clear();
                   for(int i = 0;i<paseh.length;i++) {
                       namaDesa.add(paseh[i]);
                   }
               }else if(getTextKecamatan.getSelectedItem() == "Kecamatan Solokanjeruk"){
                   namaDesa.clear();
                   for(int i = 0;i<solokanjeruk.length;i++) {
                       namaDesa.add(solokanjeruk[i]);
                   }
               }else if(getTextKecamatan.getSelectedItem() == "Kecamatan Cikancung"){
                   namaDesa.clear();
                   for(int i = 0;i<cikancung.length;i++) {
                       namaDesa.add(cikancung[i]);
                   }
               }else if(getTextKecamatan.getSelectedItem() == "Kecamatan Arjasari"){
                   namaDesa.clear();
                   for(int i = 0;i<arjasari.length;i++) {
                       namaDesa.add(arjasari[i]);
                   }
               }else if(getTextKecamatan.getSelectedItem() == "Kecamatan Banjaran"){
                   namaDesa.clear();
                   for(int i = 0;i<banjaran.length;i++) {
                       namaDesa.add(banjaran[i]);
                   }
               }else if(getTextKecamatan.getSelectedItem() == "Kecamatan Cimaung"){
                   namaDesa.clear();
                   for(int i = 0;i<cimaung.length;i++) {
                       namaDesa.add(cimaung[i]);
                   }
               }else if(getTextKecamatan.getSelectedItem() == "Kecamatan Pangalengan"){
                   namaDesa.clear();
                   for(int i = 0;i<pangalengan.length;i++) {
                       namaDesa.add(pangalengan[i]);
                   }
               }else if(getTextKecamatan.getSelectedItem() == "Kecamatan Baleendah"){
                   namaDesa.clear();
                   for(int i = 0;i<baleendah.length;i++) {
                       namaDesa.add(baleendah[i]);
                   }
               }else if(getTextKecamatan.getSelectedItem() == "Kecamatan Bojongsoang"){
                   namaDesa.clear();
                   for(int i = 0;i<bojongsoang.length;i++) {
                       namaDesa.add(bojongsoang[i]);
                   }
               }else if(getTextKecamatan.getSelectedItem() == "Kecamatan Dayeuhkolot"){
                   namaDesa.clear();
                   for(int i = 0;i<dayeuhkolot.length;i++) {
                       namaDesa.add(dayeuhkolot[i]);
                   }
               }else if(getTextKecamatan.getSelectedItem() == "Kecamatan Ibun"){
                   namaDesa.clear();
                   for(int i = 0;i<ibun.length;i++) {
                       namaDesa.add(ibun[i]);
                   }
               }else if(getTextKecamatan.getSelectedItem() == "Kecamatan Pacet"){
                   namaDesa.clear();
                   for(int i = 0;i<pacet.length;i++) {
                       namaDesa.add(pacet[i]);
                   }
               }else if(getTextKecamatan.getSelectedItem() == "Kecamatan Kertasari"){
                   namaDesa.clear();
                   for(int i = 0;i<kertasari.length;i++) {
                       namaDesa.add(kertasari[i]);
                   }
               }else if(getTextKecamatan.getSelectedItem() == "Kecamatan Majalaya"){
                   namaDesa.clear();
                   for(int i = 0;i<majalaya.length;i++) {
                       namaDesa.add(majalaya[i]);
                   }
               }else if(getTextKecamatan.getSelectedItem() == "Kecamatan Rancaekek"){
                   namaDesa.clear();
                   for(int i = 0;i<rancaekek.length;i++) {
                       namaDesa.add(rancaekek[i]);
                   }
               }else if(getTextKecamatan.getSelectedItem() == "Kecamatan Cicalengka"){
                   namaDesa.clear();
                   for(int i = 0;i<cicalengka.length;i++) {
                       namaDesa.add(cicalengka[i]);
                   }
               }else if(getTextKecamatan.getSelectedItem() == "Kecamatan Cileunyi"){
                   namaDesa.clear();
                   for(int i = 0;i<cileunyi.length;i++) {
                       namaDesa.add(cileunyi[i]);
                   }
               }else if(getTextKecamatan.getSelectedItem() == "Kecamatan Cimenyan"){
                   namaDesa.clear();
                   for(int i = 0;i<cimenyan.length;i++) {
                       namaDesa.add(cimenyan[i]);
                   }
               }else if(getTextKecamatan.getSelectedItem() == "Kecamatan Cilengkrang"){
                   namaDesa.clear();
                   for(int i = 0;i<cilengkrang.length;i++) {
                       namaDesa.add(cilengkrang[i]);
                   }
               }else if(getTextKecamatan.getSelectedItem() == "Kecamatan Soreang"){
                   namaDesa.clear();
                   for(int i = 0;i<soreang.length;i++) {
                       namaDesa.add(soreang[i]);
                   }
               }else if(getTextKecamatan.getSelectedItem() == "Kecamatan Ciwidey"){
                   namaDesa.clear();
                   for(int i = 0;i<ciwiday.length;i++) {
                       namaDesa.add(ciwiday[i]);
                   }
               }else if(getTextKecamatan.getSelectedItem() == "Kecamatan Pasirjambu"){
                   namaDesa.clear();
                   for(int i = 0;i<pasirjambu.length;i++) {
                       namaDesa.add(pasirjambu[i]);
                   }
               }else if(getTextKecamatan.getSelectedItem() == "Kecamatan Rancabali"){
                   namaDesa.clear();
                   for(int i = 0;i<rancabali.length;i++) {
                       namaDesa.add(rancabali[i]);
                   }
               }else if(getTextKecamatan.getSelectedItem() == "Kecamatan Kutawaringin"){
                   namaDesa.clear();
                   for(int i = 0;i<kutawaringin.length;i++) {
                       namaDesa.add(kutawaringin[i]);
                   }
               }

               ArrayAdapter<String> adapterDesa = new ArrayAdapter<String>(LoginActivity.this,android.R.layout.simple_spinner_dropdown_item,namaDesa);
               itemDesa.setAdapter(adapterDesa);


           }

           @Override
           public void onNothingSelected(AdapterView<?> parentView) {
               // your code here
           }


       });





    }


    private void userLogin(){
        final String nik = editTextNIK.getText().toString().trim();
        final String pin = editTextPassword.getText().toString().trim();
        final String pilih = String.valueOf((editTextDesa.getSelectedItem()));

        if(pilih=="Desa Banyusari"){
            this.url = "http://banyusari.desa.id/";
        }else if(pilih=="Desa Cilampeni"){
            this.url = "http://cilampeni.desa.id/";
        }else if(pilih=="Desa Gandasari"){
            this.url = "http://gandasari.desa.id/";
        }else if(pilih=="Desa Katapang"){
            this.url = "http://katapang.desa.id/";
        }else if(pilih=="Desa Pangauban"){
            this.url = "http://pangauban-katapang.desa.id/";
        }else if(pilih=="Desa Sangkanhurip"){
            this.url = "http://sangkanhurip.desa.id/";
        }else if(pilih=="Desa Sukamukti"){
            this.url = "http://sukamukti-katapang.desa.id/";
        }else if(pilih=="Desa Babakan"){
            this.url = "http://babakan-ciparay.desa.id/";
        }else if(pilih=="Desa Bumiwangi"){
            this.url = "http://bumiwangi.desa.id/";
        }else if(pilih=="Desa Ciheulang"){
            this.url = "http://ciheulang.desa.id/";
        }else if(pilih=="Desa Cikoneng"){
            this.url = "http://cikoneng-ciparay.desa.id/";
        }else if(pilih=="Desa Ciparay"){
            this.url = "http://ciparay.desa.id/";
        }else if(pilih=="Desa Gunungleutik"){
            this.url = "http://gunungleutik.desa.id/";
        }else if(pilih=="Desa Manggungharja"){
            this.url = "http://manggungharja.desa.id/";
        }else if(pilih=="Desa Mekar Laksana"){
            this.url = "http://mekarlaksana-ciparay.desa.id/";
        }else if(pilih=="Desa Mekarsari"){
            this.url = "http://mekarsari-ciparay.desa.id/";
        }else if(pilih=="Desa Pakutandang"){
            this.url = "http://pakutandang.desa.id/";
        }else if(pilih=="Desa Sarimahi"){
            this.url = "http://sarimahi.desa.id/";
        }else if(pilih=="Desa Serangmekar"){
            this.url = "http://serangmekar.desa.id/";
        }else if(pilih=="Desa Sagaracipta"){
            this.url = "http://sagaracipta.desa.id/";
        }else if(pilih=="Desa Sumbersari"){
            this.url = "http://sumbersari-ciparay.desa.id/";
        }else if(pilih=="Desa Cigondewah Hilir"){
            this.url = "http://cigondewahhilir.desa.id/";
        }else if(pilih=="Desa Margaasih"){
            this.url = "http://margaasih.desa.id/";
        }else if(pilih=="Desa Lagadar"){
            this.url = "http://lagadar.desa.id/";
        }else if(pilih=="Desa Nanjung"){
            this.url = "http://nanjung.desa.id/";
        }else if(pilih=="Desa Mekarrahayu"){
            this.url = "http://mekarrahayu.desa.id/";
        }else if(pilih=="Desa Rahayu"){
            this.url = "http://rahayu.desa.id/";
        }else if(pilih=="Desa Bojongkunci"){
            this.url = "http://bojongkunci.desa.id/";
        }else if(pilih=="Desa Bojongmanggu"){
            this.url = "http://bojongmanggu.desa.id/";
        }else if(pilih=="Desa Langonsari"){
            this.url = "http://langonsari.desa.id/";
        }else if(pilih=="Desa Rancamulya"){
            this.url = "http://rancamulya.desa.id/";
        }else if(pilih=="Desa Rancatungku"){
            this.url = "http://rancatungku.desa.id/";
        }else if(pilih=="Desa Sukasari"){
            this.url = "http://sukasari.desa.id/";
        }else if(pilih=="Desa Bandasari"){
            this.url = "http://bandasari.desa.id/";
        }else if(pilih=="Desa Cangkuang"){
            this.url = "http://cangkuang.desa.id/";
        }else if(pilih=="Desa Ciluncat"){
            this.url = "http://ciluncat.desa.id/";
        }else if(pilih=="Desa Jatisari"){
            this.url = "http://jatisari-cangkuang.desa.id/";
        }else if(pilih=="Desa Nagrak"){
            this.url = "http://nagrak-cangkuang.desa.id/";
        }else if(pilih=="Desa Pananjung"){
            this.url = "http://pananjung.desa.id/";
        }else if(pilih=="Desa Tanjungsari"){
            this.url = "http://tanjungsari-cangkuang.desa.id/";
        }else if(pilih=="Desa Bojong"){
            this.url = "http://bojong-nagreg.desa.id/";
        }else if(pilih=="Desa Ciaro"){
            this.url = "http://ciaro.desa.id/";
        }else if(pilih=="Desa Ciherang"){
            this.url = "http://ciherang-nagreg.desa.id/";
        }else if(pilih=="Desa Citaman"){
            this.url = "http://citaman.desa.id/";
        }else if(pilih=="Desa Ganjar Sabar"){
            this.url = "http://ganjarsabar.desa.id/";
        }else if(pilih=="Desa Mandalawangi"){
            this.url = "http://mandalawangi-nagreg.desa.id/";
        }else if(pilih=="Desa Nagreg"){
            this.url = "http://nagreg.desa.id/";
        }else if(pilih=="Desa Nagreg Kendan"){
            this.url = "http://nagregkendan.desa.id/";
        }else if(pilih=="Desa Cigentur"){
            this.url = "http://cigentur.desa.id/";
        }else if(pilih=="Desa Cijagra"){
            this.url = "http://cijagra.desa.id/";
        }else if(pilih=="Desa Cipaku"){
            this.url = "http://cipaku.desa.id/";
        }else if(pilih=="Desa Cipedes"){
            this.url = "http://cipedes.desa.id/";
        }else if(pilih=="Desa Drawati"){
            this.url = "http://drawati.desa.id/";
        }else if(pilih=="Desa Karangtunggal"){
            this.url = "http://karangtunggal.desa.id/";
        }else if(pilih=="Desa Loa"){
            this.url = "http://loa.desa.id/";
        }else if(pilih=="Desa Mekarpawitan"){
            this.url = "http://mekarpawitan.desa.id/";
        }else if(pilih=="Desa Sindangsari"){
            this.url = "http://sindangsari.desa.id/";
        }else if(pilih=="Desa Sukamanah"){
            this.url = "http://sukamanah-paseh.desa.id/";
        }else if(pilih=="Desa Sukamantri"){
            this.url = "http://sukamantri.desa.id/";
        }else if(pilih=="Desa Tangsimekar"){
            this.url = "http://tangsimekar.desa.id/";
        }else if(pilih=="Desa Bojongemas"){
            this.url = "http://bojongemas.desa.id/";
        }else if(pilih=="Desa Cibodas"){
            this.url = "http://cibodas-solokanjeruk.desa.id/";
        }else if(pilih=="Desa Langensari"){
            this.url = "http://langensari.desa.id/";
        }else if(pilih=="Desa Padamukti"){
            this.url = "http://padamukti.desa.id/";
        }else if(pilih=="Desa Panyadap"){
            this.url = "http://panyadap.desa.id/";
        }else if(pilih=="Desa Rancakasumba"){
            this.url = "http://rancakasumba.desa.id/";
        }else if(pilih=="Desa Solokanjeruk"){
            this.url = "http://solokanjeruk.desa.id/";
        }else if(pilih=="Desa Cihanyir"){
            this.url = "http://cihanyir.desa.id/";
        }else if(pilih=="Desa Cikancung"){
            this.url = "http://cikancung.desa.id/";
        }else if(pilih=="Desa Cikasungka"){
            this.url = "http://cikasungka.desa.id/";
        }else if(pilih=="Desa Ciluluk"){
            this.url = "http://ciluluk.desa.id/";
        }else if(pilih=="Desa Hegarmanah"){
            this.url = "http://hegarmanah-cikancung.desa.id/";
        }else if(pilih=="Desa Mandalasari"){
            this.url = "http://mandalasari.desa.id/";
        }else if(pilih=="Desa Mekarlaksana"){
            this.url = "http://mekarlaksana-cikancung.desa.id/";
        }else if(pilih=="Desa Srirahayu"){
            this.url = "http://srirahayu.desa.id/";
        }else if(pilih=="Desa Tanjunglaya"){
            this.url = "http://tanjunglaya.desa.id/";
        }else if(pilih=="Desa Ancolmekar"){
            this.url = "http://ancolmekar.desa.id/";
        }else if(pilih=="Desa Arjasari "){
            this.url = "http://arjasari.desa.id/";
        }else if(pilih=="Desa Baros"){
            this.url = "http://baros.desa.id/";
        }else if(pilih=="Desa Batukarut"){
            this.url = "http://batukarut.desa.id/";
        }else if(pilih=="Desa Lebakwangi"){
            this.url = "http://lebakwangi.desa.id/";
        }else if(pilih=="Desa Mangunjaya"){
            this.url = "http://mangunjaya.desa.id/";
        }else if(pilih=="Desa Mekarjaya"){
            this.url = "http://mekarjaya-arjasari.desa.id/";
        }else if(pilih=="Desa Patrolsari"){
            this.url = "http://patrolsari.desa.id/";
        }else if(pilih=="Desa Pinggirsari"){
            this.url = "http://pinggirsari.desa.id/";
        }else if(pilih=="Desa Rancakole"){
            this.url = "http://rancakole.desa.id/";
        }else if(pilih=="Desa Wargaluyu"){
            this.url = "http://wargaluyu.desa.id/";
        }else if(pilih=="Desa Banjaran"){
            this.url = "http://banjaran.desa.id/";
        }else if(pilih=="Desa Banjaran Wetan"){
            this.url = "http://banjaranwetan.desa.id/";
        }else if(pilih=="Desa Ciapus"){
            this.url = "http://ciapus.desa.id/";
        }else if(pilih=="Desa Kamasan"){
            this.url = "http://kamasan-banjaran.desa.id/";
        }else if(pilih=="Desa Kiangroke"){
            this.url = "http://kiangroke.desa.id/";
        }else if(pilih=="Desa Margahurip"){
            this.url = "http://margahurip.desa.id/";
        }else if(pilih=="Desa Mekarjaya Banjaran"){
            this.url = "http://mekarjaya-banjaran.desa.id/";
        }else if(pilih=="Desa Neglasari"){
            this.url = "http://neglasari.desa.id/";
        }else if(pilih=="Desa Pasirmulya"){
            this.url = "http://pasirmulya.desa.id/";
        }else if(pilih=="Desa Sindangpanon"){
            this.url = "http://sindangpanon.desa.id/";
        }else if(pilih=="Desa Tarajusari"){
            this.url = "http://tarajusari.desa.id/";
        }else if(pilih=="Desa Campakamulya"){
            this.url = "http://campakamulya.desa.id/";
        }else if(pilih=="Desa Cikalong"){
            this.url = "http://cikalong.desa.id/";
        }else if(pilih=="Desa Cimaung"){
            this.url = "http://cimaung.desa.id/";
        }else if(pilih=="Desa Cipinang"){
            this.url = "http://cipinang.desa.id/";
        }else if(pilih=="Desa Jagabaya"){
            this.url = "http://jagabaya.desa.id/";
        }else if(pilih=="Desa Malasari"){
            this.url = "http://malasari-cimaung.desa.id/";
        }else if(pilih=="Desa Pasirhuni"){
            this.url = "http://pasirhuni.desa.id/";
        }else if(pilih=="Desa Sukamaju"){
            this.url = "http://sukamaju-cimaung.desa.id/";
        }else if(pilih=="Desa Mekarsari Cimaung"){
            this.url = "http://mekarsari-cimaung.desa.id/";
        }else if(pilih=="Desa Warjabakti"){
            this.url = "http://warjabakti.desa.id/";
        }else if(pilih=="Desa Banjarsari"){
            this.url = "http://banjarsari-pangalengan.desa.id/";
        }else if(pilih=="Desa Lamajang"){
            this.url = "http://lamajang.desa.id/";
        }else if(pilih=="Desa Margaluyu"){
            this.url = "http://margaluyu.desa.id/";
        }else if(pilih=="Desa Margamekar"){
            this.url = "http://margamekar.desa.id/";
        }else if(pilih=="Desa Margamukti"){
            this.url = "http://margamukti.desa.id/";
        }else if(pilih=="Desa Margamulya"){
            this.url = "http://margamulya-pangalengan.desa.id/";
        }else if(pilih=="Desa Pangalengan"){
            this.url = "http://pangalengan.desa.id/";
        }else if(pilih=="Desa Pulosari"){
            this.url = "http://pulosari.desa.id/";
        }else if(pilih=="Desa Sukaluyu"){
            this.url = "http://sukaluyu-pangalengan.desa.id/";
        }else if(pilih=="Desa Sukamanah Pengalengan"){
            this.url = "http://sukamanah-pangalengan.desa.id/";
        }else if(pilih=="Desa Tribaktimulya"){
            this.url = "http://tribaktimulya.desa.id/";
        }else if(pilih=="Desa Wanasuka"){
            this.url = "http://wanasuka.desa.id/";
        }else if(pilih=="Desa Warnasari"){
            this.url = "http://warnasari.desa.id/";
        }else if(pilih=="Desa Bojongmalaka"){
            this.url = "http://bojongmalaka.desa.id/";
        }else if(pilih=="Desa Malakasari"){
            this.url = "http://malakasari.desa.id/";
        }else if(pilih=="Desa Rancamanyar"){
            this.url = "http://rancamanyar.desa.id/";
        }else if(pilih=="Desa Bojongsari"){
            this.url = "http://bojongsari.desa.id/";
        }else if(pilih=="Desa Bojongsoang"){
            this.url = "http://bojongsoang.desa.id/";
        }else if(pilih=="Desa Buahbatu"){
            this.url = "http://buahbatu.desa.id/";
        }else if(pilih=="Desa Cipagalo"){
            this.url = "http://cipagalo.desa.id/";
        }else if(pilih=="Desa Lengkong"){
            this.url = "http://lengkong.desa.id/";
        }else if(pilih=="Desa Tegalluar"){
            this.url = "http://tegalluar.desa.id/";
        }else if(pilih=="Desa Cangkuang Kulon"){
            this.url = "http://cangkuangkulon.desa.id/";
        }else if(pilih=="Desa Cangkuang Wetan"){
            this.url = "http://cangkuangwetan.desa.id/";
        }else if(pilih=="Desa Citeureup"){
            this.url = "http://citeureup-bandung.desa.id/";
        }else if(pilih=="Desa Dayeuhkolot"){
            this.url = "http://dayeuhkolot.desa.id/";
        }else if(pilih=="Desa Sukapura"){
            this.url = "http://sukapura.desa.id/";
        }else if(pilih=="Desa Margahayu Tengah"){
            this.url = "http://margahayutengah.desa.id/";
        }else if(pilih=="Desa Margahayu Selatan"){
            this.url = "http://margahayuselatan.desa.id/";
        }else if(pilih=="Desa Sukamenak"){
            this.url = "http://sukamenak.desa.id/";
        }else if(pilih=="Desa Sayati"){
            this.url = "http://sayati.desa.id/";
        }else if(pilih=="Kelurahan Andir"){
            this.url = "http://kelurahanandir.bandungkab.go.id/";
        }else if(pilih=="Kelurahan Baleendah"){
            this.url = "http://kelurahanbaleendah.bandungkab.go.id/";
        }else if(pilih=="Kelurahan Jelekong"){
            this.url = "http://kelurahanjelekong.bandungkab.go.id/";
        }else if(pilih=="Kelurahan Manggahang"){
            this.url = "http://kelurahanmanggahang.bandungkab.go.id/";
        }else if(pilih=="Kelurahan Wargamekar"){
            this.url = "http://kelurahanwargamekar.bandungkab.go.id/";
        }else if(pilih=="Kelurahan Pasawahan"){
            this.url = "http://kelurahanpasawahan.bandungkab.go.id/";
        }else if(pilih=="Kelurahan Sulaiman"){
            this.url = "http://kelurahansulaiman.bandungkab.go.id/";
        }else if(pilih=="Desa Cibeet"){
            this.url = "http://cibeet.desa.id/";
        }else if(pilih=="Desa Dukuh"){
            this.url = "http://dukuh.desa.id/";
        }else if(pilih=="Desa Ibun"){
            this.url = "http://ibun.desa.id/";
        }else if(pilih=="Desa Karyalaksana"){
            this.url = "http://karyalaksana.desa.id/";
        }else if(pilih=="Desa Laksana"){
            this.url = "http://laksana.desa.id/";
        }else if(pilih=="Desa Lampegan"){
            this.url = "http://lampegan-ibun.desa.id/";
        }else if(pilih=="Desa Mekarwangi"){
            this.url = "http://mekarwangi-ibun.desa.id/";
        }else if(pilih=="Desa Neglasari Ibun"){
            this.url = "http://neglasari-ibun.desa.id/";
        }else if(pilih=="Desa Pangguh"){
            this.url = "http://pangguh.desa.id/";
        }else if(pilih=="Desa Sudi"){
            this.url = "http://sudi.desa.id/";
        }else if(pilih=="Desa Talun"){
            this.url = "http://talun.desa.id/";
        }else if(pilih=="Desa Tanggulun"){
            this.url = "http://tanggulun.desa.id/";
        }else if(pilih=="Desa Cinanggela"){
            this.url = "http://cinanggela.desa.id/";
        }else if(pilih=="Desa Cikawao"){
            this.url = "http://cikawao.desa.id/";
        }else if(pilih=="Desa Cikitu"){
            this.url = "http://cikitu.desa.id/";
        }else if(pilih=="Desa Cipeujeuh"){
            this.url = "http://cipeujeuh.desa.id/";
        }else if(pilih=="Desa Girimulya"){
            this.url = "http://girimulya.desa.id/";
        }else if(pilih=="Desa Mandalahaji"){
            this.url = "http://mandalahaji.desa.id/";
        }else if(pilih=="Desa Maruyung"){
            this.url = "http://maruyung.desa.id/";
        }else if(pilih=="Desa Mekarjaya Pacet"){
            this.url = "http://mekarjaya.desa.id/";
        }else if(pilih=="Desa Mekarsari Pacet"){
            this.url = "http://mekarsari-pacet.desa.id/";
        }else if(pilih=="Desa Nagrak Pacet"){
            this.url = "http://nagrak.desa.id/";
        }else if(pilih=="Desa Pangauban Pacet"){
            this.url = "http://pangauban.desa.id/";
        }else if(pilih=="Desa Sukarame"){
            this.url = "http://sukarame-pacet.desa.id/";
        }else if(pilih=="Desa Tanjungwangi"){
            this.url = "http://tanjungwangi-pacet.desa.id/";
        }else if(pilih=="Desa Cibeureum"){
            this.url = "http://cibeureum.desa.id/";
        }else if(pilih=="Desa Cihawuk"){
            this.url = "http://cihawuk.desa.id/";
        }else if(pilih=="Desa Cikembang"){
            this.url = "http://cikembang.desa.id/";
        }else if(pilih=="Desa Neglawangi"){
            this.url = "http://neglawangi.desa.id/";
        }else if(pilih=="Desa Resmi Tingal"){
            this.url = "http://resmitingal.desa.id/";
        }else if(pilih=="Desa Santosa"){
            this.url = "http://santosa.desa.id/";
        }else if(pilih=="Desa Sukapura Kertasari"){
            this.url = "http://sukapura-kertasari.desa.id/";
        }else if(pilih=="Desa Tarumajaya"){
            this.url = "http://tarumajaya.desa.id/";
        }else if(pilih=="Desa Biru"){
            this.url = "http://biru.desa.id/";
        }else if(pilih=="Desa Bojong Majalaya"){
            this.url = "http://bojong-majalaya.desa.id/";
        }else if(pilih=="Desa Majakerta"){
            this.url = "http://majakerta.desa.id/";
        }else if(pilih=="Desa Majalaya"){
            this.url = "http://majalaya.desa.id/";
        }else if(pilih=="Desa Majasetra"){
            this.url = "http://majasetra.desa.id/";
        }else if(pilih=="Desa Neglasari Majalaya"){
            this.url = "http://neglasari-majalaya.desa.id/";
        }else if(pilih=="Desa Padamulya"){
            this.url = "http://padamulya.desa.id/";
        }else if(pilih=="Desa Padaulun"){
            this.url = "http://padaulun.desa.id/";
        }else if(pilih=="Desa Sukamaju Majalaya"){
            this.url = "http://sukamaju-majalaya.desa.id/";
        }else if(pilih=="Desa Sukamukti Majalaya"){
            this.url = "http://sukamukti.desa.id/";
        }else if(pilih=="Desa Wangisagara"){
            this.url = "http://wangisagara.desa.id/";
        }else if(pilih=="Desa Bojongloa"){
            this.url = "http://bojongloa.desa.id/";
        }else if(pilih=="Desa Bojongsalam"){
            this.url = "http://bojongsalam.desa.id/";
        }else if(pilih=="Desa Cangkuang Rancaekek"){
            this.url = "http://cangkuang-rancaekek.desa.id/";
        }else if(pilih=="Desa Haurpugur"){
            this.url = "http://haurpugur.desa.id/";
        }else if(pilih=="Desa Jelegong"){
            this.url = "http://jelegong.desa.id/";
        }else if(pilih=="Desa Linggar"){
            this.url = "http://linggar.desa.id/";
        }else if(pilih=="Desa Nanjung Mekar"){
            this.url = "http://nanjungmekar.desa.id/";
        }else if(pilih=="Desa Rancaekek Kulon"){
            this.url = "http://rancaekekkulon.desa.id/";
        }else if(pilih=="Desa Rancaekek Wetan"){
            this.url = "http://rancaekekwetan.desa.id/";
        }else if(pilih=="Desa Sangiang"){
            this.url = "http://sangiang.desa.id/";
        }else if(pilih=="Desa Sukamanah Rancaekek"){
            this.url = "http://sukamanah-rancaekek.desa.id/";
        }else if(pilih=="Desa Sukamulya"){
            this.url = "http://sukamulya-rancaekek.desa.id/";
        }else if(pilih=="Desa Tegal Sumedang"){
            this.url = "http://tegalsumedang.desa.id/";
        }else if(pilih=="Kelurahan Kencana"){
            this.url = "http://kelurahankencana.bandungkab.go.id/";
        }else if(pilih=="Desa Babakan Peuteuy"){
            this.url = "http://babakanpeteuy.desa.id/";
        }else if(pilih=="Desa Cicalengka Kulon"){
            this.url = "http://cicalengkakulon.desa.id/";
        }else if(pilih=="Desa Cicalengka Wetan"){
            this.url = "http://cicalengkawetan.desa.id/";
        }else if(pilih=="Desa Cikuya"){
            this.url = "http://cikuya.desa.id/";
        }else if(pilih=="Desa Dampit"){
            this.url = "http://dampit.desa.id/";
        }else if(pilih=="Desa Margaasih Cicalengka"){
            this.url = "http://margaasih-cicalengka.desa.id/";
        }else if(pilih=="Desa Nagrog"){
            this.url = "http://nagrog.desa.id/";
        }else if(pilih=="Desa Narawita"){
            this.url = "http://narawita.desa.id/";
        }else if(pilih=="Desa Panenjoan"){
            this.url = "http://panenjoan.desa.id/";
        }else if(pilih=="Desa Tanjungwangi Cicalengka"){
            this.url = "http://tanjungwangi-cicalengka.desa.id/";
        }else if(pilih=="Desa Tenjolaya"){
            this.url = "http://tenjolaya-cicalengka.desa.id/";
        }else if(pilih=="Desa Waluya"){
            this.url = "http://waluya.desa.id/";
        }else if(pilih=="Desa Cibiru Hilir"){
            this.url = "http://cibiruhilir.desa.id/";
        }else if(pilih=="Desa Cibiru Wetan"){
            this.url = "http://cibiruwetan.desa.id/";
        }else if(pilih=="Desa Cileunyi Kulon"){
            this.url = "http://cileunyikulon.desa.id/";
        }else if(pilih=="Desa Cileunyi Wetan"){
            this.url = "http://cileunyiwetan.desa.id/";
        }else if(pilih=="Desa Cimekar"){
            this.url = "http://cimekar.desa.id/";
        }else if(pilih=="Desa Cinunuk"){
            this.url = "http://cinunuk.desa.id/";
        }else if(pilih=="Desa Ciburial"){
            this.url = "http://ciburial.desa.id/";
        }else if(pilih=="Desa Cikadut"){
            this.url = "http://cikadut.desa.id/";
        }else if(pilih=="Desa Cimenyan"){
            this.url = "http://cimenyan.desa.id/";
        }else if(pilih=="Desa Mandalamekar"){
            this.url = "http://madalamekar-cimenyan.desa.id/";
        }else if(pilih=="Desa Mekarmanik"){
            this.url = "http://mekarmanik.desa.id/";
        }else if(pilih=="Desa Mekarsaluyu"){
            this.url = "http://mekarsaluyu.desa.id/";
        }else if(pilih=="Desa Sindanglaya"){
            this.url = "http://sindanglaya.desa.id/";
        }else if(pilih=="Kelurahan Cibeunying"){
            this.url = "http://kelurahancibeunying.bandungkab.go.id/";
        }else if(pilih=="Kelurahan Padasuka"){
            this.url = "http://kelurahanpadasuka.bandungkab.go.id/";
        }else if(pilih=="Desa Cilengkrang"){
            this.url = "http://cilengkrang.desa.id/";
        }else if(pilih=="Desa Cipanjalu"){
            this.url = "http://cipanjalu.desa.id/";
        }else if(pilih=="Desa Ciporeat"){
            this.url = "http://ciporeat.desa.id/";
        }else if(pilih=="Desa Girimekar"){
            this.url = "http://girimekar.desa.id/";
        }else if(pilih=="Desa Jatiendah"){
            this.url = "http://jatiendah.desa.id/";
        }else if(pilih=="Desa Melatiwangi"){
            this.url = "http://melatiwangi.desa.id/";
        }else if(pilih=="Desa Cingcin"){
            this.url = "http://cingcin.desa.id/";
        }else if(pilih=="Desa Karamatmulya"){
            this.url = "http://karamatmulya.desa.id/";
        }else if(pilih=="Desa Pamekaran"){
            this.url = "http://pamekaran.desa.id/";
        }else if(pilih=="Desa Panyirapan"){
            this.url = "http://panyirapan.desa.id/";
        }else if(pilih=="Desa Parungserab"){
            this.url = "http://parungserab.desa.id/";
        }else if(pilih=="Desa Sadu"){
            this.url = "http://sadu.desa.id/";
        }else if(pilih=="Desa Sekarwangi"){
            this.url = "http://sekarwangi.desa.id/";
        }else if(pilih=="Desa Soreang"){
            this.url = "http://soreang.desa.id/";
        }else if(pilih=="Desa Sukajadi"){
            this.url = "http://sukajadi.desa.id/";
        }else if(pilih=="Desa Sukanagara"){
            this.url = "http://sukanagara.desa.id/";
        }else if(pilih=="Desa Cibodas Pasirjambu"){
            this.url = "http://cibodas.desa.id/";
        }else if(pilih=="Desa Cikoneng Pasirjambu"){
            this.url = "http://cikoneng-pasirjambu.desa.id/";
        }else if(pilih=="Desa Cisondari"){
            this.url = "http://cisondari.desa.id/";
        }else if(pilih=="Desa Cukanggenteng"){
            this.url = "http://cukanggenteng.desa.id/";
        }else if(pilih=="Desa Margamulya Pasirjambu"){
            this.url = "http://margamulya.desa.id/";
        }else if(pilih=="Desa Mekarmaju"){
            this.url = "http://mekarmaju.desa.id/";
        }else if(pilih=="Desa Mekarsari Pasirjambu"){
            this.url = "http://mekarsari-pasirjambu.desa.id/";
        }else if(pilih=="Desa Pasirjambu"){
            this.url = "http://pasirjambu.desa.id/";
        }else if(pilih=="Desa Sugihmukti"){
            this.url = "http://sugihmukti.desa.id/";
        }else if(pilih=="Desa Tenjolaya Pasirjambu"){
            this.url = "http://tenjolaya.desa.id/";
        }else if(pilih=="Desa Ciwidey"){
            this.url = "http://ciwidey.desa.id/";
        }else if(pilih=="Desa Lebakmuncang"){
            this.url = "http://lebakmuncang.desa.id/";
        }else if(pilih=="Desa Nengkelan"){
            this.url = "http://nengkelan.desa.id/";
        }else if(pilih=="Desa Panundaan"){
            this.url = "http://panundaan.desa.id/";
        }else if(pilih=="Desa Panyocokan"){
            this.url = "http://panyocokan.desa.id/";
        }else if(pilih=="Desa Rawabogo"){
            this.url = "http://rawabogo.desa.id/";
        }else if(pilih=="Desa Sukawening"){
            this.url = "http://sukawening.desa.id/";
        }else if(pilih=="Desa Alamendah"){
            this.url = "http://alamendah.desa.id/";
        }else if(pilih=="Desa Cipelah"){
            this.url = "http://cipelah.desa.id/";
        }else if(pilih=="Desa Indragiri"){
            this.url = "http://indragiri.desa.id/";
        }else if(pilih=="Desa Patengan"){
            this.url = "http://patengan.desa.id/";
        }else if(pilih=="Desa Sukaresmi"){
            this.url = "http://sukaresmi-rancabali.desa.id/";
        }else if(pilih=="Desa Buninagara"){
            this.url = "http://buninagara.desa.id/";
        }else if(pilih=="Desa Cibodas Kutawaringin"){
            this.url = "http://cibodas-kutawaringin.desa.id/";
        }else if(pilih=="Desa Cilame"){
            this.url = "http://cilame.desa.id/";
        }else if(pilih=="Desa Gajah Mekar"){
            this.url = "http://gajahmekar.desa.id/";
        }else if(pilih=="Desa Jatisari"){
            this.url = "http://jatisari-kutawaringin.desa.id/";
        }else if(pilih=="Desa Jelegong"){
            this.url = "http://jelegong-kutawaringin.desa.id/";
        }else if(pilih=="Desa Kopo"){
            this.url = "http://kopo.desa.id/";
        }else if(pilih=="Desa Kutawaringin"){
            this.url = "http://kutawaringin.desa.id/";
        }else if(pilih=="Desa Padasuka"){
            this.url = "http://padasuka.desa.id/";
        }else if(pilih=="Desa Pameuntasan"){
            this.url = "http://pameuntasan.desa.id/";
        }else if(pilih=="Desa Sukamulya"){
            this.url = "http://sukamulya.desa.id/";
        }else{
            Toast.makeText(
                    getApplicationContext(),
                    "Kesalahan pada URL",
                    Toast.LENGTH_LONG).show();
            finish();
        }

        progressDialog.show();

        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,url + URL_LOGIN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try {
                            JSONObject obj = new JSONObject(response);
                            if(!obj.getBoolean("error")){
                                SharedPreManager.getInstance(getApplicationContext())
                                        .userLogin(
                                                obj.getInt("id"),
                                                obj.getString("nik"),
                                                obj.getString("nama"),
                                                obj.getString("tanggallahir"),
                                                obj.getString("tempatlahir"),
                                                obj.getString("dusun"),
                                                obj.getString("agama"),
                                                obj.getString("pekerjaan"),
                                                obj.getString("pendidikan"),
                                                obj.getString("pendidikankk"),
                                                obj.getString("status"),
                                                obj.getString("gender"),
                                                obj.getString("warganegara"),
                                                obj.getString("rt"),
                                                obj.getString("rw"),
                                                obj.getString("nokk"),
                                                obj.getString("url"))
                                        ;
                                Toast.makeText(
                                        getApplicationContext(),
                                        "Anda berhasil Masuk",
                                        Toast.LENGTH_LONG).show();
                                startActivity(new Intent(getApplicationContext(), DashboardActivity.class));
                                finish();
                            }else{
                                Toast.makeText(
                                        getApplicationContext(),
                                        obj.getString("message"),
                                        Toast.LENGTH_LONG
                                ).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();

                        Toast.makeText(
                                getApplicationContext(),
                                error.getMessage(),
                                Toast.LENGTH_LONG
                        ).show();
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("nik", nik);
                params.put("pin", pin);
                return params;
            }

        };

        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }

    @Override
    public void onClick(View view) {
        if(view == buttonMasuk){
            userLogin();
        }
    }

    private static long back_pressed;

    @Override
    public void onBackPressed()
    {
        if (back_pressed + 2000 > System.currentTimeMillis()) super.onBackPressed();
        back_pressed = System.currentTimeMillis();
        finish();

    }

}
