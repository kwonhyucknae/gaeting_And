package com.gaetingdev.m.gaetingdev.Register.Ajax;

import android.app.Activity;

import com.androidquery.callback.AjaxCallback;
import com.gaetingdev.m.gaetingdev.Framework.AjaxAdapter;

import org.json.JSONArray;

import java.util.HashMap;

/**
 * Created by x-note on 2016-10-02.
 */


public class RegisterAjax extends AjaxAdapter {
    /*
      * 2016-10-02
      * 김지광
      * 회원가입에서 호출되는 Ajax 함수 정의
      */

    private static String setUserProfileImage = "/test/kjg/setUserProfileImage.jsp"; //테스트함수 url 하위디렉토리

    public RegisterAjax(){

    }
    public RegisterAjax(Activity activity){
        super(activity);
    }


    /*
         * 2016-09-25
         * 김지광
         * getDogInfo > dog_id 날려서 dog정보 받아오기
         */
    public void setUserProfileImage(String image_name, AjaxCallback callback){

        //parameter 설정
        //이미지 이름 ex) gildong_0.png|gildong_3.png
        HashMap<String, Object> map = new HashMap<>();
        map.put("image_name", image_name);

        //ajax호출
        aq.ajax(httpUrl(setUserProfileImage), map, JSONArray.class, callback);

    }


}

