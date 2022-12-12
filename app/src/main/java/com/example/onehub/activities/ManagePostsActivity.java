package com.example.onehub.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.onehub.R;
import com.example.onehub.models.Post;
import com.example.onehub.models.PostDB;

import java.sql.Timestamp;
import java.util.Date;

public class ManagePostsActivity extends AppCompatActivity {

    PostDB pdb = new PostDB(this);
    EditText PS;
    EditText PM;
    Button PADD;
    TextView TVP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_posts);

        Intent intent = getIntent();
        String userID = intent.getStringExtra("key");
        int idu = Integer.parseInt(userID);
        System.out.println(userID+"*****************************"+idu);

        PS = (EditText) findViewById(R.id.edtSubject);
        PM = (EditText) findViewById(R.id.edtMessage);
        PADD = (Button) findViewById(R.id.btnAddUPost);

        PADD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sujet = PS.getText().toString();
                String texte = PM.getText().toString();
                Date date = new Date();

                String postedat = String.valueOf(new Timestamp(date.getTime()));

                Post post = new Post(idu,sujet,texte,postedat);
                pdb.addPost(post);
                Toast.makeText(ManagePostsActivity.this, "Post added with success ! ", Toast.LENGTH_SHORT).show();



            }
        });

        TVP = (TextView) findViewById(R.id.textviewPosts);
        TVP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ManagePostsActivity.this, AdminListPosts.class);
                startActivity(i);

            }
        });





    }





}