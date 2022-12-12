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

public class PostDB extends SQLiteOpenHelper{


    private static final int DATABASE_VERSION = 3;
    private static final String DATABASE_NAME = "onehub";
    private static final String TABLE_POST = "posts";
    private static final String ID = "id";
    private static final String SUBJECT = "sujet";
    private static final String MESSAGE = "post_msg";
    private static final String ID_USER = "id_user";
    private static final String POSTED_AT = "posted_at";
    private static final String REQUETE_CREATION_BD = "create table "+ TABLE_POST + " ( " + ID + " integer primary key autoincrement, " + SUBJECT + " TEXT ,"+ MESSAGE + " TEXT ," + ID_USER + " integer , "+ POSTED_AT + " TEXT , foreign key (id_user) references id (user) );";




    public PostDB(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public PostDB(@Nullable Context context) {
        super(context, DATABASE_NAME,null,DATABASE_VERSION );
    }


    @Override
    public void onCreate(@NonNull SQLiteDatabase db) {
        db.execSQL(REQUETE_CREATION_BD);
    }

    @Override
    public void onUpgrade(@NonNull SQLiteDatabase db, int i, int i1) {
        // Drop older table if existed
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_POST + ";");
        // Create tables again
        onCreate(db);

    }



    public void addPost(Post post) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(ID_USER, post.getUser_id());
        values.put(SUBJECT, post.getTitre());
        values.put(MESSAGE, post.getPost_msg());
        values.put(POSTED_AT,post.getDate());



        // Inserting Row
        db.insert(TABLE_POST, null, values);
        //2nd argument is String containing nullColumnHack


        db.close(); // Closing database connection
    }

    public int updatePost(Post post) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(ID_USER, post.getUser_id());
        values.put(SUBJECT, post.getTitre());
        values.put(MESSAGE, post.getPost_msg());
        values.put(POSTED_AT,post.getDate());

        // updating row
        return db.update(TABLE_POST, values, ID + " = ?",
                new String[] { String.valueOf(post.getId()) });
    }




    public void deletePost(Post post) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_POST, ID + " = ?",
                new String[] { String.valueOf(post.getId()) });
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

    public List<Post> getAllPosts() {
        List<Post> postList = new ArrayList<Post>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_POST;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Post post = new Post();

                post.setId(Integer.parseInt(cursor.getString(0)));
                post.setTitre(cursor.getString(1));
                post.setPost_msg(cursor.getString(2));
                post.setUser_id(Integer.parseInt(cursor.getString(3)));
                post.setDate(cursor.getString(4));



                // Adding user to list
                postList.add(post);
            } while (cursor.moveToNext());
        }

        // return post list
        return postList;
    }















}
