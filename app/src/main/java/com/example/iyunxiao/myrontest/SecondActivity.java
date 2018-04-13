package com.example.iyunxiao.myrontest;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.example.iyunxiao.myrontest.util.GlideUtil;

/**
 * Created by iyunxiao on 2018/4/13.
 */

public class SecondActivity extends AppCompatActivity {
    String path="https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1523516714496&di=014eee225f8b51e7997651ff52a02e7b&imgtype=0&src=http%3A%2F%2Fpic19.photophoto.cn%2F20110426%2F0034034846955393_b.jpg";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        initView();
        init();
    }
    private ImageView image_1,image_2,image_3,image_4,image_5;
    private void initView(){
        image_1 = (ImageView) findViewById(R.id.image_1);
        image_2 = (ImageView) findViewById(R.id.image_2);
        image_3 = (ImageView) findViewById(R.id.image_3);
        image_4 = (ImageView) findViewById(R.id.image_4);
        image_5 = (ImageView) findViewById(R.id.image_5);
    }

    private void init(){
        GlideUtil.displayNormalImage(this,path,R.mipmap.ic_launcher,image_1);
//        GlideUtil.displayBlurImage(this,path,R.mipmap.ic_launcher,image_2);

    }
}
