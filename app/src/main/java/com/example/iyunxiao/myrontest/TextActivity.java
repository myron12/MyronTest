package com.example.iyunxiao.myrontest;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by iyunxiao on 2018/4/13.
 */

public class TextActivity extends AppCompatActivity {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);
        initView();
    }
    private TextView mtext;
    private void initView(){
        mtext= (TextView) findViewById(R.id.texts);
        mtext.setMovementMethod(ScrollingMovementMethod.getInstance());
        mtext.setText("健康坎坎坷坷坎健康坎坎坷坷坎健康坎坎坷坷坎健康坎坎坷坷坎健康坎坎坷坷坎健康坎坎坷坷坎健康坎坎坷坷坎健康坎坎坷坷坎健康坎坎坷坷坎健康坎坎坷坷坎");
    }
}
