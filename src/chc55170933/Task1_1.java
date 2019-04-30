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
    private int number;
    private int []everynum;
    int count;

    public Narnum(int num){
        number = num;
        GetCount();
        Createeverynum();
        Geteverynum();
        CheckNatnum();
    }

    public int GetCount(){
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

    public void Geteverynum(){
        int Temp = number;
        int Temp01 = number;
        for(int i = 0 ; i < count ; i++){
            Temp %= 10;
            everynum[i] = Temp;
            Temp01 = Temp01 / 10;
            Temp = Temp01;
        }
    }

    public boolean CheckNatnum(){
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
