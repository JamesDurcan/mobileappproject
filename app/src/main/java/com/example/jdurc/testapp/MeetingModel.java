package com.example.jdurc.testapp;

/*
    Created by jdurc on 11/04/2017.
    Contains all the details on the meeting (id, title, date, time, location)
 */

public class MeetingModel {

    public int id;
    public String title;
    public String date;
    public String time;
    public String location;

    public MeetingModel(int id, String title, String date, String time, String location) {
        this.id = id;
        this.title = title;
        this.date = date;
        this.time = time;
        this.location = location;
    }
    public MeetingModel(){

    }
}
