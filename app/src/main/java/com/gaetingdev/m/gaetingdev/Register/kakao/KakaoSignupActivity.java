package com.gaetingdev.m.gaetingdev.Register.kakao;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.gaetingdev.m.gaetingdev.Framework.DB.UseDB;
import com.gaetingdev.m.gaetingdev.Main.Temp.LogoutNDrop;
import com.gaetingdev.m.gaetingdev.Register.SampleLoginActivity;
import com.kakao.auth.ErrorCode;
import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.MeResponseCallback;
import com.kakao.usermgmt.response.model.UserProfile;
import com.kakao.util.helper.log.Logger;

/**
 * Created by soooo on 2016-09-23.
 */
public class KakaoSignupActivity extends Activity{

    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("test", "KakaoSignupActivity onCreate");
        requestMe();
    }

    protected void requestMe() {
        UserManagement.requestMe(new MeResponseCallback() {
            public void onFailure(ErrorResult errorResult){
                String message = "failed " + errorResult;
                Logger.d(message);

                ErrorCode result = ErrorCode.valueOf(errorResult.getErrorCode());
                if (result == ErrorCode.CLIENT_ERROR_CODE) {
                    finish();
                }
                else {
                    redirectLoginActivity();
                }
            }

            public void onSessionClosed(ErrorResult errorResult){
                redirectLoginActivity();
            }

            public void onNotSignedUp() {}

            public void onSuccess(UserProfile userProfile){
                Log.d("UserProfile : ", userProfile.getId()+"");
                Log.d("test", "성공-------");

                SharedPreferences pref = getSharedPreferences("setpreference", MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();
                editor.putInt("logintype", 1); // 카카오톡
                editor.commit();

                //db에 id저장
                UseDB db = new UseDB(KakaoSignupActivity.this);
                db.updateDB(1, userProfile.getId() + "");

                redirectMainActivity();
            }
        });
    }

    private void redirectMainActivity() {
        // 테스트 위해 로그아웃*탈퇴 창으로 이동, 추후 변경요망0929
        startActivity(new Intent(this, LogoutNDrop.class));
        finish();
    }

    protected void redirectLoginActivity(){
        final Intent intent = new Intent(this, SampleLoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
        finish();
    }
}
