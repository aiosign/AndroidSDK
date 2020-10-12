package com.example.showpdf;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * 作者：zjt on 2020/10/11.
 * 邮箱：13120490031@163.com
 * 版本：v1.0
 * qq：1158710428
 */
public class MainActivityNew extends AppCompatActivity {
    Button liberty,areaSign,preview,face;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_new);
        liberty = findViewById(R.id.liberty);
        liberty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivityNew.this,MainActivity.class));
            }
        });
        areaSign = findViewById(R.id.areaSign);
        areaSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivityNew.this,AreaSignActivity.class));
            }
        });
        preview = findViewById(R.id.preview);
        preview .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivityNew.this, PreviewActivity.class));
            }
        });


        face = findViewById(R.id.face);
        face .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivityNew.this,FaceActivity.class));
            }
        });


    }




}
