package com.example.tyagis.androidphp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Prescription extends AppCompatActivity {
    String prescription;
    TextView pres;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prescription);
        pres=(TextView)findViewById(R.id.prescript);
        Intent intent = getIntent();
        Bundle bd=intent.getExtras();
        prescription=bd.getString("prescription");
        pres.setText(prescription);
    }
}
