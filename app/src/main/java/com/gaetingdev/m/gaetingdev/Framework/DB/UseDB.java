package com.gaetingdev.m.gaetingdev.Framework.DB;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

/**
 * Created by soooo on 2016-10-01.
 */
// DB사용을 위한 클래스입니다
public class UseDB {

    private DBHelper helper;
    private SQLiteDatabase db;
    private boolean IS_WAIT = TRUE;

    // 헬퍼 생성, SQLiteDB 객체 획득
    public UseDB(Activity activity){
        helper = new DBHelper(activity);
        db = helper.getWritableDatabase();
        IS_WAIT = FALSE;
    }

    //업데이트를 위한 클래스
    public void updateDB(int type, String id){

        while(IS_WAIT);
        IS_WAIT = TRUE;

        ContentValues row = new ContentValues();
        row.put("login_type", type);
        row.put("id", id);
        String whereClause = "_id=?";
        String[] whereArgs = new String[]{"1"};
        db.update ("id_table", row, whereClause, whereArgs);

        IS_WAIT = FALSE;

    }

    public int getType(){

        while(IS_WAIT);
        IS_WAIT = TRUE;
        int rtn;
        String[] columns = {"_id", "login_type", "id"};
        String selection = "_id=?";
        String[] selectArgs = new String[]{"1"};

        Cursor cursor = db.query("id_table", columns, selection, selectArgs,
                null, null, null, null);

        cursor.moveToNext();
        rtn = cursor.getInt(1);

        cursor.close();
        IS_WAIT = FALSE;

        return rtn;
    }

    public String getID(){

        while(IS_WAIT);
        IS_WAIT = TRUE;
        String rtn;
        String[] columns = {"_id", "login_type", "id"};
        String selection = "_id=?";
        String[] selectArgs = new String[]{"1"};

        Cursor cursor = db.query("id_table", columns, selection, selectArgs,
                null, null, null, null);

        cursor.moveToNext();
        rtn = cursor.getString(2);

        cursor.close();
        IS_WAIT = FALSE;

        return rtn;
    }

    public void closeDB(){

        while(IS_WAIT);
        IS_WAIT = TRUE;

        db.close();
        helper.close();

        IS_WAIT = FALSE;
    }


    /**************************UseDB_사용법_1001*************************
     * 1.DB를 사용할 엑티비티를 매개변수로 하는 UseDB 객체를 선언해줍니다.
     *   ex) UseDB db = new UseDB(SampleLoginActivity.this);
     *
     * 2-1.업테이트를 사용할경우 updateDB를 사용합니다.
     *     매개변수:int 로그인타입 (카카오톡-1, 페이스북-2) / String 아이디
     *     ex) db.updateDB(2, currentProfile.getId().toString());
     *
     * 2-2.데이터에 접근할 경우 getInfo를 사용합니다.
     *     매개변수:String 얻어 올 정보 (login_type-로그인타입, id-아이디)
     *    ex) db.getInfo("login_type");
     *
     * 3. DB사용이 완료되면 반드시!!!!!! closeDB로 객체를 닫아줍니다.
     *    ex) db.closeDB();
     ********************************************************************/
}
