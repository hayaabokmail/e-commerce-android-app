package com.example.coffee_ecommerceapp.data;

public class Products {
    String product_name , product_details;
    int product_id ;
    byte[] coffeimage;
    int product_price;

    public Products(String product_name, String product_details, int product_id, int product_price , byte[] coffeimage) {
        this.product_name = product_name;
        this.product_details = product_details;
        this.product_id = product_id;
        this.product_price = product_price;
        this.coffeimage = coffeimage;

    }



    public byte[] getCoffeimage() {
        return coffeimage;
    }

    public void setCoffeimage(byte[] coffeimage) {
        this.coffeimage = coffeimage;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getProduct_details() {
        return product_details;
    }

    public void setProduct_details(String product_details) {
        this.product_details = product_details;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public int getProduct_price() {
        return product_price;
    }

    public void setProduct_price(int product_price) {
        this.product_price = product_price;
    }
}
