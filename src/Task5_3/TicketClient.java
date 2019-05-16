package Task5_3;

import java.awt.*;
import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/**
 * @ClassName Client
 * @Description TODO
 * @Author kc345ws
 * @Date 2019-5-16 7:52
 * @Version 1.0
 **/
public class TicketClient {
    static Socket client;
    static InputStream inFromserver;
    static DataInputStream in;
    static OutputStream outToServer;
    static DataOutputStream out;

    public static void main(String[]args) throws IOException {
        String serverName = "127.0.0.1";
        int port = 59806;
        System.out.println("连接到主机："+serverName
                +",端口号："+port);

        client = new Socket(serverName,port);
        System.out.println("远程主机地址:"
                +client.getRemoteSocketAddress());

        //inFromserver = client.getInputStream();
        //in = new DataInputStream(inFromserver);
        //System.out.println("服务器响应：" + in.readUTF());




        BuyTicketThread buyTicketThread = new BuyTicketThread();
        Thread thread = new Thread(buyTicketThread);
        thread.start();
        getMessagefromServerThread getMessagefromServerThread = new getMessagefromServerThread();
        Thread thread1 = new Thread(getMessagefromServerThread);
        thread1.start();
    }
}


class BuyTicketThread extends TicketClient implements Runnable{

    @Override
    public void run() {
        try {
            buyTicket();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void buyTicket() throws IOException {
        while(true) {
            outToServer = client.getOutputStream();
            out = new DataOutputStream(outToServer);
            System.out.println("请输入要购买机票的ID(1-100)");
            String id;
            Scanner scanner = new Scanner(System.in);
            id = scanner.next();

            //System.out.println("来自" + client.getLocalAddress() + "的消息");
            //out.writeUTF("购买id为"+id+"的机票--来自" + client.getLocalAddress() + "的消息");
            out.writeUTF(id);//向服务器发送要购买机票的ID
        }
    }
}

class getMessagefromServerThread extends TicketClient implements Runnable{
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