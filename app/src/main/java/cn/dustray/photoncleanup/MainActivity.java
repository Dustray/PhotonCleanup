package cn.dustray.photoncleanup;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import cn.dustray.adapter.MainViewPagerAdapter;

public class MainActivity extends AppCompatActivity implements MemoryFragment.OnFragmentInteractionListener {

    private ViewPager mainViewPager;
    private Toolbar toolbar;
    private BottomNavigationView navigation;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mainViewPager.setCurrentItem(0);
                    return true;
                case R.id.navigation_dashboard:
                    mainViewPager.setCurrentItem(1);
                    return true;
                case R.id.navigation_notifications:
                    mainViewPager.setCurrentItem(2);
                    return true;
            }
            return false;
        }
    };
    private ViewPager.OnPageChangeListener mOnPageChangeListener
            = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int i, float v, int i1) {

        }

        @Override
        public void onPageSelected(int i) {
            switch (i) {
                case 0:
                    toolbar.setTitle("应用管理");
                    navigation.setSelectedItemId(R.id.navigation_home);
                    break;
                case 1:
                    toolbar.setTitle("垃圾清理");
                    navigation.setSelectedItemId(R.id.navigation_dashboard);
                    break;
                case 2:
                    toolbar.setTitle("游戏工具");
                    navigation.setSelectedItemId(R.id.navigation_notifications);
                    break;

                default:
                    break;
            }
        }

        @Override
        public void onPageScrollStateChanged(int i) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("应用管理");

        mainViewPager = findViewById(R.id.main_viewpager);
        mainViewPager.addOnPageChangeListener(mOnPageChangeListener);
        mainViewPager.setAdapter(new MainViewPagerAdapter(getSupportFragmentManager()));

        navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

    }

    private void clickTabOne() {
        //为防止隔页切换时,滑过中间页面的问题,去除页面切换缓慢滑动的动画效果
        mainViewPager.setCurrentItem(0, false);
    }

    private void clickTabTwo() {
        mainViewPager.setCurrentItem(1, false);
    }

    private void clickTabThree() {
        mainViewPager.setCurrentItem(2, false);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onChangeActionBarTitle(String title) {
        getSupportActionBar().setTitle(title);
    }


}
