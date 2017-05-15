package com.gaetingdev.m.gaetingdev.Main.Contents.Adaption;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.gaetingdev.m.gaetingdev.Main.Ajax.MainAjax;
import com.gaetingdev.m.gaetingdev.R;

import org.json.JSONArray;

/**
 * Created by 권혁내 on 2016-11-05.
 */
public class Read_Adaption extends Activity {

    private static String sLog="Read_Adaption";
    private MainAjax ajax;
    private TextView readID;
    private TextView readDogAge;
    private TextView readHit;
    private TextView readDate;
    private TextView readLocate;
    private TextView readPrice;
    private TextView readContext;
    private TextView readDogType;

    int test=20;
/*
    test 가 글 번호 대용으로 쓰이고잇음
    *글 읽기 를 클릭하면
    *글 번호를 받아와야한다.
    * 아직 구현 안함
 */

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.read_adaption);

        ajax=new MainAjax(this);
        readDogAge=(TextView)findViewById(R.id.adaption_read_dog_age);
        readID=(TextView)findViewById(R.id.adaption_read_ID_et);
        readHit=(TextView)findViewById(R.id.adaption_read_hit);
        readDate=(TextView)findViewById(R.id.adaption_read_date);
        readLocate=(TextView)findViewById(R.id.adaption_read_location);
        readPrice=(TextView)findViewById(R.id.adaption_read_price);
        readContext=(TextView)findViewById(R.id.adaption_read_note_et);
        readDogType=(TextView)findViewById(R.id.adaption_read_dog_type_et);

        ajax.readAdaption(test,new AjaxCallback<JSONArray>()
        {
            @Override
            public void callback(String url, JSONArray object, AjaxStatus status)
            {
             if(object !=null)
             {
                 try{
                 if(object.length()!=0)
                 {
                     String ui=object.getJSONObject(0).get("USER_ID").toString();
                     String dogt=object.getJSONObject(0).get("ANIMAL_TYPE").toString();
                     String doga=object.getJSONObject(0).get("ANIMAL_AGE").toString();
                     String readhit=object.getJSONObject(0).get("SELL_HIT").toString();
                     String rd=object.getJSONObject(0).get("SELL_REG_DATE").toString();
                     String rl=object.getJSONObject(0).get("SELL_LOCATION").toString();
                     String rp=object.getJSONObject(0).get("SELL_PRICE").toString();
                     String rc=object.getJSONObject(0).get("SELL_CONTENT").toString();
                     readContext.setText(rc);
                     readPrice.setText(rp);
                     readLocate.setText(rl);
                     readDate.setText(rd);
                     readHit.setText(readhit);
                     readDogAge.setText(doga);
                     readDogType.setText(dogt);
                     readID.setText(ui);

                 }else
                 {
                     readID.setText("결과값이 없어.");
                     readDogType.setText("결과값이 없어.");
                     readDogAge.setText("결과값이 없어.");
                     readHit.setText("결과값이 없어.");
                     readDate.setText("결과값이 없어.");
                     readLocate.setText("결과값이 없어.");
                     readPrice.setText("결과값이 없어.");
                     readContext.setText("결과값이 없어.");

                 }
                 }catch (Exception e)
                 {
                     Log.e(sLog,e.getMessage());
                 }
             }else
             {
                 readID.setText("object 가 null이야");
             }
            }

        });

    }
}


/*11월 8일 2:03  수정할점
1.ajax.writeAdaption 사용시에 한글을 사용하면 데이터베이스에 저장이 안된다.
2.상세내용 (Context) 가 추가가 안된다 아무래도 String을 사용해서?
3.강아지 ID 받아오기를 아직 못함
*/