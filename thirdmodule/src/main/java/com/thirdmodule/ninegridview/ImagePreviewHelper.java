package com.thirdmodule.ninegridview;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.blankj.utilcode.util.BarUtils;
import com.common.utils.CollectionUtils;
import com.lzy.ninegrid.ImageInfo;
import com.lzy.ninegrid.NineGridView;
import com.lzy.ninegrid.preview.ImagePreviewActivity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ImagePreviewHelper {

    /**
     * 一般的图片预览
     **/
    public static <T extends IPreviewImageListener> void previewImage(Activity activity, List<T> imageList, int selIndex) {
        if (CollectionUtils.isEmpty(imageList)) return;
        List<ImageInfo> imageInfos = new ArrayList<>();
        for (T iPreviewImageListener : imageList) {
            ImageInfo imageInfo = new ImageInfo();
            imageInfo.bigImageUrl = iPreviewImageListener.getImgUrl();
            imageInfo.thumbnailUrl = iPreviewImageListener.getImgUrl();
            imageInfos.add(imageInfo);
        }

        toPreview(activity, imageInfos, selIndex);
    }

    /**
     * 带对应预览view缩放动画的
     **/
    public static <T extends IPreviewImageListener> void previewImage(Activity activity, List<View> imageViewList, List<T> imageList, int selIndex) {
        previewImage(activity, imageViewList, imageList.size(), imageList, selIndex);
        NineGridView nineGridView;
    }

    public static <T extends IPreviewImageListener> void previewImage(Activity activity, List<View> imageViewList, int maxShowNum, List<T> imageList, int selIndex) {
        if (CollectionUtils.isEmpty(imageList)) return;
        List<ImageInfo> imageInfos = new ArrayList<>();

        for (int i = 0; i < imageList.size(); i++) {
            ImageInfo imageInfo = new ImageInfo();
            IPreviewImageListener iPreviewImageListener = imageList.get(i);
            imageInfo.bigImageUrl = iPreviewImageListener.getImgUrl();
            imageInfo.thumbnailUrl = iPreviewImageListener.getImgUrl();

            View imageView;
            if (i < maxShowNum) {
                imageView = imageViewList.get(i);
            } else {
                //如果图片的数量大于显示的数量，则超过部分的返回动画统一退回到最后一个图片的位置
                imageView = imageViewList.get(maxShowNum - 1);
            }
            imageInfo.imageViewWidth = imageView.getWidth();
            imageInfo.imageViewHeight = imageView.getHeight();
            int[] points = new int[2];
            imageView.getLocationInWindow(points);
            imageInfo.imageViewX = points[0];
            imageInfo.imageViewY = points[1] - BarUtils.getStatusBarHeight();
            imageInfos.add(imageInfo);
        }

        toPreview(activity, imageInfos, selIndex);
    }

    /**
     * 针对NineGridView的预览
     **/
    public static <T extends IPreviewImageListener> void previewImage(Activity activity, NineGridView nineGridView, List<T> imageList, int selIndex) {
        if (CollectionUtils.isEmpty(imageList)) return;
        List<ImageInfo> imageInfos = new ArrayList<>();

        for (int i = 0; i < imageList.size(); i++) {
            ImageInfo imageInfo = new ImageInfo();
            IPreviewImageListener iPreviewImageListener = imageList.get(i);
            imageInfo.bigImageUrl = iPreviewImageListener.getImgUrl();
            imageInfo.thumbnailUrl = iPreviewImageListener.getImgUrl();

            View imageView;
            if (i < nineGridView.getMaxSize()) {
                imageView = nineGridView.getChildAt(i);
            } else {
                //如果图片的数量大于显示的数量，则超过部分的返回动画统一退回到最后一个图片的位置
                imageView = nineGridView.getChildAt(nineGridView.getMaxSize() - 1);
            }
            imageInfo.imageViewWidth = imageView.getWidth();
            imageInfo.imageViewHeight = imageView.getHeight();
            int[] points = new int[2];
            imageView.getLocationInWindow(points);
            imageInfo.imageViewX = points[0];
            imageInfo.imageViewY = points[1] - BarUtils.getStatusBarHeight();
            imageInfos.add(imageInfo);
        }

        Intent intent = new Intent(activity, ImagePreviewActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(ImagePreviewActivity.IMAGE_INFO, (Serializable) imageInfos);
        bundle.putInt(ImagePreviewActivity.CURRENT_ITEM, selIndex);
        intent.putExtras(bundle);
        activity.startActivity(intent);
        activity.overridePendingTransition(0, 0);

        toPreview(activity, imageInfos, selIndex);
    }

    public static void toPreview(Activity activity, List<ImageInfo> imageInfos, int selIndex) {
        Intent intent = new Intent(activity, ImagePreviewActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(ImagePreviewActivity.IMAGE_INFO, (Serializable) imageInfos);
        bundle.putInt(ImagePreviewActivity.CURRENT_ITEM, selIndex);
        intent.putExtras(bundle);
        activity.startActivity(intent);
        activity.overridePendingTransition(0, 0);
    }


}
