package com.example.coffee_ecommerceapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.coffee_ecommerceapp.database.DBhelper;

public class Productlist extends AppCompatActivity {
    TextView product_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productlist);

        Button delete_btn = findViewById(R.id.delete_btn);
        product_id = (TextView) findViewById(R.id.product_id);
        DBhelper DB = new DBhelper(this);

        delete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (inputValidation())
                    DB.deleteProduct(Integer.parseInt(product_id.getText().toString()));
            }
        });



        }
    public boolean inputValidation() {

        boolean flag = true;
        if (product_id.getText().toString().isEmpty()) {
            product_id.setError("Can't be Empty");
            flag = false;
        }

        return flag;

    }
}
