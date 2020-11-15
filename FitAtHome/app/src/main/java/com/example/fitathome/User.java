package com.example.fitathome;

import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable {
    private String  name, gender, level, workout;
    private Integer age, frequency;
    private ArrayList<Boolean> day;

    User(String name, Integer age, String gender, String level, String workout, Integer frequency, ArrayList<Boolean> day){
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.level = level;
        this.workout = workout;
        this.frequency = frequency;
        this.day = day;
    }
    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name = name;
    }
    public Integer getAge(){
        return this.age;
    }
    public void setAge(Integer age){
        this.age = age;
    }
    public String getGender(){
        return this.gender;
    }
    public void setGender(String gender){
        this.gender = gender;
    }
    public String getLevel(){
        return this.level;
    }
    public void setLevel(String level){
        this.level = level;
    }
    public String getWorkout(){
        return this.workout;
    }
    public void setWorkout(String workout){
        this.workout = workout;
    }
    public Integer getFrequency(){
        return this.frequency;
    }
    public void setFrequency(Integer frequency){
        this.frequency = frequency;
    }
    public ArrayList<Boolean> getDay(){
        return this.day;
    }
    public void setDay(ArrayList<Boolean> day){
        this.day= day;
    }
}
