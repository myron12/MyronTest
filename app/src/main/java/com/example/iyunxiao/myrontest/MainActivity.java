package com.example.iyunxiao.myrontest;

import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewStub;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Button mTextMy;
    private TextView mTextMys;

    private ViewStub mViewStub;

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
        mTextMy.setOnClickListener((v) -> {
        });

        init();
    }
    String str ;

    private void init() {
//        1.
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("Before Java8, too much code for too little to do");
//            }
//        }).start();
//        new Thread(()->System.out.println("In Java8, Lambda expression rocks !!")).start();

//        2.
//        List<String> features = Arrays.asList("Lambdas", "Default Method", "Stream API", "Date and Time API");
//        for (String feature : features) {
//            System.out.println(feature);
//        }
//        List<String> features = Arrays.asList("Lambdas", "Default Method", "Stream API", "Date and Time API");
//       features.forEach(n->System.out.println(n));

str = "myron";

str = "maqihang";

    }

}
