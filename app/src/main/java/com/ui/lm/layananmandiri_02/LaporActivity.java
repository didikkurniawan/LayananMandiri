package com.ui.lm.layananmandiri_02;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class LaporActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText editTextPengirim, editTextNoNikk, editTextLaporan;
    private Button buttonKirim;
    private ProgressDialog progressDialog;
    android.support.v7.widget.Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lapor);


        //toolbar
        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.bartool);
        setSupportActionBar(toolbar);
        toolbar.setLogo(R.drawable.typetoolbar);

        editTextPengirim = (EditText) findViewById(R.id.TnameUser);
        editTextNoNikk = (EditText) findViewById(R.id.TnikUser);
        editTextLaporan = (EditText) findViewById(R.id.TkomentarUser);

        //Set nama dan nik
        editTextPengirim.setText(SharedPreManager.getInstance(this).getUserNokk());
        editTextNoNikk.setText(SharedPreManager.getInstance(this).getUrl());

        buttonKirim = (Button) findViewById(R.id.BtnKirim);

        progressDialog = new ProgressDialog(this);
        buttonKirim.setOnClickListener(this);
    }

    public static String desa3="";

    public static void setDesa3(String Desa){
        desa3 = Desa;
    }

    private void laporUser(){
        final String nama = editTextPengirim.getText().toString().trim();
        final String noNik = editTextNoNikk.getText().toString().trim();
        final String laporan = editTextLaporan.getText().toString().trim();
        final String alamat = desa3+ "/api_desa/post_laporan";


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
        if (v == buttonKirim)
            laporUser();
    }
}
