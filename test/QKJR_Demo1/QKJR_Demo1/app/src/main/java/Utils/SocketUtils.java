package Utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Proxy;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by zhuzhuxia on 16/5/10.
 */
public class SocketUtils {
    private Socket socket;
    private ServerSocket serverSocket;
    private boolean isServer;
    private int PORT;


    public SocketUtils(boolean isServer,String ipAddress) throws IOException {

        this.isServer=isServer;
        if(isServer){

            serverSocket=new ServerSocket(PORT);
        }
        else{

            socket=new Socket(ipAddress,PORT);
        }
    }
    public Socket accept() throws IOException {

        return serverSocket.accept();
    }

    public String rcvMsg(Socket socket) throws IOException {
        InputStream in=socket.getInputStream();
        StringBuilder build=new StringBuilder();
        byte[] buffer=new byte[1024];
        while(in.read(buffer)>0){
            build.append(new String(buffer));
        }
        return build.toString();
    }

    public void sendMsg(Socket socket,String msg) throws IOException {
        OutputStream out=socket.getOutputStream();
        out.write(msg.getBytes());
    }





}
