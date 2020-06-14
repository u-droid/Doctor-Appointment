package com.example.tyagis.androidphp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HistoryFragment extends Fragment{
    List<AppointmentData> appointmentDataList;
    RecyclerView recyclerView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view= inflater.inflate(R.layout.history,container,false);
        recyclerView=view.findViewById(R.id.history);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        appointmentDataList=new ArrayList<>();
        loadData();
        return view;
    }

    private void loadData() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.URL_VIEW_APPOINTMENT, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray array = new JSONArray(response);
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject appointment = array.getJSONObject(i);

                        appointmentDataList.add(new AppointmentData(appointment.getString("patient_name"),
                                        appointment.getString("doctor_name"),
                                        appointment.getString("date"),
                                        appointment.getInt("Time"),appointment.getInt("token")

                        ));

                    }
                    AppointmentAdapter adapter = new AppointmentAdapter(getContext(), appointmentDataList);
                    recyclerView.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_LONG).show();
            }
        }) {
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("mobile", SharedPrefManager.getInstance(getContext()).getUsername());
                return params;
            }
        };
        Volley.newRequestQueue(getContext()).add(stringRequest);
    }
}
