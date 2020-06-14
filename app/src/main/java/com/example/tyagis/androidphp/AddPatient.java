package com.example.tyagis.androidphp;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import javax.xml.transform.Templates;

public class AddPatient extends AppCompatActivity implements View.OnClickListener {
    EditText name,age;
    RadioButton male,female,other;
    Button add;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_patient);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        name=(EditText) findViewById(R.id.name);
        male=(RadioButton)findViewById(R.id.male);
        female=(RadioButton)findViewById(R.id.female);
        other=(RadioButton)findViewById(R.id.other);
        age=(EditText)findViewById(R.id.age);
        add=(Button)findViewById(R.id.add);
        add.setOnClickListener(this);

    }

    private void inputdata() {
        final String Name=name.getText().toString();
        String Gender="";
        final String Age=age.getText().toString();
        if(male.isChecked())
            Gender="Male";
        else
            if(female.isChecked())
                Gender="Female";
        else
            Gender="Other";
        final String finalGender = Gender;
        StringRequest stringRequest=new StringRequest(Request.Method.POST, Constants.URL_CREATE_PATIENT, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                        JSONObject jsonObject=new JSONObject(response);

                        Toast.makeText(getApplicationContext(), jsonObject.getString("message"), Toast.LENGTH_LONG).show();
                        finish();
                        Intent intent=new Intent(getApplicationContext(),patient.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_LONG).show();//error is volley object
            }
        }){
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params=new HashMap<>();
                params.put("patient_name",Name);
                params.put("mobile",SharedPrefManager.getInstance(getApplicationContext()).getUsername());
                params.put("gender", finalGender);
                params.put("age",Age);
                return params;
            }

        };
        RequestQueue requestQueue=Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }

    @Override
    public void onClick(View v) {
        if(v==add)
            inputdata();
    }
}
