package com.gaetingdev.m.gaetingdev.Register;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.gaetingdev.m.gaetingdev.Framework.DB.UseDB;
import com.gaetingdev.m.gaetingdev.Main.Ajax.MainAjax;
import com.gaetingdev.m.gaetingdev.R;

import org.json.JSONArray;

import java.text.SimpleDateFormat;
import java.util.Date;

public class checkout extends AppCompatActivity {

    private static String sLog = "MainActivity";
    private MainAjax ajax;
    AQuery aqt;
    private EditText ID_et;
    private EditText NickName_et;
    private EditText Ag_et;
    private TextView fir_tv;
    private Button bt;
    private RadioButton rb;
    int logintype;
    private RadioGroup rg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.checkout);

        SharedPreferences pref= getSharedPreferences("setpreference", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        logintype=pref.getInt("logintype",0);


        ajax = new MainAjax(this);
        ID_et=(EditText)findViewById(R.id.et_message);
        NickName_et=(EditText)findViewById(R.id.nickname_et);
        Ag_et=(EditText)findViewById(R.id.sign_up_age_et);
        rg=(RadioGroup)findViewById(R.id.sign_up_gender_radiogr);
        rb=(RadioButton)findViewById(rg.getCheckedRadioButtonId());


        bt=(Button)findViewById(R.id.btn_sendData);
        fir_tv=(TextView)findViewById(R.id.firstchotTV);

        if(logintype==1)
        {
            ID_et.setVisibility(View.GONE);
            fir_tv.setVisibility(View.GONE);
        }
        if(logintype==2)
        {
            ID_et.setVisibility(View.GONE);
            fir_tv.setVisibility(View.GONE);
        }

        bt.setOnClickListener(new View.OnClickListener() //버튼을 클릭해주면
        {
            @Override
            public void onClick(View v)
            {
                UseDB db = new UseDB(checkout.this);

                String NM= NickName_et.getText().toString();
                String Ag=Ag_et.getText().toString();
             //   String gender=rb.getText().toString();
                Integer agint;
                agint=Integer.parseInt(Ag);

                //현재 시간 구하기
                SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm",java.util.Locale.getDefault());
                long now=System.currentTimeMillis();
                Date date=new Date(now);
                String strDate=dateFormat.format(date);
                //strDate=strDate.replace(" ","");
                /*
                char gd=0;

                if(gender.equals("남자"))
                {
                    gd='m';
                }
                else if(gender.equals("여자"))
                {
                    gd='n';
                }
                */

                if(logintype==1) {

                    String kakaoID = db.getID();
                    Toast.makeText(getApplicationContext(),strDate,Toast.LENGTH_SHORT).show();
                    ajax.setCustomerInfo(kakaoID,NM,agint,logintype,strDate,new AjaxCallback<JSONArray>(){});
                    //ajax.setCustomerPermission(kakaoID,strDate,new AjaxCallback<JSONArray>(){});
                    //logintype=0;

                }
                else if(logintype==2)
                {
                    String facebookID=db.getID();
                    Toast.makeText(getApplicationContext(),strDate,Toast.LENGTH_SHORT).show();
                    ajax.setCustomerInfo(facebookID,NM,agint,logintype,strDate,new AjaxCallback<JSONArray>(){});
                    //ajax.setCustomerPermission(facebookID,strDate,new AjaxCallback<JSONArray>(){});
                    //logintype=0;

                }
                else if(logintype!=1&&logintype!=2) {
                    String ID = ID_et.getText().toString();    //EditText로 받아온 글자를 ID 값에 넣어준다.

                    ajax.setCustomerInfo(ID, NM,agint,logintype,strDate,new AjaxCallback<JSONArray>()  //받아온 글자를 넘겨준다.
                    {});
                    //ajax.setCustomerPermission(ID,strDate,new AjaxCallback<JSONArray>(){});
                }

            }
        });
    }
}
