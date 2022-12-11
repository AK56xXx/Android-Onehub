package com.example.onehub.activities;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.onehub.R;

public class AdminPanel extends AppCompatActivity {

    TextView idx ;
    Button btnMngUsers;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_panel);

        idx= (TextView) findViewById(R.id.idadmin);

        // get intent data
        Intent intent = getIntent();
        String adminID = intent.getStringExtra("key");
        idx.setText("Admin ID: "+adminID);
        //

        btnMngUsers=(Button) findViewById(R.id.mngUsers);
        btnMngUsers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(AdminPanel.this, ManageUsersActivity.class);
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
                finish();
                Intent i = new Intent(AdminPanel.this, MainActivity.class);
                startActivity(i);

                break;

        }
        return super.onOptionsItemSelected(item);

    }








}