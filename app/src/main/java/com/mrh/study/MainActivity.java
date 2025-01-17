package com.mrh.study;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.mrh.chapter4.MdActivity;
import com.mrh.scroller.ScrollTextViewActivity;
import com.mrh.test.AidlActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mScrollerBtn;
    private Button mAidlBtn;
    private Button mMdBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mScrollerBtn = findViewById(R.id.btn_scroller);
        mAidlBtn = findViewById(R.id.btn_aidl);
        mMdBtn = findViewById(R.id.btn_md);
        mScrollerBtn.setOnClickListener(this);
        mAidlBtn.setOnClickListener(this);
        mMdBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_scroller:
                startActivity(new Intent(this, ScrollTextViewActivity.class));
                break;
            case R.id.btn_aidl:
                startActivity(new Intent(this, AidlActivity.class));
                break;
            case R.id.btn_md:
                startActivity(new Intent(this, MdActivity.class));
                break;
        }
    }
}
