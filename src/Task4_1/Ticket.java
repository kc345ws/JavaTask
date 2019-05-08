package Task4_1;

/**
 * @ClassName Ticket
 * @Description 火车站售票模拟
 * @Author kc345ws
 * @Date 2019-5-8 21:19
 * @Version 1.0
 **/
public class Ticket{
    public static void main(String[]args){
        for(int i = 0 ; i < 5; i++){
            MyThread thread = new MyThread();
            thread.start();
        }
    }
}

class MyThread extends Thread{
    private static int state = 0;//用来对站点号进行递增
    private int thisstate;//本站点的站点号
    private static int Ticketnum = 100;//票数
    private static Object Lock = new Object();//用来锁住票数，防止多个线程同时修改
    MyThread(){
        state++;
        thisstate = state;
    }

    public void sellTicket() throws InterruptedException {
        while (true){
            if(Ticketnum == 0){
                break;//当执行完run方法时该线程自动结束
            }
            synchronized (Lock){
                System.out.println(thisstate+"_____"+Ticketnum);
                Ticketnum--;
            }
            sleep(1000);
        }
    }

    public void run() {
        try {
            sellTicket();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

