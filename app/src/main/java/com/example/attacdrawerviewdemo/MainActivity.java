package com.example.attacdrawerviewdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.attacdrawerview.AttacDrawerView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MoveTextView mViewById = findViewById(R.id.text_tv);
        AttacDrawerView mAttacDrawerView = new AttacDrawerView(this, R.layout.activity_main1);
        mAttacDrawerView.bindView(mViewById, .05f);

        MoveTextView mViewById2 = findViewById(R.id.text_tv2);
        AttacDrawerView mAttacDrawerView2 = new AttacDrawerView(this, R.layout.activity_main1);
        mAttacDrawerView2.bindView(mViewById2, .2f);

        MoveTextView mViewById3 = findViewById(R.id.text_tv3);
        AttacDrawerView mAttacDrawerView3 = new AttacDrawerView(this, R.layout.activity_main1);
        mAttacDrawerView3.bindView(mViewById3, .4f);

        MoveTextView mViewById4 = findViewById(R.id.text_tv4);
        AttacDrawerView mAttacDrawerView4 = new AttacDrawerView(this, R.layout.activity_main1);
        mAttacDrawerView4.bindView(mViewById4, .6f);

        MoveTextView mViewById5 = findViewById(R.id.text_tv5);
        AttacDrawerView mAttacDrawerView5 = new AttacDrawerView(this, R.layout.activity_main1);
        mAttacDrawerView5.bindView(mViewById5, .8f);

        MoveTextView mViewById6 = findViewById(R.id.text_tv6);
        AttacDrawerView mAttacDrawerView6 = new AttacDrawerView(this, R.layout.activity_main1);
        mAttacDrawerView6.bindView(mViewById6, .9f);
    }
}
