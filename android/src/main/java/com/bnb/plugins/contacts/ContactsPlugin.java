package com.bnb.plugins.contacts;

import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

public class ContactsPlugin {

    private AppCompatActivity activity;

    public ContactsPlugin(AppCompatActivity activity) {
        this.activity = activity;
    }

    public String echo(String value) {
        Log.i("Echo", value);
        return value;
    }
}
