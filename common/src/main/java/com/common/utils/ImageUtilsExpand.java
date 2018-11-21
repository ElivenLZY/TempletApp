package com.common.utils;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;

import com.blankj.utilcode.util.ImageUtils;

import java.io.File;

public class ImageUtilsExpand {

    /**
     * 保存图片到系统相册
     *
     * @author lzy
     * create at 2018/4/20 18:23
     **/
    public static boolean saveImageToGallery(Bitmap bmp, String fileName) {
        // 首先保存图片
        File file = BasePathUtils.getTradQrCodeSaveFile(fileName);

        boolean isSuccess = ImageUtils.save(bmp, file, Bitmap.CompressFormat.JPEG);

        //保存图片后发送广播通知更新数据库
        Uri uri = Uri.fromFile(file);
        UIUtils.getAppContext().sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, uri));
        return isSuccess;
    }
}
