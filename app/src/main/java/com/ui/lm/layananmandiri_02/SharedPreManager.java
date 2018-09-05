package com.ui.lm.layananmandiri_02;

import android.content.Context;
import android.content.SharedPreferences;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class SharedPreManager {
    private static SharedPreManager mInstance;
    private static Context mCtx;

    private static final String SHARED_PREF_NAME = "mysharedpref12";
    private static final String KEY_NIK = "usernik";
    private static final String KEY_NAMA = "usernama";
    private static final String KEY_USER_ID = "userid";
    private static final String KEY_USER_TANGGALLAHIR = "usertanggallahir";
    private static final String KEY_ALAMAT = "useralamat";
    private static final String KEY_AGAMA = "useragama";
    private static final String KEY_DUSUN = "userdusun";
    private static final String KEY_PEKERJAAN = "userpekerjaan";
    private static final String KEY_PENDIDIKAN = "userpendidikan";
    private static final String KEY_PENDIDIKANKK = "userpedidikankk";
    private static final String KEY_STATUS = "userstatus";
    private static final String KEY_GENDER = "usergender";
    private static final String KEY_WARGANEGARA = "userwarganegara";
    private static final String KEY_RT = "userrt";
    private static final String KEY_RW = "userrw";
    private static final String KEY_NOKK = "usernokk";
    private static final String KEY_URL = "url";


    private SharedPreManager(Context context) {
        mCtx = context;

    }

    public static synchronized SharedPreManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SharedPreManager(context);
        }
        return mInstance;
    }

    public boolean userLogin(int id, String nik, String nama, String tanggallahir,
                             String tempatlahir, String dusun, String agama, String pekerjaaan,
                             String pendidikan, String pendidikankk, String status, String gender,
                             String warganegara, String rt,String rw, String nokk,String url) {

        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putInt(KEY_USER_ID, id);
        editor.putString(KEY_NAMA, nama);
        editor.putString(KEY_NIK, nik);
        editor.putString(KEY_USER_TANGGALLAHIR, tanggallahir);
        editor.putString(KEY_ALAMAT, tempatlahir);
        editor.putString(KEY_AGAMA, agama);
        editor.putString(KEY_DUSUN, dusun);
        editor.putString(KEY_PEKERJAAN, pekerjaaan);
        editor.putString(KEY_PENDIDIKAN, pendidikan);
        editor.putString(KEY_PENDIDIKANKK, pendidikankk);
        editor.putString(KEY_STATUS, status);
        editor.putString(KEY_GENDER, gender);
        editor.putString(KEY_WARGANEGARA, warganegara);
        editor.putString(KEY_RT, rt);
        editor.putString(KEY_RW, rw);
        editor.putString(KEY_NOKK, nokk);
        editor.putString(KEY_URL, url);

        editor.apply();

        return true;
    }

    public boolean isLoggedIn() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        if (sharedPreferences.getString(KEY_NIK, null) != null) {
            return true;
        }
        return false;
    }

    public boolean logout() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        return true;
    }

    public int getUserId() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getInt(KEY_USER_ID, 0);

    }

    public String getUserNIK() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_NIK, null);

    }

    public String getUserNama() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_NAMA, null);
    }

    public String getUserTanggallahir() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_USER_TANGGALLAHIR, null);
    }

    public String getUserAlamat() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_ALAMAT, null);
    }

    public String getUserDusun() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_DUSUN, null);
    }

    public String getUserAgama() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_AGAMA, null);
    }

    public String getUserPekerjaan() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_PEKERJAAN, null);
    }

    public String getUserPendidikan() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_PENDIDIKAN, null);
    }

    public String getUserPendidikankk() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_PENDIDIKANKK, null);
    }

    public String getUserStatus() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_STATUS, null);
    }

    public String getUserGender() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_GENDER, null);
    }

    public String getUserWargaNegara() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_WARGANEGARA, null);
    }

    public String getUserRt() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_RT, null);
    }

    public String getUserRw() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_RW, null);
    }

    public String getUserNokk() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_NOKK, null);
    }
    public String getUrl() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_URL, null);
    }

}
