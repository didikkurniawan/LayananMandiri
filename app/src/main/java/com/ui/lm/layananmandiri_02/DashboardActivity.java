package com.ui.lm.layananmandiri_02;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.ui.lm.layananmandiri_02._sliders.SliderIndicator;
import com.ui.lm.layananmandiri_02._sliders.SliderPagerAdapter;
import com.ui.lm.layananmandiri_02._sliders.SliderView;
import com.ui.lm.layananmandiri_02._sliders.FragmentSlider;
import com.ui.lm.layananmandiri_02.model.Artikel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import java.util.ArrayList;
import java.util.List;

import static com.ui.lm.layananmandiri_02.Constants.URL_ARTIKEL;

public class DashboardActivity extends AppCompatActivity implements View.OnClickListener, BottomNavigationView.OnNavigationItemSelectedListener {
    private CardView profilCard, layananCard, laporCard, bantuanCard, keluarCard;
    Toolbar toolbar;
    private TextView textViewUsername, textViewNIK;

    private static final String TAG = "DashboardActivity";
    BottomNavigationView bottomNavigationView, bottomLogoutView;
    private SliderPagerAdapter mAdapter;
    private SliderIndicator mIndicator;
    private ImageView buttonLogout;

    public SliderView sliderView;
    public LinearLayout mLinearLayout;
    public BantuanMain bantuanMain;
    public ProgressDialog progressDialog;
    public List<Fragment> fragments = new ArrayList<>();
    private JsonArrayRequest request;
    private RequestQueue requestQueue;
    private List<Artikel> artikelList;
    private RecyclerView recyclerView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.home);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);

        sliderView = (SliderView) findViewById(R.id.sliderView);
        mLinearLayout = (LinearLayout) findViewById(R.id.pagesContainer);
        setupSlider();

        artikelList = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerViewId);
        jsonRequest();

        buttonLogout = (ImageView) findViewById(R.id.buttonLogout);

        buttonLogout.setOnClickListener(this);
    }

    public void jsonRequest() {

        request = new JsonArrayRequest(SharedPreManager.getInstance(this).getUrl() + URL_ARTIKEL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for (int i=0; i<response.length();i++){
                    try {
                        jsonObject = response.getJSONObject(i);
                        Artikel artikel = new Artikel();
                        artikel.setJudul(jsonObject.getString("judul"));
                        artikel.setKategori(jsonObject.getString("kategori"));
                        artikel.setUrl(jsonObject.getString("url"));
                        artikelList.add(artikel);

                    }catch (JSONException e){
                        e.printStackTrace();
                    }
                }
                setuprecycleview(artikelList);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });


        requestQueue = Volley.newRequestQueue(DashboardActivity.this);
        requestQueue.add(request);
    }

    private void setuprecycleview(List<Artikel> artikelList) {
        LinearLayoutManager llm = new LinearLayoutManager(this);
        RecyclerViewAdapter myadapter = new RecyclerViewAdapter(this,artikelList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
        recyclerView.setAdapter(myadapter);
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
                newAct2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
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

    public static ArrayList<String> list = new ArrayList<String>();

    private void setupSlider() {
        fragments.add(FragmentSlider.newInstance("http://www.menucool.com/slider/prod/image-slider-3.jpg"));
        mAdapter = new SliderPagerAdapter(getSupportFragmentManager(), fragments);
        sliderView.setAdapter(mAdapter);
        mIndicator = new SliderIndicator(this, mLinearLayout, sliderView, R.drawable.indicator_circle);
        mIndicator.setPageCount(fragments.size());
        mIndicator.show();

    }

    private static long back_pressed;
    @Override
    public void onBackPressed() {
        if (back_pressed + 2000 > System.currentTimeMillis()) super.onBackPressed();
        back_pressed = System.currentTimeMillis();
        finish();
    }

}
