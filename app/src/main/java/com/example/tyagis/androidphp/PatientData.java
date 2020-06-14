package com.example.tyagis.androidphp;

public class PatientData {
    private int patient_id;
    private String patient_name;
    private int user_id;
    private String gender;

    private int age;

    public PatientData(int patient_id,String patient_name,int user_id,String gender,int age){
        this.patient_id=patient_id;
        this.patient_name=patient_name;
        this.user_id=user_id;
        this.gender=gender;
        this.age=age;
    }

    public int getPatient_id(){
        return patient_id;
    }

    public int getAge() {
        return age;
    }



    public String getGender() {
        return gender;
    }

    public String getPatient_name() {
        return patient_name;
    }
    public int getUser_id(){
        return user_id;
    }
}
