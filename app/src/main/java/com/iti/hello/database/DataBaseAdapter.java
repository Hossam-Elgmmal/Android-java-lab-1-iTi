package com.iti.hello.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseAdapter {
    static DataBaseHelper dbHelper;

    public DataBaseAdapter(Context context) {
        dbHelper = new DataBaseHelper(context);
    }

    public long insertUser(User user) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DataBaseHelper.UID, user.getId());
        contentValues.put(DataBaseHelper.USERNAME, user.getUsername());
        contentValues.put(DataBaseHelper.PHONE, user.getPhone());

        return db.insertWithOnConflict(DataBaseHelper.USER_TABLE_NAME, null, contentValues, SQLiteDatabase.CONFLICT_REPLACE);
    }

    public User[] getAllUsers() {
        User[] Users = null;
        int i = 0;
        Cursor c;
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] columns = {DataBaseHelper.UID,
                DataBaseHelper.USERNAME,
                DataBaseHelper.PHONE,};

        c = db.query(DataBaseHelper.USER_TABLE_NAME, columns, null, null, null, null, null);
        Users = new User[c.getCount()];
        while (c.moveToNext()) {
            User User = new User();
            User.setId(c.getInt(0));
            User.setUsername(c.getString(1));
            User.setPhone(c.getString(2));
            Users[i++] = User;
        }
        c.close();
        return Users;
    }

    public void deleteAllUsers() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.execSQL("delete from " + DataBaseHelper.USER_TABLE_NAME);
    }

    static class DataBaseHelper extends SQLiteOpenHelper {
        private static final int DATABASE_VERSION = 1;
        private static final String DATABASE_NAME = "user.db";

        private static final String USER_TABLE_NAME = "USER";
        private static final String UID = "_id";
        private static final String USERNAME = "username";
        private static final String PHONE = "phone";

        private static final String CREATE_USER_TABLE =
                "CREATE TABLE " + USER_TABLE_NAME + " (" + UID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        PHONE + " TEXT, " + USERNAME + " TEXT);";


        public DataBaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_USER_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // TODO Auto-generated method stub

        }
    }
}
