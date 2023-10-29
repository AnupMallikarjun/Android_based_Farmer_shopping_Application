package com.example.agrishop;

import java.io.Serializable;

public class Mycartmodel implements Serializable {
   String Date;
   String Name;
   int Price;
   int Quantity;
   String Time;
   String address;
   String mobilenumber;
   String username;
    String documentid;

    public Mycartmodel() {
    }

    public Mycartmodel(String date, String name, int price, int quantity, String time, String address, String mobilenumber, String username, String documentid) {
        Date = date;
        Name = name;
        Price = price;
        Quantity = quantity;
        Time = time;
        this.address = address;
        this.mobilenumber = mobilenumber;
        this.username = username;
        this.documentid = documentid;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int price) {
        Price = price;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobilenumber() {
        return mobilenumber;
    }

    public void setMobilenumber(String mobilenumber) {
        this.mobilenumber = mobilenumber;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDocumentid() {
        return documentid;
    }

    public void setDocumentid(String documentid) {
        this.documentid = documentid;
    }
}
