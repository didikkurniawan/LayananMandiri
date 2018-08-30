package com.ui.lm.layananmandiri_02;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;



import static com.ui.lm.layananmandiri_02.Constants.URL_LOGIN;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private BantuanMain bantuanMain;
    private LayananMain layananMain;
    private LaporActivity laporActivity;
    private EditText editTextNIK, editTextPassword;
    private Spinner editTextDesa;
    private Button buttonMasuk;
    private ProgressDialog progressDialog;
    private String link;
//    public String desa = "";

    AutoCompleteTextView suggestion_box;
    Spinner items;

    ArrayList<String> namaDesa = new ArrayList<>();

   @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        suggestion_box = (AutoCompleteTextView)findViewById(R.id.suggestion_box);
        items = (Spinner)findViewById(R.id.desaid);

        namaDesa.add("Desa Babakan");
        namaDesa.add("Desa Banyusari");
        namaDesa.add("Desa Bandasari");
        namaDesa.add("Desa Bojongkuncu");
        namaDesa.add("Desa Bojongmanggu");
        namaDesa.add("Desa Bumiwangi");
        namaDesa.add("Desa Cangkuang");
        namaDesa.add("Desa Ciluncat");
        namaDesa.add("Desa Cilampeni");
        namaDesa.add("Desa Cigondewa Hilir");
        namaDesa.add("Desa Ciheulang");
        namaDesa.add("Desa Cikoneng");
        namaDesa.add("Desa Ciparay");
        namaDesa.add("Desa Gandasari");
        namaDesa.add("Desa Gunung Leutik");
        namaDesa.add("Desa Jatisari");
        namaDesa.add("Desa Katapang");
        namaDesa.add("Desa Lagadar");
        namaDesa.add("Desa Langonsari");
        namaDesa.add("Desa Mekarlaksana");
        namaDesa.add("Desa Mekarsari");
        namaDesa.add("Desa Mekarrahayu");
        namaDesa.add("Desa Manggung Harja");
        namaDesa.add("Desa Margahayu Tengah");
        namaDesa.add("Desa Margahayu Selatan");
        namaDesa.add("Desa Margaasih");
        namaDesa.add("Desa Nanjung");
        namaDesa.add("Desa Nagrak");
        namaDesa.add("Desa Pakutandang");
        namaDesa.add("Desa Pananjung");
        namaDesa.add("Desa Pangauban");
        namaDesa.add("Desa Rahayu");
        namaDesa.add("Desa Rancamulya");
        namaDesa.add("Desa Rancatungku");
        namaDesa.add("Desa Sangkanhurip");
        namaDesa.add("Desa Sarimahi");
        namaDesa.add("Desa Sarangmekar");
        namaDesa.add("Desa Sayati");
        namaDesa.add("Desa Segaracipta");
        namaDesa.add("Desa Sukamenak");
        namaDesa.add("Desa Sukamukti");
        namaDesa.add("Desa Sukasari");
        namaDesa.add("Desa Sumbersari");
        namaDesa.add("Desa Tanjungsari");
        namaDesa.add("Kelurahan Sulaiman");



       ArrayAdapter<String> adapter = new ArrayAdapter<String>(LoginActivity.this,android.R.layout.simple_spinner_dropdown_item,namaDesa);

       ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(LoginActivity.this,android.R.layout.simple_spinner_dropdown_item,namaDesa);

       suggestion_box.setAdapter(adapter);
       items.setAdapter(adapter);


        if(SharedPreManager.getInstance(this).isLoggedIn()){
            finish();
            startActivity(new Intent(this, DashboardActivity.class));
            return;
        }

        editTextNIK = (EditText) findViewById(R.id.nikid);
        editTextPassword = (EditText) findViewById(R.id.noTknid);
        buttonMasuk = (Button) findViewById(R.id.masuktombol);
        editTextDesa = (Spinner) findViewById(R.id.desaid);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait...");

        buttonMasuk.setOnClickListener(this);

    }


    private void userLogin(){
        final String nik = editTextNIK.getText().toString().trim();
        final String pin = editTextPassword.getText().toString().trim();
        final String pilih = String.valueOf((editTextDesa.getSelectedItem()));
/*        laporActivity = new LaporActivity();
        laporActivity.setDesa3(String.valueOf((editTextDesa.getSelectedItem())));
        layananMain = new LayananMain();
        layananMain.setDesa2(String.valueOf((editTextDesa.getSelectedItem())));
        bantuanMain = new BantuanMain();
        bantuanMain.setDesa(String.valueOf((editTextDesa.getSelectedItem())));*/

//Pemilihan Url desa
    if (pilih .equals("Kelurahan Sulaiman")){
        laporActivity = new LaporActivity();
        laporActivity.setDesa3("http://kelurahansulaiman.bandungkab.go.id/index.php");
        layananMain = new LayananMain();
        layananMain.setDesa2("http://kelurahansulaiman.bandungkab.go.id/index.php");
        bantuanMain = new BantuanMain();
        bantuanMain.setDesa("http://kelurahansulaiman.bandungkab.go.id/index.php");}

    else if(pilih .equals("Desa Margahayu Tengah")){
        laporActivity = new LaporActivity();
        laporActivity.setDesa3("http://margahayutengah.desa.id");
        layananMain = new LayananMain();
        layananMain.setDesa2("http://margahayutengah.desa.id");
        bantuanMain = new BantuanMain();
        bantuanMain.setDesa("http://margahayutengah.desa.id");}

    else if(pilih .equals("Desa Margahayu Selatan")){
            laporActivity = new LaporActivity();
            laporActivity.setDesa3("http://margahayuselatan.desa.id");
            layananMain = new LayananMain();
            layananMain.setDesa2("http://margahayuselatan.desa.id");
            bantuanMain = new BantuanMain();
            bantuanMain.setDesa("http://margahayuselatan.desa.id");}

    else if(pilih .equals("Desa Sukamenak")){
        laporActivity = new LaporActivity();
        laporActivity.setDesa3("https://sukamenak.desa.id");
        layananMain = new LayananMain();
        layananMain.setDesa2("https://sukamenak.desa.id");
        bantuanMain = new BantuanMain();
        bantuanMain.setDesa("https://sukamenak.desa.id");}

    else if(pilih .equals("Desa Sukamenak")){
        laporActivity = new LaporActivity();
        laporActivity.setDesa3("https://sukamenak.desa.id");
        layananMain = new LayananMain();
        layananMain.setDesa2("https://sukamenak.desa.id");
        bantuanMain = new BantuanMain();
        bantuanMain.setDesa("https://sukamenak.desa.id");}

    else if(pilih .equals("Desa Sayati")){
        laporActivity = new LaporActivity();
        laporActivity.setDesa3("http://sayati.desa.id");
        layananMain = new LayananMain();
        layananMain.setDesa2("http://sayati.desa.id");
        bantuanMain = new BantuanMain();
        bantuanMain.setDesa("http://sayati.desa.id");}
//penambahan
    else if(pilih .equals("Desa Banyusari")){
        laporActivity = new LaporActivity();
        laporActivity.setDesa3("http://banyusari.desa.id");
        layananMain = new LayananMain();
        layananMain.setDesa2("http://banyusari.desa.id");
        bantuanMain = new BantuanMain();
        bantuanMain.setDesa("http://banyusari.desa.id");}
    else if(pilih .equals("Desa Cilampeni")){
        laporActivity = new LaporActivity();
        laporActivity.setDesa3("http://cilampeni.desa.id");
        layananMain = new LayananMain();
        layananMain.setDesa2("http://cilampeni.desa.id");
        bantuanMain = new BantuanMain();
        bantuanMain.setDesa("http://cilampeni.desa.id");}
    else if(pilih .equals("Desa Gandasari")){
        laporActivity = new LaporActivity();
        laporActivity.setDesa3("http://gandasari.desa.id");
        layananMain = new LayananMain();
        layananMain.setDesa2("http://gandasari.desa.id");
        bantuanMain = new BantuanMain();
        bantuanMain.setDesa("http://gandasari.desa.id");}
    else if(pilih .equals("Desa Katapang")){
        laporActivity = new LaporActivity();
        laporActivity.setDesa3("http://katapang.desa.id");
        layananMain = new LayananMain();
        layananMain.setDesa2("http://katapang.desa.id");
        bantuanMain = new BantuanMain();
        bantuanMain.setDesa("http://katapang.desa.id");}
    else if(pilih .equals("Desa Pangauban")){
        laporActivity = new LaporActivity();
        laporActivity.setDesa3("http://pangauban-katapang.desa.id");
        layananMain = new LayananMain();
        layananMain.setDesa2("http://pangauban-katapang.desa.id");
        bantuanMain = new BantuanMain();
        bantuanMain.setDesa("http://pangauban-katapang.desa.id");}
    else if(pilih .equals("Desa Sangkanhurip")){
        laporActivity = new LaporActivity();
        laporActivity.setDesa3("http://sangkanhurip.desa.id");
        layananMain = new LayananMain();
        layananMain.setDesa2("http://sangkanhurip.desa.id");
        bantuanMain = new BantuanMain();
        bantuanMain.setDesa("http://sangkanhurip.desa.id");}
    else if(pilih .equals("Desa Sukamukti")){
        laporActivity = new LaporActivity();
        laporActivity.setDesa3("http://sukamukti-katapang.desa.id");
        layananMain = new LayananMain();
        layananMain.setDesa2("http://sukamukti-katapang.desa.id");
        bantuanMain = new BantuanMain();
        bantuanMain.setDesa("http://sukamukti-katapang.desa.id");}
    else if(pilih .equals("Desa Babakan")){
        laporActivity = new LaporActivity();
        laporActivity.setDesa3("http://babakan-ciparay.desa.id");
        layananMain = new LayananMain();
        layananMain.setDesa2("http://babakan-ciparay.desa.id");
        bantuanMain = new BantuanMain();
        bantuanMain.setDesa("http://babakan-ciparay.desa.id");}
    else if(pilih .equals("Desa Bumiwangi")){
        laporActivity = new LaporActivity();
        laporActivity.setDesa3("http://bumiwangi.desa.id");
        layananMain = new LayananMain();
        layananMain.setDesa2("http://bumiwangi.desa.id");
        bantuanMain = new BantuanMain();
        bantuanMain.setDesa("http://bumiwangi.desa.id");}
    else if(pilih .equals("Desa Ciheulang")){
        laporActivity = new LaporActivity();
        laporActivity.setDesa3("http://ciheulang.desa.id");
        layananMain = new LayananMain();
        layananMain.setDesa2("http://ciheulang.desa.id");
        bantuanMain = new BantuanMain();
        bantuanMain.setDesa("http://ciheulang.desa.id");}
    else if(pilih .equals("Desa Cikoneng")){
        laporActivity = new LaporActivity();
        laporActivity.setDesa3("http://cikoneng-ciparay.desa.id");
        layananMain = new LayananMain();
        layananMain.setDesa2("http://cikoneng-ciparay.desa.id");
        bantuanMain = new BantuanMain();
        bantuanMain.setDesa("http://cikoneng-ciparay.desa.id");}
    else if(pilih .equals("Desa Ciparay")){
        laporActivity = new LaporActivity();
        laporActivity.setDesa3("http://ciparay.desa.id");
        layananMain = new LayananMain();
        layananMain.setDesa2("http://ciparay.desa.id");
        bantuanMain = new BantuanMain();
        bantuanMain.setDesa("http://ciparay.desa.id");}
    else if(pilih .equals("Desa Gunung Leutik")){
        laporActivity = new LaporActivity();
        laporActivity.setDesa3("http://gunungleutik.desa.id");
        layananMain = new LayananMain();
        layananMain.setDesa2("http://gunungleutik.desa.id");
        bantuanMain = new BantuanMain();
        bantuanMain.setDesa("http://gunungleutik.desa.id");}
    else if(pilih .equals("Desa Manggung Harja")){
        laporActivity = new LaporActivity();
        laporActivity.setDesa3("http://manggungharja.desa.id");
        layananMain = new LayananMain();
        layananMain.setDesa2("http://manggungharja.desa.id");
        bantuanMain = new BantuanMain();
        bantuanMain.setDesa("http://manggungharja.desa.id");}
    else if(pilih .equals("Desa Mekarlaksana")){
        laporActivity = new LaporActivity();
        laporActivity.setDesa3("http://mekarlaksana-ciparay.desa.id");
        layananMain = new LayananMain();
        layananMain.setDesa2("http://mekarlaksana-ciparay.desa.id");
        bantuanMain = new BantuanMain();
        bantuanMain.setDesa("http://mekarlaksana-ciparay.desa.id");}
    else if(pilih .equals("Desa Mekarsari")){
        laporActivity = new LaporActivity();
        laporActivity.setDesa3("http://mekarsari-ciparay.desa.id");
        layananMain = new LayananMain();
        layananMain.setDesa2("http://mekarsari-ciparay.desa.id");
        bantuanMain = new BantuanMain();
        bantuanMain.setDesa("http://mekarsari-ciparay.desa.id");}
    else if(pilih .equals("Desa Pakutandang")){
        laporActivity = new LaporActivity();
        laporActivity.setDesa3("http://pakutandang.desa.id");
        layananMain = new LayananMain();
        layananMain.setDesa2("http://pakutandang.desa.id");
        bantuanMain = new BantuanMain();
        bantuanMain.setDesa("http://pakutandang.desa.id");}
    else if(pilih .equals("Desa Sarimahi")){
        laporActivity = new LaporActivity();
        laporActivity.setDesa3("http://sarimahi.desa.id");
        layananMain = new LayananMain();
        layananMain.setDesa2("http://sarimahi.desa.id");
        bantuanMain = new BantuanMain();
        bantuanMain.setDesa("http://sarimahi.desa.id");}
    else if(pilih .equals("Desa Sarangmekar")){
        laporActivity = new LaporActivity();
        laporActivity.setDesa3("http://sarangmekar.desa.id");
        layananMain = new LayananMain();
        layananMain.setDesa2("http://sarangmekar.desa.id");
        bantuanMain = new BantuanMain();
        bantuanMain.setDesa("http://sarangmekar.desa.id");}
    else if(pilih .equals("Desa Segaracipta")){
        laporActivity = new LaporActivity();
        laporActivity.setDesa3("http://segaracipta.desa.id");
        layananMain = new LayananMain();
        layananMain.setDesa2("http://segaracipta.desa.id");
        bantuanMain = new BantuanMain();
        bantuanMain.setDesa("http://segaracipta.desa.id");}
    else if(pilih .equals("Desa Sumbersari")){
        laporActivity = new LaporActivity();
        laporActivity.setDesa3("http://sumbersari-ciparay.desa.id");
        layananMain = new LayananMain();
        layananMain.setDesa2("http://sumbersari-ciparay.desa.id");
        bantuanMain = new BantuanMain();
        bantuanMain.setDesa("http://sumbersari-ciparay.desa.id");}
    else if(pilih .equals("Desa Cigondewa Hilir")){
        laporActivity = new LaporActivity();
        laporActivity.setDesa3("http://cigondewahilir.desa.id");
        layananMain = new LayananMain();
        layananMain.setDesa2("http://cigondewahilir.desa.id");
        bantuanMain = new BantuanMain();
        bantuanMain.setDesa("http://cigondewahilir.desa.id");}
    else if(pilih .equals("Desa Margaasih")){
        laporActivity = new LaporActivity();
        laporActivity.setDesa3("http://margaasih.desa.id");
        layananMain = new LayananMain();
        layananMain.setDesa2("http://margaasih.desa.id");
        bantuanMain = new BantuanMain();
        bantuanMain.setDesa("http://margaasih.desa.id");}
    else if(pilih .equals("Desa Lagadar")){
        laporActivity = new LaporActivity();
        laporActivity.setDesa3("http://lagadar.desa.id");
        layananMain = new LayananMain();
        layananMain.setDesa2("http://lagadar.desa.id");
        bantuanMain = new BantuanMain();
        bantuanMain.setDesa("http://lagadar.desa.id");}
    else if(pilih .equals("Desa Nanjung")){
        laporActivity = new LaporActivity();
        laporActivity.setDesa3("http://nanjung.desa.id");
        layananMain = new LayananMain();
        layananMain.setDesa2("http://nanjung.desa.id");
        bantuanMain = new BantuanMain();
        bantuanMain.setDesa("http://nanjung.desa.id");}
    else if(pilih .equals("Desa Mekarrahayu")){
        laporActivity = new LaporActivity();
        laporActivity.setDesa3("http://mekarrahayu.desa.id");
        layananMain = new LayananMain();
        layananMain.setDesa2("http://mekarrahayu.desa.id");
        bantuanMain = new BantuanMain();
        bantuanMain.setDesa("http://mekarrahayu.desa.id");}
    else if(pilih .equals("Desa Rahayu")){
        laporActivity = new LaporActivity();
        laporActivity.setDesa3("http://rahayu.desa.id");
        layananMain = new LayananMain();
        layananMain.setDesa2("http://rahayu.desa.id");
        bantuanMain = new BantuanMain();
        bantuanMain.setDesa("http://rahayu.desa.id");}
    else if(pilih .equals("Desa Bojongkunci")){
        laporActivity = new LaporActivity();
        laporActivity.setDesa3("http://bojongkunci.desa.id");
        layananMain = new LayananMain();
        layananMain.setDesa2("http://bojongkunci.desa.id");
        bantuanMain = new BantuanMain();
        bantuanMain.setDesa("http://bojongkunci.desa.id");}
    else if(pilih .equals("Desa Bojongmanggu")){
        laporActivity = new LaporActivity();
        laporActivity.setDesa3("http://bojongmanggu.desa.id");
        layananMain = new LayananMain();
        layananMain.setDesa2("http://bojongmanggu.desa.id");
        bantuanMain = new BantuanMain();
        bantuanMain.setDesa("http://bojongmanggu.desa.id");}
    else if(pilih .equals("Desa Langonsari")){
        laporActivity = new LaporActivity();
        laporActivity.setDesa3("http://langonsari.desa.id");
        layananMain = new LayananMain();
        layananMain.setDesa2("http://langonsari.desa.id");
        bantuanMain = new BantuanMain();
        bantuanMain.setDesa("http://langonsari.desa.id");}
    else if(pilih .equals("Desa Rancamulya")){
        laporActivity = new LaporActivity();
        laporActivity.setDesa3("http://rancamulya.desa.id");
        layananMain = new LayananMain();
        layananMain.setDesa2("http://rancamulya.desa.id");
        bantuanMain = new BantuanMain();
        bantuanMain.setDesa("http://rancamulya.desa.id");}
    else if(pilih .equals("Desa Rancatungku")){
        laporActivity = new LaporActivity();
        laporActivity.setDesa3("http://rancatungku.desa.id");
        layananMain = new LayananMain();
        layananMain.setDesa2("http://rancatungku.desa.id");
        bantuanMain = new BantuanMain();
        bantuanMain.setDesa("http://rancatungku.desa.id");}
    else if(pilih .equals("Desa Sukasari")){
        laporActivity = new LaporActivity();
        laporActivity.setDesa3("http://sukasari.desa.id");
        layananMain = new LayananMain();
        layananMain.setDesa2("http://sukasari.desa.id");
        bantuanMain = new BantuanMain();
        bantuanMain.setDesa("http://sukasari.desa.id");}
    else if(pilih .equals("Desa Bandasari")){
        laporActivity = new LaporActivity();
        laporActivity.setDesa3("http://bandasari.desa.id");
        layananMain = new LayananMain();
        layananMain.setDesa2("http://bandasari.desa.id");
        bantuanMain = new BantuanMain();
        bantuanMain.setDesa("http://bandasari.desa.id");}
    else if(pilih .equals("Desa Cangkuang")){
        laporActivity = new LaporActivity();
        laporActivity.setDesa3("http://cangkuang.desa.id");
        layananMain = new LayananMain();
        layananMain.setDesa2("http://cangkuang.desa.id");
        bantuanMain = new BantuanMain();
        bantuanMain.setDesa("http://cangkuang.desa.id");}
    else if(pilih .equals("Desa Ciluncat")){
        laporActivity = new LaporActivity();
        laporActivity.setDesa3("http://ciluncat.desa.id");
        layananMain = new LayananMain();
        layananMain.setDesa2("http://ciluncat.desa.id");
        bantuanMain = new BantuanMain();
        bantuanMain.setDesa("http://ciluncat.desa.id");}
    else if(pilih .equals("Desa Jatisari")){
        laporActivity = new LaporActivity();
        laporActivity.setDesa3("http://jatisari.desa.id");
        layananMain = new LayananMain();
        layananMain.setDesa2("http://jatisari.desa.id");
        bantuanMain = new BantuanMain();
        bantuanMain.setDesa("http://jatisari.desa.id");}
    else if(pilih .equals("Desa Nagrak")){
        laporActivity = new LaporActivity();
        laporActivity.setDesa3("http://nagrak.desa.id");
        layananMain = new LayananMain();
        layananMain.setDesa2("http://nagrak.desa.id");
        bantuanMain = new BantuanMain();
        bantuanMain.setDesa("http://nagrak.desa.id");}
    else if(pilih .equals("Desa Pananjung")){
        laporActivity = new LaporActivity();
        laporActivity.setDesa3("http://pananjung.desa.id");
        layananMain = new LayananMain();
        layananMain.setDesa2("http://pananjung.desa.id");
        bantuanMain = new BantuanMain();
        bantuanMain.setDesa("http://pananjung.desa.id");}
    else if(pilih .equals("Desa Tanjungsari")){
        laporActivity = new LaporActivity();
        laporActivity.setDesa3("http://tanjungsari.desa.id");
        layananMain = new LayananMain();
        layananMain.setDesa2("http://tanjungsari.desa.id");
        bantuanMain = new BantuanMain();
        bantuanMain.setDesa("http://tanjungsari.desa.id");}




    else{
        Toast.makeText(
                getApplicationContext(),
                "Kesalahan pada URL",
                Toast.LENGTH_LONG).show();
        }

        progressDialog.show();

        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                bantuanMain.getDesa() + URL_LOGIN,
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
                                                obj.getString("nokk"))
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

}
