package com.ui.lm.layananmandiri_02;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class DashboardActivity extends AppCompatActivity implements View.OnClickListener {
    private CardView profilCard, layananCard, laporCard, bantuanCard, keluarCard;
    Toolbar toolbar;
    private TextView textViewUsername, textViewNIK;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

//        BottomNavigationView bottomNavigationView = (BottomNavigationView)findViewById(R.id.bottom_navigation);
//
//        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                switch (item.getItemId()){
//                    case R.id.home:
//                        Toast.makeText(DashboardActivity.this, "INI HOME", Toast.LENGTH_SHORT).show();
//                        break;
//                    case R.id.home1:
//                        Toast.makeText(DashboardActivity.this, "INI HOME 1", Toast.LENGTH_SHORT).show();
//                        break;
//                    case R.id.home2:
//                        Toast.makeText(DashboardActivity.this, "INI HOME 2", Toast.LENGTH_SHORT).show();
//                        break;
//                    case R.id.home3:
//                        Toast.makeText(DashboardActivity.this, "INI HOME 3", Toast.LENGTH_SHORT).show();
//                        break;
//                    case R.id.home4:
//                        Toast.makeText(DashboardActivity.this, "INI HOME 4", Toast.LENGTH_SHORT).show();
//                        break;
//                }
//                return false;
//            }
//        });
        //toolbar
//        toolbar = (Toolbar) findViewById(R.id.bartool);
//        setSupportActionBar(toolbar);
//        toolbar.setLogo(R.drawable.typetoolbar);

        //Pendefinisian Card
//        profilCard = (CardView) findViewById(R.id.profilId);
//        layananCard = (CardView) findViewById(R.id.layananid);
//        laporCard = (CardView) findViewById(R.id.laporid);
//        bantuanCard = (CardView) findViewById(R.id.bantuanid);
//        keluarCard = (CardView) findViewById(R.id.keluarid);
//
//        //Menambahkan Click Listener pada Card
//        profilCard.setOnClickListener(this);
//        laporCard.setOnClickListener(this);
//        layananCard.setOnClickListener(this);
//        laporCard.setOnClickListener(this);
//        bantuanCard.setOnClickListener(this);
//        keluarCard.setOnClickListener(this);
//
//        //Pendefinisian tulisan username dan NIk user
//        textViewNIK = (TextView) findViewById(R.id.textnik);
//        textViewUsername = (TextView) findViewById(R.id.textuser);
//
//        textViewUsername.setText(SharedPreManager.getInstance(this).getUserNama());
//        textViewNIK.setText(SharedPreManager.getInstance(this).getUserNIK());
    }

    @Override
    public void onClick(View v) {
        Intent i;

//        switch(v.getId()){
//            case R.id.profilId :    i = new Intent(this,ProfilActivity.class); startActivity(i); break;
//            case R.id.layananid :    i = new Intent(this,LayananMain.class);startActivity(i); break;
//            case R.id.keluarid : SharedPreManager.getInstance(this).logout();finish();startActivity(new Intent(this, LoginActivity.class));break;
//            case R.id.laporid : i = new Intent(this,LaporActivity.class);startActivity(i);break;
//            case R.id.bantuanid : i = new Intent(this, BantuanMain.class);startActivity(i);break;
//            default:break;
//        }

    }
}
