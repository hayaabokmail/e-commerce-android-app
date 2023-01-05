package com.example.coffee_ecommerceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.coffee_ecommerceapp.database.DBhelper;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText  username = findViewById(R.id.username);
        EditText  password = findViewById(R.id.password);
        EditText  re_password = findViewById(R.id.re_password);
        Button sign_up_btn = findViewById(R.id.sign_up_btn);
        Button sign_in_btn = findViewById(R.id.sign_in_btn);

        DBhelper DB = new DBhelper(this);

        sign_up_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String pass_word = password.getText().toString();
                String re_pass = re_password.getText().toString();

                if (user.equals("") || pass_word.equals("") || re_pass.equals("")){
                    Toast.makeText(MainActivity.this, "Please enter all fields!", Toast.LENGTH_SHORT).show();
                }
                else {
                    if (pass_word.equals(re_pass)) {
                        boolean checkuser = DB.checkusername(user);
                        if (checkuser == false) {
                            boolean insert = DB.insertData(user, pass_word);
                            if (insert == true) {
                                Toast.makeText(MainActivity.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(MainActivity.this , Dashboared.class);
                                startActivity(intent);
                            }
                            else {
                                Toast.makeText(MainActivity.this, "Registration failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            Toast.makeText(MainActivity.this, "User already exist! please sign in", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        Toast.makeText(MainActivity.this, "Password not matching", Toast.LENGTH_SHORT).show();
                    }
                }
            } });

        sign_in_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Login_activity.class);
                startActivity(intent);
            }
        });


    }
}