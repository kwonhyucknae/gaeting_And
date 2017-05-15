package com.gaetingdev.m.gaetingdev.Framework.Permission;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by x-note on 2016-10-02.
 */
public class PermManager {
    private static final String TAG = "PermManager_TEST";

    private Activity mActivity = null;
    public PermManager(Activity context){
        this.mActivity = context;
    }

    /**
     * Permission check.
     */
    @TargetApi(Build.VERSION_CODES.M)
    public boolean checkPermission(String[] requestPermission, int PERMISSION_CODE){
            boolean checkPermission = true;

            for(int i=0; i<requestPermission.length; i++){
                if (mActivity.checkSelfPermission(requestPermission[i]) == PackageManager.PERMISSION_DENIED) {
                    checkPermission = false;
                    // Should we show an explanation?
//                    if (mActivity.shouldShowRequestPermissionRationale(requestPermission[i])) {
//                        // Explain to the user why we need to write the permission.
//                        //Toast.makeText(mActivity, "You have to accept this PERMISSION.", Toast.LENGTH_SHORT).show();
//                    }

                    mActivity.requestPermissions(requestPermission, PERMISSION_CODE);


                }else{
                    Log.d(TAG, requestPermission[i]+" is GRANTED");
                }
            }

            return checkPermission;
        }
    }
