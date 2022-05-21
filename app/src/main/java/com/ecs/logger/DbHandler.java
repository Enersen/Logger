package com.ecs.logger;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;


public class DbHandler extends SQLiteOpenHelper {

    public DbHandler(Context context) {
        super(context, Constants.DATABASE_NAME, null, Constants.DATABASE_VERSION);
    }

    public void cleanDatabase() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + Constants.TABLE_NAME_SUBJECTS);
        db.execSQL("DROP TABLE IF EXISTS " + Constants.TABLE_NAME_LOGITEMS);
        onCreate(db);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "";

        CREATE_TABLE = "CREATE TABLE " + Constants.TABLE_NAME_SUBJECTS + "("
                + Constants.FIELD_NAME_SUBJECTS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + Constants.FIELD_NAME_SUBJECTS_NAME + " TEXT UNIQUE,"
                + Constants.FIELD_NAME_SUBJECTS_COMMENT + " TEXT,"
                + Constants.FIELD_NAME_SUBJECTS_DATE_ADDED + " TEXT,"
                + Constants.FIELD_NAME_SUBJECTS_DATE_ACCESSED + " TEXT"+ ")";
        db.execSQL(CREATE_TABLE);
        CREATE_TABLE = "CREATE TABLE " + Constants.TABLE_NAME_LOGITEMS + "("
                + Constants.FIELD_NAME_LOGITEMS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + Constants.FIELD_NAME_LOGITEMS_SUBJECT + " INTEGER,"
                + Constants.FIELD_NAME_LOGITEMS_COMMENT + " TEXT,"
                + Constants.FIELD_NAME_LOGITEMS_DATE_ADDED + " TEXT,"
                + Constants.FIELD_NAME_LOGITEMS_DATE_HAPPENED + " TEXT"+ ")";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Constants.TABLE_NAME_SUBJECTS);
        db.execSQL("DROP TABLE IF EXISTS " + Constants.TABLE_NAME_LOGITEMS);
        onCreate(db);
    }

    public void enterSomeSubjectData() {
        insertNewSubjectDetails("Subject 01", "Comment 1", makeDateString(),
                makeDateString());
        insertNewSubjectDetails("Subject 02", "Comment 2", makeDateString(),
                makeDateString());
        insertNewSubjectDetails("Subject 03", "Comment 3", makeDateString(),
                makeDateString());
        insertNewSubjectDetails("Subject 04", "Comment 4", makeDateString(),
                makeDateString());
        insertNewSubjectDetails("Subject 05", "Comment 5", makeDateString(),
                makeDateString());
        insertNewSubjectDetails("Subject 06", "Comment 6", makeDateString(),
                makeDateString());
        insertNewSubjectDetails("Subject 07", "Comment 7", makeDateString(),
                makeDateString());
        insertNewSubjectDetails("Subject 08", "Comment 8", makeDateString(),
                makeDateString());
        insertNewSubjectDetails("Subject 09", "Comment 9", makeDateString(),
                makeDateString());
        insertNewSubjectDetails("Subject 10", "Comment 10", makeDateString(),
                makeDateString());
        insertNewSubjectDetails("Subject 11", "Comment 11", makeDateString(),
                makeDateString());
        insertNewSubjectDetails("Subject 12", "Comment 12", makeDateString(),
                makeDateString());
        insertNewSubjectDetails("Subject 13", "Comment 13", makeDateString(),
                makeDateString());
        insertNewSubjectDetails("Subject 14", "Comment 14", makeDateString(),
                makeDateString());
        insertNewSubjectDetails("Subject 15", "Comment 15", makeDateString(),
                makeDateString());
        insertNewSubjectDetails("Subject 16", "Comment 16", makeDateString(),
                makeDateString());
        insertNewSubjectDetails("Subject 17", "Comment 17", makeDateString(),
                makeDateString());
        insertNewSubjectDetails("Subject 18", "Comment 18", makeDateString(),
                makeDateString());
        insertNewSubjectDetails("Subject 19", "Comment 19", makeDateString(),
                makeDateString());
        insertNewSubjectDetails("Subject 20", "Comment 20", makeDateString(),
                makeDateString());
    }

    // Adding new Subject Details
    public void insertNewSubjectDetails(String name, String comment, String date_added, String date_accessed){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cValues = new ContentValues();
        cValues.put(Constants.FIELD_NAME_SUBJECTS_NAME, name);
        cValues.put(Constants.FIELD_NAME_SUBJECTS_COMMENT, comment);
        cValues.put(Constants.FIELD_NAME_SUBJECTS_DATE_ADDED, date_added);
        cValues.put(Constants.FIELD_NAME_SUBJECTS_DATE_ACCESSED, date_accessed);
        long newRowId = db.insert(Constants.TABLE_NAME_SUBJECTS,null, cValues);
        db.close();
    }

    // Adding new Subject Details
    public void insertNewLogitemDetails(long subject, String comment, String date_added, String date_happened){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cValues = new ContentValues();
        cValues.put(Constants.FIELD_NAME_LOGITEMS_SUBJECT, subject);
        cValues.put(Constants.FIELD_NAME_LOGITEMS_COMMENT, comment);
        cValues.put(Constants.FIELD_NAME_LOGITEMS_DATE_ADDED, date_added);
        cValues.put(Constants.FIELD_NAME_LOGITEMS_DATE_HAPPENED, date_happened);
        long newRowId = db.insert(Constants.TABLE_NAME_LOGITEMS,null, cValues);
        db.close();
    }

    // Get Subjects
    public ArrayList<HashMap<String, String>> getSubjects() {
        // Log.e(Constants.LOG_TAG, "in getSubjects()");
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<HashMap<String, String>> userList = new ArrayList<>();
        String query = "SELECT id, name, comment, date_added, date_accessed FROM " + Constants.TABLE_NAME_SUBJECTS
                + " ORDER BY date_accessed, name";
        Cursor cursor = db.rawQuery(query,null);
        while (cursor.moveToNext()){
            Log.e(Constants.LOG_TAG, "in while moveToNext");
            HashMap<String,String> subject = new HashMap<>();
            subject.put("id", cursor.getString(cursor.getColumnIndex(Constants.FIELD_NAME_SUBJECTS_ID)));
            subject.put("name", cursor.getString(cursor.getColumnIndex(Constants.FIELD_NAME_SUBJECTS_NAME)));
            subject.put("comment", cursor.getString(cursor.getColumnIndex(Constants.FIELD_NAME_SUBJECTS_COMMENT)));
            subject.put("date_added", stringToDate(cursor.getString(cursor.getColumnIndex(Constants.FIELD_NAME_SUBJECTS_DATE_ADDED))));
            subject.put("date_accessed", stringToDate(cursor.getString(cursor.getColumnIndex(Constants.FIELD_NAME_SUBJECTS_DATE_ACCESSED))));
            userList.add(subject);
        }
        return  userList;
    }

    // Get Logitems
    public ArrayList<HashMap<String, String>> getLogitems(long id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<HashMap<String, String>> logitemsList = new ArrayList<>();
        String query = "SELECT id, subject, comment, date_added, date_happened FROM " + Constants.TABLE_NAME_LOGITEMS
                + " WHERE subject = " + id
                + " ORDER BY date_happened";
        Cursor cursor = db.rawQuery(query,null);
        while (cursor.moveToNext()){
            HashMap<String,String> logitem = new HashMap<>();
            logitem.put("id", cursor.getString(cursor.getColumnIndex(Constants.FIELD_NAME_LOGITEMS_ID)));
            logitem.put("subject", cursor.getString(cursor.getColumnIndex(Constants.FIELD_NAME_LOGITEMS_SUBJECT)));
            logitem.put("comment", cursor.getString(cursor.getColumnIndex(Constants.FIELD_NAME_LOGITEMS_COMMENT)));
            logitem.put("date_added", stringToDate(cursor.getString(cursor.getColumnIndex(Constants.FIELD_NAME_LOGITEMS_DATE_ADDED))));
            logitem.put("date_happened", stringToDate(cursor.getString(cursor.getColumnIndex(Constants.FIELD_NAME_LOGITEMS_DATE_HAPPENED))));
            logitemsList.add(logitem);
        }
        return  logitemsList;
    }

    // Get Subject Details based on subjectId
    public ArrayList<HashMap<String, String>> getSubjectBySubjectId(int subjectId){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<HashMap<String, String>> subjectList = new ArrayList<>();
        String query = "SELECT name, comment, date_added, date_accessed FROM " + Constants.TABLE_NAME_SUBJECTS;
        Cursor cursor = db.query(Constants.TABLE_NAME_SUBJECTS,
                new String[]{Constants.FIELD_NAME_SUBJECTS_NAME, Constants.FIELD_NAME_SUBJECTS_COMMENT, Constants.FIELD_NAME_SUBJECTS_DATE_ADDED, Constants.FIELD_NAME_SUBJECTS_DATE_ACCESSED}, Constants.FIELD_NAME_SUBJECTS_ID + "=?",
                new String[]{String.valueOf(subjectId)},null, null, null, null);
        if (cursor.moveToNext()){
            HashMap<String,String> subject = new HashMap<>();
            subject.put("id", cursor.getString(cursor.getColumnIndex(Constants.FIELD_NAME_SUBJECTS_ID)));
            subject.put("name", cursor.getString(cursor.getColumnIndex(Constants.FIELD_NAME_SUBJECTS_NAME)));
            subject.put("comment", cursor.getString(cursor.getColumnIndex(Constants.FIELD_NAME_SUBJECTS_COMMENT)));
            subject.put("date_added", cursor.getString(cursor.getColumnIndex(Constants.FIELD_NAME_SUBJECTS_DATE_ADDED)));
            subject.put("date_accessed", cursor.getString(cursor.getColumnIndex(Constants.FIELD_NAME_SUBJECTS_DATE_ACCESSED)));
            subjectList.add(subject);
        }
        return  subjectList;
    }

    // Delete User Details
    public void deleteUser(int userid){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Constants.TABLE_NAME_SUBJECTS, Constants.FIELD_NAME_SUBJECTS_ID + " = ?",
                new String[]{String.valueOf(userid)});
        db.close();
    }

    // Delete Logitem Details
    public void deleteLogitem(int logitemid){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Constants.TABLE_NAME_LOGITEMS, Constants.FIELD_NAME_LOGITEMS_ID + " = ?",
                new String[]{String.valueOf(logitemid)});
        db.close();
    }

    // Update Subject Details
    public int updateSubjectDetails(String name, String comment, String date_added, String date_accessed, int id){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cVals = new ContentValues();
        cVals.put(Constants.FIELD_NAME_SUBJECTS_NAME, name);
        cVals.put(Constants.FIELD_NAME_SUBJECTS_COMMENT, comment);
        cVals.put(Constants.FIELD_NAME_SUBJECTS_DATE_ADDED, date_added);
        cVals.put(Constants.FIELD_NAME_SUBJECTS_DATE_ACCESSED, makeDateString());
        int count = db.update(Constants.TABLE_NAME_SUBJECTS, cVals, Constants.FIELD_NAME_SUBJECTS_ID +
                " = ?",new String[]{String.valueOf(id)});
        return  count;
    }

    public String makeDateString() {
        long ms = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy:MM:dd:HH:mm:ss:SSS");
        Date resultdate = new Date(ms);
        return sdf.format(resultdate);
    }

    public String stringToDate(String d) {
        String s[] = d.split(":");
        return s[2] + "." + s[1] + "." + s[0] + " " + s[3] + ":" + s[4];
    }

}
