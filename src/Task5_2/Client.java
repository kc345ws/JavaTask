package Task5_2;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * @ClassName Client
 * @Description TODO
 * @Author kc345ws
 * @Date 2019-5-15 23:52
 * @Version 1.0
 **/
public class Client{
    static Socket client;
    static InputStream inFromserver;
    static DataInputStream in;
    static OutputStream outToServer;
    static DataOutputStream out;

    public static void main(String []args) throws IOException {
        String serverName = "127.0.0.1";
        int port = 1888;
        System.out.println("连接到主机："+serverName
                +",端口号："+port);

        client = new Socket(serverName,port);
        System.out.println("远程主机地址:"
                +client.getRemoteSocketAddress());

        inFromserver = client.getInputStream();
        in = new DataInputStream(inFromserver);
        //System.out.println("服务器响应：" + in.readUTF());

        outToServer = client.getOutputStream();
        out = new DataOutputStream(outToServer);

        SendMessageThread sendMessage = new SendMessageThread();
        GetMessageThread getMessageThread = new GetMessageThread();
        Thread thread =new Thread(sendMessage);
        Thread thread1 =new Thread(getMessageThread);
        thread.start();
        thread1.start();

    }
}

class SendMessageThread extends Client implements Runnable{

    @Override
    public void run() {
        try {
            sendMessage();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage() throws IOException {
        while(true) {
            System.out.println("请输入要发送的消息");
            String message;
            Scanner scanner = new Scanner(System.in);
            message = scanner.next();
            //System.out.println("来自" + client.getLocalAddress() + "的消息");
            out.writeUTF(message+"--来自" + client.getLocalAddress() + "的消息");
        }
    }
}

class GetMessageThread extends Client implements Runnable{

    @Override
    public void run() {
        try {
            getMessage();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getMessage() throws IOException {
        while (true){
            InputStream inFromserver = client.getInputStream();
            DataInputStream in = new DataInputStream(inFromserver);
            System.out.println("服务器的回复：" + in.readUTF());
        }
    }
}
