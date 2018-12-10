package com.common.widget.adapter.viewpager;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/4/16.
 */

public class BasePagerAdapter<T> extends PagerAdapter {

    List<T> dataList=new ArrayList<>();

    public BasePagerAdapter(List<T> dataList) {
        this.dataList.clear();
        this.dataList.addAll(dataList);
    }

    @Override
    public int getCount() {
        return dataList==null?0:dataList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view=mPageAdapterLisenter.getItemView(container,position);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        View view = (View) object;
        container.removeView(view);
    }

    PageAdapterLisenter mPageAdapterLisenter;
    public void setPageAdapterLisenter(PageAdapterLisenter pageAdapterLisenter){
        this.mPageAdapterLisenter=pageAdapterLisenter;
    }
    public interface PageAdapterLisenter{
        View getItemView(@NonNull ViewGroup container, int position);
    }

}
