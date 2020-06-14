package com.example.tyagis.androidphp;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder> {
    private Context ctx;
    private List<AppointmentData> appointmentDataList;
    public HistoryAdapter(Context ctx,List<AppointmentData> appointmentDataList){
        this.ctx=ctx;
        this.appointmentDataList=appointmentDataList;
    }
    @NonNull
    @Override
    public HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater=LayoutInflater.from(ctx);
        View view=inflater.inflate(R.layout.historylist,null);
        return new HistoryAdapter.HistoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryViewHolder holder, int i) {
        final AppointmentData appointmentData=appointmentDataList.get(i);
        holder.patientname.setText(appointmentData.getPatient_name());
        holder.doctor.setText(appointmentData.getDoctor_name());
        holder.date.setText(appointmentData.getDate());
        holder.time.setText(appointmentData.getTime());
        holder.prescription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ctx,Prescription.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("prescription",appointmentData.getPrescription());
                ctx.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return appointmentDataList.size();
    }

    class HistoryViewHolder extends RecyclerView.ViewHolder{
        TextView patientname,doctor,date,time;
        Button prescription;
        public HistoryViewHolder(@NonNull View itemView) {
            super(itemView);
            patientname=(TextView)itemView.findViewById(R.id.patient_name);
            doctor=(TextView)itemView.findViewById(R.id.doctor);
            date=(TextView)itemView.findViewById(R.id.date);
            time=(TextView)itemView.findViewById(R.id.time);
            prescription=(Button)itemView.findViewById(R.id.prescription);
        }
    }
}
