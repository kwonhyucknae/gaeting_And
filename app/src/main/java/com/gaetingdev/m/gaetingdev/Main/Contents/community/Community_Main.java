package com.gaetingdev.m.gaetingdev.Main.Contents.community;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.gaetingdev.m.gaetingdev.Main.Ajax.MainAjax;
import com.gaetingdev.m.gaetingdev.R;

import org.json.JSONArray;


/**
 * Created by 권혁내 on 2016-11-24.
 */

public class Community_Main extends Fragment{

    private static String sLog="Community_Main";
    private MainAjax ajax;
    private TextView tv;
    double lat;
    double lng;
    int i;
    private ListView listView;
    private ListViewAdapter adapter;

    public Community_Main()
    {

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {

         View v=inflater.inflate(R.layout.community_main, container, false);

        ajax=new MainAjax(this.getActivity());
        //ListView listView;
        //ListViewAdapter adapter;

        adapter=new ListViewAdapter();

        listView=(ListView)v.findViewById(R.id.community_listview);
        listView.setAdapter(adapter);
        lat=37.0000000;
        lng=127.0000000;

        adapter.addItem(getResources().getDrawable(R.drawable.icon_login),"Box","Account Box Black 36dp",0,0);
        adapter.addItem(getResources().getDrawable(R.drawable.icon_login),"Circle","Account Circle Black 366dp",0,0);

        ajax.CommunityMainRead(lat,lng,new AjaxCallback<JSONArray>()
        {
            @Override
            public void callback(String url, JSONArray object, AjaxStatus status)
            {
                if(object !=null)
                {
                    try {
                                adapter.addItem(getResources().getDrawable(R.drawable.icon_login),"zzz","kkk",0,0);
                                String Lt=object.getJSONObject(0).get("count").toString();
                                int Length=Integer.parseInt(Lt);
                                for(i=0;i<2;i++)
                                {


                                    String Nm=object.getJSONObject(i).get("USER_NM").toString();
                                    String Tx=object.getJSONObject(i).get("COMMUNITY_TEXT").toString();
                                    String Lk=object.getJSONObject(i).get("COMMUNITY_LIKE").toString();
                                    String Tm=object.getJSONObject(i).get("td").toString();
                                    String ds=object.getJSONObject(i).get("distance").toString();
                                    int Like=Integer.parseInt(Lk);
                                    int Time=Integer.parseInt(Tm);
                                    double distance=Double.parseDouble(ds);

                                    adapter.addItem(getResources().getDrawable(R.drawable.icon_login),Nm,Tx,Like,0);

                                }

                    }catch(Exception e)
                    {
                        adapter.addItem(getResources().getDrawable(R.drawable.icon_login),"Exception","Exception",0,0);
                        Log.e(sLog,e.getMessage());
                    }
                }else
                {

                    adapter.addItem(getResources().getDrawable(R.drawable.icon_login),"object null","object null",0,0);
                }
            }
        });

        //adapter.addItem(getResources().getDrawable(R.drawable.icon_login),"Box","Account Box Black 36dp",0,0);
        //adapter.addItem(getResources().getDrawable(R.drawable.icon_login),"Circle","Account Circle Black 366dp",0,0);


        //checkDangerousPermissions();

    return v;
    }
/*
    private void checkDangerousPermissions() {
        String[] permissions = {
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
        };

        int permissionCheck = PackageManager.PERMISSION_GRANTED;
        for (int i = 0; i < permissions.length; i++) {
            permissionCheck = ContextCompat.checkSelfPermission(getActivity(), permissions[i]);
            //프래그먼트에선 this 를 getActivity()로 바꿔준다.
            if (permissionCheck == PackageManager.PERMISSION_DENIED) {
                break;
            }
        }

        if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
            // Toast.makeText(this, "권한 있음", Toast.LENGTH_LONG).show();
        } else {
            // Toast.makeText(this, "권한 없음", Toast.LENGTH_LONG).show();

            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), permissions[0])) {
                //   Toast.makeText(this, "권한 설명 필요함.", Toast.LENGTH_LONG).show();
            } else {
                ActivityCompat.requestPermissions(getActivity(), permissions, 1);
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
        LocationManager manager = (LocationManager)getActivity().getSystemService(Context.LOCATION_SERVICE);
        //프래그먼트에선 getActivitiy().getSystemService 로 바꿔준다.
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
    */
}
