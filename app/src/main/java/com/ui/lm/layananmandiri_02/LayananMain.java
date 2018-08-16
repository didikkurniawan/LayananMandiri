package com.ui.lm.layananmandiri_02;


import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

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

public class LayananMain extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private LayananAdapter rvAdapter;
    private LoginActivity LgAk;
    private Layanan layanan;
    Toolbar toolbar;
    private RecyclerView.LayoutManager mLayoutManager;
    /*private Context context = LayananActivity.this;*/

    private List<Layanan> layananList = new ArrayList<Layanan>();
    private ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layanan_main);
        //toolbar
        toolbar = (Toolbar) findViewById(R.id.bartool);
        setSupportActionBar(toolbar);
        toolbar.setLogo(R.drawable.typetoolbar);


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

    public static String desa2="";

    public static void setDesa2(String Desa){
        desa2 = Desa;
    }
    public static String getDesa2(){
        return desa2;
    }


    //ambil data sever volley
    private void loadDataServerVolley(){
        final String nik2 = SharedPreManager.getInstance(this).getUserNIK();



         /*String url = "http://192.168.1.64/android_php/v1/userLayanan.php";*/
        pDialog.setMessage("Retieve Data Layanan    ...");
        showDialog();
         /*layanan = new Layanan();*/


        StringRequest postRequest = new StringRequest(Request.Method.POST, desa2+"/api_desa/get_layanan",
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
}
