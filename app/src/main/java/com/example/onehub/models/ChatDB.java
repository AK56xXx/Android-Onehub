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
    private static final String REQUETE_CREATION_BD = "create table "+ TABLE_CHAT + " ( " + ID + " integer primary key autoincrement, " + ID_USER + " integer not null , " + MESSAGE + " TEXT ," + POSTED_AT + " TEXT , foreign key (id_user) references id (user) );";




    public ChatDB(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public ChatDB(@Nullable Context context) {
        super(context, DATABASE_NAME,null,DATABASE_VERSION );
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
    //    db.execSQL(REQUETE_CREATION_BD);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        // Drop older table if existed
    //    db.execSQL(" DROP TABLE IF EXISTS " + TABLE_CHAT + ";");
        // Create tables again
    //    onCreate(db);

    }
}
