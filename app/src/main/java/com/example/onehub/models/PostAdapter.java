package com.example.onehub.models;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.onehub.R;


import java.util.List;

public class PostAdapter extends ArrayAdapter<Post> {

    Context mCtx;
    int listLayoutRes;
    List<Post> postList;

    PostDB pdb = new PostDB(getContext());
    UserDB userdb = new UserDB(this.getContext());

    private int status = 0;
    PostAdapter p ;
    String name = "";



    public PostAdapter(Context mCtx, int listLayoutRes, List<Post> postList) {
        super(mCtx, listLayoutRes, postList);

        this.mCtx = mCtx;
        this.listLayoutRes = listLayoutRes;
        this.postList = postList;
        // this.mDatabase = mDatabase;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(listLayoutRes, null);

        //getting post of the specified position
        Post post = postList.get(position);


        //getting views
        TextView textViewSubject = view.findViewById(R.id.lpSubject);
        TextView textViewMessage = view.findViewById(R.id.lpMsg);
        TextView textViewPoster = view.findViewById(R.id.lpMsg);
        TextView textViewTime = view.findViewById(R.id.lpPostTime);

        List<User> users = userdb.getAllUsers();

        for (User u : users) {

            if (u.getId() == post.getUser_id()){
                name = u.getUsername();
            }

        }


        //adding data to views
        textViewSubject.setText(post.getTitre());
        textViewMessage.setText(post.getPost_msg());
        textViewPoster.setText(name);
        textViewTime.setText(post.getDate());





        textViewTime.setText(post.getDate());



        return view;
    }





    private void reloadPostFromDatabase() {
        Cursor cursorPosts = pdb.getReadableDatabase().rawQuery("SELECT * FROM posts", null);
        if (cursorPosts.moveToFirst()) {
            postList.clear();
            do {
                postList.add(new Post(
                        cursorPosts.getInt(0),
                        cursorPosts.getInt(1),
                        cursorPosts.getString(2),
                        cursorPosts.getString(3),
                        cursorPosts.getString(4)

                ));
            } while (cursorPosts.moveToNext());
        }
        cursorPosts.close();
        //   udb.getAllPosts();
        notifyDataSetChanged();
    }










}
