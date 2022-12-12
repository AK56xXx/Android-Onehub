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

public class ChatDB extends SQLiteOpenHelper{


    private static final int DATABASE_VERSION = 3;
    private static final String DATABASE_NAME = "onehub";
    private static final String TABLE_CHAT = "chat";
    private static final String ID = "id";
    private static final String ID_USER = "id_user";
    private static final String MESSAGE = "msg";
    private static final String POSTED_AT = "posted_at";
    private static final String REQUETE_CREATION_BD = "create table "+ TABLE_CHAT + " ( " + ID + " integer primary key autoincrement, " + ID_USER + " integer , " + MESSAGE + " TEXT ," + POSTED_AT + " TEXT , foreign key (id_user) references id (user) );";




    public ChatDB(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public ChatDB(@Nullable Context context) {
        super(context, DATABASE_NAME,null,DATABASE_VERSION );
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(REQUETE_CREATION_BD);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        // Drop older table if existed
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_CHAT + ";");
        // Create tables again
        onCreate(db);

    }



    public void addMessage(Chat chat) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(ID_USER, chat.getId_user());
        values.put(MESSAGE, chat.getMsg());
        values.put(POSTED_AT, chat.getDate());



        // Inserting Row
        db.insert(TABLE_CHAT, null, values);
        //2nd argument is String containing nullColumnHack


        db.close(); // Closing database connection
    }

    public int updateMessage(Chat chat) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ID_USER, chat.getId_user());
        values.put(MESSAGE, chat.getMsg());
        values.put(POSTED_AT, chat.getDate());

        // updating row
        return db.update(TABLE_CHAT, values, ID + " = ?",
                new String[] { String.valueOf(chat.getId()) });
    }




    public void deleteMessage(Chat chat) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CHAT, ID + " = ?",
                new String[] { String.valueOf(chat.getId()) });
        db.close();
    }

  /*  public void deleteUserById (int id) {

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

    }*/

    public List<Chat> getAllChat() {
        List<Chat> chatList = new ArrayList<Chat>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_CHAT;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Chat chat = new Chat();

                chat.setId(Integer.parseInt(cursor.getString(0)));
                chat.setId_user(Integer.parseInt(cursor.getString(1)));
                chat.setMsg(cursor.getString(2));
                chat.setDate(cursor.getString(3));



                // Adding user to list
                chatList.add(chat);
            } while (cursor.moveToNext());
        }

        // return contact list
        return chatList;
    }















}
