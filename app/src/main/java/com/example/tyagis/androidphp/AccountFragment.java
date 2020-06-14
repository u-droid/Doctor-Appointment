package com.example.tyagis.androidphp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class AccountFragment extends Fragment{
    @Nullable
    TextView username,email,logout,location;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View view=inflater.inflate(R.layout.account,container,false);
        username=(TextView)view.findViewById(R.id.username);
        email=(TextView)view.findViewById(R.id.email);
        location=(TextView)view.findViewById(R.id.location);
        logout=(TextView)view.findViewById(R.id.logout);
        username.setText(SharedPrefManager.getInstance(view.getContext()).getUsername());
        email.setText(SharedPrefManager.getInstance(view.getContext()).getUserEmail());
        location.setText(SharedPrefManager.getInstance(view.getContext()).getUserLocation());
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPrefManager.getInstance(view.getContext()).logout();
                getActivity().finishAffinity();
                startActivity(new Intent(view.getContext(),LoginActivity.class));
            }
        });
        return view;

    }
}
