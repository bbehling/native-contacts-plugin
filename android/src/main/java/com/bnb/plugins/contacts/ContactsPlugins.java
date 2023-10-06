package com.bnb.plugins.contacts;

import android.util.Log;

public class ContactsPlugins {

    public String echo(String value) {
        Log.i("Echo", value);
        return value;
    }
}
