package com.gaetingdev.m.gaetingdev.Main;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.ImageView;

import com.gaetingdev.m.gaetingdev.Main.Ajax.MainAjax;
import com.gaetingdev.m.gaetingdev.R;

/**
 * Created by x-note on 2016-10-01.
 */
public class MorePage extends Fragment {

    private View mView;

    private ImageView profileImg;

    private String mUserID = "xnote";
    private MainAjax ajax;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        mView = inflater.inflate(R.layout.morepage, container, false);

        ajax = new MainAjax(this.getActivity());


        profileImg = (ImageView) mView.findViewById(R.id.profile_img);
        ajax.setImage(profileImg, mUserID+"_0.jpg");

        //setHasOptionsMenu(true);
        return mView;
    }

//    @Override
//    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        super.onCreateOptionsMenu(menu, inflater);
//        //getActivity().setTitle("더보기!");
//        //inflater.inflate(R.menu.profile_menu, menu);
//    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return false;
    }
}
