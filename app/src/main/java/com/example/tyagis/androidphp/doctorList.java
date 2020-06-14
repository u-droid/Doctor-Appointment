package com.example.tyagis.androidphp;

import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
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

public class doctorList extends AppCompatActivity {
    List<Doctor> doctorList;
    doctorAdapter adapter;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_list);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        doctorList=new ArrayList<Doctor>();
        recyclerView=(RecyclerView)findViewById(R.id.doctor_list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        loaddoctor();



    }
    private void loaddoctor(){
        final StringRequest stringRequest=new StringRequest(Request.Method.POST, Constants.URL_VIEW_DOCTOR, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray doctors=new JSONArray(response);
                    for(int i=0;i<doctors.length();i++){
                        JSONObject doctorobject=doctors.getJSONObject(i);
                        int doctor_id=doctorobject.getInt("doctor_id");
                        String doctor_name=doctorobject.getString("doctor_name");
                        String qualification=doctorobject.getString("qualification");
                        String Specialization=doctorobject.getString("Specialization");
                        int morning_shift=doctorobject.getInt("morning_shift");
                        int evening_shift=doctorobject.getInt("evening_shift");
                        int fees=doctorobject.getInt("fees");
                        Doctor doctor=new Doctor(doctor_id,doctor_name,qualification,Specialization,morning_shift,evening_shift,fees);
                        doctorList.add(doctor);

                    }
                    adapter=new doctorAdapter(doctorList.this,doctorList);
                    recyclerView.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_LONG).show();
            }
        }){
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params=new HashMap<>();
                params.put("city",SharedPrefManager.getInstance(getApplicationContext()).getUserLocation());
                return params;
            }
        };
        Volley.newRequestQueue(this).add(stringRequest);
    }
}
