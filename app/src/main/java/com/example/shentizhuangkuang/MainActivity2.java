package com.example.shentizhuangkuang;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class MainActivity2 extends AppCompatActivity {

    DatePicker dp_1;
    int nian,yue,ri;
    Button btn_5;
    Spinner sp_1,sp_2;
    EditText et_1;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Calendar calendar = Calendar.getInstance();
        nian = calendar.get(Calendar.YEAR);
        yue = calendar.get(Calendar.MONTH) + 1;
        ri = calendar.get(Calendar.DAY_OF_MONTH);

        dp_1 = findViewById(R.id.dp_1);
        dp_1.init(nian, yue-1, ri, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                MainActivity2.this.nian = year;
                MainActivity2.this.yue = monthOfYear+1;
                MainActivity2.this.ri = dayOfMonth;
            }
        });

        sp_1 = findViewById(R.id.sp_1);
        sp_2 = findViewById(R.id.sp_2);


        et_1 = findViewById(R.id.et_1);

        db = new DBHelper(MainActivity2.this,"tb_stzk",null,1);

        btn_5 = findViewById(R.id.btn_5);
        btn_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s0 = nian+"年"+yue+"月"+ri+"日";
                String s1 = sp_1.getSelectedItem().toString();
                String s2 = sp_2.getSelectedItem().toString();
                String s3 = et_1.getText().toString();
                Log.i("test", "日期:" + s0);
                Log.i("test", "状况:" + s1);
                Log.i("test", "体温:" + s2);
                Log.i("test", "备注:" + s3);
                shujukucaozuo sj1 = new shujukucaozuo();
                sj1.insert(db.getWritableDatabase(), s0, s1, s2, s3);
                Intent intent = new Intent();
                setResult(Activity.RESULT_OK,intent);
                finish();
            }
        });

    }




}