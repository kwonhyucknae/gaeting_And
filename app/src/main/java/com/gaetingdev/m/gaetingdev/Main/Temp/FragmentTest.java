package com.gaetingdev.m.gaetingdev.Main.Temp;

import android.os.Bundle;

import java.util.List;
import java.util.Vector;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TabHost;

import com.gaetingdev.m.gaetingdev.Main.Contents.Adaption.Adaption_Main;
import com.gaetingdev.m.gaetingdev.Main.Contents.Matching.Matching_Main;
import com.gaetingdev.m.gaetingdev.Main.MorePage;
import com.gaetingdev.m.gaetingdev.R;
/**
 * Created by x-note on 2016-10-01.
 */
public class FragmentTest extends AppCompatActivity implements TabHost.OnTabChangeListener, ViewPager.OnPageChangeListener{

    private static String TAG = "FragmentTest";

    private TabHost mTabHost;
    private ViewPager mViewPager;
    private PagerAdapter mPagerAdapter;

    /*
	  * 2016-10-01
	  * 김지광
	  * TAB변수 정의
	  */
    private String[] tabs = { Matching_Main.class.getName(), Adaption_Main.class.getName() , MorePage.class.getName() };
    private String[] tab_names = { "매칭", "분양", "더보기"};

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_test);

        /* Tab과 ViewPager 초기화 */
        this.initialiseTabHost(savedInstanceState);
        this.intialiseViewPager();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.fragment_test_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.d(TAG,item.getItemId()+"");

        return super.onOptionsItemSelected(item);

    }

    private void initialiseTabHost(Bundle args) {
        mTabHost = (TabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup();

        //Tab에 들어갈 Item adding
        for(int i=0; i<tabs.length; i++){
            AddTab(this, this.mTabHost, this.mTabHost.newTabSpec(tabs[i]).setIndicator(tab_names[i]));
        }
        //tab change 핸들러 세팅
        mTabHost.setOnTabChangedListener(this);
    }

    private void AddTab(Context context, TabHost tabHost, TabHost.TabSpec tabSpec) {
        tabSpec.setContent(new TabFactory(context));
        tabHost.addTab(tabSpec);
    }

    private void intialiseViewPager() {
        //ViewPager에 들어갈 Fragment adding
        List<Fragment> fragments = new Vector<Fragment>();

        for(int i=0; i<tabs.length; i++){
            fragments.add(Fragment.instantiate(this, tabs[i]));
        }

        //PageAdapter 초기화
        this.mPagerAdapter = new PagerAdapter(super.getSupportFragmentManager(), fragments);
        this.mViewPager = (ViewPager) super.findViewById(R.id.viewpager);
        this.mViewPager.setAdapter(this.mPagerAdapter);

        //viewPager 핸들러 세팅
        this.mViewPager.setOnPageChangeListener(this);
    }


    public void onTabChanged(String tag) {
        int pos = this.mTabHost.getCurrentTab();
        this.mViewPager.setCurrentItem(pos);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        this.mTabHost.setCurrentTab(position);

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    /* Tab 객체 커스터마이징 */
    class TabFactory implements TabHost.TabContentFactory {

        private final Context mContext;

        public TabFactory(Context context) {
            mContext = context;
        }

        public View createTabContent(String tag) {

            View v = new View(mContext);
            return v;
        }
    }

    /* ViewPager 객체 커스터마이징 */
    class PagerAdapter extends FragmentPagerAdapter {

        private List<Fragment> fragments;

        public PagerAdapter(FragmentManager fm, List<Fragment> fragments) {
            super(fm);
            this.fragments = fragments;
        }

        @Override
        public Fragment getItem(int position) {
            return this.fragments.get(position);
        }

        @Override
        public int getCount() {
            return this.fragments.size();
        }
    }


}
