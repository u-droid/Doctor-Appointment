package com.example.tyagis.androidphp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class PatientAdapter extends RecyclerView.Adapter<PatientAdapter.PatientViewHolder> {

    Context ctx;
    private List<PatientData> patientList;
    public PatientAdapter(@NonNull Context ctx, List<PatientData> patientList) {
        this.ctx = ctx;
        this.patientList= patientList;
    }
    @NonNull
    @Override
    public PatientViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater= LayoutInflater.from(viewGroup.getContext());
        View view=inflater.inflate(R.layout.patient_list,null);

        return new PatientViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final PatientViewHolder patientViewHolder, int i) {
        final PatientData patientData=patientList.get(i);
        patientViewHolder.patientname.setText(patientData.getPatient_name().toUpperCase());
        patientViewHolder.gender.setText(patientData.getGender().toUpperCase());
        patientViewHolder.age.setText(String.valueOf(patientData.getAge()));
        patientViewHolder.patient_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ctx,Appointment.class);
                intent.putExtra("patient_id",patientData.getPatient_id());
                ctx.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return patientList.size();
    }

    public class PatientViewHolder extends RecyclerView.ViewHolder {
        TextView patientname,gender,age;
        RelativeLayout patient_item;
        public PatientViewHolder(@NonNull View itemView) {
            super(itemView);
            patientname=(TextView)itemView.findViewById(R.id.patient_name);
            gender=(TextView)itemView.findViewById(R.id.gender);
            age=(TextView)itemView.findViewById(R.id.age);
            patient_item=(RelativeLayout)itemView.findViewById(R.id.patient_item);
        }
    }
}
