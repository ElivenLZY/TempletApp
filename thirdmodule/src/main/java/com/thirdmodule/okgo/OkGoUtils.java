package com.thirdmodule.okgo;

import android.app.Application;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.cookie.CookieJarImpl;
import com.lzy.okgo.cookie.store.DBCookieStore;
import com.lzy.okgo.interceptor.HttpLoggingInterceptor;
import com.lzy.okgo.model.HttpHeaders;
import com.lzy.okgo.request.DeleteRequest;
import com.lzy.okgo.request.GetRequest;
import com.lzy.okgo.request.HeadRequest;
import com.lzy.okgo.request.OptionsRequest;
import com.lzy.okgo.request.PatchRequest;
import com.lzy.okgo.request.PostRequest;
import com.lzy.okgo.request.PutRequest;
import com.lzy.okgo.request.TraceRequest;

import java.io.File;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;

/**
 * Created by Administrator on 2017/12/19.
 */

public class OkGoUtils {

    public static final MediaType MEDIA_TYPE_FORM = MediaType.parse("application/x-www-form-urlencoded");

    public static String BASE_SERVER = "";

    public static void initOkGo(Application app, String baseServer,HttpHeaders headers) {
        BASE_SERVER = baseServer;
        //以下设置的所有参数是全局参数,同样的参数可以在请求的时候再设置一遍,那么对于该请求来讲,请求中的参数会覆盖全局参数
        //好处是全局参数统一,特定请求可以特别定制参数
        try {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            //log相关
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor("OkGo");
            loggingInterceptor.setPrintLevel(HttpLoggingInterceptor.Level.BODY);        //log打印级别，决定了log显示的详细程度
            loggingInterceptor.setColorLevel(Level.INFO);                               //log颜色级别，决定了log在控制台显示的颜色
            builder.addInterceptor(loggingInterceptor);                                 //添加OkGo默认debug日志
            //第三方的开源库，使用通知显示当前请求的log，不过在做文件下载的时候，这个库好像有问题，对文件判断不准确
            //builder.addInterceptor(new ChuckInterceptor(this));

            //超时时间设置，默认60秒
            builder.readTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS);      //全局的读取超时时间
            builder.writeTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS);     //全局的写入超时时间
            builder.connectTimeout(10 * 1000, TimeUnit.MILLISECONDS);   //全局的连接超时时间

            //自动管理cookie（或者叫session的保持），以下几种任选其一就行
            //builder.cookieJar(new CookieJarImpl(new SPCookieStore(this)));            //使用sp保持cookie，如果cookie不过期，则一直有效
            builder.cookieJar(new CookieJarImpl(new DBCookieStore(app)));              //使用数据库保持cookie，如果cookie不过期，则一直有效
            //builder.cookieJar(new CookieJarImpl(new MemoryCookieStore()));            //使用内存保持cookie，app退出后，cookie消失

            // 其他统一的配置
            // 详细说明看GitHub文档：https://github.com/jeasonlzy/
            OkGo.getInstance().init(app)                           //必须调用初始化
                    .setOkHttpClient(builder.build())               //建议设置OkHttpClient，不设置会使用默认的
                    .setCacheMode(CacheMode.NO_CACHE)               //全局统一缓存模式，默认不使用缓存，可以不传
//                    .setCacheTime(CacheEntity.CACHE_NEVER_EXPIRE)   //全局统一缓存时间，默认永不过期，可以不传
                    .setRetryCount(3)             //全局统一超时重连次数，默认为三次，那么最差的情况会请求4次(一次原始请求，三次重连请求)，不需要可以设置为0
                    .addCommonHeaders(headers);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 通过 ‘？’ 和 ‘/’ 判断文件名
     * http://mavin-manzhan.oss-cn-hangzhou.aliyuncs.com/1486631099150286149.jpg?x-oss-process=image/watermark,image_d2F0ZXJtYXJrXzIwMF81MC5wbmc
     */
    public static String getUrlFileName(String url) {
        String filename = null;
        String[] strings = url.split("/");
        for (String string : strings) {
            if (string.contains("?")) {
                int endIndex = string.indexOf("?");
                if (endIndex != -1) {
                    filename = string.substring(0, endIndex);
                    return filename;
                }
            }
        }
        if (strings.length > 0) {
            filename = strings[strings.length - 1];
        }
        return filename;
    }

    /**
     * 通过File接口解析url中的文件名
     */
    public static String getUrlFileNameSimple(String url) {
        File tempFile = new File(url.trim());
        String fileName = tempFile.getName();
        return fileName;
    }

    /**
     * get请求
     */
    public static <T> GetRequest<T> get(String url) {
        if (!url.startsWith("http")) {
            url = BASE_SERVER + url;
        }
        return new GetRequest<>(url);
    }

    /**
     * post请求
     */
    public static <T> PostRequest<T> post(String url) {
        if (!url.startsWith("http")) {
            url = BASE_SERVER + url;
        }
        return new PostRequest<>(url);
    }

    /**
     * put请求
     */
    public static <T> PutRequest<T> put(String url) {
        if (!url.startsWith("http")) {
            url = BASE_SERVER + url;
        }
        return new PutRequest<>(url);
    }

    /**
     * head请求
     */
    public static <T> HeadRequest<T> head(String url) {
        if (!url.startsWith("http")) {
            url = BASE_SERVER + url;
        }
        return new HeadRequest<>(url);
    }

    /**
     * delete请求
     */
    public static <T> DeleteRequest<T> delete(String url) {
        if (!url.startsWith("http")) {
            url = BASE_SERVER + url;
        }
        return new DeleteRequest<>(url);
    }

    /**
     * options请求
     */
    public static <T> OptionsRequest<T> options(String url) {
        if (!url.startsWith("http")) {
            url = BASE_SERVER + url;
        }
        return new OptionsRequest<>(url);
    }

    /**
     * patch请求
     */
    public static <T> PatchRequest<T> patch(String url) {
        if (!url.startsWith("http")) {
            url = BASE_SERVER + url;
        }
        return new PatchRequest<>(url);
    }

    /**
     * trace请求
     */
    public static <T> TraceRequest<T> trace(String url) {
        if (!url.startsWith("http")) {
            url = BASE_SERVER + url;
        }
        return new TraceRequest<>(url);
    }

}
