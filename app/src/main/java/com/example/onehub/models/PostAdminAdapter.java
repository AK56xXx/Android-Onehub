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


import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class PostAdminAdapter extends ArrayAdapter<Post> {

    Context mCtx;
    int listLayoutRes;
    List<Post> postList;

    PostDB pdb = new PostDB(getContext());
    UserDB userdb = new UserDB(this.getContext());

    private int status = 0;
    PostAdminAdapter p ;
    String name = "";



    public PostAdminAdapter(Context mCtx, int listLayoutRes, List<Post> postList) {
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
        TextView textViewSubject = view.findViewById(R.id.postSubject_ADMIN);
        TextView textViewMessage = view.findViewById(R.id.postMsg_ADMIN);
        TextView textViewPoster = view.findViewById(R.id.poster_ADMIN);
        TextView textViewTime = view.findViewById(R.id.postTime_ADMIN);

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


        //we will use these buttons later for update and delete operation

        Button buttonEdit = view.findViewById(R.id.btn_edit_post);
        buttonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updatePostAdapter(post);
                pdb.getAllPosts(); //refresh
            }
        });


        Button buttonDelete = view.findViewById(R.id.btn_delete_post);
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(mCtx);
                builder.setTitle("Are you sure?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        pdb.deletePost(post);

                        //refresh
                        reloadPostsFromDatabase();

                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();

            }
        });

        return view;
    }







    private void updatePostAdapter(final Post post) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(mCtx);

        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.dialogue_update_admin_post, null);
        builder.setView(view);


        final EditText editTextSubject = view.findViewById(R.id.subject_update);
        final EditText editTextPost = view.findViewById(R.id.message_update);






        editTextSubject.setText(post.getTitre());
        editTextPost.setText(post.getPost_msg());



        final AlertDialog dialog = builder.create();
        dialog.show();

        view.findViewById(R.id.btnUpdatePost).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String sujet = editTextSubject.getText().toString().trim();
                String msg = editTextPost.getText().toString().trim();







                if (sujet.isEmpty()) {
                    editTextSubject.setError("Subject can't be blank");
                    editTextSubject.requestFocus();
                    return;
                }

                if (msg.isEmpty()) {
                    editTextPost.setError("Message can't be blank");
                    editTextPost.requestFocus();
                    return;
                }


                Date date = new Date();

                String postedat = String.valueOf(new Timestamp(date.getTime()));


                post.setTitre(sujet);post.setPost_msg(msg);post.setDate(postedat);
                pdb.updatePost(post);


                Toast.makeText(mCtx, "Post Updated", Toast.LENGTH_SHORT).show();
                // reloadPostsFromDatabase();


                dialog.dismiss();
            }
        });


    }

    //***

    private void reloadPostsFromDatabase() {
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
