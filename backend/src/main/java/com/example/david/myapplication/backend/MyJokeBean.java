package com.example.david.myapplication.backend;

/**
 * The object model for the data we are sending through endpoints
 */
public class MyJokeBean {

    private String myData;

    public String getData() {
        return myData;
    }

    public void setData(String data) {
        myData = data;
    }
}