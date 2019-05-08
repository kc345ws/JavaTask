package Task4_1;

import com.sun.jmx.remote.internal.ArrayQueue;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

/**
 * @ClassName Que
 * @Description TODO
 * @Author kc345ws
 * @Date 2019-5-8 20:00
 * @Version 1.0
 **/
public class Que {
    public static MyQueue testque = new MyQueue();
    public static void main(String[]args){
        while (true){
            Menu();
        }
    }

    public static void Menu(){
        System.out.println("请输入要选择的功能");
        System.out.println("1.入队列");
        System.out.println("2.出队列");
        System.out.println("3.查看是否为空");

        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        if(choice == 1){
            System.out.println("请输入要入队列的数据");
            testque.put(scanner.next());
        }else if(choice == 2){

            System.out.println(testque.get());
        }else if(choice == 3){
            if(testque.isEmpty()){
                System.out.println("队列为空");
            }else{
                System.out.println("队列非空");
            }
        }
    }
}

class MyQueue{
    private List<Object> MyList;

    MyQueue(){
        MyList = new ArrayList<>();
    }
    public Object get(){//出队列
        if (!isEmpty()){//如果队列不为空
            System.out.println("队首数据为");
            Object obj = MyList.get(0);
            MyList.remove(0);
            return obj;
        }else{
            return "队列为空";
        }
    }

    public void put(Object obj){//入队列
        MyList.add(obj);
    }

    public boolean isEmpty(){//队列是否空
        if (MyList.size()==0){
            return true;
        }else {
            return false;
        }
    }
}


