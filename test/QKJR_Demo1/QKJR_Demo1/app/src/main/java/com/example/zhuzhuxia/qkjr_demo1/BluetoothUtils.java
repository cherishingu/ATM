package com.example.zhuzhuxia.qkjr_demo1;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Set;
import java.util.UUID;

import Utils.LogUtils;

/**
 * Created by zhuzhuxia on 16/5/10.
 */

public class BluetoothUtils {
    private BluetoothAdapter adapter;
    private Activity activity;
    private UUID uuid=UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    private BluetoothDevice targetDevice;

    private String targetDeviceName;
    public BluetoothUtils(Activity activity){
        this.activity=activity;
        initBluetooth();
        scan();
    }



    private void initBluetooth(){
        adapter=BluetoothAdapter.getDefaultAdapter();
        if(!adapter.isEnabled()){
            adapter.enable();
        }
        Intent enable=new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
        enable.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 3600);
        activity.startActivity(enable);
    }

    /**
     * 客户端连接服务端
     */
    public BluetoothSocket connect() throws IOException {

        LogUtils.logV("uuid:" + uuid);
        //连接
        if(targetDevice!=null) {
            BluetoothSocket clientSocket=targetDevice.createRfcommSocketToServiceRecord(uuid);
            LogUtils.logV("连接成功");
            return clientSocket;
        }
        else{
            LogUtils.logD("targetDevice is null(扫描蓝牙列表时得不到H60-L03)");
            return null;
        }

    }

    public BluetoothSocket listen() throws IOException {

        BluetoothServerSocket serverSocket=adapter.listenUsingRfcommWithServiceRecord("qkjr",uuid);
        BluetoothSocket socket=serverSocket.accept();
        serverSocket.close();
        LogUtils.logV("连接成功");
        return socket;

    }

    /**
     * 发送信息
     * @param socket,message
     */

    public void sendMsg(BluetoothSocket socket,String message) throws IOException {

        if(socket!=null){
            boolean re=socket.isConnected();
            if(re){
                LogUtils.logD("socket is connected");
            }
            else{
                LogUtils.logD("socket is not connected");
            }

            if(socket==null){
                LogUtils.logD("socket is null");
            }
            else{
                LogUtils.logD("socket is not null");
            }
            OutputStream out=socket.getOutputStream();
            //写进信息
            if(out==null){
                LogUtils.logD("out is null");
            }
            else{
                LogUtils.logD("out is not null");
            }
            if(message==null){
                LogUtils.logD("message is null");
            }
            else{
                LogUtils.logD("message is not null");
            }
            out.write(message.getBytes());
        }
        else{

            LogUtils.logD("发消息时,socket是null,accept没有获取socket");
        }

    }

    /**
     * 收到信息
     * @return
     */
    public String rcvMsg(BluetoothSocket socket) throws IOException {

        InputStream in=socket.getInputStream();
        StringBuilder build=new StringBuilder();
        byte[] buffer=new byte[1024];
        while(in.read(buffer)>0){
            build.append(new String(buffer));
        }
        return build.toString();
    }

    private Set<BluetoothDevice> resultList;


    private void scan(){

        resultList=adapter.getBondedDevices();

        if(resultList.isEmpty()){
            LogUtils.logV("the result is empty");
        }
        for(BluetoothDevice device:resultList){
            LogUtils.logV(device.getName());
            if(device.getName().toString().equals("QiKU")){
                targetDevice=device;
            }
        }
    }
}
