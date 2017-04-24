package com.example.jdurc.testapp;

/*
    Created by jdurc on 13/04/2017.
    The purpose of this class is to take the users input(Notes) and store them in a file on your device.
    This class is linked to the take_notes layout, where a editText field and a Save Button are used.
 */

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import static android.content.ContentValues.TAG;


public class TakeNotes extends Activity implements View.OnClickListener {

    String content = "";
    File file;
    FileOutputStream outputStream;
    EditText tv;
    Button btnSave;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.take_notes);
        tv = (EditText) findViewById(R.id.txtInput);
        btnSave = (Button) findViewById(R.id.btnSave);
        btnSave.setOnClickListener(this);

    }

    //Save notes from the editText field to a file when the Save button is clicked.
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSave:
                if (v == findViewById(R.id.btnSave)) {
                    content = tv.getText().toString();
                    try {
                        file = new File(Environment.getExternalStorageDirectory(), "notes.txt");
                        outputStream = new FileOutputStream(file);
                        outputStream.write(content.getBytes());
                        outputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    //Toast provides feedback to the user. i.e Telling them either their notes have been saved or there has been an error,etc.
                    try {
                        Toast
                                .makeText(this, "Your notes have been saved", Toast.LENGTH_LONG)
                                .show();

                    } catch (Throwable t) {
                        Toast
                                .makeText(this, "Exception: " + t.toString(), Toast.LENGTH_LONG)
                                .show();
                    }
                    //Help found on : https://code.tutsplus.com/tutorials/quick-tip-enabling-users-to-send-email-from-your-android-applications-the-easy-way--mobile-1686
                    //Opens Email App to send users notes
                    Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
                    emailIntent.setType("plain/text");
                    emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, content);
                    startActivity(emailIntent);
                    startActivity(Intent.createChooser(emailIntent, "Send your email in:"));


                }
        }

    }
}