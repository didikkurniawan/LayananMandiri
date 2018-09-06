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
        String[] kecamatan = {"-- Pilih Kecamatan --","Kecamatan Margahayu","Kecamatan Ciparay"};
        final String[] margahayu = {"Desa Margahayu Tengah","Desa Margahayu Selatan"};

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

        if(pilih=="Desa Margahayu Tengah"){
            this.url = "http://margahayutengah.desa.id/";
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
