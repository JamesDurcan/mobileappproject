package com.example.jdurc.testapp;

/*
    Created by jdurc on 11/04/2017.
    The purpose of this class is to allow users to enter their meeting details and save them.
    This class is linked to the add_meeting layout, and the MainActivity class
 */

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.os.Bundle;
import android.app.Activity;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AddMeeting extends Activity implements OnClickListener{
    EditText title, date, time, location, id;
    Button addButton;
    TextView tv;
    List<MeetingModel> list = new ArrayList<>();
    DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_meeting);
        db = new DatabaseHelper(getApplicationContext());
        title = (EditText) findViewById(R.id.editText1);
        date = (EditText) findViewById(R.id.editText2);
        time = (EditText) findViewById(R.id.editText3);
        location = (EditText) findViewById(R.id.editText4);
        id = (EditText) findViewById(R.id.editText6);
        addButton = (Button) findViewById(R.id.add);
        tv = (TextView) findViewById(R.id.tv);
        addButton.setOnClickListener(this);

    }

    private void print(List<MeetingModel> list) {

        String value = "";
        for(MeetingModel meeting : list){
            value = value+" id: "+meeting.id+"\n"+" title: "+meeting.title+"\n"+" date: "+meeting.date+"\n"+" time: "+meeting.time+"\n"+" location: "+meeting.location+"\n"+"\n";
        }
        tv.setText(value);
    }

    @Override
    public void onClick(View v) {
            if(v == findViewById(R.id.add)){
            MeetingModel meeting = new MeetingModel();
            meeting.title = title.getText().toString();
            meeting.date = date.getText().toString();
            meeting.time = time.getText().toString();
            meeting.location = location.getText().toString();
            db.addMeetingDetail(meeting);

                //Toast provides feedback to the user.
          try {
            Toast
                    .makeText(this, "Your Meeting has been added", Toast.LENGTH_LONG)
                    .show();

        } catch (Throwable t) {
            Toast
                    .makeText(this, "Exception: " + t.toString(), Toast.LENGTH_LONG)
                    .show();
             }
        }
    }

}