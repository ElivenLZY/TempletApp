package com.thirdmodule.scaner

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.gyf.barlibrary.ImmersionBar
import com.thirdmodule.R
import com.uuzuche.lib_zxing.activity.CaptureFragment
import com.uuzuche.lib_zxing.activity.CodeUtils
import kotlinx.android.synthetic.main.thirdmodule_activity_scan.*

/**
 * 定制化显示扫描界面
 */
class ScanActivity : AppCompatActivity() {

    private var captureFragment: CaptureFragment? = null

    private var mImmersionBar: ImmersionBar? = null

    private var isLight: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.thirdmodule_activity_scan)
        mImmersionBar = ImmersionBar.with(this)
        mImmersionBar?.transparentBar()?.init()
        captureFragment = CaptureFragment()
        // 为二维码扫描界面设置定制化界面
        CodeUtils.setFragmentArgs(captureFragment, R.layout.thirdmodule_my_camera)
        captureFragment?.analyzeCallback = analyzeCallback
        supportFragmentManager.beginTransaction().replace(R.id.fl_container, captureFragment).commit()
        tv_light.setOnClickListener {
            isLight = if (isLight) {
                CodeUtils.isLightEnable(false)
                tv_light.text = "轻触照亮"
                false
            } else {
                CodeUtils.isLightEnable(true)
                tv_light.text = "轻触关闭"
                true
            }

        }
    }

    /**
     * 二维码解析回调函数
     */
    private var analyzeCallback: CodeUtils.AnalyzeCallback = object : CodeUtils.AnalyzeCallback {
        override fun onAnalyzeSuccess(mBitmap: Bitmap, result: String) {
            val resultIntent = Intent()
            val bundle = Bundle()
            bundle.putInt(CodeUtils.RESULT_TYPE, CodeUtils.RESULT_SUCCESS)
            bundle.putString(CodeUtils.RESULT_STRING, result)
            resultIntent.putExtras(bundle)
            this@ScanActivity.setResult(RESULT_OK, resultIntent)
            this@ScanActivity.finish()
        }

        override fun onAnalyzeFailed() {
            val resultIntent = Intent()
            val bundle = Bundle()
            bundle.putInt(CodeUtils.RESULT_TYPE, CodeUtils.RESULT_FAILED)
            bundle.putString(CodeUtils.RESULT_STRING, "")
            resultIntent.putExtras(bundle)
            this@ScanActivity.setResult(RESULT_OK, resultIntent)
            this@ScanActivity.finish()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mImmersionBar?.destroy()
    }

}
