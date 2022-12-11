package com.example.onehub.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.onehub.R;
import com.example.onehub.models.User;
import com.example.onehub.models.UserDB;

public class ManageUsersActivity extends AppCompatActivity {

    EditText FN;
    EditText LN;
    EditText UN;
    EditText PWD;
    Spinner SP;
    Button addUser;
    UserDB udb = new UserDB(this);
    TextView TVL;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_users);

        FN = (EditText) findViewById(R.id.edtFN);
        LN = (EditText) findViewById(R.id.edtLN);
        UN = (EditText) findViewById(R.id.edtUN);
        PWD = (EditText) findViewById(R.id.edtPWD);
        SP = (Spinner) findViewById(R.id.rolesSpinner);

        addUser=(Button)findViewById(R.id.btnAddUser);
        addUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String first_name = FN.getText().toString();
                String last_name = LN.getText().toString();
                String username = UN.getText().toString();
                String password = PWD.getText().toString();
                String role = SP.getSelectedItem().toString().toLowerCase();

                User user = new User(first_name,last_name,username,password,role);
                if(udb.CheckUserExit(username)==false)
                {
                    udb.addUser(user);
                    Toast.makeText(ManageUsersActivity.this, "User added with success ! ", Toast.LENGTH_SHORT).show();

                }
                else if(udb.CheckUserExit(username)==true)
                {
                    Toast.makeText(ManageUsersActivity.this, "User already exists ! ", Toast.LENGTH_SHORT).show();
                }

            }



        });

        TVL = (TextView) findViewById(R.id.tvUsers);
        TVL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ManageUsersActivity.this, ListUsersActivity.class);
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
                //finish all previous activities and return to login activity
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                finishAffinity(); //(*)
                startActivity(i);


                break;

        }
        return super.onOptionsItemSelected(item);

    }


}