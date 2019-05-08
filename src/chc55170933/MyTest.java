package chc55170933;

import java.util.Scanner;

/**
 * @ClassName MyTest
 * @Description TODO
 * @Author kc345ws
 * @Date 2019-5-8 17:08
 * @Version 1.0
 **/
public class MyTest {
    public static  void main(String[]args){
        Node Mynode = new Node();
        Scanner scanner = new Scanner(System.in);
        //Mynode.data = scanner.nextInt();
        Mynode.data = 1;
        Mynode.next = new Node();
        Mynode.next.data = 2;
        for (int i = 0 ; i < 2 ; i ++){
            System.out.println(Mynode.data);
            Mynode = Mynode.next;
        }
    }
}

class Node{
    int data;
    Node next;
    Node(){
        data = 0;
        next = null;
    }
}
