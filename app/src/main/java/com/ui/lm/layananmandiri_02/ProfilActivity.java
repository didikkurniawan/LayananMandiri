package com.ui.lm.layananmandiri_02;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ProfilActivity extends AppCompatActivity {
    private TextView textViewNam, textViewNik,textViewTKK, textViewDusun,textViewRtRw, textViewJenisKelamin, textViewTglLahir,
                     textViewAgama, textViewPendidikanKK, textViewPendidikan, textViewPekerjaan, textViewStatus, textViewWargaNegara,
                     textViewAlmat;

    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        //toolbar
        toolbar = (Toolbar) findViewById(R.id.bartool);
        setSupportActionBar(toolbar);
        toolbar.setLogo(R.drawable.typetoolbar);


        //Pengecekan Login
        if(!SharedPreManager.getInstance(this).isLoggedIn()){
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }

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

    }
}
