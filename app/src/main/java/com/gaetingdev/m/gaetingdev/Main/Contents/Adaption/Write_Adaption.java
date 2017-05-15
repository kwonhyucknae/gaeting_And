package com.gaetingdev.m.gaetingdev.Main.Contents.Adaption;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.androidquery.callback.AjaxCallback;
import com.gaetingdev.m.gaetingdev.Framework.DB.UseDB;
import com.gaetingdev.m.gaetingdev.Main.Ajax.MainAjax;
import com.gaetingdev.m.gaetingdev.R;

import org.json.JSONArray;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by 권혁내 on 2016-11-05.
 */
public class Write_Adaption extends Activity{

    private MainAjax ajax;
    private EditText dogtype;
    private EditText title;
    private EditText age;
    private EditText price;
    private EditText pluslotate;
    private EditText note;
    private Button btnok;
    private RadioGroup rg;
    private RadioButton genderrb;
    private Spinner locationspi;
    private String realloca;
    private String AnimalID;
    private static String sLog="Write_Adaption";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.write_adaption);

        rg=(RadioGroup)findViewById(R.id.adaption_write_rg);
        genderrb=(RadioButton)findViewById(rg.getCheckedRadioButtonId());

        ajax=new MainAjax(this);

        AnimalID=null;
        String[] str=getResources().getStringArray(R.array.spinnerArray);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,str);
        locationspi=(Spinner)findViewById(R.id.locationSpinner);
        locationspi.setAdapter(adapter);



        dogtype=(EditText)findViewById(R.id.adaption_wirte_dog_type_et);
        title=(EditText)findViewById(R.id.adaption_write_title_et);
        age=(EditText)findViewById(R.id.adaption_write_dog_age_et);
        price=(EditText)findViewById(R.id.adaption_write_price_et);
        note=(EditText)findViewById(R.id.adaption_write_note_et);

        btnok=(Button)findViewById(R.id.adaption_write_OK);

        btnok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UseDB db=new UseDB(Write_Adaption.this);

                SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm",java.util.Locale.getDefault());
                long now=System.currentTimeMillis();
                Date date=new Date(now);
                String strDate=dateFormat.format(date);

                //현재 시간 구하기
                String writer=db.getID();
                //String genderst=genderrb.getText().toString();
                String tt=title.getText().toString();
                String ag=age.getText().toString();
                String pri=price.getText().toString();
                String nt=note.getText().toString();
                String Location=locationspi.getSelectedItem().toString();
                String dt=dogtype.getText().toString();


                if(Location.equals("서울"))
                {
                    realloca="seoul";
                }

                Toast.makeText(getApplicationContext(),writer,Toast.LENGTH_SHORT).show();
                Integer ppri=Integer.parseInt(pri);
                Integer dog_age;
                dog_age=Integer.parseInt(ag);

                //String dogtp=dogtype.getText().toString();

                //ajax.writeAdaption(ppri,realloca,tt,nt,0,strDate,0,new AjaxCallback<JSONArray>(){});

               // ajax.adaptionDoginfo(dog_age,new AjaxCallback<JSONArray>(){});

                ajax.writeAdaption(ppri,realloca,tt,nt,0,strDate,0,dog_age,writer,dt,new AjaxCallback<JSONArray>(){});

            }
        });
    }
}


/*11월 8일 2:03  수정할점
1.ajax.writeAdaption 사용시에 한글을 사용하면 데이터베이스에 저장이 안된다.
2.상세내용 (Context) 가 추가가 안된다 아무래도 String을 사용해서? -해결
3.강아지 ID 받아오기를 아직 못함 -해결

******11월 12일 수정할점
* animal_ID 토스트로 뿌려주기

*/

