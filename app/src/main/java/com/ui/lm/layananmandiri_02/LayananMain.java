package com.ui.lm.layananmandiri_02;


import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

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

import static com.ui.lm.layananmandiri_02.Constants.URL_LAYANAN;

public class LayananMain extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    private RecyclerView mRecyclerView;
    private LayananAdapter rvAdapter;
    private LoginActivity LgAk;
    private Layanan layanan;
    Toolbar toolbar;
    private RecyclerView.LayoutManager mLayoutManager;
    /*private Context context = LayananActivity.this;*/
    BottomNavigationView bottomNavigationView, bottomLogoutView;
    private static final String TAG = "LayananMain";
    private List<Layanan> layananList = new ArrayList<Layanan>();
    private ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layanan_main);
        //toolbar
//        toolbar = (Toolbar) findViewById(R.id.bartool);
//        setSupportActionBar(toolbar);
//        toolbar.setLogo(R.drawable.typetoolbar);
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.layanan);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        pDialog = new ProgressDialog(this);
        loadDataServerVolley();
    }


    private void gambarDatakeRecyclerView(){
        rvAdapter = new LayananAdapter(layananList);
        mRecyclerView.setAdapter(rvAdapter);
    }

    //ambil data sever volley
    private void loadDataServerVolley(){
        final String nik2 = SharedPreManager.getInstance(this).getUserNIK();



         /*String url = "http://192.168.1.64/android_php/v1/userLayanan.php";*/
        pDialog.setMessage("Retieve Data Layanan    ...");
        showDialog();
         /*layanan = new Layanan();*/


        StringRequest postRequest = new StringRequest(Request.Method.POST, SharedPreManager.getInstance(this).getUrl()+ URL_LAYANAN,
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
                params.put("nik",nik2);
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
            Layanan objectlayanan = null;
            layananList.clear();
            for(int i = 0; i < jsonArray.length(); i++){
                JSONObject obj = jsonArray.getJSONObject(i);
                objectlayanan= new Layanan();
                /*objectbarang.setId(obj.getString("id"));*/

                objectlayanan.setNomorSurat(obj.getString("NomorSurat"));
                objectlayanan.setJenisSurat(obj.getString("JenisSurat"));
                objectlayanan.setNamaStaff(obj.getString("NamaStaff"));
                objectlayanan.setTanggal(obj.getString("Tanggal"));

                layananList.add(objectlayanan);
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
                SharedPreManager.getInstance(this).logout();
                Intent newAct4 = new Intent(this, LoginActivity.class);
                newAct4.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(newAct4);
                finish();
                return true;
        }
        Log.i(TAG,"OnNavigationItemSelectedListener:");
        return true;
    }
}
