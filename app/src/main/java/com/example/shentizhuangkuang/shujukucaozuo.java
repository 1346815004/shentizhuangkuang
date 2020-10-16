package com.example.shentizhuangkuang;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class shujukucaozuo {

    public ArrayList<Map<String, String>> find(SQLiteDatabase sqLiteDatabase, String riqi){
        Cursor cursor = sqLiteDatabase.rawQuery("select * from tb_stzk where riqi like ?", new String[]{riqi});
        ArrayList<Map<String, String>> resultList = new ArrayList<Map<String, String>>();
        while (cursor.moveToNext()){
            Map<String, String> map = new HashMap<String, String>();
            map.put("id",cursor.getString(0));
            map.put("riqi",cursor.getString(1));
            map.put("zhuangtai",cursor.getString(2));
            map.put("tiwen",cursor.getString(3));
            map.put("beizhu",cursor.getString(4));
            resultList.add(map);
        }
        return resultList;
    }


    public static ArrayList<Map<String, String>> findall(SQLiteDatabase sqLiteDatabase){
        Cursor cursor = sqLiteDatabase.query("tb_stzk", null,
                "", new String[]{}, null, null, null);
        ArrayList<Map<String, String>> resultList = new ArrayList<Map<String, String>>();
        while (cursor.moveToNext()){
            Map<String, String> map = new HashMap<String, String>();
            map.put("id",cursor.getString(0));
            map.put("riqi",cursor.getString(1));
            map.put("zhuangtai",cursor.getString(2));
            map.put("tiwen",cursor.getString(3));
            map.put("beizhu",cursor.getString(4));
            resultList.add(map);
        }
        return resultList;
    }

    public void insert(SQLiteDatabase sqLiteDatabase, String riqi, String zhuangtai, String tiwen, String beizhu){
        ContentValues values = new ContentValues();
        values.put("riqi",riqi);
        values.put("zhuangtai",zhuangtai);
        values.put("tiwen",tiwen);
        values.put("beizhu",beizhu);
        sqLiteDatabase.insert("tb_stzk", null, values);
    }

    public int update(SQLiteDatabase sqLiteDatabase, String riqi, String zhuangtai, String tiwen, String beizhu){
        ContentValues values = new ContentValues();
        if(!zhuangtai.equals(""))
            values.put("zhuangtai",zhuangtai);
        if(!tiwen.equals(""))
            values.put("tiwen",tiwen);
        if(!beizhu.equals(""))
            values.put("beizhu",beizhu);
        int number = sqLiteDatabase.update("tb_stzk", values, "riqi=?", new String[]{riqi});
        return number;
    }

    public int delete(SQLiteDatabase sqLiteDatabase, String riqi){
        int number = sqLiteDatabase.delete("tb_stzk","riqi=?", new String[]{riqi});
        return number;
    }
}
