package com.example.iyunxiao.myrontest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewStub;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Button mTextMy;
    private TextView mTextMys;

    private ViewStub mViewStub;
    private String str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    View view1;

    private void initView() {
        mTextMy = (Button) findViewById(R.id.text_my);
        mTextMys = (TextView) findViewById(R.id.text_mys);
        mTextMys.setText(str == null ? "暂未获取" : str);
//        mViewStub = (ViewStub) findViewById(R.id.viewStub_loading);
//        mTextMy.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                mViewStub.inflate();
//                view1 = findViewById(R.id.inflated);
//                TextView textView = (TextView) view1.findViewById(R.id.tv_my);
//                textView.setText("hkhjjkjjjjjj");
//            }
//        });


    }

}
