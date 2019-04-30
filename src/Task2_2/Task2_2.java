package Task2_2;


import Task1_2.Common;
import Task1_2.ComputeTime;
import chc55170933.Task1_1;

import java.util.Scanner;

/**
 * @ClassName Task2_2
 * @Description TODO
 * @Author kc345ws
 * @Date 2019-4-28 9:00
 * @Version 1.0
 **/
public class Task2_2 {
    public static void main(String[]args){

        String type;
        int a,b,c;
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入交通工具类型和参数A,B,C");
        type =scanner.next();
        a=scanner.nextInt();
        b=scanner.nextInt();
        c=scanner.nextInt();

        try {
            Object object = Class.forName("Task1_2."+type).newInstance();
            Common common = (Common)object;
            common.setA(a);
            common.setB(b);
            common.setC(c);
            float speed = common.getSpeed();
            ComputeTime computeTime = new ComputeTime(speed);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            //e.printStackTrace();
            System.out.print("没有此类");
        }
    }
}
