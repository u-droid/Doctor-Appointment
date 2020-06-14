package com.example.tyagis.androidphp;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class Appointment extends AppCompatActivity {
    RadioButton morning,evening;
    Button book;
    TextView textView;
    EditText description;

    private DatePickerDialog.OnDateSetListener dateSetListener;
    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        morning=(RadioButton)findViewById(R.id.morning);
        book=(Button)findViewById(R.id.book);
        evening=(RadioButton)findViewById(R.id.evening);
        textView=(TextView)findViewById(R.id.date);
        description=(EditText)findViewById(R.id.description);
        morning.setText("Morning : "+SharedPrefManager.getInstance(getApplicationContext()).getMorningShift()+"am");
        evening.setText("Evening : "+SharedPrefManager.getInstance(getApplicationContext()).getEveningShift()+"pm");
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal=Calendar.getInstance();
                int year=cal.get(Calendar.YEAR);
                int month=cal.get(Calendar.MONTH);
                int day=cal.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog dialog=new DatePickerDialog(Appointment.this,android.R.style.Theme_DeviceDefault_Dialog_MinWidth,
                        dateSetListener,year,month,day);
                dialog.getDatePicker().setMinDate(cal.getTimeInMillis());
                dialog.getDatePicker().setMaxDate(cal.getTimeInMillis()+1000*60*60*24*6);
                dialog.show();
            }
        });
        dateSetListener=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month=month+1;
                String date=year+"-"+month+"-"+dayOfMonth;
                textView.setText(date);
            }
        };
        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int time;
                Intent intent=getIntent();
                final String Description=description.getText().toString();
                final int PATIENT_ID=intent.getIntExtra("patient_id",0);
                final int DOCTOR_ID=SharedPrefManager.getInstance(Appointment.this).getDoctorId();
                if(morning.isChecked()){
                     time=SharedPrefManager.getInstance(getApplicationContext()).getMorningShift();
                }
                else{
                     time=SharedPrefManager.getInstance(getApplicationContext()).getEveningShift()+12;
                }
                final String DATE=textView.getText().toString();


                StringRequest stringRequest=new StringRequest(Request.Method.POST, Constants.URL_GET_APPOINTMENT, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject=new JSONObject(response);
                            Toast.makeText(getApplicationContext(),jsonObject.getString("message"),Toast.LENGTH_LONG).show();
                            Intent intent=new Intent(getApplicationContext(),profileActivity.class);
                            startActivity(intent);
                        }catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_LONG).show();
                    }
                })
                {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String> params=new HashMap<>();
                        params.put("patient_id",String.valueOf(PATIENT_ID));
                        params.put("doctor_id",String.valueOf(DOCTOR_ID));
                        params.put("date",DATE);
                        params.put("Time",String.valueOf(time));
                        params.put("description",Description);
                        return params;
                    }
                };

                RequestHandler.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);

            }
        });


    }
}
