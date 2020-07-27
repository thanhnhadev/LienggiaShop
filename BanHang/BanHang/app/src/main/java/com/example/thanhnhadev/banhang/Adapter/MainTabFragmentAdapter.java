package com.example.thanhnhadev.banhang.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;

import java.util.List;

/**
 * Created by Administrator on 5/10/2017.
 */

public class MainTabFragmentAdapter extends FragmentStatePagerAdapter {
    private List<Fragment> listFragment;// create list fragment

    public MainTabFragmentAdapter(FragmentManager fm, List<Fragment> listFragment) {
        super(fm);
        this.listFragment = listFragment;
    }

    @Override
    public Fragment getItem(int position) {//get position fragment
        return listFragment.get(position);
    }

    @Override
    public int getItemPosition(Object object) {
        return PagerAdapter.POSITION_NONE;
    }

    @Override
    public int getCount() {// de get het tat ca fragment trong list
        return listFragment.size();
    }
}
