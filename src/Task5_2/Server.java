package Task5_2;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * @ClassName Server
 * @Description TODO
 * @Author kc345ws
 * @Date 2019-5-15 23:52
 * @Version 1.0
 **/
public class Server {
    static ServerSocket serverSocket;
    static DataOutputStream out;
    static DataInputStream in;
    static Socket server;
    public static void main(String[] args) throws IOException {
        int port = 1888;
        serverSocket = new ServerSocket(port);
        System.out.println("等待远程连接,端口号："
                + serverSocket.getLocalPort() + "...");
        try {

            server = serverSocket.accept();
            //监听端口
            System.out.println("远程主机地址:"
                    + server.getRemoteSocketAddress());
            in = new DataInputStream(server.getInputStream());
            System.out.println(in.readUTF());
            out = new DataOutputStream(server.getOutputStream());
            //out.writeUTF("连接成功"+server.getLocalAddress()+"\nGood Bye!");
        } catch (IOException e) {
            e.printStackTrace();
        }

        SendClientMessageThread sendMessage = new SendClientMessageThread();
        GetClientMessageThread getMessageThread = new GetClientMessageThread();
        Thread thread =new Thread(sendMessage);
        Thread thread1 =new Thread(getMessageThread);
        thread.start();
        thread1.start();
    }


}

class SendClientMessageThread extends Server implements Runnable {

    @Override
    public void run() {
        try {
            sendMessage();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage() throws IOException {
        while (true) {
            System.out.println("请输入要发送的消息");
            String message;
            Scanner scanner = new Scanner(System.in);
            message = scanner.next();
            //System.out.println("来自" + client.getLocalAddress() + "的消息");
            out.writeUTF(message + "--来自" + server.getLocalAddress() + "的消息");
        }
    }
}

class GetClientMessageThread extends Server implements Runnable {

    @Override
    public void run() {
        try {
            getMessage();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getMessage() throws IOException {
        while (true) {
            InputStream inFromserver = server.getInputStream();
            DataInputStream in = new DataInputStream(inFromserver);
            System.out.println("服务器的回复：" + in.readUTF());
        }
    }
}