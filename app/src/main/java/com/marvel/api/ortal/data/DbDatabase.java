package com.marvel.api.ortal.data;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DbDatabase extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "MarvelDB";

    public DbDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static void deleteDatabase(Context context) {
        context.deleteDatabase(DATABASE_NAME);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // SQL statement to create  table

        final String CREATE_CHARACTER = "CREATE TABLE " + DbContract.Tables.TABLE_CHARACTER + " (" +
                DbContract.CharacterColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                DbContract.CharacterColumns.NAME + "  TEXT DEFAULT '' ," +
                DbContract.CharacterColumns.TIME + "  TEXT DEFAULT '' ," +
                DbContract.CharacterColumns.THUMBNAIL + "  TEXT DEFAULT '' ," +
                DbContract.CharacterColumns.DESCRIPTION + "  TEXT DEFAULT '' , " +
                " UNIQUE (" + DbContract.CharacterColumns.NAME + ") ON CONFLICT REPLACE);";

        // create  table
        db.execSQL(CREATE_CHARACTER);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) {

        }
        this.onCreate(db);
    }

}