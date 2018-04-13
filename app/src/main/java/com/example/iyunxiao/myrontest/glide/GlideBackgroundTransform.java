package com.example.iyunxiao.myrontest.glide;

/**
 * Created by iyunxiao on 2018/4/12.
 */

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;

import java.security.MessageDigest;

/**
 * 为图片添加背景的Transform
 */
public class GlideBackgroundTransform extends BitmapTransformation {
    private static final String ID = "com.example.iyunxiao.myrontest.glide.GlideBackgroundTransform";
    private static final byte[] ID_BYTES = ID.getBytes(CHARSET);
    private int mColor;

    public GlideBackgroundTransform(Context context, @ColorInt int color) {
        mColor = color;
    }
    @Override
    protected Bitmap transform(@NonNull BitmapPool pool, @NonNull Bitmap toTransform, int outWidth, int outHeight) {
        try {
            return circleCrop(pool, toTransform);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return toTransform;
    }

    private Bitmap circleCrop(BitmapPool pool, Bitmap source) throws Exception {
        if (source == null) return null;
        // TODO this could be acquired from the pool too
        int width = source.getWidth();
        int height = source.getHeight();

        int size = Math.max(width, height);
        if (size > 4096) {//缩放图片到4096.
            float scale = ((float) 4096) / size;
            Matrix matrix = new Matrix();
            matrix.postScale(scale, scale);
            Bitmap bitmap = Bitmap.createBitmap(source, 0, 0, width,
                    height, matrix, true);
            width = bitmap.getWidth();
            height = bitmap.getHeight();

            Bitmap result = pool.get(width, height, Bitmap.Config.ARGB_8888);
            if (result == null) {
                result = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            }
            Canvas canvas = new Canvas(result);
            canvas.drawColor(mColor);
            Paint paint = new Paint();
            paint.setShader(new BitmapShader(bitmap, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP));
            paint.setAntiAlias(true);
            RectF rectF = new RectF(0f, 0f, width, height);
            canvas.drawRect(rectF, paint);
            return result;
        }else {
            Bitmap result = pool.get(width, height, Bitmap.Config.ARGB_8888);
            if (result == null) {
                result = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            }
            Canvas canvas = new Canvas(result);
            canvas.drawColor(mColor);
            Paint paint = new Paint();
            paint.setShader(new BitmapShader(source, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP));
            paint.setAntiAlias(true);
            RectF rectF = new RectF(0f, 0f, width, height);
            canvas.drawRect(rectF, paint);
            return result;
        }
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof GlideBackgroundTransform;
    }

    @Override
    public int hashCode() {
        return ID.hashCode();
    }

    @Override
    public void updateDiskCacheKey(MessageDigest messageDigest) {
        messageDigest.update(ID_BYTES);
    }
}
