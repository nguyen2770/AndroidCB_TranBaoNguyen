package com.example.loginbasic;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.List;

public class DBManager {
    private Context context;
    private SQLiteDatabase sqLiteDatabase;
    private DBHelper dbHelper;

    public DBManager(Context context) {
        this.context = context;
    }

    public DBManager(Context context, SQLiteDatabase sqLiteDatabase, DBHelper dbHelper) {
        this.context = context;
        this.sqLiteDatabase = sqLiteDatabase;
        this.dbHelper = dbHelper;
    }

    public DBManager open(){
        dbHelper = new DBHelper(context);
        sqLiteDatabase = dbHelper.getWritableDatabase();// lấy ra đối tượng sqlitesatabase
        return this;
    }

    public void close(){
        dbHelper.close();
    }

    // ínsert note
    public void insertNote(Note note){
        ContentValues values = new ContentValues();
        values.put(dbHelper.TITLE, note.getTitle());
        values.put(dbHelper.DATE, note.getCreateDate());
        values.put(dbHelper.CONTENT, note.getContent());
        sqLiteDatabase.insert(dbHelper.TBName, null, values);
    }

    // update note
    public void updateNote(int id , String Title, String Date, String Content){
        ContentValues values = new ContentValues();
        values.put(dbHelper.TITLE, Title);
        values.put(dbHelper.DATE, Date);
        values.put(dbHelper.CONTENT, Content);
        sqLiteDatabase.update(dbHelper.TBName ,values, dbHelper.ID + "="+ id, null);
    }

    // delete note
    public void deleteNote(int id){
        sqLiteDatabase.delete(dbHelper.TBName , dbHelper.ID + "=" +id, null);
    }

    public List<Note> getAllNote(){
        List<Note> noteList = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + dbHelper.TBName, null);
        if(cursor != null){
            while (cursor.moveToNext()){
                int id = cursor.getInt(0);
                String title = cursor.getString(1);
                String date = cursor.getString(2);
                String content = cursor.getString(3);
                noteList.add(new Note(id,title,date,content));
            }
        }
        return noteList;
    }

    public Note getNoteById(int id){
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + dbHelper.TBName + " WHERE "
                + dbHelper.ID + " = " + id, null);
        if(cursor != null){
            int id1 = cursor.getInt(0);
            String title = cursor.getString(1);
            String date = cursor.getString(2);
            String content = cursor.getString(3);

            return new Note(id,title,date,content);
        }
        return null;
    }
}
