package com.example.onehub.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.example.onehub.R;
import com.example.onehub.models.Chat;
import com.example.onehub.models.ChatAdapter;
import com.example.onehub.models.ChatDB;
import com.example.onehub.models.Post;
import com.example.onehub.models.PostAdapter;
import com.example.onehub.models.PostAdminAdapter;
import com.example.onehub.models.PostDB;
import com.example.onehub.models.UserDB;

import java.util.ArrayList;
import java.util.List;

public class AdminListPosts extends AppCompatActivity {

    PostDB pdb = new PostDB(this);
    List<Post> postList;
    UserDB mDatabase = new UserDB(this);
    ListView listViewPost1;
    PostAdminAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_list_posts);

      //  Post p = new Post(2,"Dev","Prog","999");
     //   pdb.addPost(p);






        listViewPost1 = (ListView) findViewById(R.id.lvAdminPosts);
        postList = new ArrayList<>();

        //opening the database
        //  mDatabase = openOrCreateDatabase(, MODE_PRIVATE, null);

        //this method will display the employees in the list

        showPostFromDatabase();













    }

    private void showPostFromDatabase() {

        postList =  pdb.getAllPosts();

        //creating the adapter object
        adapter = new PostAdminAdapter(this, R.layout.list_layout_posts_admin, postList);

        //adding the adapter to listview
        listViewPost1.setAdapter(adapter);


    }


}