package com.gaetingdev.m.gaetingdev.Framework.File;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.gaetingdev.m.gaetingdev.R;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by x-note on 2016-09-28.
 */

    /*
	  * 2016-09-28
	  * 김지광
	  * 서버와 파일 업/다운 통신을 위한 공통함수
	  */
public class FileTransfer {

    private static final String TAG = "FileTransfer_TEST";

    private Socket socket;
    private String ftp_url;
    private int ftp_port;

    //전송할 파일을 담을 변수
    private Queue<HashMap<String, String>> files;

    public  FileTransfer(Context context){
        files = new LinkedList<>();
        ftp_url = context.getResources().getString(R.string.ftp_url);
        ftp_port = Integer.parseInt(context.getResources().getString(R.string.ftp_port));
    }

    //파일 추가
    public void AddFile(String path, String name){
        HashMap<String, String> file = new HashMap<>();
        file.put("file_path", path);
        file.put("file_name", name);
        files.offer(file);
    }

    /*
      * 2016-10-02
      * 김지광
      * 파일 path, 파일명으로 파일 전송
      */
    public void fileTransferWithPath(Handler handler)
    {
        new FileTransferWithPath(handler).execute(this.files);
    }

    private class FileTransferWithPath extends AsyncTask<Queue<HashMap<String, String>>, Void, Integer> {

        private Handler handler;
        private Exception exception;
        private int result = 0;

        public FileTransferWithPath(Handler handler) {
            this.handler = handler;
        }

        @Override
        protected Integer doInBackground(Queue<HashMap<String, String>>... params) {
            try
            {
                Queue<HashMap<String, String>> files = params[0];

                String file_path = null;
                String file_name = null;
                BufferedOutputStream toServer = null;
                BufferedInputStream bis = null;
                FileInputStream fis = null;
                DataOutputStream dos = null;
                try {

                    //전송 파일 갯수
                    int fileSize = files.size();
                    for(int i=0; i<fileSize; i++){
                        socket = new Socket(ftp_url, ftp_port);
                        //서버로 보낼 outputstream Buffer
                        toServer = new BufferedOutputStream( socket.getOutputStream() );
                        //서버로 보낼 outputstream
                        dos = new DataOutputStream( socket.getOutputStream() );
                        //큐에서 하나씩 빼오며 전송
                        HashMap<String, String> file = files.poll();
                        file_path = file.get("file_path");
                        file_name = file.get("file_name");
                        //해더에 파일이름 삽입
                        dos.writeUTF( file_name );
                        //파일을 읽어드릴 InputStream
                        fis = new FileInputStream( file_path );
                        //파일을 읽어드릴 InputStream Buffer
                        bis = new BufferedInputStream( fis );

                        //file내용 유무 check하면서 읽어 들임과 동시에 server단으로 전송
                        int ch;
                        byte[] buffer = new byte[1024]; //1MB씩 전송
                        while((ch = bis.read(buffer, 0, 1024) ) != -1) {
                            toServer.write(buffer, 0, ch);
                        }

                        //서버로 보내는 데이터 buffer flush
                        dos.flush();
                        toServer.flush();
                        toServer.close();
                        fis.close();
                        socket.close();
                    }

                } catch (UnknownHostException e) {
                    result = -1;
                    exception = e;
                } catch (IOException e) {
                    result = -1;
                    exception = e;
                }
            }
            catch (Exception e)
            {
                result = -1;
                exception = e;
            }

            return result;
        }

        @Override
        protected void onPostExecute(Integer result) {
            if (result != null) {
                Message msg = handler.obtainMessage();
                msg.what = result;
                msg.obj = exception;
                handler.sendMessage(msg);
                return;
            }
        }
    }
}
