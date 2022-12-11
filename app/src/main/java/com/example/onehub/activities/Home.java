package com.example.onehub.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.onehub.R;

public class Home extends AppCompatActivity {

    TextView idx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        idx = (TextView) findViewById(R.id.iduser);

        Intent intent = getIntent();
        String userID = intent.getStringExtra("key");
        idx.setText("User ID: "+userID);



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