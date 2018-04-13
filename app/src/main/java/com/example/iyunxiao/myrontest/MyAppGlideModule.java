package com.example.iyunxiao.myrontest;

import android.content.Context;
import android.os.Environment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.Registry;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.cache.DiskCache;
import com.bumptech.glide.load.engine.cache.ExternalCacheDiskCacheFactory;
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.module.AppGlideModule;
import com.bumptech.glide.request.target.ViewTarget;

/**
 * Created by iyunxiao on 2018/4/12.
 */

@GlideModule
public class MyAppGlideModule extends AppGlideModule {
    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
        // Apply options to the builder here.
        //内存缓存相关
        int memoryCacheSizeByte = 1024 * 1024 * 5;//5MB
        builder.setMemoryCache(new LruResourceCache(memoryCacheSizeByte));

        //设置磁盘缓存及其路径
        int MAX_CACHE_SIZE = 50 * 1024 * 1024;
        DiskCache.Factory factory = null;
        if (checkSDCard()) {
            if (checkSDCard()) {
                factory = new ExternalCacheDiskCacheFactory(context, Environment.DIRECTORY_PICTURES, MAX_CACHE_SIZE);
            } else {
                factory = new InternalCacheDiskCacheFactory(context, Environment.DIRECTORY_PICTURES, MAX_CACHE_SIZE);
            }
        }
        builder.setDiskCache(factory);
    }

    /**
     * 检查SD card
     *
     * @return
     */
    private boolean checkSDCard() {
        String externalStorageState = Environment.getExternalStorageState();
        if (externalStorageState != null) {
            return externalStorageState.equals(Environment.MEDIA_MOUNTED);
        }
        return false;
    }

    @Override
    public void registerComponents(Context context, Glide glide, Registry registry) {
        super.registerComponents(context, glide, registry);
    }

    @Override
    public boolean isManifestParsingEnabled() {
        //不使用清单配置的方式,减少初始化时间
        return false;
    }
}
