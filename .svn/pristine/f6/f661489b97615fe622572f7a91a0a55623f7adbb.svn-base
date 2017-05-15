package com.gaetingdev.m.gaetingdev.Main.Contents.Main;

import android.os.Bundle;

import java.util.List;
import java.util.Vector;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TabHost;

import com.gaetingdev.m.gaetingdev.Main.Contents.Adaption.Adaption_Main;
import com.gaetingdev.m.gaetingdev.Main.Contents.Matching.Matching_Main;
import com.gaetingdev.m.gaetingdev.Main.MorePage;
import com.gaetingdev.m.gaetingdev.R;
/**
 * Created by x-note on 2016-10-01.
 */
    public class MainFragment extends Fragment implements FragmentTabHost.OnTabChangeListener, ViewPager.OnPageChangeListener{

    private static String TAG = "FragmentTest";

    private FragmentTabHost mTabHost;
    private ViewPager mViewPager;
    private PagerAdapter mPagerAdapter;

    private View mView;
    /*
	  * 2016-10-01
	  * 김지광
	  * TAB변수 정의
	  */
    private Class[] tabclass = { Matching_Main.class, Adaption_Main.class , MorePage.class };
    private String[] tabs = { Matching_Main.class.getName(), Adaption_Main.class.getName() , MorePage.class.getName() };
    private String[] tab_names = { "매칭", "분양", "더보기"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.mainfragment, container, false);

        ///* Tab과 ViewPager 초기화 */
        this.initialiseTabHost();
        this.intialiseViewPager();

        return mView;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.d(TAG,item.getItemId()+"");

        return super.onOptionsItemSelected(item);

    }

    private void initialiseTabHost() {
        mTabHost = (FragmentTabHost) mView.findViewById(android.R.id.tabhost);
        mTabHost.setup(getActivity(), getChildFragmentManager(), android.R.id.tabcontent);

        //Tab에 들어갈 Item adding
        for(int i=0; i<tabs.length; i++){
            AddTab(mView, this.mTabHost, this.mTabHost.newTabSpec(tabs[i]).setIndicator(tab_names[i]), tabclass[i]);
        }
//        //tab change 핸들러 세팅
        //mTabHost.setOnTabChangedListener(this);
    }

    private void AddTab(View view, FragmentTabHost tabHost, FragmentTabHost.TabSpec tabSpec, Class tabClass) {
        //tabSpec.setContent(new TabFactory(view));
        tabHost.addTab(tabSpec, tabClass, null);
    }

    private void intialiseViewPager() {
        //ViewPager에 들어갈 Fragment adding
        List<Fragment> fragments = new Vector<Fragment>();

        for(int i=0; i<tabs.length; i++){
            fragments.add(Fragment.instantiate(mView.getContext(), tabs[i]));
        }

        //PageAdapter 초기화
        this.mPagerAdapter = new PagerAdapter(getChildFragmentManager(), fragments);
        this.mViewPager = (ViewPager) mView.findViewById(R.id.viewpager);
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

        private final View mView;

        public TabFactory(View view) {
            mView = view;
        }

        public View createTabContent(String tag) {
            //View v = new View(mView);
            return mView;
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
