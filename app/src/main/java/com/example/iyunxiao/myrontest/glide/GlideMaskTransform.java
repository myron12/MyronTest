package com.example.iyunxiao.myrontest.glide;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.NonNull;
import android.util.Log;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;

import java.security.MessageDigest;

/**
 * 在图片上添加颜色蒙层的Transform
 */
public class GlideMaskTransform extends BitmapTransformation {
    private int mColor;

    public GlideMaskTransform(int mColor) {
        this.mColor = mColor;
    }

    @Override
    protected Bitmap transform(@NonNull BitmapPool pool, @NonNull Bitmap toTransform, int outWidth, int outHeight) {
        try {
            return maskBitmap(pool, toTransform);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return toTransform;
    }

    private Bitmap maskBitmap(BitmapPool pool, Bitmap source) throws Exception {
        if (source == null) return null;
        // TODO this could be acquired from the pool too
        int width = source.getWidth();
        int height = source.getHeight();
        Log.e("info", "======width========" + width + "====height====" + height);

        int size = Math.max(width, height);
        if (size > 4096) {//缩放图片到4096.
            Log.e("info", "=======ifssss=======");

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
            Paint paint = new Paint();
            paint.setShader(new BitmapShader(bitmap, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP));
            paint.setAntiAlias(true);
            RectF rectF = new RectF(0f, 0f, width, height);
            canvas.drawRect(rectF, paint);
            canvas.drawColor(mColor);
            return result;
        } else {
            Bitmap result = pool.get(width, height, Bitmap.Config.ARGB_8888);
            if (result == null) {
                result = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            }
            Log.e("info", "=======elsesssssss=======");
            Canvas canvas = new Canvas(result);
            Paint paint = new Paint();
            paint.setShader(new BitmapShader(source, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP));
            paint.setAntiAlias(true);
            RectF rectF = new RectF(0f, 0f, width, height);
            canvas.drawRect(rectF, paint);
            canvas.drawColor(mColor);
            return result;
        }
    }


    @Override
    public void updateDiskCacheKey(MessageDigest messageDigest) {

    }
}
