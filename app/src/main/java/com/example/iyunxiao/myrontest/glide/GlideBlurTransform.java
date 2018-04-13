package com.example.iyunxiao.myrontest.glide;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.example.iyunxiao.myrontest.blurry.internal.Blur;
import com.example.iyunxiao.myrontest.blurry.internal.BlurFactor;

import java.security.MessageDigest;

/**
 * 为图片添加高斯模糊效果的Transform
 */

public class GlideBlurTransform extends BitmapTransformation {
    private BlurFactor mFactor;
    private Context mContext;
    public GlideBlurTransform(Context context,int color ,int radius){
        mFactor = new BlurFactor();
        mFactor.color = color;
        mFactor.radius = radius;

//        mContext = context;//如果context是Activity,直接赋值会导致内存泄露，静态的Glide的实例会引用BitmapTransformation,简介引用context
        mContext=context.getApplicationContext();

    }
    @Override
    protected Bitmap transform(@NonNull BitmapPool pool, @NonNull Bitmap toTransform, int outWidth, int outHeight) {
       mFactor.width = toTransform.getWidth();
       mFactor.height = toTransform.getHeight();
       try {
           return Blur.of(mContext,toTransform,mFactor);
       }catch (Exception e){
           e.printStackTrace();
       }
       return toTransform;
    }

    @Override
    public void updateDiskCacheKey(MessageDigest messageDigest) {

    }
}
