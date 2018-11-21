package com.thirdmodule.ninegridview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.lzy.ninegrid.NineGridView;
import com.thirdmodule.R;

/**
 * 九空格图片加载器
 *
 * @author lzy
 * create at 2018/9/14 17:53
 **/
public class NineGridImageLoader implements NineGridView.ImageLoader {

    private RequestOptions requestOptions;

    @Override
    public void onDisplayImage(Context context, ImageView imageView, String url) {
        if (requestOptions == null)
            requestOptions = new RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(R.drawable.ic__blk_place_holder).error(R.drawable.ic__blk_place_holder);
        showImage(Glide.with(context), url, imageView, requestOptions);
    }

    @Override
    public Bitmap getCacheImage(String url) {
        return null;
    }

    public static void showImage(RequestManager requestManager, String imageURL, final ImageView imageView, RequestOptions requestOptions) {
        requestManager.load(imageURL).apply(requestOptions).into(imageView);
    }
}
