package com.common.utils;


import com.blankj.utilcode.util.EncryptUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Liu on 2016/10/23.
 */
public class TestData {

    //横图
    public static String[] imgsH = {
            "http://c.hiphotos.baidu.com/image/pic/item/9c16fdfaaf51f3deb617016496eef01f3a29790c.jpg",
            "http://e.hiphotos.baidu.com/image/pic/item/b999a9014c086e06613eab4b00087bf40ad1cb18.jpg",
            "http://f.hiphotos.baidu.com/image/pic/item/0df3d7ca7bcb0a46dfd3acd76f63f6246a60af6e.jpg",
            "http://g.hiphotos.baidu.com/image/pic/item/54fbb2fb43166d2219dec065442309f79152d292.jpg",
            "http://f.hiphotos.baidu.com/image/pic/item/b2de9c82d158ccbf79a00f8c1cd8bc3eb1354163.jpg",
            "http://h.hiphotos.baidu.com/image/pic/item/4610b912c8fcc3cef4e772759745d688d43f2054.jpg",
            "http://f.hiphotos.baidu.com/image/pic/item/c8ea15ce36d3d539eba5c1dc3887e950352ab011.jpg",
            "http://h.hiphotos.baidu.com/image/pic/item/a2cc7cd98d1001e9460fd63bbd0e7bec54e797d7.jpg",
            "http://e.hiphotos.baidu.com/image/pic/item/18d8bc3eb13533fa3e01489aadd3fd1f40345bcb.jpg",
            "http://a.hiphotos.baidu.com/image/pic/item/cdbf6c81800a19d814dcb35431fa828ba71e4687.jpg",
            "http://e.hiphotos.baidu.com/image/pic/item/b7fd5266d016092477539711d60735fae6cd3441.jpg",
            "http://g.hiphotos.baidu.com/image/h%3D200/sign=f89cc11abb389b5027ffe752b535e5f1/c8177f3e6709c93dbe82d5f39d3df8dcd1005446.jpg",
            "http://h.hiphotos.baidu.com/image/pic/item/9d82d158ccbf6c81c79f78e9be3eb13533fa4002.jpg",
            "http://c.hiphotos.baidu.com/image/pic/item/9c16fdfaaf51f3deb617016496eef01f3a29790c.jpg",
            "http://e.hiphotos.baidu.com/image/pic/item/b999a9014c086e06613eab4b00087bf40ad1cb18.jpg",
            "http://f.hiphotos.baidu.com/image/pic/item/cb8065380cd791234275326baf345982b2b7801c.jpg",
            "http://e.hiphotos.baidu.com/image/pic/item/86d6277f9e2f0708ecf04d0eeb24b899a801f2c3.jpg",
            "http://b.hiphotos.baidu.com/image/pic/item/cb8065380cd791230678766aaf345982b2b78010.jpg",
            "http://b.hiphotos.baidu.com/image/pic/item/a6efce1b9d16fdfa4e075fe3b68f8c5494ee7b50.jpg",
            "http://e.hiphotos.baidu.com/image/pic/item/78310a55b319ebc4133915d58026cffc1e17163c.jpg",
            "http://f.hiphotos.baidu.com/image/pic/item/d50735fae6cd7b899b6bd2850d2442a7d9330eb4.jpg",
            "http://e.hiphotos.baidu.com/image/pic/item/b7003af33a87e9506992c66612385343fbf2b436.jpg",
            "http://a.hiphotos.baidu.com/image/pic/item/37d12f2eb9389b507ff8874d8735e5dde7116ead.jpg",
            "http://a.hiphotos.baidu.com/image/pic/item/71cf3bc79f3df8dc39cb6295cf11728b461028c4.jpg",
            "http://f.hiphotos.baidu.com/image/pic/item/32fa828ba61ea8d358824a0d950a304e251f5812.jpg",
            "http://f.hiphotos.baidu.com/image/pic/item/b2de9c82d158ccbf79a00f8c1cd8bc3eb1354163.jpg",
            "http://a.hiphotos.baidu.com/image/pic/item/0b46f21fbe096b63ae14827609338744ebf8ac83.jpg",
            "http://f.hiphotos.baidu.com/image/h%3D200/sign=a2c37cfc0846f21fd6345953c6246b31/00e93901213fb80e0ee553d034d12f2eb9389484.jpg",
            "http://h.hiphotos.baidu.com/image/pic/item/9d82d158ccbf6c81c79f78e9be3eb13533fa4002.jpg"

    };

    //竖图
    public static String[] imgsV = {
            "http://h.hiphotos.baidu.com/image/pic/item/f9dcd100baa1cd11dd1855cebd12c8fcc2ce2db5.jpg",
            "http://f.hiphotos.baidu.com/image/pic/item/060828381f30e924bd73bbdf48086e061c95f70c.jpg",
            "http://a.hiphotos.baidu.com/image/pic/item/d0c8a786c9177f3e675e7e2b75cf3bc79f3d56a9.jpg",
            "http://e.hiphotos.baidu.com/image/pic/item/64380cd7912397dd25024f305c82b2b7d0a2878f.jpg",
            "http://a.hiphotos.baidu.com/image/pic/item/d6ca7bcb0a46f21fcee6b957f4246b600d33aee0.jpg",
            "http://c.hiphotos.baidu.com/image/pic/item/f31fbe096b63f6240191d71c8544ebf81b4ca3e5.jpg",
            "http://e.hiphotos.baidu.com/image/pic/item/8d5494eef01f3a29913f38ca9b25bc315c607c3b.jpg",
            "http://a.hiphotos.baidu.com/image/pic/item/08f790529822720e5cc83afe79cb0a46f21fabb4.jpg",
            "http://e.hiphotos.baidu.com/image/pic/item/8b13632762d0f7031db73fdc0afa513d2697c5ad.jpg",
            "http://a.hiphotos.baidu.com/image/pic/item/38dbb6fd5266d016291d1037952bd40735fa35ac.jpg",
            "http://a.hiphotos.baidu.com/image/pic/item/267f9e2f070828385f623e10ba99a9014c08f143.jpg",
            "http://g.hiphotos.baidu.com/image/pic/item/810a19d8bc3eb135f59564bfa41ea8d3fd1f4460.jpg",
            "http://f.hiphotos.baidu.com/image/pic/item/6a600c338744ebf8af3cc53fdbf9d72a6059a765.jpg",
            "http://h.hiphotos.baidu.com/image/pic/item/6609c93d70cf3bc7b09b77ecd300baa1cd112a12.jpg",
            "http://c.hiphotos.baidu.com/image/pic/item/8b13632762d0f7030bf12e9a0dfa513d2697c531.jpg",
            "http://a.hiphotos.baidu.com/image/pic/item/55e736d12f2eb938d3de795ad0628535e4dd6fe2.jpg",
            "http://c.hiphotos.baidu.com/image/pic/item/78310a55b319ebc4856784ed8126cffc1e1716a2.jpg",
            "http://b.hiphotos.baidu.com/image/pic/item/6a63f6246b600c3306832dc8184c510fd9f9a13c.jpg",
            "http://h.hiphotos.baidu.com/image/pic/item/0823dd54564e9258de0520ee9e82d158ccbf4ef8.jpg",
            "http://b.hiphotos.baidu.com/image/pic/item/810a19d8bc3eb13522a819bba41ea8d3fd1f4446.jpg",
            "http://g.hiphotos.baidu.com/image/pic/item/d009b3de9c82d15872bc84b8820a19d8bc3e42f9.jpg",
            "http://b.hiphotos.baidu.com/image/pic/item/4afbfbedab64034f499e3506adc379310a551d5a.jpg",
            "http://f.hiphotos.baidu.com/image/pic/item/72f082025aafa40f468f193ba964034f79f0198e.jpg",
            "http://h.hiphotos.baidu.com/image/pic/item/a2cc7cd98d1001e9460fd63bbd0e7bec54e797d7.jpg",
            "http://c.hiphotos.baidu.com/image/pic/item/cefc1e178a82b901b0c2f206768da9773912ef9c.jpg",
            "http://d.hiphotos.baidu.com/image/pic/item/c8177f3e6709c93d5b13b5b19a3df8dcd0005496.jpg",
            "http://b.hiphotos.baidu.com/image/pic/item/5243fbf2b211931331292a4a61380cd790238de6.jpg",
            "http://f.hiphotos.baidu.com/image/pic/item/f636afc379310a5566becb8fb24543a982261036.jpg",
            "http://b.hiphotos.baidu.com/image/pic/item/d62a6059252dd42a912da008063b5bb5c9eab869.jpg",


    };


    //视频url
    public static String[] videos = {
            "http://video.jiecao.fm/8/17/bGQS3BQQWUYrlzP1K4Tg4Q__.mp4",
            "http://video.jiecao.fm/8/17/%E6%8A%AB%E8%90%A8.mp4",
            "http://video.jiecao.fm/8/18/%E5%A4%A7%E5%AD%A6.mp4",
            "http://video.jiecao.fm/8/16/%E8%B7%B3%E8%88%9E.mp4",
            "http://video.jiecao.fm/8/16/%E9%B8%AD%E5%AD%90.mp4",
            "http://video.jiecao.fm/8/16/%E9%A9%BC%E8%83%8C.mp4",
            "http://video.jiecao.fm/8/16/%E4%BF%AF%E5%8D%A7%E6%92%91.mp4",
            "http://video.jiecao.fm/5/1/%E8%87%AA%E5%8F%96%E5%85%B6%E8%BE%B1.mp4",
            "http://gslb.miaopai.com/stream/ed5HCfnhovu3tyIQAiv60Q__.mp4",
            "http://video.jiecao.fm/11/23/xu/%E5%A6%B9%E5%A6%B9.mp4",
            "http://video.jiecao.fm/11/24/xin/-%2024%20-%20.mp4",
            "http://video.jiecao.fm/11/24/6/%E9%85%92%E9%A9%BE.mp4",
            "http://video.jiecao.fm/11/23/6/%E7%8B%97.mp4",
            "http://video.jiecao.fm/11/23/xu/%E7%94%B5%E5%BD%B1.mp4",
            "http://video.jiecao.fm/11/23/6/%E5%AD%A9%E5%AD%90.mp4",
            "http://video.jiecao.fm/11/24/xu/%E6%97%A5%E5%8E%86.mp4",
            "http://video.jiecao.fm/11/26/-iDareX.mp4",
            "http://video.jiecao.fm/11/24/6/%E5%AD%94%E6%98%8E%E7%81%AF.mp4"

    };

    public static String[] imgsTestV = {
            "http://img.mshishang.com/pics/2017/0416/1492321785767454.jpg",

    };

    public static String[] imgsTestH = {
            "https://up.enterdesk.com/edpic_source/31/20/63/31206360eee022b16464277ea21ac4ff.jpg",
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1539583825481&di=49d52381a0c6bd4ae71a4d0cf0d403c8&imgtype=0&src=http%3A%2F%2Fpic1.win4000.com%2Fwallpaper%2F2017-10-25%2F59f0003419d0b.jpg",
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1539583861517&di=bd0d2774ba9bff94cbd6a26fc9c47cb4&imgtype=0&src=http%3A%2F%2Fold.bz55.com%2Fuploads%2Fallimg%2F150106%2F139-1501060U420-50.jpg",
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1539583878461&di=449b6662098154cac7077914531f4ab6&imgtype=0&src=http%3A%2F%2Fimg.tuku.cn%2Ffile_thumb%2F201507%2Fm2015072012334643.jpg",
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1539583911272&di=22fa7c3c9669003cb8d0cb64969f2249&imgtype=0&src=http%3A%2F%2Fs8.sinaimg.cn%2Fmw690%2F005VWHVRgy6PDEqrH7167%26690",
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1539583932251&di=3ea21401ec96702dde9ec956d67c0f2b&imgtype=0&src=http%3A%2F%2Fimg4.duitang.com%2Fuploads%2Fitem%2F201507%2F25%2F20150725113008_t4veX.thumb.700_0.jpeg",
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1539583955411&di=48d53efbb0d1b03184d7f8f345a260d4&imgtype=0&src=http%3A%2F%2Fphoto.3761.com%2Fpictures%2F2015-01%2F83_1421044781.jpg",
            "http://img3.imgtn.bdimg.com/it/u=3864729721,2856756558&fm=26&gp=0.jpg",
    };
    public static String getimgsTestH(){
        int index = RandomUtils.getRandom(imgsTestH.length);
        return imgsTestH[index];
    }

    public static String getimgsTestV(){
        int index = RandomUtils.getRandom(imgsTestV.length);
        return imgsTestV[index];
    }



    public static String getImgUrlH() {
        int index = RandomUtils.getRandom(imgsH.length);
        return imgsH[index];
    }

    public static String getImgUrlV() {
        int index = RandomUtils.getRandom(imgsV.length);
        return imgsV[index];
    }

    public static String getImgUrlH(int index) {
        return imgsH[index];
    }

    public static String getImgUrlV(int index) {
        return imgsV[index];
    }

    static List<String> lists = Arrays.asList("测试数据1", "测试数据2", "测试数据3", "测试数据4", "测试数据5", "测试数据6");

    public static List<String> getListData() {
        return lists;
    }

    public static List<String> getListDataForImg(int size) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            list.add(imgsV[RandomUtils.getRandom(imgsV.length)]);
        }
        return list;
    }

    public static List<String> getListData(int dataSize) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < dataSize; i++) {
            list.add(getStr());
        }
        return list;
    }

    public static List<String> getListImage(int dataSize) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < dataSize; i++) {
            list.add(getImageUrl());
        }
        return list;
    }

    static String[] strs = {"好", "身", "少", "女", "美", "小", "妹", "姐", "色", "艳", "爱", "玩", "分", "约"};

    public static String getStr() {
        StringBuilder builder = new StringBuilder();
        int length = RandomUtils.getRandom(2, 6);
        for (int i = 0; i < length; i++) {
            int index = RandomUtils.getRandom(strs.length);
            builder.append(strs[index]);
        }
        return builder.toString();
    }

    public static String getVideoUrl() {
        int index = RandomUtils.getRandom(videos.length);
        return videos[index];
    }

    public static String getImageUrl() {
        int index = RandomUtils.getRandom(imgsV.length);
        return imgsV[index];
    }

    public static List<String> getFlowTitle(){
        List<String> mList = new ArrayList<>();
        mList.add("鲁班七号");
        mList.add("元歌");
        mList.add("白起");
        mList.add("小乔");
        mList.add("雅典娜");
        mList.add("大乔");
        mList.add("百里守约");
        mList.add("百里玄策");
        mList.add("刘备");
        return mList;
    }

    public static void main(String[] args) {
    }


}
