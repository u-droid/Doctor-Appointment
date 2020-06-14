package com.example.tyagis.androidphp;

public class AppointmentData {
    String patient_name,doctor_name,date,time,prescription;
    int token;

    public String getPatient_name() {
        return patient_name;
    }

    public String getDoctor_name() {
        return doctor_name;
    }

    public String getTime() {
        return time;
    }

    public String getDate() {
        return date;
    }

    public int getToken() {
        return token;
    }
    public String getPrescription(){
        return prescription;
    }

    public AppointmentData(String patient_name, String doctor_name, String date,int time,int token){
        this.patient_name=patient_name;
        this.doctor_name=doctor_name;
        this.date=date;
        if(time<12)
            this.time=String.valueOf(time)+"am";
        else if(time==12)
            this.time=String.valueOf(time)+"pm";
        else
            this.time=String.valueOf(time-12)+"pm";
        this.token=token;

    }
    public AppointmentData(String patient_name, String doctor_name, String date,int time,String prescription){
        this.patient_name=patient_name;
        this.doctor_name=doctor_name;
        this.prescription=prescription;
        this.date=date;
        if(time<12)
            this.time=String.valueOf(time)+"am";
        else if(time==12)
            this.time=String.valueOf(time)+"pm";
        else
            this.time=String.valueOf(time-12)+"pm";
        this.token=token;

    }
}
