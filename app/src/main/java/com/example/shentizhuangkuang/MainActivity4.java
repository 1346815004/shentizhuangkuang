package com.example.shentizhuangkuang;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity4 extends AppCompatActivity {

    private static String s1;
    TextView tv_1;
    Spinner sp_1,sp_2;
    EditText et_1;
    Button btn_1,btn_2;
    String s2,s3,s4;
    DBHelper db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        db = new DBHelper(MainActivity4.this,"tb_stzk",null,1);

        s1 = getIntent().getStringExtra("riqi");
        s2 = getIntent().getStringExtra("zhuangtai");
        s3 = getIntent().getStringExtra("tiwen");
        s4 = getIntent().getStringExtra("beizhu");
        int i1 = 0;
        int i2 = 0;

        switch (s2){
            case "难受":
                i1 = 0;
                break;
            case "一般":
                i1 = 1;
                break;
            case "良好":
                i1 = 2;
                break;
        }
        switch (s3){
            case "35°C":
                i2 = 0;
                break;
            case "36°C":
                i2 = 1;
                break;
            case "37°C":
                i2 = 2;
                break;
            case "38°C":
                i2 = 3;
                break;
            case "39°C":
                i2 = 4;
                break;
            case "40°C":
                i2 = 5;
                break;
        }

        tv_1 = findViewById(R.id.tv_1);
        tv_1.setText(s1);

        et_1 = findViewById(R.id.et_1);
        et_1.setText(s4);

        sp_1 = findViewById(R.id.sp_1);
        sp_1.setSelection(i1);
        sp_2 = findViewById(R.id.sp_2);
        sp_2.setSelection(i2);

        btn_1 = findViewById(R.id.btn_1);
        btn_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s1 = MainActivity4.s1;
                shujukucaozuo sj1 = new shujukucaozuo();
                sj1.delete(db.getReadableDatabase(),s1);
                Intent intent = new Intent();
                setResult(Activity.RESULT_OK,intent);
                finish();
            }
        });

        btn_2 = findViewById(R.id.btn_2);
        btn_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s1 = MainActivity4.s1;
                String s2 = sp_1.getSelectedItem().toString();
                String s3 = sp_2.getSelectedItem().toString();
                String s4 = et_1.getText().toString();
                shujukucaozuo sj1 = new shujukucaozuo();
                sj1.update(db.getWritableDatabase(), s1, s2, s3, s4);
                Intent intent = new Intent();
                setResult(Activity.RESULT_OK,intent);
                finish();
            }
        });
    }
}