package com.gaetingdev.m.gaetingdev.Framework.File;

import android.net.Uri;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

/**
 * Created by x-note on 2016-10-02.
 */
public class FileManager {
    private static final String TAG = "FileManager_TEST";

    private String TEMP_FILE_NAME = "file_%1$s.jpg";
    public enum FileType { image };

    public FileManager(FileType type){
        switch (type){
            case image:
                TEMP_FILE_NAME = "img_%1$s.jpg";
                break;
        }
    }

    //잘려진 이미지를 저장할 TempFile 생성
    public Uri createTempFile(String path){

        //폴더가 있는지 체크
        File isDirectory = new File(Environment.getExternalStorageDirectory()+"/gaeting/Temp/");
        if(!isDirectory.exists())
        {   //없다면 생성
            isDirectory.mkdirs();
        }

        //currentTimeMillis로 임의 파일이름 생성
        File temp = new File(isDirectory, String.format(TEMP_FILE_NAME, String.valueOf(System.currentTimeMillis())));
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            //새로운 파일 생성
            temp.createNewFile();
            fis = new FileInputStream(path);
            fos = new FileOutputStream(temp);

            int data = 0;
            //1메가씩 buffer에 담아 복사
            byte[] buffer = new byte[1024];
            while((data=fis.read(buffer, 0, 1024))!=-1)
            {
                fos.write(buffer, 0, data);
            }

            fis.close();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

        }
        return Uri.fromFile(temp);
    }
    public void deleteTempDir(){
        deleteFileInDir(Environment.getExternalStorageDirectory()+"/gaeting/Temp");
    }

    //path Dir에 존재하는 file 모두 삭제
    public void deleteFileInDir(String path)
    {
        File file = new File(path);
        File[] childFileList = file.listFiles();
        if(childFileList != null){
            for(File childFile : childFileList)
            {
                if(childFile.isDirectory()) {
                    deleteFileInDir(childFile.getAbsolutePath());     //하위 디렉토리 루프
                }
                else {
                    Log.d(TAG,childFile.getPath()+" 삭제");
                    childFile.delete();    //하위 파일삭제
                }
            }
        }

    }

}
