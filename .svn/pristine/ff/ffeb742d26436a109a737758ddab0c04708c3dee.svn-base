package com.gaetingdev.m.gaetingdev.Framework;

import android.app.Activity;

import com.androidquery.AQuery;
import com.gaetingdev.m.gaetingdev.R;

/**
 * Created by x-note on 2016-09-24.
 */
public class AjaxAdapter {
    protected AQuery aq;
    protected Activity activity;
    /*
	  * 2016-09-25
	  * 김지광
	  * 서버와의 통신을 위한 AQuery 공통함수
	  */
    public AjaxAdapter(){

    }
    public AjaxAdapter(Activity activity){
        this.activity = activity;
        aq = new AQuery(activity);
    }
    /*
	  * 2016-09-25
	  * 김지광
	  * 통신 url 받아오기
	  */
    protected String httpUrl(){
        return activity.getResources().getString(R.string.http_url);
    }

    /*
	  * 2016-09-25
	  * 김지광
	  * 통신 url 받아오기
	  * path : 하위디렉토리 경로
	  */
    protected String httpUrl(String path){
        return activity.getResources().getString(R.string.http_url) + path;
    }

}
