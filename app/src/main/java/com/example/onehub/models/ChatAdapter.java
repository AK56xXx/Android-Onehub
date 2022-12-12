package com.example.onehub.models;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.onehub.R;
import com.example.onehub.activities.ListChatActivity;

import java.util.List;

public class ChatAdapter extends ArrayAdapter<Chat> {

    Context mCtx;
    int listLayoutRes;
    List<Chat> chatList;
    ChatDB udb = new ChatDB(this.getContext()); // (*)
    UserDB userdb = new UserDB(this.getContext());
    private int status = 0;
    ChatAdapter ca ;
    String name = "";



    public ChatAdapter(Context mCtx, int listLayoutRes, List<Chat> chatList) {
        super(mCtx, listLayoutRes, chatList);

        this.mCtx = mCtx;
        this.listLayoutRes = listLayoutRes;
        this.chatList = chatList;
        // this.mDatabase = mDatabase;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(listLayoutRes, null);

        //getting chat of the specified position
        Chat chat = chatList.get(position);


        //getting views
        TextView textViewName = view.findViewById(R.id.chatName);
        TextView textViewMessage = view.findViewById(R.id.chatMsg);
        TextView textViewTime = view.findViewById(R.id.chatTime);

        List<User> users = userdb.getAllUsers();

        for (User u : users) {

            if (u.getId() == chat.getId_user()){
                name = u.getUsername();
            }

        }


        //adding data to views
        textViewName.setText(name);
        textViewMessage.setText(chat.getMsg());
        textViewTime.setText(chat.getDate());


        //we will use these buttons later for update and delete operation
/*
        Button buttonEdit = view.findViewById(R.id.BEChat);
        buttonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateChatAdapter(chat);
                udb.getAllChats(); //refresh
            }
        });
        Button buttonDelete = view.findViewById(R.id.BDChat);
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(mCtx);
                builder.setTitle("Are you sure?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        udb.deleteChat(chat);

                        //refresh
                        reloadChatsFromDatabase();

                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();

            }
        });*/

        return view;
    }






/*
    private void updateChatAdapter(final Chat chat) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(mCtx);

        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.dialogue_update_chat, null);
        builder.setView(view);


        final EditText editTextFName = view.findViewById(R.id.edtFN_UPDATE);
        final EditText editTextLName = view.findViewById(R.id.edtLN_UPDATE);
        final EditText editTextUN = view.findViewById(R.id.edtUN_UPDATE);
        final EditText editTextPW = view.findViewById(R.id.edtPWD_UPDATE);
        final Spinner spinnerRole = view.findViewById(R.id.rolesSpinner_UPDATE);
        final RadioButton rActive = view.findViewById(R.id.activeRB);
        final RadioButton rBan = view.findViewById(R.id.bannedRB);

        final RadioGroup rg = view.findViewById(R.id.statusRadioGroup);
        int selectedRb = rg.getCheckedRadioButtonId();
        final RadioButton rStatus = view.findViewById(selectedRb);



        editTextFName.setText(chat.getFirst_name());
        editTextLName.setText(chat.getLast_name());
        editTextUN.setText(chat.getChatname());
        editTextPW.setText(chat.getPassword());

        if (chat.getBlocked()==0) {
            rActive.setSelected(true);
        }
        else if(chat.getBlocked()==1)
        {
            rBan.setSelected(true);
        }


        final AlertDialog dialog = builder.create();
        dialog.show();

        view.findViewById(R.id.btnUpdateChat).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fname = editTextFName.getText().toString().trim();
                String lname = editTextLName.getText().toString().trim();
                String chatname = editTextUN.getText().toString().trim();
                String password = editTextPW.getText().toString().trim();
                String role = spinnerRole.getSelectedItem().toString();


                if(rActive.isChecked())
                {
                    status = 0;
                }
                else if(rBan.isChecked())
                {
                    status = 1;
                }



                if (fname.isEmpty()) {
                    editTextFName.setError("First Name can't be blank");
                    editTextFName.requestFocus();
                    return;
                }

                if (lname.isEmpty()) {
                    editTextLName.setError("Last Name can't be blank");
                    editTextLName.requestFocus();
                    return;
                }


                if (chatname.isEmpty()) {
                    editTextUN.setError("Chatname can't be blank");
                    editTextUN.requestFocus();
                    return;
                }

                if (password.isEmpty()) {
                    editTextUN.setError("Password can't be blank");
                    editTextUN.requestFocus();
                    return;
                }

                //chat = new Chat( fname,lname,chatname,password,role,0);
                chat.setFirst_name(fname);chat.setLast_name(lname);chat.setChatname(chatname);chat.setRole(role);chat.setBlocked(status);chat.setAttempts(0);
                udb.updateChat(chat);


                Toast.makeText(mCtx, "Chat Updated", Toast.LENGTH_SHORT).show();
                // reloadChatsFromDatabase();


                dialog.dismiss();
            }
        });


    }*/

        private void reloadChatFromDatabase() {
            Cursor cursorChats = udb.getReadableDatabase().rawQuery("SELECT * FROM chat", null);
            if (cursorChats.moveToFirst()) {
                chatList.clear();
                do {
                    chatList.add(new Chat(
                            cursorChats.getInt(0),
                            cursorChats.getInt(1),
                            cursorChats.getString(2),
                            cursorChats.getString(3)

                    ));
                } while (cursorChats.moveToNext());
            }
            cursorChats.close();
            //   udb.getAllChats();
            notifyDataSetChanged();
        }










}
