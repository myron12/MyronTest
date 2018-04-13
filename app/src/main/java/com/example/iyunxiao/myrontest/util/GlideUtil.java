package com.example.iyunxiao.myrontest.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.load.resource.bitmap.FitCenter;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.example.iyunxiao.myrontest.glide.GlideBackgroundTransform;
import com.example.iyunxiao.myrontest.glide.GlideBlurTransform;
import com.example.iyunxiao.myrontest.glide.GlideCustomCircleTransform;
import com.example.iyunxiao.myrontest.glide.GlideMaskTransform;


/**
 * 头像url包含 密钥，针对密钥问题统一用GlideUrlNoToken对url进行处理，当url为空时 GlideUrlNoToken报错，
 * 故使用 GlideUtil统一进行处理
 */

public class GlideUtil {

    /**
     * 在图片上添加颜色蒙层
     *
     * @param context
     * @param url：图片url
     * @param color：需要添加的颜色
     * @param placeHolderResId：占位图
     * @param imageView：图片显示的ImageView
     * @return
     */

    public static void displayPaperMaskImage(Context context, String url, int color, int placeHolderResId, ImageView imageView) {
        RequestOptions options = new RequestOptions()
                .placeholder(placeHolderResId)
                .dontAnimate()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .transform(new GlideMaskTransform(color));
        if (TextUtils.isEmpty(url)) {
            Glide.with(context).load(url).apply(options).into(imageView);
        } else {
            Glide.with(context)
                    .load(new GlideUrlNoToken(url))
                    .into(imageView);
        }
    }

    /**
     * 显示圆形图片
     *
     * @param context
     * @param url：图片url
     * @param placeHolderResId：占位图
     * @param imageView
     * @return
     */
    public static void displayCircleImage(Context context, String url, int placeHolderResId, ImageView imageView) {
        RequestOptions options = new RequestOptions()
                .placeholder(placeHolderResId)
                .dontAnimate()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .transform(new CircleCrop());
        if (TextUtils.isEmpty(url)) {
            Glide.with(context).load(url).apply(options).into(imageView);
        } else {
            Glide.with(context)
                    .load(new GlideUrlNoToken(url))
                    .into(imageView);
        }
    }

    /**
     * 显示带有圆角的图片
     *
     * @param context
     * @param url：图片url
     * @param cornerDpi：圆角角度，以dp为单位
     * @param cornerMode：显示圆角的mode,详情{@link GlideCustomCircleTransform.CORNER_MODE}
     * @param placeHolderResId：占位图
     * @param imageView
     * @return
     */
    public static void displayCustomCircleImage(Context context, String url, float cornerDpi, @GlideCustomCircleTransform.CORNER_MODE int cornerMode, int placeHolderResId, ImageView imageView) {
        RequestOptions options = new RequestOptions()
                .placeholder(placeHolderResId)
                .dontAnimate()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .transforms(new GlideCustomCircleTransform(context, cornerDpi, cornerMode), new FitCenter());
        if (TextUtils.isEmpty(url)) {
            Glide.with(context).load(url).apply(options).into(imageView);
        } else {
            Glide.with(context)
                    .load(new GlideUrlNoToken(url))
                    .into(imageView);
        }
    }

    /**
     * 为图片设置指定的Transform并显示
     *
     * @param context
     * @param url：图片url
     * @param placeHolderResId：占位图
     * @param imageView
     * @param transformation：指定的图片转换的Transform
     * @return
     */
    public static void displayTransformImage(Context context, String url, int placeHolderResId, ImageView imageView, BitmapTransformation transformation) {
        RequestOptions options = new RequestOptions()
                .placeholder(placeHolderResId)
                .dontAnimate()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .transform(transformation);
        if (TextUtils.isEmpty(url)) {
            Glide.with(context).load(url).apply(options).into(imageView);
        } else {
            Glide.with(context)
                    .load(new GlideUrlNoToken(url))
                    .into(imageView);
        }
    }

    /**
     * 加载图片：设置加载listener
     *
     * @param context
     * @param url
     * @param imageView
     * @param requestListener：请求listener
     * @return
     */
    public static void displayNormalImage(Context context, String url, ImageView imageView,

                                          RequestListener<Drawable> requestListener) {
        RequestOptions options = new RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .fitCenter();
        if (TextUtils.isEmpty(url)) {
            requestListener.onLoadFailed(new GlideException("url is empty"), null, null, false);
            Glide.with(context)
                    .load(url)
                    .apply(options)
                    .into(imageView);
        } else {
            Glide.with(context)
                    .load(new GlideUrlNoToken(url))
                    .listener(requestListener)
                    .apply(options)
                    .into(imageView);
        }
    }

    /**
     * 加载图片：有占位图
     *
     * @param context
     * @param url
     * @param placeHolderResId
     * @param imageView
     * @return
     */
    public static void displayNormalImage(Context context, String url, int placeHolderResId, ImageView imageView) {
        RequestOptions options = new RequestOptions()
                .placeholder(placeHolderResId)
                .dontAnimate()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .fitCenter();
        if (TextUtils.isEmpty(url)) {
            Glide.with(context).load(url).apply(options).into(imageView);
        } else {
            Glide.with(context)
                    .load(new GlideUrlNoToken(url))
                    .into(imageView);
        }
    }

    /**
     * 加载图片：没有占位图
     *
     * @param context
     * @param url
     * @param imageView
     * @return
     */
    public static void displayNormalImage(Context context, String url, ImageView imageView) {
        RequestOptions options = new RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .fitCenter();
        if (TextUtils.isEmpty(url)) {
            Glide.with(context).load(url).apply(options).into(imageView);
        } else {
            Glide.with(context)
                    .load(new GlideUrlNoToken(url))
                    .into(imageView);
        }
    }

    public static void displayLongImage(Context context, String url, ImageView imageView) {
        RequestOptions options = new RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .transform(new GlideBackgroundTransform(context, Color.TRANSPARENT))
                .diskCacheStrategy(DiskCacheStrategy.ALL);
        if (TextUtils.isEmpty(url)) {
            Glide.with(context).load(url).apply(options).into(imageView);
        } else {
            Glide.with(context)
                    .load(new GlideUrlNoToken(url))
                    .into(imageView);
        }
    }


    /**
     * 显示带有高斯模糊效果的图片
     *
     * @param context
     * @param url：图片url
     * @param placeHolderResId：占位图
     * @param imageView
     * @param color：设置模糊是的色值
     * @param radius：模糊半径，取值范围为[1,25]，值越大，模糊程度越高
     * @return
     */
    public static void displayBlurImage(Context context, String url, int placeHolderResId, ImageView imageView, @ColorInt int color, int radius) {
        if (TextUtils.isEmpty(url)) {
            RequestOptions options = new RequestOptions()
                    .fitCenter()
                    .placeholder(placeHolderResId)
                    .diskCacheStrategy(DiskCacheStrategy.ALL);
            Glide.with(context)
                    .load(url)
                    .apply(options)
                    .into(imageView);
        } else {
            RequestOptions options = new RequestOptions()
                    .placeholder(placeHolderResId)
                    .transforms(new GlideBlurTransform(context, color, radius), new FitCenter())
                    .diskCacheStrategy(DiskCacheStrategy.ALL);
            Glide.with(context)
                    .load(url)
                    .apply(options)
                    .into(imageView);
        }
    }

    public static void displayPaperOriginImage(Context context, String url, ImageView imageView,
                                               RequestListener<Drawable> requestListener) {
        RequestOptions options = new RequestOptions()
                .transform(new GlideBackgroundTransform(context, Color.WHITE))
                .diskCacheStrategy(DiskCacheStrategy.ALL);
        if (TextUtils.isEmpty(url)) {
            requestListener.onLoadFailed(new GlideException("url is empty"), null, null, false);
            Glide.with(context)
                    .load(url)
                    .apply(options)
                    .into(imageView);
        } else {
            Glide.with(context)
                    .load(new GlideUrlNoToken(url))
                    .listener(requestListener)
                    .apply(options)
                    .into(imageView);
        }

    }

    /**
     * 为Bitmap添加颜色蒙层
     *
     * @param bitmap：需要处理的图片
     * @param color：蒙层颜色
     * @return 处理完成的图片
     */
    public static Bitmap getMaskBitmap(Bitmap bitmap, int color) {
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        paint.setShader(new BitmapShader(bitmap, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP));
        paint.setAntiAlias(true);
        RectF rectF = new RectF(0f, 0f, bitmap.getWidth(), bitmap.getHeight());
        canvas.drawRect(rectF, paint);
        canvas.drawColor(color);
        return bitmap;
    }

    /**
     * 显示GIF 图
     *
     * @param context
     * @param resId     资源ID
     * @param imageView
     * @return
     */
    public static Target<GifDrawable> displayGif(Context context, int resId, ImageView imageView) {
        RequestOptions options = new RequestOptions().diskCacheStrategy(DiskCacheStrategy.NONE);
        return Glide.with(context)
                .asGif()
                .load(resId)
                .apply(options)
                .into(imageView);

    }

}
