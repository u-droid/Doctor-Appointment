package com.example.tyagis.androidphp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.app.VoiceInteractor;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

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

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText username,email,password,location;
    private TextView signup;
    private ProgressDialog progressDialog;
    private TextView login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        Context context=getApplicationContext();
        ConnectivityManager cm = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null && activeNetwork.isConnected();
        if(isConnected==false){   //when user is not connected to internet
            AlertDialog.Builder myalert = new AlertDialog.Builder(MainActivity.this);
            myalert.setTitle("Error");
            myalert.setMessage("Internet Connection Not Found");
            myalert.setPositiveButton("Exit", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                         finish();
                }
            });
            myalert.setNegativeButton("Setting", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    startActivityForResult(new Intent(Settings.ACTION_SETTINGS),0);
                }
            });
            myalert.setCancelable(false);
            myalert.show();
        }
        else {

            //If the user is logged in then the profile activity can be directly opened
            if (SharedPrefManager.getInstance(this).isLoggedIn()) {
                finish();
                startActivity(new Intent(this, profileActivity.class));
                return;
            }


            username = (EditText) findViewById(R.id.username);
            email = (EditText) findViewById(R.id.email);
            password = (EditText) findViewById(R.id.password);
            location = (EditText) findViewById(R.id.location);
            signup = (TextView) findViewById(R.id.signup);
            login = (TextView) findViewById(R.id.login);
            progressDialog = new ProgressDialog(this);
            //progressDialog.setMessage("");
            signup.setOnClickListener(this);
            login.setOnClickListener(this);
        }
    }


    private void registeruser() {
        final String usernames = username.getText().toString().trim();
        final String emails = email.getText().toString().trim();
        final String passwords = password.getText().toString().trim();
        final String locations = location.getText().toString().trim();
        if (validate(emails,passwords,usernames,locations)) {
            progressDialog.setMessage("Registering user");
            progressDialog.show();
            StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.URL_REGISTER, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    progressDialog.dismiss();
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        Toast.makeText(getApplicationContext(), jsonObject.getString("message"), Toast.LENGTH_LONG).show();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    progressDialog.dismiss();
                    Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                }
            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("mobile", usernames);
                    params.put("email", emails);
                    params.put("password", passwords);
                    params.put("city", locations);
                    return params;
                }
            };

            RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
        }
    }
    @Override
    public void onClick(View view) {
        if(view==signup)
            registeruser();
        if(view==login)
            startActivity(new Intent(this,LoginActivity.class));
    }
    private boolean validate(String email,String password,String username,String location){
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        String passwordRegex="((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})";
        Pattern passwordPattern=Pattern.compile(passwordRegex);
        if(username.length()!=10){
            Toast.makeText(getApplicationContext(),"Invalid Phone number",Toast.LENGTH_SHORT).show();
            return false;
        }


        if(!passwordPattern.matcher(password).matches()){
            Toast.makeText(getApplicationContext(),"Password must contain at least one uppercase character, one lowercase character, one digit, one special character @#$% and at 6-20 characters long",Toast.LENGTH_LONG).show();
            return false;
        }
        if(!pat.matcher(email).matches()){
            Toast.makeText(getApplicationContext(),"Invalid Email",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(location.length()==0){
            Toast.makeText(getApplicationContext(),"Enter location",Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }
}




