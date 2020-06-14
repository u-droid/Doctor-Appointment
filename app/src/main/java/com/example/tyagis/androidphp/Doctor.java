package com.example.tyagis.androidphp;

public class Doctor {
    private String doctor_name,qualification,specialization;
    int morning,evening,doctor_id,fees;

    public Doctor(int doctor_id,String doctor_name,String qualification,String specialization,int morning,int evening,int fees){
        this.doctor_name=doctor_name;
        this.doctor_id=doctor_id;
        this.qualification=qualification;
        this.specialization=specialization;
        this.morning=morning;
        this.evening=evening;
        this.fees=fees;
    }
    public  int getDoctor_id(){
        return doctor_id;
    }

    public String getDoctor_name(){
        return doctor_name;
    }

    public String getQualification() {
        return qualification;
    }

    public String getSpecialization() {
        return specialization;
    }
    public int getMorning(){
        return morning;
    }
    public  int getEvening(){
        return evening;
    }

    public int getFees() {
        return fees;
    }
}
