package com.example.tyagis.androidphp;

import android.content.Context;
import android.content.SharedPreferences;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class SharedPrefManager {
    private static SharedPrefManager instance;
    private static Context ctx;

    private static final String SHARED_PREF_NAME="mysharedpref12";
    private static final String KEY_USERNAME="username";
    private static final String KEY_EMAIL="useremail";
    private static final String KEY_LOCATION="city";

    private static final String DOCTOR_SHARED_PREF="doctor_shared_pref";
    private static final String KEY_DOCTOR_ID="doctor_id";
    private static final String KEY_DOCTOR_NAME="doctor_name";
    private static final String KEY_DOCTOR_QUALIFICATION="qualification";
    private static final String KEY_DOCTOR_SPECIALIZATION="Specialization";
    private static final String KEY_MORNING_SHIFT="morning_shift";
    private static final String KEY_EVENING_SHIFT="evening_shift";
    private static final String KEY_FEES="fees";


    private SharedPrefManager(Context context) {
        ctx = context;

    }

    public static synchronized SharedPrefManager getInstance(Context context) {
        if (instance == null) {
            instance = new SharedPrefManager(context);
        }
        return instance;
    }

    public boolean userLogin(String username, String email,String city){
        SharedPreferences sharedPreferences=ctx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);//mode private so that only this app can use this shared prefrence
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString(KEY_USERNAME,username);
        editor.putString(KEY_EMAIL,email);
        editor.putString(KEY_LOCATION,city);
        editor.apply();
        return true;
    }

    public boolean storeDoctorDetails(int doctor_id,String doctor_name,String qualification,String Specialization,int morning_shift,int evening_shift,int fees){
        SharedPreferences sharedPreferences=ctx.getSharedPreferences(DOCTOR_SHARED_PREF,Context.MODE_PRIVATE);//mode private so that only this app can use this shared prefrence
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putInt(KEY_DOCTOR_ID,doctor_id);
        editor.putString(KEY_DOCTOR_NAME,doctor_name);
        editor.putString(KEY_DOCTOR_QUALIFICATION,qualification);
        editor.putString(KEY_DOCTOR_SPECIALIZATION,Specialization);
        editor.putInt(KEY_MORNING_SHIFT,morning_shift);
        editor.putInt(KEY_EVENING_SHIFT,evening_shift);
        editor.putInt(KEY_FEES,fees);
        editor.apply();
        return true;
    }



    public boolean isLoggedIn(){
        SharedPreferences sharedPreferences=ctx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        if(sharedPreferences.getString(KEY_USERNAME,null)!=null){
            return true;
        }
        return false;
    }

    public boolean logout(){
        SharedPreferences sharedPreferences=ctx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.clear();
        editor.apply();
        return true;
    }

    public String getUsername(){
        SharedPreferences sharedPreferences=ctx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_USERNAME,null);
    }

    public String getUserEmail(){
        SharedPreferences sharedPreferences=ctx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_EMAIL,null);
    }
    public String getUserLocation(){
        SharedPreferences sharedPreferences=ctx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_LOCATION,null);
    }

    public int getDoctorId(){
        SharedPreferences sharedPreferences=ctx.getSharedPreferences(DOCTOR_SHARED_PREF,Context.MODE_PRIVATE);
        return sharedPreferences.getInt(KEY_DOCTOR_ID,0);
    }
    public int getMorningShift(){
        SharedPreferences sharedPreferences=ctx.getSharedPreferences(DOCTOR_SHARED_PREF,Context.MODE_PRIVATE);
        return sharedPreferences.getInt(KEY_MORNING_SHIFT,0);
    }
    public int getEveningShift(){
        SharedPreferences sharedPreferences=ctx.getSharedPreferences(DOCTOR_SHARED_PREF,Context.MODE_PRIVATE);
        return sharedPreferences.getInt(KEY_EVENING_SHIFT,0);
    }
    public String getDoctorName(){
        SharedPreferences sharedPreferences=ctx.getSharedPreferences(DOCTOR_SHARED_PREF,Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_DOCTOR_NAME,null);
    }
    public String getDoctorqualifications(){
        SharedPreferences sharedPreferences=ctx.getSharedPreferences(DOCTOR_SHARED_PREF,Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_DOCTOR_QUALIFICATION,null);
    }
    public String getDoctorSpecialization(){
        SharedPreferences sharedPreferences=ctx.getSharedPreferences(DOCTOR_SHARED_PREF,Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_DOCTOR_SPECIALIZATION,null);
    }
}
