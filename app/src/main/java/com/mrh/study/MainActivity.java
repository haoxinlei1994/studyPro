package com.mrh.study;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.mrh.scroller.ScrollTextViewActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mScrollerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mScrollerBtn = findViewById(R.id.btn_scroller);
        mScrollerBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_scroller:
                startActivity(new Intent(this, ScrollTextViewActivity.class));
                break;
        }
    }
}
