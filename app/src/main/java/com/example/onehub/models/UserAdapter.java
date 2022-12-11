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
import com.example.onehub.activities.ListUsersActivity;

import java.util.List;

public class UserAdapter extends ArrayAdapter<User> {

    Context mCtx;
    int listLayoutRes;
    List<User> userList;
    UserDB udb = new UserDB(this.getContext()); // (*)
    private int status = 0;
    UserAdapter ua ;



    public UserAdapter(Context mCtx, int listLayoutRes, List<User> userList) {
        super(mCtx, listLayoutRes, userList);

        this.mCtx = mCtx;
        this.listLayoutRes = listLayoutRes;
        this.userList = userList;
       // this.mDatabase = mDatabase;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(listLayoutRes, null);

        //getting employee of the specified position
        User user = userList.get(position);


        //getting views
        TextView textViewName = view.findViewById(R.id.tvName);
        TextView textViewUN = view.findViewById(R.id.tvUN);
        TextView textViewPW = view.findViewById(R.id.tvPW);
        TextView textViewBan = view.findViewById(R.id.tvBAN);
        TextView textViewRole = view.findViewById(R.id.tvROLE);

        //adding data to views
        textViewName.setText(user.getFirst_name()+" "+user.getLast_name());
        textViewUN.setText("Username: "+user.getUsername());
        textViewPW.setText("Password: "+user.getPassword());
        textViewBan.setText("Status: "+String.valueOf(user.getBlocked()));
        textViewRole.setText("Role: "+user.getRole());

        //we will use these buttons later for update and delete operation

        Button buttonEdit = view.findViewById(R.id.BEUser);
        buttonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateUserAdapter(user);
                udb.getAllUsers(); //refresh
            }
        });
        Button buttonDelete = view.findViewById(R.id.BDUser);
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(mCtx);
                builder.setTitle("Are you sure?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                      /*  String sql = "DELETE FROM employees WHERE id = ?";
                        mDatabase.execSQL(sql, new Integer[]{employee.getId()});*/
                        udb.deleteUser(user);

                        //refresh
                        reloadUsersFromDatabase();

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
        });

        return view;
    }







    private void updateUserAdapter(final User user) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(mCtx);

        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.dialogue_update_user, null);
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



        editTextFName.setText(user.getFirst_name());
        editTextLName.setText(user.getLast_name());
        editTextUN.setText(user.getUsername());
        editTextPW.setText(user.getPassword());

        if (user.getBlocked()==0) {
            rActive.setSelected(true);
        }
        else if(user.getBlocked()==1)
        {
            rBan.setSelected(true);
        }


        final AlertDialog dialog = builder.create();
        dialog.show();

        view.findViewById(R.id.btnUpdateUser).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fname = editTextFName.getText().toString().trim();
                String lname = editTextLName.getText().toString().trim();
                String username = editTextUN.getText().toString().trim();
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


                if (username.isEmpty()) {
                    editTextUN.setError("Username can't be blank");
                    editTextUN.requestFocus();
                    return;
                }

                if (password.isEmpty()) {
                    editTextUN.setError("Password can't be blank");
                    editTextUN.requestFocus();
                    return;
                }

                //user = new User( fname,lname,username,password,role,0);
                user.setFirst_name(fname);user.setLast_name(lname);user.setUsername(username);user.setRole(role);user.setBlocked(status);user.setAttempts(0);
                udb.updateUser(user);

             /*   String sql = "UPDATE employees \n" +
                        "SET name = ?, \n" +
                        "department = ?, \n" +
                        "salary = ? \n" +
                        "WHERE id = ?;\n";

                mDatabase.execSQL(sql, new String[]{name, dept, salary, String.valueOf(employee.getId())});*/
                Toast.makeText(mCtx, "User Updated", Toast.LENGTH_SHORT).show();
               // reloadUsersFromDatabase();


                dialog.dismiss();
            }
        });


    }

    private void reloadUsersFromDatabase() {
       Cursor cursorUsers = udb.getReadableDatabase().rawQuery("SELECT * FROM users", null);
        if (cursorUsers.moveToFirst()) {
            userList.clear();
            do {
                userList.add(new User(
                        cursorUsers.getString(0),
                        cursorUsers.getString(1),
                        cursorUsers.getString(2),
                        cursorUsers.getInt(3)

                ));
            } while (cursorUsers.moveToNext());
        }
        cursorUsers.close();
     //   udb.getAllUsers();
        notifyDataSetChanged();
    }









}
