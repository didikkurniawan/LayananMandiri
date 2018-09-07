package com.ui.lm.layananmandiri_02;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LaporActivity extends AppCompatActivity implements View.OnClickListener, BottomNavigationView.OnNavigationItemSelectedListener {
    private EditText editTextPengirim, editTextNoNikk, editTextLaporan;
    private Button buttonKirim;
    private ImageView buttonLogout;
    private ProgressDialog progressDialog;
    android.support.v7.widget.Toolbar toolbar;
    BottomNavigationView bottomNavigationView, bottomLogoutView;
    private static final String TAG = "LaporActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lapor);


        //toolbar
//        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.bartool);
//        setSupportActionBar(toolbar);
//        toolbar.setLogo(R.drawable.typetoolbar);
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.lapor);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);

        editTextPengirim = (EditText) findViewById(R.id.TnameUser);
        editTextNoNikk = (EditText) findViewById(R.id.TnikUser);
        editTextLaporan = (EditText) findViewById(R.id.TkomentarUser);

        //Set nama dan nik
        editTextPengirim.setText(SharedPreManager.getInstance(this).getUserNama());
        editTextNoNikk.setText(SharedPreManager.getInstance(this).getUserNokk());
        editTextPengirim.setEnabled(false);
        editTextNoNikk.setEnabled(false);

        buttonKirim = (Button) findViewById(R.id.BtnKirim);
        buttonLogout = (ImageView) findViewById(R.id.buttonLogout);

        progressDialog = new ProgressDialog(this);
        buttonLogout.setOnClickListener(this);
        buttonKirim.setOnClickListener(this);

    }


    private void laporUser(){
        final String nama = editTextPengirim.getText().toString().trim();
        final String noNik = editTextNoNikk.getText().toString().trim();
        final String laporan = editTextLaporan.getText().toString().trim();
        final String alamat = SharedPreManager.getInstance(this).getUrl();


        progressDialog.setMessage("Mengirim Laporan...");
        progressDialog.show();


        StringRequest stringRequest = new StringRequest(Request.Method.POST, alamat, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    Toast.makeText(getApplicationContext(), jsonObject.getString("message"), Toast.LENGTH_LONG).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.hide();
                        Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError{
                Map<String,String> params = new HashMap<>();
                params.put("owner",nama);
                params.put("email",noNik);
                params.put("komentar",laporan);
                return params;
            }
        };

        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }

    @Override
    public void onClick(View v) {
        if (v == buttonKirim) {
            laporUser();
        }else if(v == buttonLogout){
            SharedPreManager.getInstance(this).logout();
            Intent newAct4 = new Intent(this, LoginActivity.class);
            newAct4.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(newAct4);
            finish();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.layanan:
                Intent newAct = new Intent(this, LayananMain.class);
                startActivity(newAct);
                return true;
            case R.id.bantuan:
                Intent newAct1 = new Intent(this, BantuanMain.class);
                startActivity(newAct1);
                return true;
            case R.id.home:
                Intent newAct2 = new Intent(this, DashboardActivity.class);
                startActivity(newAct2);
                return true;
            case R.id.lapor:
                Intent newAct3 = new Intent(this, LaporActivity.class);
                startActivity(newAct3);
                return true;
            case R.id.account:
                Intent newAct4 = new Intent(this, ProfilActivity.class);
                startActivity(newAct4);
                return true;
        }
        Log.i(TAG,"OnNavigationItemSelectedListener:");
        return true;
    }

    public void onBackPressed() {
        Intent newAct2 = new Intent(this, DashboardActivity.class);
        newAct2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(newAct2);
        finish();
    }
}
