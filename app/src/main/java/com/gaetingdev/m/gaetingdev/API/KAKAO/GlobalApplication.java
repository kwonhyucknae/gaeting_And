package com.gaetingdev.m.gaetingdev.API.KAKAO;

import android.app.Activity;
import android.app.Application;
import android.graphics.Bitmap;
import android.support.v4.util.LruCache;
import android.view.Display;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.kakao.auth.KakaoSDK;

public class GlobalApplication extends Application{
    private static volatile GlobalApplication instance = null;
    private static volatile Activity currentActivity = null;
    private ImageLoader imageLoader;
//커밋을위한주석! 지우시면됩니다.
    public static Activity getCurrentActivity() {
        return currentActivity;
    }

    public static void setCurrentActivity(Activity currentActivity){
        GlobalApplication.currentActivity = currentActivity;
    }

    public static GlobalApplication getGlobalApplicationContext(){
        if(instance == null)
            throw new IllegalStateException("Exception111111111");
        return instance;
    }

    public void onCreate(){
        super.onCreate();
        instance = this;

        KakaoSDK.init(new KakaoSDKAdapter());

        final RequestQueue requestQueue = Volley.newRequestQueue(this);

        ImageLoader.ImageCache imageCache = new ImageLoader.ImageCache() {
            final LruCache<String, Bitmap> imageCache = new LruCache<String, Bitmap>(3);

            public void putBitmap(String key, Bitmap value){
                imageCache.put(key, value);
            }

            public Bitmap getBitmap(String key){
                return imageCache.get(key);
            }
        };

        imageLoader = new ImageLoader(requestQueue, imageCache);
    }

    public ImageLoader getImageLoader(){
        return imageLoader;
    }

    public void onTerminate() {
        super.onTerminate();
        instance = null;
    }
    public static Display mDisplay;

    public static void setDisplay(Display display) {
        mDisplay = display;
    }

    public static int getDisplayWidth(){
        return mDisplay.getWidth();
    }

    public static int getDisplayHeight(){
        return mDisplay.getHeight();
    }

    public int resize_Height(int width, int height, int resize_width){
        return (this.getDisplayHeight()*resize_width)/getDisplayWidth();
    }
}
