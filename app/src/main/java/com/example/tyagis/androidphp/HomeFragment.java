package com.example.tyagis.androidphp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

public class HomeFragment extends Fragment{
    @Nullable

    FrameLayout ambulance,appointment,emergency,privacypolicy;
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view=inflater.inflate(R.layout.home,container,false);
        ambulance=(FrameLayout)view.findViewById(R.id.ambulance_frame);
        appointment=(FrameLayout)view.findViewById(R.id.appointment_frame);
        emergency=(FrameLayout)view.findViewById(R.id.emergency_frame);
        privacypolicy=(FrameLayout)view.findViewById(R.id.privacypolicy_frame);

        ambulance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:108"));
                if(intent.resolveActivity(getActivity().getPackageManager())!=null){
                    startActivity(intent);
                }
            }
        });
        appointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(view.getContext(),doctorList.class));
            }
        });
        privacypolicy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(view.getContext(),PrivacyPolicy.class));
            }
        });
        emergency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(view.getContext(),History.class));
                Toast.makeText(view.getContext(),"emergency",Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
}
