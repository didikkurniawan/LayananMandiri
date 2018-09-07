package com.ui.lm.layananmandiri_02;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.ui.lm.layananmandiri_02.Constants.URL_BANTUAN;
import static com.ui.lm.layananmandiri_02.Constants.URL_LAYANAN;

public class BantuanMain extends AppCompatActivity implements View.OnClickListener ,BottomNavigationView.OnNavigationItemSelectedListener{
    private Constants cons;
    private RecyclerView mRecyclerView;
    private BantuanAdapter rvAdapter;
    Toolbar toolbar;
    private RecyclerView.LayoutManager mLayoutManager;
    private static final String TAG = "BantuanMain";
    private ImageView buttonLogout;
    /*private Context context = BantuanMain.this;*/

    private List<Bantuan> bantuanList = new ArrayList<Bantuan>();
    private ProgressDialog pDialog;
    BottomNavigationView bottomNavigationView, bottomLogoutView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bantuan_main);

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.bantuan);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        pDialog = new ProgressDialog(this);
        loadDataServerVolley();
        buttonLogout = (ImageView) findViewById(R.id.buttonLogout);

        buttonLogout.setOnClickListener(this);

    }

    private void gambarDatakeRecyclerView(){
        rvAdapter = new BantuanAdapter(bantuanList);
        mRecyclerView.setAdapter(rvAdapter);
    }

    //ambil data sever volley
    private void loadDataServerVolley(){
        final String peserta = SharedPreManager.getInstance(this).getUserNIK();
        StringRequest postRequest = new StringRequest(Request.Method.POST,SharedPreManager.getInstance(this).getUrl() + URL_BANTUAN,
            new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("MainActivity","response:"+response);
                        hideDialog();
                        processResponse(response);
                        gambarDatakeRecyclerView();

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        hideDialog();
                        error.printStackTrace();
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<>();
                // the POST parameters:
                params.put("peserta",peserta);
                return params;
            }
        };
        Volley.newRequestQueue(this).add(postRequest);
    }

    private void processResponse(String response){

        try {
            JSONObject jsonObj = new JSONObject(response);
            JSONArray jsonArray = jsonObj.getJSONArray("data");
            Log.d("TAG", "data length: " + jsonArray.length());
            Bantuan objectbantuan = null;
            bantuanList.clear();
            for(int i = 0; i < jsonArray.length(); i++){
                JSONObject obj = jsonArray.getJSONObject(i);
                objectbantuan= new Bantuan();
                /*objectbarang.setId(obj.getString("id"));*/

                objectbantuan.setNama(obj.getString("nama"));
                objectbantuan.setSdate("Dari : " + obj.getString("sdate"));
                objectbantuan.setEdate("Sampai : " + obj.getString("edate"));

                bantuanList.add(objectbantuan);
            }

        } catch (JSONException e) {
            Log.d("MainActivity", "errorJSON");
        }

    }
    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }
    public void onClick(View v) {
        if (v == buttonLogout){
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
                finish();
                return true;
        }
        Log.i(TAG,"OnNavigationItemSelectedListener:");
        return true;
    }

    @Override
    public void onBackPressed() {
        Intent newAct2 = new Intent(this, DashboardActivity.class);
        newAct2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(newAct2);
        finish();
    }

}
