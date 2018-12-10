package com.common.widget.adapter.viewpager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/3/28.
 */

public class BaseFragmentAdapter extends FragmentStatePagerAdapter {

    List<Fragment> fragmentList = new ArrayList<>();

    public BaseFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    public void replaceData(List<Fragment> fragmentList) {
        this.fragmentList.clear();
        this.fragmentList.addAll(fragmentList);
        notifyDataSetChanged();
    }

    @Override
    public Fragment getItem(int position) {
        if (position < 0||position > fragmentList.size() - 1) return null;
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }


    private int mChildCount = 0;

    @Override
    public void notifyDataSetChanged() {
        mChildCount = getCount();
        super.notifyDataSetChanged();
    }

    @Override
    public int getItemPosition(Object object) {
        if (mChildCount > 0) {
            mChildCount--;
            return POSITION_NONE;
        }
        return super.getItemPosition(object);
    }


}
