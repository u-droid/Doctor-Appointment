package com.example.tyagis.androidphp;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class doctorAdapter extends RecyclerView.Adapter<doctorAdapter.DoctorViewHolder> {

    @NonNull
    private Context ctx;
    private List<Doctor> doctorList;

    public doctorAdapter(@NonNull Context ctx, List<Doctor> doctorList) {
        this.ctx = ctx;
        this.doctorList = doctorList;
    }

    @Override
    public DoctorViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater=LayoutInflater.from(viewGroup.getContext());
        View view=inflater.inflate(R.layout.doctor_list_item,null);

        return new DoctorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DoctorViewHolder doctorViewHolder, int position) {
        final Doctor doctor=doctorList.get(position);
        doctorViewHolder.doctor_name.setText("Dr. "+doctor.getDoctor_name());
        doctorViewHolder.qualification.setText(doctor.getQualification());
        doctorViewHolder.specialization.setText(doctor.getSpecialization());
        doctorViewHolder.morning_shift.setText("Morning Time :"+String.valueOf(doctor.getMorning())+"am");
        doctorViewHolder.evening_shift.setText("Evening Time :"+String.valueOf(doctor.getEvening())+"pm");
        doctorViewHolder.fees.setText(String.valueOf(doctor.getFees()));
        doctorViewHolder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPrefManager.getInstance(ctx).storeDoctorDetails(doctor.getDoctor_id(),doctor.getDoctor_name(),doctor.getQualification(),doctor.getSpecialization(),doctor.getMorning(),doctor.getEvening(),doctor.getFees());
                Intent intent=new Intent(ctx,patient.class);
                ctx.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return doctorList.size();
    }

    public class DoctorViewHolder extends RecyclerView.ViewHolder{

        TextView doctor_name,qualification,specialization,morning_shift,evening_shift,fees;
        LinearLayout linearLayout;
        public DoctorViewHolder(@NonNull View itemView) {
            super(itemView);
            doctor_name=(TextView)itemView.findViewById(R.id.doctorname);
            qualification=(TextView)itemView.findViewById(R.id.qualification);
            specialization=(TextView)itemView.findViewById(R.id.specialization);
            morning_shift=(TextView)itemView.findViewById(R.id.morning);
            evening_shift=(TextView)itemView.findViewById(R.id.evening);
            fees=(TextView)itemView.findViewById(R.id.fees);
            linearLayout=(LinearLayout)itemView.findViewById(R.id.doctor_item);

        }
    }
}
