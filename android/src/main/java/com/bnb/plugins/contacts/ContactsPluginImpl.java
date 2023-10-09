package com.bnb.plugins.contacts;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.util.Log;

import com.getcapacitor.JSObject;
import com.getcapacitor.PluginCall;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import com.getcapacitor.Plugin;
public class ContactsPluginImpl {
    @SuppressLint("Range")
    static JSObject loadContactsImpl(PluginCall call, ContentResolver cr) {
        ArrayList<Map> contactList = new ArrayList<>();

        Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI,
                null, null, null, null);
        if ((cur != null ? cur.getCount() : 0) > 0) {
            while (cur != null && cur.moveToNext()) {
                Map<String,String> map =  new HashMap<String, String>();

                String s = ContactsContract.Contacts._ID;

                String id = cur.getString(
                        cur.getColumnIndex(ContactsContract.Contacts._ID));
                String name = cur.getString(cur.getColumnIndex(
                        ContactsContract.Contacts.DISPLAY_NAME));

                map.put("firstName", name);
                map.put("lastName", "");

                String contactNumber = "";

                if (cur.getInt(cur.getColumnIndex( ContactsContract.Contacts.HAS_PHONE_NUMBER)) > 0) {
                    Cursor pCur = cr.query(
                            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                            null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                            new String[]{id}, null);
                    pCur.moveToFirst();
                    contactNumber = pCur.getString(pCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                    Log.i("phoneNUmber", "The phone number is "+ contactNumber);
                }
                map.put("telephone", contactNumber);
                contactList.add(map);
            }
        }
        if (cur != null) {
            cur.close();
        }

        JSONArray jsonArray = new JSONArray(contactList);
        JSObject ret = new JSObject();
        ret.put("results", jsonArray);
        return ret;
    }
}
