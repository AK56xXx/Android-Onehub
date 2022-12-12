package com.example.onehub.activities;

import static java.security.AccessController.getContext;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.onehub.R;
import com.example.onehub.models.User;
import com.example.onehub.models.UserDB;

import java.util.List;

public class ProfileActivity extends AppCompatActivity {

    UserDB udb = new UserDB(this);
    TextView PFN;
    TextView PLN;
    TextView PUN;
    TextView PPWD;
    Button editInfo;
    private String m_Text_fn = "";
    private String m_Text_ln = "";
    private String m_Text_pwd = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        List<User> users = udb.getAllUsers();
        Intent intent = getIntent();
        String userID = intent.getStringExtra("key");
        int idu = Integer.parseInt(userID);

        System.out.println(userID+"*****************************"+idu);


        PFN = (TextView) findViewById(R.id.pfn);
        PLN = (TextView) findViewById(R.id.pln);
        PUN = (TextView) findViewById(R.id.pun);
        PPWD = (TextView) findViewById(R.id.ppwd);




        for (User u : users) {

            if (u.getId() == idu){
                PFN.setText(u.getFirst_name());
                PLN.setText(u.getLast_name());
                PUN.setText(u.getUsername());
                PPWD.setText(u.getPassword());
            }

        }

        editInfo = (Button) findViewById(R.id.pedit);
        editInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ProfileActivity.this);


                View viewInflated = LayoutInflater.from(view.getContext()).inflate(R.layout.dialog_profil_updateuser, null, false);

                final EditText inputFN = (EditText) viewInflated.findViewById(R.id.edtFN_PROFIL);
                final EditText inputLN = (EditText) viewInflated.findViewById(R.id.edtLN_PROFIL);
                final EditText inputPWD = (EditText) viewInflated.findViewById(R.id.edtPWD_PROFIL);



                builder.setView(viewInflated);

// Set up the buttons
                builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {




                        m_Text_fn = inputFN.getText().toString();
                        m_Text_ln = inputLN.getText().toString();
                        m_Text_pwd = inputPWD.getText().toString();

                        //alert?
                        if (m_Text_fn.isEmpty()) {
                            inputFN.setError("First Name can't be blank");
                            inputFN.requestFocus();
                            return;
                        }

                        if (m_Text_ln.isEmpty()) {
                            inputLN.setError("Last Name can't be blank");
                            inputLN.requestFocus();
                            return;
                        }

                        if (m_Text_pwd.isEmpty()) {
                            inputFN.setError("Password can't be blank");
                            inputFN.requestFocus();
                            return;
                        }

                        for (User u : users) {

                            if (u.getId() == idu){
                            //get data ?
                                u.setFirst_name(m_Text_fn);
                                u.setLast_name(m_Text_ln);
                                u.setPassword(m_Text_pwd);
                                udb.updateUser(u);
                                recreate(); //refresh

                            }

                        }

                        Toast.makeText(ProfileActivity.this, "Information update !", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();


                    }
                });
                builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.show();
            }
        });










    }
}