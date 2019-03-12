package cn.dustray.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import cn.dustray.photoncleanup.MemoryFragment;

public class MainViewPagerAdapter extends FragmentPagerAdapter {
    //由于页面已经固定,故这里把Adapter需要的fragment提前创建
    private Fragment[] mFragments = new Fragment[]{new MemoryFragment(), new MemoryFragment(), new MemoryFragment()};

    public MainViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments[position];
    }

    @Override
    public int getCount() {
        return 3;
    }
}
