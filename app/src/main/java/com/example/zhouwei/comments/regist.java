package com.example.zhouwei.comments;

import android.app.Activity;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class regist extends AppCompatActivity {

    private String username=null;
    private String password=null;

    Activity this_activity=this;

    private Thread thread=new Thread()
    {
        public void run()
        {
            String spec="http://119.29.60.170/index.aspx?type=regist&username=";
            Log.i("链接:",spec);
            try {
                URL url=new URL(spec);
                HttpURLConnection conn=(HttpURLConnection)url.openConnection();
                conn.setRequestProperty("Charaset","utf-8");
                conn.connect();
                if(conn.getResponseCode()==200)
                {
                    InputStream inputStream=conn.getInputStream();
                    InputStreamReader inputStreamReader=new InputStreamReader(inputStream);
                    BufferedReader bufferedReader=new BufferedReader(inputStreamReader);

                    StringBuffer stringBuffer=new StringBuffer();
                    String t;

                    while((t=bufferedReader.readLine())!=null) {
                        stringBuffer.append(t);
                    }

                    if(stringBuffer.toString().equals("regist success"))
                    {
                        Looper.prepare();
                        Toast.makeText(this_activity, "注册成功", Toast.LENGTH_SHORT).show();
                        Looper.loop();
                    }

                    else
                    {
                        Looper.prepare();
                        Toast.makeText(this_activity, "注册失败:"+stringBuffer.toString(), Toast.LENGTH_SHORT).show();
                        Looper.loop();
                    }
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    };
    private Thread newThread=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);

        Button regist_button=(Button)findViewById(R.id.button_regist);
        EditText username_editText=(EditText) findViewById(R.id.editText_username);
        EditText password_editText=(EditText)findViewById(R.id.editText_password);

        regist_button.setOnClickListener((View v) ->{
            username=username_editText.getText().toString();
            password=password_editText.getText().toString();

            newThread=new Thread(thread);
            newThread.start();
        });
    }
}
