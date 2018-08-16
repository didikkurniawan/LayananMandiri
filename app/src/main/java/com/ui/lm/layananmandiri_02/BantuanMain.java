package com.ui.lm.layananmandiri_02;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
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

public class BantuanMain extends AppCompatActivity {
    private Constants cons;
    private RecyclerView mRecyclerView;
    private BantuanAdapter rvAdapter;
    Toolbar toolbar;
    private RecyclerView.LayoutManager mLayoutManager;
    /*private Context context = BantuanMain.this;*/

    private List<Bantuan> bantuanList = new ArrayList<Bantuan>();
    private ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bantuan_main);

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
        rvAdapter = new BantuanAdapter(bantuanList);
        mRecyclerView.setAdapter(rvAdapter);
    }


    public static String desa="";

    public static void setDesa(String Desa){
        desa = Desa;
    }
    public static String getDesa(){
        return desa;
    }

    //ambil data sever volley
    private void loadDataServerVolley(){
        final String peserta = SharedPreManager.getInstance(this).getUserNIK();

        /*String url = "http://192.168.1.64/android_php/v1/userBantuan.php";*/

        pDialog.setMessage("Retieve Data Barang...");
        showDialog();
       /* cons = new Constants();*/
       /* String url = cons.getDesa();*/
       /* Toast.makeText(
                getApplicationContext(),
                desa, Toast.LENGTH_LONG).show();
        finish();*/
        StringRequest postRequest = new StringRequest(Request.Method.POST, desa + URL_BANTUAN,

//        StringRequest postRequest = new StringRequest(Request.Method.POST, URL_BANTUAN,
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
                objectbantuan.setSdate(obj.getString("sdate"));
                objectbantuan.setEdate(obj.getString("edate"));

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

}
