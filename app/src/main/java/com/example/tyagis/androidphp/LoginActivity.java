package com.example.tyagis.androidphp;

import android.app.ActionBar;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText editTextUsername,editTextPassword;
    private TextView login;
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //If the user is logged in then the profile activity can be directly opened
        if(SharedPrefManager.getInstance(this).isLoggedIn()){
            finish();
            startActivity(new Intent(this,profileActivity.class));
            return;
        }


        editTextPassword=(EditText)findViewById(R.id.password);
        editTextUsername=(EditText)findViewById(R.id.username);
        login=(TextView) findViewById(R.id.login);

        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Please wait...");
        login.setOnClickListener(this);
    }

    private void userlogin(){
        final String usernames=editTextUsername.getText().toString().trim();
        final String passwords=editTextPassword.getText().toString().trim();
        progressDialog.show();
        StringRequest stringRequest=new StringRequest(Request.Method.POST, Constants.URL_LOGIN, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    if(!jsonObject.getBoolean("error")){
                        SharedPrefManager.getInstance(getApplicationContext())
                                .userLogin(jsonObject.getString("mobile"),jsonObject.getString("email"),jsonObject.getString("city")
                                );
                        startActivity(new Intent(getApplicationContext(),profileActivity.class));
                        finish(); //so that when user press back button he shouldn't be on login activity
                    }
                    else {
                        Toast.makeText(getApplicationContext(), jsonObject.getString("message"), Toast.LENGTH_LONG).show();
                    }
                }catch (JSONException e){
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();

                Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_LONG).show();//error is volley object
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params=new HashMap<>();
                params.put("mobile",usernames);
                params.put("password",passwords);
                return params;
            }
        };
        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }

    @Override
    public void onClick(View view) {
        if(view==login)
            userlogin();
    }
}
