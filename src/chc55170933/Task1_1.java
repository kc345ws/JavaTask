package chc55170933;

import java.util.Scanner;

/**
 * @ClassName Task1_1
 * @Description TODO
 * @Author kc345ws
 * @Date 2019-4-27 22:34
 * @Version 1.0
 **/
public class Task1_1 {

    public static void main(String[] args){
        System.out.println("100-1000的水仙花数有:");
        for(int i = 100 ; i <= 1000 ; i++){
            Narnum narnum = new Narnum(i);
            if(narnum.CheckNatnum()==true){
                System.out.println(i);
            }
        }
    }
}



class Narnum{
    private int number;//要判断是否是水仙花数的数字
    private int []everynum;//数字的每一位
    int count;//数字位数

    public Narnum(int num){
        number = num;
        GetCount();
        Createeverynum();
        Geteverynum();
        CheckNatnum();
    }

    public int GetCount(){//获得数字总共有几位
        int Temp = number;
        count = 0;
        while(Temp != 0){
            count++;
            Temp /= 10;
        }
        return count;
    }

    public void Createeverynum(){
        everynum = new int[count];
    }

    public void Geteverynum(){//获得每一位的数字分别是什么
        int Temp = number;
        int Temp01 = number;
        for(int i = 0 ; i < count ; i++){
            Temp %= 10;
            everynum[i] = Temp;
            Temp01 = Temp01 / 10;
            Temp = Temp01;
        }
    }

    public boolean CheckNatnum(){//判断是否是水仙花数
        int Temp = 0;
        for(int i = 0 ; i < count ; i++){
            Temp += everynum[i]*everynum[i]*everynum[i];
        }
        if(Temp == number){
            return true;
        }else {
            return false;
        }
    }
}
