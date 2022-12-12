package com.example.onehub.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.onehub.R;

public class Home extends AppCompatActivity {

    TextView idx;
    ImageButton profile;
    ImageButton home_posts;
    ImageButton chat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        idx = (TextView) findViewById(R.id.iduser);

        Intent intent = getIntent();
        String userID = intent.getStringExtra("key");
        idx.setText("User ID: "+userID);


        //get pics ID's
        profile = (ImageButton) findViewById(R.id.profilePic);
        home_posts = (ImageButton) findViewById(R.id.homePic);
        chat = (ImageButton) findViewById(R.id.chatPic);

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Home.this,ProfileActivity.class);
                i.putExtra("key",userID);
                startActivity(i);
            }
        });

        home_posts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Home.this,Home_posts.class);
                startActivity(i);
            }
        });

        chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Home.this,ChatActivity.class);
                startActivity(i);
            }
        });





    }





    public boolean onCreateOptionsMenu(Menu menu)
    {
        menu.add(0,1,0,"Logout");
        return super.onCreateOptionsMenu(menu);
    }


    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 1:

                Intent i = new Intent(Home.this, MainActivity.class);
                finishAffinity();
                startActivity(i);

                break;

        }
        return super.onOptionsItemSelected(item);

    }

}