package com.example.iyunxiao.myrontest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewStub;
import android.widget.Button;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.RequestOptions;
import com.example.iyunxiao.myrontest.glide.GlideBackgroundTransform;
import com.github.chrisbanes.photoview.PhotoView;


public class MainActivity extends AppCompatActivity {
    private Button mTextMy;
    private TextView mTextMys;

    private ViewStub mViewStub;
    String path= "https://inthecheesefactory.com/uploads/source/glidepicasso/cover.jpg";
    String path1="https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1523516714496&di=014eee225f8b51e7997651ff52a02e7b&imgtype=0&src=http%3A%2F%2Fpic19.photophoto.cn%2F20110426%2F0034034846955393_b.jpg";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    View view1;
    private PhotoView mPhotoView;

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
        mPhotoView = (PhotoView) findViewById(R.id.photo);
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
//        Log.e("info","abc");

//        GlideApp.with(this)
//                .load(path1)
//                .transform(new MyGlideTransform(getResources().getColor(R.color.colorAccent)))
//                .into(mPhotoView);

//        GlideApp.with(this)
//                .load(path1)
//                .into(mPhotoView);

//        GlideApp.with(this)
//                .load(path1)
//                .transform(new RotateTransformation(180))
//                .transition(new DrawableTransitionOptions().crossFade(1000))//渐显效果
//                .into(mPhotoView);

//        Glide.with(this)
//                .load("http://img1.imgtn.bdimg.com/it/u=594559231,2167829292&fm=27&gp=0.jpg")
//                .apply(RequestOptions.bitmapTransform(new GlideBlurformation(this)))
//                .into(mPhotoView);

//        Glide.with(this)
//                .load(path1)
//                .apply(RequestOptions.bitmapTransform(new GlideMaskTransform(getResources().getColor(R.color.c13_a93))))
//                .into(mPhotoView);

//        Glide.with(this)
//                .load(path1)
//                .apply(RequestOptions.bitmapTransform(new GlideBlurTransform(this,getResources().getColor(R.color.c13_a93),15)))
//                .into(mPhotoView);
        Glide.with(this)
                .load(path1)
                .apply(RequestOptions.bitmapTransform(new GlideBackgroundTransform(this,getResources().getColor(R.color.c13_a93))))
                .into(mPhotoView);

    }

}
