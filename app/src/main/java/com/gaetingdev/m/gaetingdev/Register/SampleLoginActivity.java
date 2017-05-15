package com.gaetingdev.m.gaetingdev.Register;

/**
 * Created by soooo on 2016-09-23.
 */

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
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
import com.facebook.login.widget.LoginButton;
import com.gaetingdev.m.gaetingdev.Framework.DB.UseDB;
import com.gaetingdev.m.gaetingdev.Main.Temp.LogoutNDrop;
import com.gaetingdev.m.gaetingdev.Register.kakao.KakaoSignupActivity;
import com.gaetingdev.m.gaetingdev.R;
import com.kakao.auth.ISessionCallback;
import com.kakao.auth.Session;
import com.kakao.util.exception.KakaoException;
import com.kakao.util.helper.log.Logger;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class SampleLoginActivity extends Activity{
    private SessionCallback callback;
    private ProfileTracker profileTracker;
    /* facebook 로그인 변수 */
    private CallbackManager facebook_callbackManager;
    private FacebookCallback facebook_callbackFunc;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /* 페이스북 sdk 초기화 */
        setContentView(R.layout.layout_common_kakao_login);

        /* 페이스북 연동 */
        FacebookSdk.sdkInitialize(this.getApplicationContext());
        facebook_callbackManager = CallbackManager.Factory.create();
        facebook_callbackFunc = new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                GraphRequest graphRequest = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        Toast.makeText(getBaseContext(), "페이스북 로그인 성공"+ object.toString(), Toast.LENGTH_SHORT).show();
                        IntentMain(object);
                    }
                });

                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,name,email,gender,birthday");
                graphRequest.setParameters(parameters);
                graphRequest.executeAsync();
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {
                Log.e("LoginErr",error.toString());
            }

        };

        /* 페이스북 로그인버튼 이벤트 Handler */
        LoginButton facebook_loginButton = (LoginButton) findViewById(R.id.facebook_login_button);
        facebook_loginButton.setReadPermissions(Arrays.asList("public_profile", "email"));
        facebook_loginButton.registerCallback(facebook_callbackManager, facebook_callbackFunc);


        /* 카카오톡 연동 */

        //키해싱 값 받아오기 > 나중에 필요없음
        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    this.getPackageName(),
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("test", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {
        }

        callback = new SessionCallback();
        Session.getCurrentSession().addCallback(new ISessionCallback() {
            @Override
            public void onSessionOpened() {
                redirectSignupActivity();
                Log.d("test", "세션연결 성공");
            }

            @Override
            public void onSessionOpenFailed(KakaoException exception) {
                if (exception != null){
                    Log.d("test", "세션연결 실패");
                    Log.e("test", exception.getMessage());
                }
                setContentView(R.layout.layout_common_kakao_login);
            }
        });
        if (!Session.getCurrentSession().checkAndImplicitOpen()) {
            setContentView(R.layout.layout_common_kakao_login);
        } else{
        }

        /* 현재 로그인 세션상태 확인 후 자동로그인 //이거 지워도 되죠???????????????*/
        //checkLoginSession();
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if (Session.getCurrentSession().handleActivityResult(requestCode, resultCode, data)){
            return;
        }
        super.onActivityResult(requestCode, resultCode, data);
        facebook_callbackManager.onActivityResult(requestCode, resultCode, data);
    }



    private class SessionCallback implements ISessionCallback{

        public void onSessionOpened(){
            redirectSignupActivity();
            Log.d("test", "세션연결 성공");
        }

        public void onSessionOpenFailed(KakaoException exception){
            if (exception != null){
                Log.d("test", "세션연결 실패");
                Logger.e(exception);
            }
            setContentView(R.layout.layout_common_kakao_login);
        }

    }

    protected void redirectSignupActivity() {
        Intent intent = new Intent(this, KakaoSignupActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
        finish();
    }

    private void checkLoginSession(){

        /* FACEBOOK 현재 세션 상태 확인 */
        LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile"));
        LoginManager.getInstance().registerCallback(facebook_callbackManager, facebook_callbackFunc);

    }


    protected void onDestroy(){
        super.onDestroy();
        Session.getCurrentSession().removeCallback(callback);
    }

    public void IntentMain(JSONObject object){

        try {
            UseDB db = new UseDB(SampleLoginActivity.this);
            db.updateDB(2, (String)object.get("id"));
            db.closeDB();
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        Intent intent = new Intent(SampleLoginActivity.this, LogoutNDrop.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
    }
}
