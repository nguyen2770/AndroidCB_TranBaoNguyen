package com.example.loginbasic;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    // khai báo các tham số hằng của database
    public static final String DBNAME = "note.db";
    public static final int DBVersion = 1;
    // khai báo các tham số hằng của table
    public static final String TBName = "tblNote";
    public static final String ID = "id";
    public static final String TITLE = "title";
    public static final String DATE = "date";
    public static final String CONTENT = "content";
    public DBHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

   public DBHelper(Context context){
        super(context,DBNAME,null,DBVersion);
   }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE " + TBName + "( " +
                ID + " Integer primary key autoincrement," +
                TITLE + " Text, " + DATE + " Text, " +
                CONTENT + " Text);";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TBName);
    }
}
