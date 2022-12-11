package com.example.onehub.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.example.onehub.R;
import com.example.onehub.models.User;
import com.example.onehub.models.UserAdapter;
import com.example.onehub.models.UserDB;

import java.util.ArrayList;
import java.util.List;

public class ListUsersActivity extends AppCompatActivity {

    List<User> userList;
    UserDB mDatabase = new UserDB(this);
    ListView listViewUsers1;
    UserAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_users);

        listViewUsers1 = (ListView) findViewById(R.id.listViewUsers);
        userList = new ArrayList<>();

        //opening the database
      //  mDatabase = openOrCreateDatabase(, MODE_PRIVATE, null);

        //this method will display the employees in the list
        showUsersFromDatabase();





    }

    private void showUsersFromDatabase() {

       userList =  mDatabase.getAllUsers();

        //creating the adapter object
        adapter = new UserAdapter(this, R.layout.list_layout_users, userList);

        //adding the adapter to listview
        listViewUsers1.setAdapter(adapter);


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
                Intent i = new Intent(ListUsersActivity.this, MainActivity.class);
                finishAffinity();
                startActivity(i);
                break;

        }
        return super.onOptionsItemSelected(item);

    }





}