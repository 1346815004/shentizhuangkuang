package com.example.shentizhuangkuang;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {


    Button btn_1;
    ListView lv_1;
    ArrayList zhuangtailist = new ArrayList();
    DBHelper db;
    ArrayList ztlist = new ArrayList();
    String string1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new DBHelper(MainActivity.this,"tb_stzk",null,1);

        ListView lv_1 = findViewById(R.id.lv_1);
        gengxinquanbu();

        btn_1 = findViewById(R.id.btn_1);
        btn_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                startActivityForResult(intent,0);
            }
        });

        lv_1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                string1 = (String) parent.getItemAtPosition(position);
                xiangxi();
            }
        });
    }

    public void xiangxi() {
        shujukucaozuo sj1 = new shujukucaozuo();
        ArrayList<Map<String, String>> dancilist = sj1.find(db.getReadableDatabase(), string1);

        String s1 =  dancilist.get(0).getOrDefault("riqi","test");
        String s2 =  dancilist.get(0).getOrDefault("zhuangtai","test");
        String s3 =  dancilist.get(0).getOrDefault("tiwen","test");
        String s4 =  dancilist.get(0).getOrDefault("beizhu","test");
        Intent intent = new Intent(MainActivity.this,MainActivity4.class);
        intent.putExtra("riqi", s1);
        intent.putExtra("zhuangtai", s2);
        intent.putExtra("tiwen", s3);
        intent.putExtra("beizhu", s4);
        startActivityForResult(intent,0);
    }

    public void gengxinquanbu(){
       shujukucaozuo s1 = new shujukucaozuo();
        ArrayList<Map<String, String>> alllist = shujukucaozuo.findall(db.getReadableDatabase());
        if( alllist != null && alllist.size() != 0){
            ztlist.clear();
            for ( int i=0; i<alllist.size(); i++ ){
                ztlist.add(alllist.get(i).getOrDefault("riqi","未查到"));
            }
            gengxinlist();
            Toast.makeText(MainActivity.this, "刷新", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(MainActivity.this, "没刷新", Toast.LENGTH_SHORT).show();
        }
    }

    public void gengxinlist(){
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,ztlist);
        ListView lv_1 = findViewById(R.id.lv_1);
        lv_1.setAdapter(adapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        gengxinquanbu();
        Toast.makeText(this, "test", Toast.LENGTH_SHORT).show();
    }
}