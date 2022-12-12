package com.example.onehub.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.onehub.R;
import com.example.onehub.models.Chat;
import com.example.onehub.models.ChatAdapter;
import com.example.onehub.models.ChatDB;
import com.example.onehub.models.User;
import com.example.onehub.models.UserAdapter;
import com.example.onehub.models.UserDB;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ListChatActivity extends AppCompatActivity {

    ChatDB dbc = new ChatDB(this);
    List<Chat> chatList;
    ListView listViewChat1;
    ChatAdapter adapter;

    UserDB udb = new UserDB(this);
    EditText msg;
    Button send;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_chat);




        Intent intent = getIntent();
        String userID = intent.getStringExtra("key");
        int idu = Integer.parseInt(userID);
        System.out.println(userID+"*****************************"+idu);
        msg = (EditText) findViewById(R.id.edtMsg);
        send = (Button) findViewById(R.id.btnSend);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int userid = idu;
                String message = msg.getText().toString();

                Date date = new Date();

                String postedat = String.valueOf(new Timestamp(date.getTime()));

                Chat chat = new Chat(userid,message,postedat);
                if(!message.isEmpty()) {
                    dbc.addMessage(chat);
                    recreate();
                }

            }
        });








        //**************** listview ****************/

        listViewChat1 = (ListView) findViewById(R.id.listViewChat);
        chatList = new ArrayList<>();

        //opening the database
        //  mDatabase = openOrCreateDatabase(, MODE_PRIVATE, null);

        //this method will display the employees in the list

        showChatFromDatabase();
    }



    private void showChatFromDatabase() {

        chatList =  dbc.getAllChat();

        //creating the adapter object
        adapter = new ChatAdapter(this, R.layout.list_layout_chat, chatList);

        //adding the adapter to listview
        listViewChat1.setAdapter(adapter);


    }










}