package com.gaetingdev.m.gaetingdev.Main.Contents.PersonalPage.PageItem;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.gaetingdev.m.gaetingdev.R;

/**
 * Created by x-note on 2016-11-06.
 */

public class PageItem_Main extends Fragment {

    private View mView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mView = inflater.inflate(R.layout.pageitem_main, container, false);
        //setHasOptionsMenu(true);
        return mView;
    }

//    @Override
//    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        super.onCreateOptionsMenu(menu, inflater);
//        getActivity().setTitle("분양");
//        inflater.inflate(R.menu.fragment_test_menu, menu);
//    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return false;
    }
}
