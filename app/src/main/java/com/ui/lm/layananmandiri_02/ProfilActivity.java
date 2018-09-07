package com.ui.lm.layananmandiri_02;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ProfilActivity extends AppCompatActivity implements View.OnClickListener, BottomNavigationView.OnNavigationItemSelectedListener {
    private TextView textViewNam, textViewNik,textViewTKK, textViewDusun,textViewRtRw, textViewJenisKelamin, textViewTglLahir,
                     textViewAgama, textViewPendidikanKK, textViewPendidikan, textViewPekerjaan, textViewStatus, textViewWargaNegara,
                     textViewAlmat;
    private static final String TAG = "ProfilActivity";
    BottomNavigationView bottomNavigationView, bottomLogoutView;
    Toolbar toolbar;
    private ImageView buttonLogout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        //toolbar
//        toolbar = (Toolbar) findViewById(R.id.bartool);
//        setSupportActionBar(toolbar);
//        toolbar.setLogo(R.drawable.typetoolbar);

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.account);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);

        //Pendefinisian text Item Profil
        textViewNam = (TextView) findViewById(R.id.tv_name);
        textViewNik = (TextView) findViewById(R.id.Tnik);
        textViewTKK = (TextView) findViewById(R.id.Tkk);
        textViewDusun = (TextView) findViewById(R.id.Tdusun);
        textViewPekerjaan = (TextView) findViewById(R.id.Tpekerjaan);
        textViewPendidikanKK = (TextView) findViewById(R.id.Tpendidikan);
        textViewPendidikan = (TextView) findViewById(R.id.Tpendidikantempuh);
        textViewStatus = (TextView) findViewById(R.id.Tstatus);
        textViewJenisKelamin = (TextView) findViewById(R.id.Tjeniskelamin);
        textViewRtRw = (TextView) findViewById(R.id.Trtrw);
        textViewAlmat = (TextView) findViewById(R.id.Talamat);
        textViewTglLahir = (TextView) findViewById(R.id.Tlahir);
        textViewWargaNegara = (TextView) findViewById(R.id.Twarganegara);
        textViewAgama = (TextView) findViewById(R.id.Tagama);

        //Set Biodata
        textViewNam.setText(SharedPreManager.getInstance(this).getUserNama());
        textViewNik.setText(SharedPreManager.getInstance(this).getUserNIK());
        textViewTKK.setText(SharedPreManager.getInstance(this).getUserNokk());
        textViewDusun.setText(SharedPreManager.getInstance(this).getUserDusun());
        textViewRtRw.setText(SharedPreManager.getInstance(this).getUserRt());
        textViewJenisKelamin.setText(SharedPreManager.getInstance(this).getUserGender());
        textViewTglLahir.setText(SharedPreManager.getInstance(this).getUserTanggallahir());
        textViewAgama.setText(SharedPreManager.getInstance(this).getUserAgama());
        textViewPendidikanKK.setText(SharedPreManager.getInstance(this).getUserPendidikankk());
        textViewPendidikan.setText(SharedPreManager.getInstance(this).getUserPendidikan());
        textViewPekerjaan.setText(SharedPreManager.getInstance(this).getUserPekerjaan());
        textViewStatus.setText(SharedPreManager.getInstance(this).getUserStatus());
        textViewWargaNegara.setText(SharedPreManager.getInstance(this).getUserWargaNegara());


        textViewAlmat.setText(SharedPreManager.getInstance(this).getUserAlamat());

        buttonLogout = (ImageView) findViewById(R.id.buttonLogout);

        buttonLogout.setOnClickListener(this);

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

    public void onClick(View v) {
        if (v == buttonLogout){
            SharedPreManager.getInstance(this).logout();
            Intent newAct4 = new Intent(this, LoginActivity.class);
            newAct4.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(newAct4);
            finish();
        }
    }
    public void onBackPressed() {
        Intent newAct2 = new Intent(this, DashboardActivity.class);
        newAct2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(newAct2);
        finish();
    }

}
