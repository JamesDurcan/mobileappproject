package com.example.jdurc.testapp;


/*
    Created by jdurc on 10/04/2017.
    Get permission to write to external storage.
    Linked to the activity_main layout
    The main/first page of the app, where three buttons appear. This class directs you to the selected layout
    depending on which button is clicked.
*/



import java.util.ArrayList;
import java.util.List;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.app.Activity;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
public class MainActivity extends Activity implements OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); //sets layout to activity_main

        //create the three buttons
        Button viewMeeting = (Button) findViewById(R.id.viewMeeting);
        viewMeeting.setOnClickListener(this);

        Button addMeeting = (Button) findViewById(R.id.addMeeting);
        addMeeting.setOnClickListener(this);

        Button takeNotes = (Button) findViewById(R.id.takeNotes);
        takeNotes.setOnClickListener(this);

       //Get Permissions - https://developer.android.com/training/permissions/requesting.html
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {


            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.viewMeeting:
                Log.i("clicks","You Clicked View Meetings Button"); //Logs activity in Android Monitor
                Intent viewmeeting = new Intent(MainActivity.this, ViewMeeting.class); //viewmeeting button directs user to ViewMeeting class which is linked to view_meeting layout
                startActivity(viewmeeting); //starts activity ViewMeeting
                break;

            case R.id.addMeeting:
                Log.i("clicks","You Clicked Add Meeting Button"); //Logs activity in Android Monitor
                Intent addmeeting = new Intent(MainActivity.this, AddMeeting.class); //addmeeting button directs user to AddMeeting class which is linked to add_meeting layout
                startActivity(addmeeting); //starts activity AddMeeting
                break;

            case R.id.takeNotes:
                Log.i("clicks","You Clicked Take Notes Button"); //Logs activity in Android Monitor
                Intent takenotes = new Intent(MainActivity.this, TakeNotes.class); //takenotes button directs user to TakeNotes class which is linked to take_notes layout
                startActivity(takenotes); //starts activity TakeNotes
                break;
        }

    }
}