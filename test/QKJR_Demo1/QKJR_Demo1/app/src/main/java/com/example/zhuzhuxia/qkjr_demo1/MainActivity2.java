package com.example.zhuzhuxia.qkjr_demo1;

import android.app.Activity;
import android.net.wifi.WifiConfiguration;
import android.os.Bundle;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

import Utils.LogUtils;
import Utils.SocketUtils;
import Utils.WifiAdmin;

/**
 * Created by zhuzhuxia on 16/5/10.
 */
public class MainActivity2  extends Activity{

    private String mainBssid,ssid;
    private Socket socket;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    private void manageSocket(final boolean isServer){
        new Thread(new Runnable() {
            @Override
            public void run() {
                SocketUtils utils= null;
                try {
                    utils = new SocketUtils(isServer,null);
                    socket=utils.accept();
                    while(true){
                        LogUtils.logV(utils.rcvMsg(socket));
                    }
                }
                catch (IOException e) {
                    e.printStackTrace();
                }


            }
        }).start();


    }


    private boolean connect(String ssid,String key)
    {
        WifiAdmin wifiAdmin = new WifiAdmin(MainActivity2.this);
        wifiAdmin.openWifi();
        WifiConfiguration configuration=wifiAdmin.CreateWifiInfo(ssid, key, 3);
        wifiAdmin.addNetwork(configuration);
        return true;
    }

    private class ServerThread implements Runnable{

        boolean isServer;
        String ipAddress;
        Socket socket;
        public ServerThread(boolean isServer,String ipAddress) {
            this.isServer=isServer;
            this.ipAddress=ipAddress;
        }

        @Override
        public void run() {
            SocketUtils utils= null;
            try {
                utils = new SocketUtils(isServer,ipAddress);
                socket=utils.accept();
                while(true){
                    LogUtils.logV(utils.rcvMsg(socket));
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }






}
