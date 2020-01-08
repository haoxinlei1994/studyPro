package com.mrh.chapter4;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MdActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_md);
    }

    public void appbarLayout(View view) {
        startActivity(new Intent(this, AppBarActivity.class));
    }

    public void collapsingToolbarLayout(View view) {
        startActivity(new Intent(this, CollapsingToolbarActivity.class));
    }
}
