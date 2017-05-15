package com.gaetingdev.m.gaetingdev.Main.Contents.community;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.androidquery.callback.AjaxCallback;
import com.gaetingdev.m.gaetingdev.Framework.DB.UseDB;
import com.gaetingdev.m.gaetingdev.Main.Ajax.MainAjax;
import com.gaetingdev.m.gaetingdev.R;

import org.json.JSONArray;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by 권혁내 on 2016-11-24.
 */

public class Community_Write extends Activity {

    private MainAjax ajax;
    private TextView tv;
    private Button btn;
    double lat;
    double lng;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.community_write);


        ajax=new MainAjax(this);
        btn=(Button)findViewById(R.id.community_write_btn);
        tv=(TextView)findViewById(R.id.community_write_textview);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                UseDB db=new UseDB(Community_Write.this);
                SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm",java.util.Locale.getDefault());
                long now=System.currentTimeMillis();
                Date date=new Date(now);
                String strDate=dateFormat.format(date);
                String text=tv.getText().toString();
                String id=db.getID();
                startLocationService();



                ajax.CommunityWrite(strDate,text,lat,lng,id,new AjaxCallback<JSONArray>(){});

            }


        });
        checkDangerousPermissions();

    }
    private void checkDangerousPermissions() {
        String[] permissions = {
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
        };

        int permissionCheck = PackageManager.PERMISSION_GRANTED;
        for (int i = 0; i < permissions.length; i++) {
            permissionCheck = ContextCompat.checkSelfPermission(this, permissions[i]);
            if (permissionCheck == PackageManager.PERMISSION_DENIED) {
                break;
            }
        }

        if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
           // Toast.makeText(this, "권한 있음", Toast.LENGTH_LONG).show();
        } else {
           // Toast.makeText(this, "권한 없음", Toast.LENGTH_LONG).show();

            if (ActivityCompat.shouldShowRequestPermissionRationale(this, permissions[0])) {
             //   Toast.makeText(this, "권한 설명 필요함.", Toast.LENGTH_LONG).show();
            } else {
                ActivityCompat.requestPermissions(this, permissions, 1);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 1) {
            for (int i = 0; i < permissions.length; i++) {
                if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                   // Toast.makeText(this, permissions[i] + " 권한이 승인됨.", Toast.LENGTH_LONG).show();
                } else {
                  //  Toast.makeText(this, permissions[i] + " 권한이 승인되지 않음.", Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    private void startLocationService() {
        LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        GPSListener gpsListener = new GPSListener();
        long minTime = 10000;
        float minDistance = 0;


        //자동으로 추가된 if문. 이 if 문이 없으면 requestLocationUpdates 가 오류 난다.


        try {
            // GPS를 이용한 위치 요청
            manager.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER,
                    minTime,
                    minDistance,
                    gpsListener);

            // 네트워크를 이용한 위치 요청
            manager.requestLocationUpdates(
                    LocationManager.NETWORK_PROVIDER,
                    minTime,
                    minDistance,
                    gpsListener);

            // 위치 확인이 안되는 경우에도 최근에 확인된 위치 정보 먼저 확인
            Location lastLocation = manager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if (lastLocation != null) {
                lat = lastLocation.getLatitude();
                lng = lastLocation.getLongitude();

                //Toast.makeText(getApplicationContext(), "Last Known Location : " + "Latitude : " + latitude + "\nLongitude:" + longitude, Toast.LENGTH_LONG).show();
            }
        } catch(SecurityException ex) {
            ex.printStackTrace();
        }

      //  Toast.makeText(getApplicationContext(), "위치 확인이 시작되었습니다. 로그를 확인하세요.", Toast.LENGTH_SHORT).show();


    }
    private class GPSListener implements LocationListener {
        public void onLocationChanged(Location location)
        {
            lat=location.getLatitude();
            lng=location.getLongitude();


            String msg = "Latitude : "+ lat + "\nLongitude:"+ lng;
            Log.i("GPSListener", msg);

          //  Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();

        }
        public void onProviderDisabled(String provider)
        {

        }
        public void onProviderEnabled(String provider)
        {

        }
        public void onStatusChanged(String provider,int status,Bundle extras)
        {

        }
    }
}
