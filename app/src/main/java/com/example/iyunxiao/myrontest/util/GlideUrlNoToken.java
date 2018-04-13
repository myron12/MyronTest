package com.example.iyunxiao.myrontest.util;

import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.Headers;

import java.net.URL;

/**
 * Created by iyunxiao on 2018/4/13.
 */

public class GlideUrlNoToken extends GlideUrl {
    public GlideUrlNoToken(URL url) {
        super(url);
    }

    public GlideUrlNoToken(String url) {
        super(url);
    }
    public GlideUrlNoToken(String url, Headers headers) {
        super(url, headers);
    }


    public GlideUrlNoToken(URL url, Headers headers) {
        super(url, headers);
    }

    @Override
    public String getCacheKey() {
        String ksUrl = super.getCacheKey();
        if ((ksUrl.startsWith("http://kssws.ks-cdn.com/") || ksUrl.contains("kss.yunxiao.com"))
                && ksUrl.contains("?")
                && ksUrl.contains("KSSAccessKeyId")) {
            String url = ksUrl.substring(0, ksUrl.lastIndexOf("?"));
            return url;
        } else {
            return ksUrl;
        }
    }
}
