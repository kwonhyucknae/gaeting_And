package com.gaetingdev.m.gaetingdev.Main.Temp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.facebook.CallbackManager;
import com.facebook.FacebookActivity;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.gaetingdev.m.gaetingdev.Main.Ajax.MainAjax;
import com.gaetingdev.m.gaetingdev.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private static String sLog = "MainActivity";

    private MainAjax ajax;

    private EditText ajaxInput;
    private Button ajaxBtn;
    private TextView ajaxText;
    private CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FacebookSdk.sdkInitialize(this.getApplicationContext());
        setContentView(R.layout.activity_main);

        //ajax 초기화
        ajax = new MainAjax(this);

        //component 초기화
        ajaxInput = (EditText) findViewById(R.id.ajaxInput);
        ajaxBtn = (Button) findViewById(R.id.ajaxBtn);
        ajaxText = (TextView) findViewById(R.id.ajaxText);

        //ajaxBtn 이벤트핸들링
        ajaxBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String dog_id = ajaxInput.getText().toString();

                //ajax호출
                ajax.getDogInfo(dog_id, new AjaxCallback<JSONArray>() {
                    @Override
                    public void callback( String url, JSONArray object, AjaxStatus status ) {
                        if(object != null) {
                            try {
                                //dogInfo가 있을때 ajaxText에 대입
                                if(object.length() == 1){
                                    String dogInfo = object.getJSONObject(0).toString();
                                    ajaxText.setText(dogInfo);
                                }else {
                                    ajaxText.setText("결과값이 없습니다.");
                                }
                            } catch ( Exception e ) {
                                Log.e(sLog, e.getMessage());
                            }
                        } else{
                        }
                    }
                });
            }
        });

        callbackManager = CallbackManager.Factory.create();
        //Facebook 연동
        LoginButton loginButton = (LoginButton) findViewById(R.id.login_button);
        loginButton.setReadPermissions(Arrays.asList("public_profile", "email"));
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                GraphRequest graphRequest = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        ajaxText.setText(object.toString());
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

        });

        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

                Toast.makeText(getBaseContext(), "onSuccess", Toast.LENGTH_SHORT).show();
                GraphRequest graphRequest = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        ajaxText.setText(object.toString());
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

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
        Toast.makeText(getBaseContext(), data.toString(), Toast.LENGTH_SHORT).show();
    }
}
