package com.gaetingdev.m.gaetingdev.Login;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.gaetingdev.m.gaetingdev.Framework.DB.UseDB;
import com.gaetingdev.m.gaetingdev.Main.Temp.LogoutNDrop;
import com.gaetingdev.m.gaetingdev.R;
import com.gaetingdev.m.gaetingdev.Register.SampleLoginActivity;
import com.kakao.auth.ApiResponseCallback;
import com.kakao.auth.AuthService;
import com.kakao.auth.Session;
import com.kakao.auth.authorization.accesstoken.AccessToken;
import com.kakao.auth.network.response.AccessTokenInfoResponse;
import com.kakao.network.ErrorResult;
import com.kakao.util.helper.log.Logger;

import org.json.JSONObject;

import java.util.Arrays;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

/**
 * Created by x-note on 2016-09-28.
 */
public class LoginTerminal extends Activity {

    private static int splashTime = 1; //로딩시간 [단위 : 초]

    private Handler handler;
    private UseDB db;


    private static String TAG = "LoginTerminal.class";

    private int facebookFlag = 0;
    private int kakaoFlag = 1;
    private boolean[] checkTokenflags = {false, false}; //토큰확인이 끝났는지
    private boolean[] resultToken = {false, false}; //로그인이 되어있는지

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        Log.d(TAG, "LoginTerminal onCreate()");


        //=========================================================
        //db 테스트용 로그인 타입 - 아이디
        db = new UseDB(this);
        int login_type = db.getType();
        String id = db.getID();
        db.closeDB();

        TextView tv = (TextView)findViewById(R.id.id);
        tv.setText(login_type + " - " + id);//----------DB테스트
        //=========================================================

        //토큰정보 확인하기
        requestAccessTokenInfoFacebook();
        requestAccessTokenInfoKakao();

    }

    //페이스북 토큰검사
    private void requestAccessTokenInfoFacebook() {

        FacebookSdk.sdkInitialize(getApplicationContext(), new FacebookSdk.InitializeCallback() {
            @Override
            public void onInitialized() {
                if(com.facebook.AccessToken.getCurrentAccessToken() == null){
                    onResultCheckToken(facebookFlag, false);
                } else {
                    //페이스북 로그인 되어있는 경우
                    onResultCheckToken(facebookFlag, true);
                }
            }
        });
    }

    //카톡 토큰검사
    private void requestAccessTokenInfoKakao() {

        AuthService.requestAccessTokenInfo(new ApiResponseCallback<AccessTokenInfoResponse>() {
            @Override
            public void onSuccess(AccessTokenInfoResponse accessTokenInfoResponse) {
                //카카오톡 로그인 되어있는 경우
                onResultCheckToken(kakaoFlag, true);
            }
            @Override
            public void onSessionClosed(ErrorResult errorResult) {
                onResultCheckToken(kakaoFlag, false);
            }
            @Override
            //세션 오픈은 성공, 가입이 안된 상태
            public void onNotSignedUp() {
                onResultCheckToken(kakaoFlag, false);
            }
            @Override
            //토큰검사실패
            public void onFailure(ErrorResult errorResult) {
                onResultCheckToken(kakaoFlag, false);
            }
        });
    }

    //토큰검사 끝날때마다 호출
    private void onResultCheckToken(int flag, boolean result){

        //결과값 넣기
        checkTokenflags[flag] = true; // 처리완료
        resultToken[flag] = result; // 결과

        boolean isComplete = true;
        //모두 확인하였는지 체크
        for(int i=0; i<checkTokenflags.length; i++)
        {
            isComplete = isComplete && checkTokenflags[i];
        }

        //완료되었다면 다음 페이지 호출
        if(isComplete)
        {
            IntentActivity();
        }
    }

    //다음 페이지를 불러옴
    public void IntentActivity() {

        handler = new Handler(){
            public void handleMessage(Message msg){

                Intent intent = null;

                //하나라도 토큰값 있다면 메인으로
                for(int i=0; i <resultToken.length ; i++)
                {
                    if(resultToken[i] == true)
                    {
                        intent = new Intent(LoginTerminal.this, LogoutNDrop.class);
                        break;
                    }
                }

                //없다면 Login 페이지로
                if(intent == null)
                    intent = new Intent(LoginTerminal.this, SampleLoginActivity.class);

                intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);

                //스플래시 종료
                finish();
            }
        };
        handler.sendEmptyMessageDelayed(0, splashTime * 1000);
    }
}

/*
**********************0930_update부분_김소연*************************
*********************************************************************
<layout>
splash_screen, logout_and_drop

<JAVA>
LoginTerminal.class, LogoutNDrop
KakaoSignupActivity.class // KakaoSignupActivity.requestMe.onSuccess() - SharedPreferences
KakaoSignupActivity.redirectMainActivity() - 로그인 완료되면 로그아웃 테스트창으로 이동
SampleLoginActivity.class // SampleLoginActivity.onCreate.onSuccess.onCompleted() - 로그인 성공시 인텐트를 메소드로 구성 (IntentMain)
SampleLoginActivity.onCreate() - 자동로그인(checkLoginSession) 주석처리



**********************1001_update부분_김소연*************************
*********************************************************************
requestAccessTokenInfoKakao - 카카오톡 토큰 검사 / IsOpen 제거 - 처리순서 문제로 토큰검사를 handler안에서 부르게 수정
requestAccessTokenInfoKakao - 페이스북 토큰 검사
<SQLite 구현>
DBHelper, UseDB(사용법 적어놨습니다)
 */