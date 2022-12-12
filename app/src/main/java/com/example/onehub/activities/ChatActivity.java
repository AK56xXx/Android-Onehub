package com.example.onehub.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.example.onehub.R;
import com.example.onehub.models.Chat;
import com.example.onehub.models.ChatAdapter;
import com.example.onehub.models.ChatDB;
import com.example.onehub.models.User;
import com.example.onehub.models.UserAdapter;
import com.example.onehub.models.UserDB;

import java.util.List;

public class ChatActivity extends AppCompatActivity {

    ChatDB dbc = new ChatDB(this);
    List<Chat> chatList;
    UserDB mDatabase = new UserDB(this);
    ListView listViewChat1;
    ChatAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

       // dbc.addMessage(new Chat(2,"Hello","111"));

        Toast.makeText(this, ""+dbc.getAllChat(), Toast.LENGTH_SHORT).show();
























    }
}