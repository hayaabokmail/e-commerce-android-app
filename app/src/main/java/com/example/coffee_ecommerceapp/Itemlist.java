package com.example.coffee_ecommerceapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.coffee_ecommerceapp.adapter.listprducts;
import com.example.coffee_ecommerceapp.data.Products;
import com.example.coffee_ecommerceapp.database.DBhelper;

import java.util.ArrayList;

public class Itemlist extends AppCompatActivity {
    TextView product_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itemlist);

//        Button delete_btn = findViewById(R.id.delete_btn);
//        product_id = (TextView)findViewById(R.id.product_id);

        RecyclerView rvproducts = findViewById(R.id.recview);
        ArrayList<Products> data = new ArrayList<>();

        DBhelper DB = new DBhelper(this);
        data = DB.showAllProducts();

        listprducts adapter = new listprducts(data,this);
        RecyclerView.LayoutManager mymanager = new LinearLayoutManager(getApplicationContext());

        rvproducts.setAdapter(adapter);
        rvproducts.setLayoutManager(mymanager);

//        delete_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (inputValidation())
//                    DB.deleteProduct(Integer.parseInt(product_id.getText().toString()));
//            }
//        });



    }
//    public boolean inputValidation() {
//        boolean flag = true;
//        if (product_id.getText().toString().isEmpty()) {
//            product_id.setError("Can't be Empty");
//            flag = false;
//        }
//
//        return flag;
//
//    }
}