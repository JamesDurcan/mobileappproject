package com.example.jdurc.testapp;

/*
    Created by jdurc on 12/04/2017.
    The purpose of this class is to allow the user to view the meetings that they have scheduled
    This class linked to the view_meeting layout, and the MainActivity class
 */
import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.jdurc.testapp.R.id.textView6;

public class ViewMeeting extends Activity implements OnClickListener {

    EditText deleteText;
    Button  deleteButton;
    TextView tv;
    List<MeetingModel> list = new ArrayList<>();
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_meeting);
        db = new DatabaseHelper(getApplicationContext());
        deleteText = (EditText) findViewById(R.id.editText6);
        deleteButton = (Button) findViewById(R.id.delete1);
        tv = (TextView) findViewById(textView6);
        deleteButton.setOnClickListener(this);
        list = db.getAllMeetingsList();
        print(list);
    }

    private void print(List<MeetingModel> list) {

        String value = "";
        for(MeetingModel sm : list){
            value = value+" ID: "+sm.id +"\n" +" Title: "+sm.title +"\n"+" Date: "+sm.date +"\n" + " Time: "+sm.time +"\n" +" Location: "+sm.location+"\n"+"\n";
        }
        tv.setText(value);
    }
    //Delete Your Meeting:
    @Override
    public void onClick(View v) {

        if(v == findViewById(R.id.delete1)){
            tv.setText("");
            String meeting_id = deleteText.getText().toString();
            db.deleteEntry(Integer.parseInt(meeting_id));
            list = db.getAllMeetingsList();
            print(list);
            deleteText.setText("");

            //Toast provides feedback to the user.
            try {
                Toast
                        .makeText(this, "Your Meeting has been deleted", Toast.LENGTH_LONG)
                        .show();

            } catch (Throwable t) {
                Toast
                        .makeText(this, "Exception: " + t.toString(), Toast.LENGTH_LONG)
                        .show();
            }
        }
    }

}


