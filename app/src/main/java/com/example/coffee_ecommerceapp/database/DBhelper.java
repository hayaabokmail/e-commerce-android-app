package com.example.coffee_ecommerceapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.coffee_ecommerceapp.data.Products;

import java.util.ArrayList;

public class DBhelper extends SQLiteOpenHelper {


    public static final String DBName = "Mycoffee.db";

    public DBhelper(@Nullable Context context) {
        super(context, "Mycoffee.db", null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("create table users(username TEXT primary key , password TEXT)");
        MyDB.execSQL("create table products(coffeimage BLOB ,product_id INT primary key , product_name TEXT ,product_price INT,product_details TEXT)");
        MyDB.execSQL("create table cartitems(coffeimage BLOB primary key ,product_name TEXT ,count INT,product_price INT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("drop table if exists users");
        onCreate(MyDB);
        MyDB.execSQL("drop table if exists products");
        onCreate(MyDB);
        MyDB.execSQL("drop table if exists cartitems");
        onCreate(MyDB);

    }


    public boolean insertData(String username, String password) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        long result = MyDB.insert("users", null, contentValues);
        if (result == -1) return false;
        else return true;
    }

    public boolean checkusername(String username) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("select * from users where username = ?", new String[]{username});

        if (cursor.getCount() > 0) return true;
        else
            return false;
    }

    public boolean checkusernamepassword(String username, String password) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("select * from users where username = ? and password = ?", new String[]{username, password});
        if (cursor.getCount() > 0) return true;
        else
            return false;
    }

    public Long InsertProduct(Products product) {

        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("coffeimage", product.getCoffeimage());
        contentValues.put("product_id", product.getProduct_id());
        contentValues.put("product_name", product.getProduct_name());
        contentValues.put("product_price", product.getProduct_price());
        contentValues.put("product_details", product.getProduct_details());

        long result = MyDB.insert("products", null, contentValues);
        return result;
    }

    public void deleteAllProducts()
    {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        MyDB.execSQL("DELETE FROM products;");
    }

    public void updateProduct(Products product)
    {
        SQLiteDatabase MyDB =getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("coffeimage", product.getCoffeimage());
        contentValues.put("product_id", product.getProduct_id());
        contentValues.put("product_name", product.getProduct_name());
        contentValues.put("product_price", product.getProduct_price());
        contentValues.put("product_details", product.getProduct_details());

        String args[] ={product.getProduct_id()+""};
        MyDB.update("products",contentValues,"id=?",args);
    }

    public int deleteProduct(int id)
    {
        int result;
        SQLiteDatabase MyDB = getWritableDatabase();
// db.execSQL("DELETE FROM product WHERE id="+id+";");
        String args[] ={""+id};
        result=MyDB.delete("products","id=?",args);
        return result;
    }

    public ArrayList<Products> showAllProducts() {

        SQLiteDatabase MyDB = getReadableDatabase();
        ArrayList<Products> products = new ArrayList<>();
        String query = "SELECT * FROM products";
        Cursor cursor = MyDB.rawQuery(query, null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            String name = cursor.getString(0);
            String details = cursor.getString(1);
            int id = cursor.getInt(2);
            int price = cursor.getInt(3);
            byte[] coffeimage =cursor.getBlob(4);
            //Create object of user and insert it in students arrayList
            Products product = new Products(name,details,id,price,coffeimage);
            products.add(product);
            cursor.moveToNext();
        }
        return products;

    }


}

