package com.example.zhuzhuxia.qkjr_demo1;

import android.bluetooth.BluetoothSocket;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;

import Utils.LogUtils;

public class MainActivity extends AppCompatActivity {

    private  BluetoothSocket socket;
    private boolean connected;
    private EditText tv;
    private Button btn;
    private BluetoothUtils utils;
    private String result;

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    tv.setText(result);
                    break;
            }
        }
    };





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        utils=new BluetoothUtils(MainActivity.this);
        Bundle bundle=getIntent().getExtras();
        String value=bundle.getString("value");
        if(value=="1"){
            manageBluetooth(utils,1);
        }
        else{

        }
        initListener();


    }

    private void initViews(){
        tv= (EditText) findViewById(R.id.state);
        btn= (Button) findViewById(R.id.submit);
    }

    View.OnClickListener listener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch(v.getId()){
                case R.id.state:

                    break;
                case R.id.submit:
                    try {
                        if(connected) {
                            utils.sendMsg(socket, "hello");
                        }
                        else{
                            LogUtils.logD("还没有连接");
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    break;
            }
        }
    };
    private void initListener(){
        btn.setOnClickListener(listener);
    }

    private void send(){

    }

    private void manageBluetooth(final BluetoothUtils utils, final int state){
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(state==1) {
                    while (true) {
                        try {
                            //服务端
                            socket = utils.listen();
                            if (socket != null) {
                                connected = true;
                                while (true) {

                                    result = utils.rcvMsg(socket);

                                    //LogUtils.logV(result);
                                    handler.sendEmptyMessage(0);
                                }
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                            break;
                        }
                    }
                }
                else{
                    //客户端
                    try {
                        socket=utils.connect();
                        if(socket.isConnected()){
                            connected=true;
                            utils.sendMsg(socket,"haha");
                            while(true){
                                String result = utils.rcvMsg(socket);
                               // LogUtils.logV(result);
                            }
                        }
                    }
                    catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
