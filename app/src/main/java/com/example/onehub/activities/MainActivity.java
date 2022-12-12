package com.example.onehub.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.onehub.R;
import com.example.onehub.models.Chat;
import com.example.onehub.models.ChatDB;
import com.example.onehub.models.PostDB;
import com.example.onehub.models.User;
import com.example.onehub.models.UserDB;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    UserDB db = new UserDB(this);
    ChatDB cdb = new ChatDB(this);
    PostDB pdb = new PostDB(this);
    EditText tusername;
    EditText tpass;
    Button connect;
    int count ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // create Admin if not exist
        if(db.CheckUserExit("admin") == false)
        {
            db.addUser(new User("admin","admin","admin"));

        }



         tusername = (EditText) findViewById(R.id.tuser);
         tpass = (EditText) findViewById(R.id.tpass);
         connect = (Button) findViewById(R.id.connect);

         connect.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {

                String username = tusername.getText().toString();
                String password = tpass.getText().toString();

                loginCheckUser(username,password);


             }
         });

    }




    public void loginCheckUser(String username,String password)
    {

         username = tusername.getText().toString();
         password = tpass.getText().toString();

        List<User> users = db.getAllUsers();

        for (User u : users) {

            if(u.getUsername().matches(username) && u.getPassword().matches(password) && u.getRole().toLowerCase().matches("admin"))
            {
                Intent i = new Intent(MainActivity.this, AdminPanel.class);
                String admin_id = String.valueOf(u.getId());
                i.putExtra("key",admin_id);
                startActivity(i);

                finish();
            }
            else if(u.getUsername().matches(username) && u.getPassword().matches(password) && u.getRole().toLowerCase().matches("employee") && u.getAttempts()!=5)
            {
                Intent i = new Intent(MainActivity.this, Home.class);
                String emp_id = String.valueOf(u.getId());
                i.putExtra("key",emp_id);
                startActivity(i);

                finish();
            }
            else
            {
                attemptsFails(username,password);
            }

        }

    }




    // 0 unblocked / 1 blocked

    public void attemptsFails(String username, String password)
    {
         username = tusername.getText().toString();
         password = tpass.getText().toString();

        List<User> users = db.getAllUsers();

        for (User u : users) {

            if(u.getUsername().matches(username) && !u.getPassword().matches(password) && u.getRole().toLowerCase().matches("employee") && u.getBlocked()==0)
            {
                Toast.makeText((Context) MainActivity.this, "LOGIN DENIED!     Attempts: " +count, Toast.LENGTH_SHORT).show();
                count = u.getAttempts();
                count++;
                u.setAttempts(count);
                db.updateUser(u);




                System.out.println(">>>>>>>>>>>>"+count);

                if(count==5)
                {
                    connect.setEnabled(false);
                    u.setBlocked(1);
                    db.updateUser(u);
                }


            }
            else if (u.getUsername().matches(username) && u.getRole().toLowerCase().matches("employee") && u.getBlocked()==1)
            {
                connect.setEnabled(false);
                Toast.makeText((Context) MainActivity.this, "YOU ARE BLOCKED ! " , Toast.LENGTH_SHORT).show();
            }


        }
    }








}