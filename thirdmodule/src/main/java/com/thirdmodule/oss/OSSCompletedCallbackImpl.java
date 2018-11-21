package com.thirdmodule.oss;

import android.util.Log;

import com.alibaba.sdk.android.oss.ClientException;
import com.alibaba.sdk.android.oss.ServiceException;
import com.alibaba.sdk.android.oss.callback.OSSCompletedCallback;
import com.alibaba.sdk.android.oss.model.PutObjectRequest;
import com.alibaba.sdk.android.oss.model.PutObjectResult;

/**
 * @author lzy
 * create at 2018/11/6 14:57
 **/
public class OSSCompletedCallbackImpl implements OSSCompletedCallback<PutObjectRequest, PutObjectResult> {

    public int position;

    public OSSCompletedCallbackImpl() {
        this(0);
    }

    public OSSCompletedCallbackImpl(int position) {
        this.position = position;
    }

    @Override
    public void onSuccess(PutObjectRequest request, PutObjectResult result) {
        Log.d("PutObject", "UploadSuccess");
        Log.d("PutObject", "Bucket: " + request.getBucketName()
                + "\nObject: " + request.getObjectKey()
                + "\nETag: " + result.getETag()
                + "\nRequestId: " + result.getRequestId()
                + "\nCallback: " + result.getServerCallbackReturnBody());
    }

    @Override
    public void onFailure(PutObjectRequest request, ClientException clientException, ServiceException serviceException) {
        // 请求异常
        if (clientException != null) {
            // 本地异常如网络异常等
            clientException.printStackTrace();
        }
        if (serviceException != null) {
            // 服务异常
            Log.e("PutObject", "ErrorCode:" + serviceException.getErrorCode());
            Log.e("PutObject", "RequestId" + serviceException.getRequestId());
            Log.e("PutObject", "HostId" + serviceException.getHostId());
            Log.e("PutObject", "RawMessage" + serviceException.getRawMessage());
        }

    }
}
