package com.example.onehub.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class UserDB extends SQLiteOpenHelper {

    // All Static variables
// Database Version
    private static final int DATABASE_VERSION = 3;
    private static final String DATABASE_NAME = "onehub";
    private static final String TABLE_USERS = "users";
    private static final String ID = "id";
    private static final String FIRST_NAME = "first_name";
    private static final String LAST_NAME = "last_name";
    private static final String USERNAME = "username";
    private static final String PASSWORD= "password";
    private static final String EMAIL = "email";
    private static final String IMAGE = "image";
    private static final String POSITION = "position";
    private static final String ROLE = "role";
    private static final String BLOCKED = "blocked";
    private static final String ATTEMPTS = "attempts";
    private static final String REQUETE_CREATION_BD = "create table "+ TABLE_USERS + " (" + ID + " integer primary key autoincrement, " + FIRST_NAME + " TEXT , " + LAST_NAME + " TEXT , " + USERNAME  + " TEXT not null unique, " + PASSWORD + " TEXT not null, " + EMAIL + " TEXT , "+ IMAGE + " TEXT , " + POSITION + " TEXT , " + ROLE + " TEXT , " + BLOCKED + " INTEGER NOT NULL DEFAULT 0 , " + ATTEMPTS + " INTEGER DEFAULT 5 ); " ;

    public UserDB(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public UserDB(@Nullable Context context) {
        super(context, DATABASE_NAME,null,DATABASE_VERSION );
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(REQUETE_CREATION_BD);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        // Drop older table if existed
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_USERS + ";");
        // Create tables again
        onCreate(db);
    }

    public void addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

            values.put(FIRST_NAME, user.getFirst_name());
            values.put(LAST_NAME, user.getLast_name());
            values.put(USERNAME, user.getUsername());
            values.put(PASSWORD, user.getPassword());
            values.put(EMAIL, user.getEmail());
            values.put(IMAGE, user.getPic());
            values.put(POSITION, user.getPosition());
            values.put(ROLE, user.getRole());
            values.put(BLOCKED, user.getBlocked());
            values.put(ATTEMPTS, user.getAttempts());

            // Inserting Row
            db.insert(TABLE_USERS, null, values);
            //2nd argument is String containing nullColumnHack


        db.close(); // Closing database connection
    }

    public int updateUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(FIRST_NAME, user.getFirst_name());
        values.put(LAST_NAME, user.getLast_name());
        values.put(USERNAME, user.getUsername());
        values.put(PASSWORD, user.getPassword());
        values.put(EMAIL, user.getEmail());
        values.put(IMAGE, user.getPic());
        values.put(POSITION, user.getPosition());
        values.put(ROLE, user.getRole());
        values.put(BLOCKED, user.getBlocked());
        values.put(ATTEMPTS, user.getAttempts());

        // updating row
        return db.update(TABLE_USERS, values, ID + " = ?",
                new String[] { String.valueOf(user.getId()) });
    }




    public void deleteUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_USERS, ID + " = ?",
                new String[] { String.valueOf(user.getId()) });
        db.close();
    }

    public void deleteUserById (int id) {

        String query = "Select*FROM " + TABLE_USERS + " WHERE " + ID + " = '" + String.valueOf(id) + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        User user = new User();
        if (cursor.moveToFirst()) {
            user.setId(Integer.parseInt(cursor.getString(0)));
            db.delete(TABLE_USERS, ID + "=?",
                    new String[] {
                            String.valueOf(user.getId())
                    });
            cursor.close();

        }
        db.close();

    }

    public User getUser(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_USERS, new String[] { ID, FIRST_NAME , LAST_NAME , USERNAME, EMAIL , IMAGE , POSITION , ROLE , BLOCKED , ATTEMPTS   }, ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        User user = new User(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2), cursor.getString(3),cursor.getString(4),cursor.getString(5), cursor.getString(6), cursor.getString(7), cursor.getString(8), Integer.parseInt(cursor.getString(9)), Integer.parseInt(cursor.getString(10)) );
        // return contact
        return user;
    }

    public Boolean CheckUserExit(String username)
    {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_USERS, new String[] { USERNAME }, USERNAME + "=?",
                new String[] { username }, null, null, null, null);
        if (cursor.getCount()>0) {

            return true;
        }
        else {
            return false;
        }

    }



    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<User>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_USERS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                User user = new User();

                user.setId(Integer.parseInt(cursor.getString(0)));
                user.setFirst_name(cursor.getString(1));
                user.setLast_name(cursor.getString(2));
                user.setUsername(cursor.getString(3));
                user.setPassword(cursor.getString(4));
                user.setEmail(cursor.getString(5));
                user.setPic(cursor.getString(6));
                user.setPosition(cursor.getString(7));
                user.setRole(cursor.getString(8));
                user.setBlocked(Integer.parseInt(cursor.getString(9)));
                user.setAttempts(Integer.parseInt(cursor.getString(10)));

                // Adding user to list
                userList.add(user);
            } while (cursor.moveToNext());
        }

        // return contact list
        return userList;
    }

    // Getting contacts Count
    public int getUsersCount() {
        String countQuery = "SELECT  * FROM " + TABLE_USERS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }












}
