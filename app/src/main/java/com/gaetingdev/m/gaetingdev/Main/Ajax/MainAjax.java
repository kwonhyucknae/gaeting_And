package com.gaetingdev.m.gaetingdev.Main.Ajax;

import android.app.Activity;
import android.widget.ImageView;

import com.androidquery.callback.AjaxCallback;
import com.gaetingdev.m.gaetingdev.Framework.AjaxAdapter;

import org.json.JSONArray;

import java.util.HashMap;

/**
 * Created by x-note on 2016-09-24.
 */
public class MainAjax extends AjaxAdapter{


    private String sLog = "MainAjax";
    private String test = "/test/kjg/test.jsp"; //테스트함수 url 하위디렉토리
    private String test1 = "/test/khn/khntest.jsp"; //테스트함수 url 하위디렉토리
    private String Customer_Sign_up="/test/khn/Sign_up/Customer_Sign_up.jsp";
    private String Customer_Permission="/test/khn/Sign_up/Customer_Permission.jsp";
    private String w_adaption_notice_info="/test/khn/Adaption/Write/Adaption_notice_info.jsp";
    private String w_adaption_dog_info="/test/khn/Adaption/Write/Adaption_dog_info.jsp";
    private String storagePath = "/Storage/images/";
    private String r_adaption_info="/test/khn/Adaption/Read/Adaption_read_info.jsp";
    private String w_Community_info="/test/khn/Community/Write/Community_info.jsp";
    private String r_Community_main="/test/khn/Community/Read/Community_Main_Read.jsp";


    /*
	  * 2016-09-25
	  * 김지광
	  * 메인에서 호출되는 Ajax 함수 정의
	  */
    public MainAjax(){

    }
    public MainAjax(Activity activity){
        super(activity);
    }

    /*
	  * 2016-10-03
	  * 김지광
	  * 스토리지에 있는 이미지를 이미지뷰에 세팅
	  */
    public void setImage(ImageView img, String img_name){
        aq.id( img ).image( httpUrl(storagePath)+img_name , false, false);

    }

    /*
     * 2016-09-25
     * 김지광
     * getDogInfo > dog_id 날려서 dog정보 받아오기
     */
    public void getDogInfo(String dog_id, AjaxCallback callback){

        //parameter 설정
        //dog_id : 강아지아이디
        HashMap<String, Object> map = new HashMap<>();
        map.put("dog_id", dog_id);

        //ajax호출
        aq.ajax(httpUrl(test), map, JSONArray.class, callback);

    }
    public void setCustomerInfo(String ID,String NM,int Age,int Logintype,String Date,AjaxCallback callback){
        //char gender 제거
        //parameter 설정

        HashMap<String, Object> map = new HashMap<>();
        map.put("ID",ID);                  //받아온 글자를 map형식으로 넣어준다.
        map.put("NickName",NM);
        //map.put("Gender",gender);
        map.put("Age",Age);
        map.put("Logintype",Logintype);
        map.put("Date",Date);

        //ajax호출
        aq.ajax(httpUrl(Customer_Sign_up), map, JSONArray.class, callback);

    }
    public void setCustomerPermission(String ID,String Req_Date,AjaxCallback callback)
    {
        HashMap<String,Object> map=new HashMap<>();
        map.put("ID",ID);
        map.put("Date",Req_Date);

        aq.ajax(httpUrl(Customer_Permission),map,JSONArray.class,callback);

    }

    public void adaptionDoginfo(int Age,AjaxCallback callback)
    {
        HashMap<String,Object> map=new HashMap<>();
        map.put("Age",Age);

        aq.ajax(httpUrl(w_adaption_dog_info),map,JSONArray.class,callback);
    }
    public void writeAdaption(int Price,String Location,String Title,String Note,int Hit,String Date,int Process,int Age,String ID,String DogType,AjaxCallback callback)
    {
        HashMap<String,Object> map=new HashMap<>();
        //map.put("ID",ID);
        map.put("Price",Price);
        map.put("Location",Location);
        map.put("Title",Title);
        map.put("Note",Note);
        map.put("Hit",Hit);
        map.put("Date",Date);
        map.put("Process",Process);
        map.put("Age",Age);
        map.put("ID",ID);
        map.put("DogType",DogType);

        aq.ajax(httpUrl(w_adaption_notice_info),map,JSONArray.class,callback);

    }

    public void readAdaption(int sell_id,AjaxCallback callback)
    {
       HashMap<String,Object> map=new HashMap<>();
        map.put("SELL_ID",sell_id);

        aq.ajax(httpUrl(r_adaption_info),map,JSONArray.class,callback);

    }
    public void CommunityWrite(String Date,String Text,double Lat,double Lng,String ID,AjaxCallback callback)
    {
        HashMap<String,Object> map=new HashMap<>();

        map.put("Date",Date);
        map.put("Text",Text);
        map.put("Lat",Lat);
        map.put("Lng",Lng);
        map.put("ID",ID);

        aq.ajax(httpUrl(w_Community_info),map,JSONArray.class,callback);
    }
    public void CommunityMainRead(double Lat,double Lng,AjaxCallback callback)
    {
        HashMap<String,Object> map=new HashMap<>();

        map.put("USER_LAT",Lat);
        map.put("USER_LNG",Lng);

        aq.ajax(httpUrl(r_Community_main),map,JSONArray.class,callback);

    }

}

