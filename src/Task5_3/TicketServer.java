package Task5_3;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Objects;
import java.util.Scanner;
import java.util.Set;

/**
 * @ClassName Task5_3
 * @Description TODO
 * @Author kc345ws
 * @Date 2019-5-16 7:49
 * @Version 1.0
 **/
public class TicketServer {
    static ServerSocket serverSocket;
    static DataOutputStream out;
    static DataInputStream in;
    static Socket server;
    static int port;
    static Set<Ticket>Tickets = new HashSet<>();
    static int TicketID;

    public static void main(String[]args) throws IOException {
        for(int i = 0 ; i < 100 ;i++){
            Ticket ticket = new Ticket();
            Tickets.add(ticket);
        }

        //System.out.println(Tickets.contains(new Ticket(0)));
        //contains用了equals和hashcode，必须对其重写

        port = 59806;
        serverSocket = new ServerSocket(port);

        System.out.println("等待远程连接,端口号："
                + serverSocket.getLocalPort() + "...");
        try {
            server = serverSocket.accept();
            //监听端口
            System.out.println("远程主机地址:"
                    + server.getRemoteSocketAddress());
            in = new DataInputStream(server.getInputStream());
            //System.out.println(in.readUTF());
            out = new DataOutputStream(server.getOutputStream());
            //out.writeUTF("连接成功"+server.getLocalAddress()+"\nGood Bye!");
        } catch (IOException e) {
            e.printStackTrace();
        }

        getMessagefromClientThread getMessagefromClientThread = new getMessagefromClientThread();
        Thread thread = new Thread(getMessagefromClientThread);
        thread.start();

        /*SellTicketThread sellTicketThread = new SellTicketThread();
        Thread thread = new Thread(sellTicketThread);
        thread.start();*/
    }
}

class Ticket{
    int id;
    static int staticid = 1;
    public Ticket(){
        id = staticid;
        staticid++;
    }

    public Ticket(int tempid){
        id = tempid;
    }

    @Override
    public boolean equals(Object obj) {
        Ticket tempticket = (Ticket)obj;
        if(tempticket.id == this.id){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}


class SellTicketThread extends TicketServer  implements Runnable{

    @Override
    public void run() {
        try {
            SellTicket(TicketID);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void SellTicket(int id) throws IOException {

        if(Tickets.contains(new Ticket(id))) {
            Tickets.remove(new Ticket(id));
            out.writeUTF(id + "");
        }
        else {
            out.writeUTF(0+"");
        }
    }
}

class getMessagefromClientThread extends TicketServer implements Runnable{

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

            String str = in.readUTF();
            TicketID = Integer.parseInt(str);
            System.out.println("购买机票:"+TicketID);
            //TicketID = Integer.parseInt(str);

            SellTicketThread sellTicketThread = new SellTicketThread();
            Thread thread1 = new Thread(sellTicketThread);
            thread1.start();

            //System.out.println("客户端的回复：" + in.readUTF());
        }
    }
}
