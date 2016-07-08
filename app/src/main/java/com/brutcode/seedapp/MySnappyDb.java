package com.brutcode.seedapp;

import android.content.Context;

import com.snappydb.DB;
import com.snappydb.DBFactory;
import com.snappydb.SnappydbException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Danilo on 07/07/2016.
 */
public class MySnappyDb {

    private static final String CONTENT_PREFIX = "c:";
    private static volatile MySnappyDb instance;

    private static DB mSnappyDB;

    public MySnappyDb() {}

    public static MySnappyDb getInstance(Context context) {
        if (instance == null ) {
            synchronized (MySnappyDb.class) {
                if (instance == null) {
                    instance = new MySnappyDb();
                }
            }
        }
        try {
            mSnappyDB = DBFactory.open(context);
        } catch (SnappydbException e) {
            e.printStackTrace();
        }
        return instance;
    }



    public boolean insertContent(Content content){
        boolean returnValue = false;
        try {
            mSnappyDB.put(generateKey(content.hashCode()),content);
            returnValue = true;
            mSnappyDB.close();
        } catch (SnappydbException e) {
            e.printStackTrace();
        }

        return returnValue;
    }

    public boolean removeContent(Content content){
        try {
            mSnappyDB.del(generateKey(content.hashCode()));
            mSnappyDB.close();
        } catch (SnappydbException e) {
            e.printStackTrace();
        }
        return true;
    }

    public List<Content> getAllContents(){
        List<Content> contents = new ArrayList<>();
        try {
            String[] keys = mSnappyDB.findKeys(CONTENT_PREFIX);
            for (int i = 0; i < keys.length ; i++) {
                Content content = mSnappyDB.getObject(keys[i], Content.class);
                contents.add(content);
            }
            mSnappyDB.close();
            Collections.sort(contents);
        } catch (SnappydbException e) {
            e.printStackTrace();
        }
        return contents;
    }

    public Content getContentByKey(int hashCode){
        Content content = null;
        try {
            content = mSnappyDB.getObject(generateKey(hashCode),Content.class);
            mSnappyDB.close();
        } catch (SnappydbException e) {
            e.printStackTrace();
        }
        return content;
    }

    
    private String generateKey(int hashCode){
        String key = CONTENT_PREFIX + Integer.toString(hashCode);
        return key;
    }
}
