package com.common.utils;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.support.annotation.AnimRes;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.ListAdapter;
import android.widget.ListView;

public class ViewUtils {

    /**
     * 把View视图转换bitmap
     *
     * @author lzy
     * create at 2018/6/8 14:56
     **/
    public static Bitmap getDrawingCacheBitmap(View view) {
        view.setDrawingCacheEnabled(true);
        Bitmap cacheBitmap = view.getDrawingCache();
        // 拷贝图片，否则在setDrawingCacheEnabled(false)以后该图片会被释放掉
        final Bitmap newBitmap = cacheBitmap.createBitmap(cacheBitmap, 0, 0, view.getMeasuredWidth(), view.getMeasuredHeight() - view.getPaddingBottom());
        view.setDrawingCacheEnabled(false);
        return newBitmap;
    }

    public static Bitmap loadBitmapFromView(View v) {
        if (v == null) {
            return null;
        }
        int h = v.getHeight();
        if (v instanceof NestedScrollView) {
            h=0;
            NestedScrollView nestedScrollView = (NestedScrollView) v;
            for (int i = 0; i < nestedScrollView.getChildCount(); i++) {
                h += nestedScrollView.getChildAt(i).getHeight();
            }
        }
        Bitmap screenshot = Bitmap.createBitmap(v.getWidth(), h, Bitmap.Config.ARGB_8888);
        final Canvas canvas = new Canvas(screenshot);
//        canvas.translate(-v.getScrollX(), -v.getScrollY());//我们在用滑动View获得它的Bitmap时候，获得的是整个View的区域（包括隐藏的），如果想得到当前区域，需要重新定位到当前可显示的区域
        v.draw(canvas);// 将 view 画到画布上
        return screenshot;
    }

    public static Bitmap loadBitmapFromView2(View view) {
        if (view == null) {
            return null;
        }
        view.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());

        Bitmap screenshot;
        screenshot = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(screenshot);
        canvas.translate(-view.getScrollX(), -view.getScrollY());//我们在用滑动View获得它的Bitmap时候，获得的是整个View的区域（包括隐藏的），如果想得到当前区域，需要重新定位到当前可显示的区域
        view.draw(canvas);// 将 view 画到画布上
        return screenshot;
    }

    /**
     * 给RecyclerView添加列表动画（放在添加完数据之后）
     *
     * @author lzy
     * create at 2018/6/19 16:07
     **/
    public static void addRecelViewAnim(RecyclerView recyclerView, @AnimRes int id) {
        LayoutAnimationController controller = AnimationUtils.loadLayoutAnimation(recyclerView.getContext(), id);
        recyclerView.setLayoutAnimation(controller);
        recyclerView.getAdapter().notifyDataSetChanged();
        recyclerView.scheduleLayoutAnimation();
    }

    /**
     * 手动计算出listView的高度，但是不再具有滚动效果
     *
     * @param listView
     */
    public static void fixListViewHeight(ListView listView) {
        // 如果没有设置数据适配器，则ListView没有子项，返回。
        ListAdapter listAdapter = listView.getAdapter();
        int totalHeight = 0;
        if (listAdapter == null) {
            return;
        }
        for (int index = 0, len = listAdapter.getCount(); index < len; index++) {
            View listViewItem = listAdapter.getView(index, null, listView);
            // 计算子项View 的宽高
            listViewItem.measure(0, 0);
            // 计算所有子项的高度
            totalHeight += listViewItem.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        // listView.getDividerHeight()获取子项间分隔符的高度
        // params.height设置ListView完全显示需要的高度
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }

}
