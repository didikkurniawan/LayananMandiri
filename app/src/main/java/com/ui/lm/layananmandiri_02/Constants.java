package com.ui.lm.layananmandiri_02;

public class Constants {


//    private static final String ROOT_URL = "http://192.168.43.209/android_php/v1/";


 /*   public static final String URL_LOGIN = ROOT_URL+"userLogin.php";
    public static final String URL_LAPOR = ROOT_URL+"userLapor.php";
    public static final String URL_BANTUAN = ROOT_URL+"userBantuan.php";
    public static final String URL_LAYANAN = ROOT_URL+"userLayanan.php";*/
    private static String ROOT_URL = "";
    public static final String URL_LOGIN = "/api_desa/get_login";
    public static final String URL_LAPOR = "/api_desa/post_laporan";
    public static final String URL_BANTUAN = "/api_desa/get_bantuan";
    public static final String URL_LAYANAN = "/api_desa/get_layanan";

    public void setDesa(String desa){
        this.ROOT_URL = desa;
    }
    public String getDesa(){
        return ROOT_URL;
    }

}
