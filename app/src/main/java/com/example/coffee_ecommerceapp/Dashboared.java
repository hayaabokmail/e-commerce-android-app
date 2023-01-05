package com.example.coffee_ecommerceapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.coffee_ecommerceapp.data.Products;
import com.example.coffee_ecommerceapp.database.DBhelper;

import java.io.ByteArrayOutputStream;


public class Dashboared extends AppCompatActivity {

    int camera_request = 100;
    ImageView product_image;
    EditText product_name ,product_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboared);

        product_name = (EditText) findViewById(R.id.product_name);
        product_id = (EditText) findViewById(R.id.product_id);
        EditText product_price = findViewById(R.id.product_price);
        EditText product_details = findViewById(R.id.product_details);

        Button add_btn = findViewById(R.id.add_btn);
        Button show_btn = findViewById(R.id.show_btn);
        Button deletall_btn = findViewById(R.id.deletall_btn);
        Button update_btn = findViewById(R.id.update_btn);
        Button add_image = findViewById(R.id.add_image);


         product_image = (ImageView)findViewById(R.id.product_image);


        DBhelper DB = new DBhelper(this);




        product_image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                openCamera();
                }
            });




        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Long result = null;
                String name = product_name.getText().toString();
                int id = Integer.parseInt(product_id.getText().toString());
                int price =Integer.parseInt(product_price.getText().toString());
                String details = product_details.getText().toString();
                byte[] imageproduct = new byte[0];

                imageproduct=conver_byte_array(product_image);
                if (inputValidation())
                    result = DB.InsertProduct(new Products(name,details,id,price,imageproduct));
                
                if (result < 0)
                    Toast.makeText(Dashboared.this, "Error", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(Dashboared.this, "Product Added succesfully", Toast.LENGTH_SHORT).show();
                
            }



        });

        show_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               Intent intent = new Intent(getApplicationContext() , Itemlist.class);
               startActivity(intent);

            }
        });

        deletall_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DB.deleteAllProducts();
            }
        });

        update_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = product_name.getText().toString();
                int id = Integer.parseInt(product_id.getText().toString());
                int price =Integer.parseInt(product_price.getText().toString());
                String details = product_details.getText().toString();
                byte[] imageproduct = new byte[0];

                imageproduct=conver_byte_array(product_image);
                if (inputValidation())
                    DB.updateProduct(new Products(name,details,id,price,imageproduct));


            }
        });
    }

    public void openCamera() {
        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, 0);
    }

    public   void  onActivityResult(int requestCode, int resultCode , @Nullable Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        Bitmap bitmap = (Bitmap)data.getExtras().get("data");

        product_image.setImageBitmap(bitmap);
    }

    public byte[] conver_byte_array(ImageView img){
        Bitmap bitmap =((BitmapDrawable)img.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,stream);
        byte[]bytes = stream.toByteArray();
        return bytes;
    }



    public boolean inputValidation() {
        boolean flag = true;
        if (product_name.getText().toString().isEmpty()) {
            product_name.setError("Can't be Empty");
            flag = false;
        }
        if (product_id.getText().toString().isEmpty()) {
            product_id.setError("Can't be Empty");
            flag = false;
        }

        return flag;

    }



}