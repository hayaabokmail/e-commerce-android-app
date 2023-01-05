package com.example.coffee_ecommerceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.coffee_ecommerceapp.database.DBhelper;


public class Login_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        EditText username1 = findViewById(R.id.username1);
        EditText  password1 = findViewById(R.id.password1);
        Button sign_in_btn1 = findViewById(R.id.sign_in_btn1);
        RadioButton userbtn = findViewById(R.id.userbtn);
        RadioButton adminbtn = findViewById(R.id.adminbtn);

        DBhelper DB = new DBhelper(this);

        sign_in_btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username1.getText().toString();
                String pass_word = password1.getText().toString();

                if (user.equals("") || pass_word.equals("")){
                    Toast.makeText(Login_activity.this, "Please enter all fields!", Toast.LENGTH_SHORT).show();
                }
                else{
                    boolean chesckuserpass = DB.checkusernamepassword(user,pass_word);

                    if(chesckuserpass ==true){

                        if(userbtn.isChecked()){
                            Intent intent = new Intent(getApplicationContext() , Homeuser_activity.class);
                            startActivity(intent);
                        }
                        if(adminbtn.isChecked()){
                            Intent intent = new Intent(getApplicationContext() , Dashboared.class);
                            startActivity(intent);
                        }
                        Toast.makeText(Login_activity.this, "Sign in successfully", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(Login_activity.this, "Invalid input user doesn`t exist", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });
    }
}