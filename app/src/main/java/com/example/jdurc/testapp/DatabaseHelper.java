package com.example.jdurc.testapp;

/*
    Created by jdurc on 10/04/2017.
    Database Helper class used to create , upgrade database , create table and perform C.R.U.D. operations
 */

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Database Name
    public static String DATABASE_NAME = "meeting_schedule_database";

    // Current version of database
    private static final int DATABASE_VERSION = 1;

    // Name of table
    private static final String TABLE_SCHEDULE = "meeting_schedule";

    // All Keys used in table
    private static final String KEY_ID = "id";
    private static final String KEY_TITLE = "title";
    private static final String KEY_DATE = "date";
    private static final String KEY_TIME = "time";
    private static final String KEY_LOCATION = "location";

    public static String TAG = "tag";

    // Schedule Table
    private static final String CREATE_TABLE_SCHEDULE = "CREATE TABLE "
            + TABLE_SCHEDULE + "(" + KEY_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_TITLE + " TEXT," + KEY_DATE + " TEXT," + KEY_TIME + " TEXT,"
            + KEY_LOCATION + " TEXT );";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //called if the database is accessed but not yet created.
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_TABLE_SCHEDULE); // create schedule table

    }
    //called when any modifications in database are done
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + CREATE_TABLE_SCHEDULE); // drop table if exists

        onCreate(db);
    }

    //method is used to add meeting details in schedule Table
    public long addMeetingDetail(MeetingModel meeting) {
        SQLiteDatabase db = this.getWritableDatabase();

        // Creating content values
        ContentValues values = new ContentValues();
        values.put(KEY_TITLE, meeting.title);
        values.put(KEY_DATE, meeting.date);
        values.put(KEY_TIME, meeting.time);
        values.put(KEY_LOCATION, meeting.location);

        // insert row in schedule table

        long insert = db.insert(TABLE_SCHEDULE, null, values);

        return insert;
    }

    //method is used to update particular meeting entry
    public int updateEntry(MeetingModel meeting) {
        SQLiteDatabase db = this.getWritableDatabase();

        // Creating content values
        ContentValues values = new ContentValues();
        values.put(KEY_TITLE, meeting.title);
        values.put(KEY_DATE, meeting.date);
        values.put(KEY_TIME, meeting.time);
        values.put(KEY_LOCATION, meeting.location);

        return db.update(TABLE_SCHEDULE, values, KEY_ID + " = ?",
                new String[] { String.valueOf(meeting.id) });
    }

    //Used to delete particular meeting entry
    public void deleteEntry(long id) {

        // delete row in schedule table based on id
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_SCHEDULE, KEY_ID + " = ?",
                new String[] { String.valueOf(id) });
    }

   // Used to get particular meeting details
    public MeetingModel getMeeting(long id) {
        SQLiteDatabase db = this.getReadableDatabase();

        // SELECT * FROM schedule WHERE id = ?;
        String selectQuery = "SELECT  * FROM " + TABLE_SCHEDULE + " WHERE "
                + KEY_ID + " = " + id;
        Log.d(TAG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();

        MeetingModel meeting = new MeetingModel();
        meeting.id = c.getInt(c.getColumnIndex(KEY_ID));
        meeting.title = c.getString(c.getColumnIndex(KEY_TITLE));
        meeting.date = c.getString(c.getColumnIndex(KEY_DATE));
        meeting.time = c.getString(c.getColumnIndex(KEY_TIME));
        meeting.location = c.getString(c.getColumnIndex(KEY_LOCATION));

        return meeting;
    }


     //Used to get detail of entire database and save in array list of data type MeetingModel

    public List<MeetingModel> getAllMeetingsList() {
        List<MeetingModel> MeetingsArrayList = new ArrayList<MeetingModel>();

        String selectQuery = "SELECT  * FROM " + TABLE_SCHEDULE;
        Log.d(TAG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {

                MeetingModel meeting = new MeetingModel();
                meeting.id = c.getInt(c.getColumnIndex(KEY_ID));
                meeting.title = c.getString(c.getColumnIndex(KEY_TITLE));
                meeting.date = c.getString(c.getColumnIndex(KEY_DATE));
                meeting.time = c.getString(c.getColumnIndex(KEY_TIME));
                meeting.location = c.getString(c.getColumnIndex(KEY_LOCATION));

                // adding to Meeting list
                MeetingsArrayList.add(meeting);
            } while (c.moveToNext());
        }

        return MeetingsArrayList;
    }
}
