package com.example.tyagis.androidphp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class AppointmentAdapter extends RecyclerView.Adapter<AppointmentAdapter.AppointmentViewHolder> {
    private Context ctx;
    private List<AppointmentData> appointmentDataList;
    public AppointmentAdapter(Context ctx,List<AppointmentData> appointmentDataList){
        this.ctx=ctx;
        this.appointmentDataList=appointmentDataList;
    }
    @NonNull
    @Override
    public AppointmentViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater=LayoutInflater.from(ctx);
        View view=inflater.inflate(R.layout.appointment_list,null);
        return new AppointmentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AppointmentViewHolder appointmentViewHolder, int i) {
        AppointmentData appointmentData=appointmentDataList.get(i);
        appointmentViewHolder.details.setText(appointmentData.getPatient_name()+", you have an appointment at Dr."+appointmentData.getDoctor_name()+" on "+appointmentData.getDate()+" at "+appointmentData.getTime()+".");
        appointmentViewHolder.token.setText("Appointment no :"+String.valueOf(appointmentData.getToken()));
    }

    @Override
    public int getItemCount() {
        return appointmentDataList.size();
    }

    class AppointmentViewHolder extends RecyclerView.ViewHolder{
                TextView details,token;
        public AppointmentViewHolder(@NonNull View itemView) {
            super(itemView);
            details=(TextView)itemView.findViewById(R.id.details);
            token=(TextView)itemView.findViewById(R.id.token);
        }
    }
}
