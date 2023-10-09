package com.bnb.plugins.contacts;

import com.getcapacitor.JSObject;
import com.getcapacitor.PermissionState;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;
import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.util.Log;
import com.bnb.plugins.contacts.ContactsPluginImpl;

import com.getcapacitor.annotation.Permission;
import com.getcapacitor.annotation.PermissionCallback;


import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@CapacitorPlugin(name = "ContactsPlugin",
        permissions = {
            @Permission (
                    alias = "contacts",
                    strings = {Manifest.permission.READ_CONTACTS}
            )
        }
)
public class ContactsPluginPlugin extends Plugin {
    private ContactsPlugin implementation;

    @Override
    public void load() {
        implementation = new ContactsPlugin(getActivity());
    }
    protected static final int REQUEST_CONTACTS = 12345;

    @PluginMethod()
    public void getContacts(PluginCall call) {
        String value = call.getString("filter");
        // Filter based on the value if want

        call.setKeepAlive(true);
        // this popups the request permission interface
        requestAllPermissions(call, "contactsPermsCallback");
    }

    void loadContacts(PluginCall call) {
        ContentResolver cr = this.getContext().getContentResolver();
        call.resolve(ContactsPluginImpl.loadContactsImpl(call, cr));
        call.resolve(ContactsPluginImpl.loadContactsImpl(call, cr));
    }

    @PermissionCallback
    private void contactsPermsCallback(PluginCall call) {
        if (getPermissionState("contacts") == PermissionState.GRANTED) {
            loadContacts(call);
        } else {
            call.reject("Permission is required to take a picture");
        }
    }

    @PluginMethod
    public void echo(PluginCall call) {
        String value = call.getString("value");

        JSObject ret = new JSObject();
        ret.put("value", implementation.echo(value));
        call.resolve(ret);
    }
}
