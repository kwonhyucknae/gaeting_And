package com.gaetingdev.m.gaetingdev.Main.Contents.Adaption;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.gaetingdev.m.gaetingdev.R;

/**
 * Created by x-note on 2016-10-01.
 */
public class Adaption_Main extends Fragment {

    private View mView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mView = inflater.inflate(R.layout.adaption_main, container, false);
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
