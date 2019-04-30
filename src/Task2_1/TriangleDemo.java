package Task2_1;

import java.awt.*;
import java.util.Scanner;

/**
 * @ClassName TriangleDemo
 * @Description TODO
 * @Author kc345ws
 * @Date 2019-4-28 8:14
 * @Version 1.0
 **/
public class TriangleDemo {
    public static void main(String[]args){
        float x1,x2,x3,y1,y2,y3;
        Point p1 = new Point();
        Point p2 = new Point();
        Point p3 = new Point();
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入第一个点的x,y");
        x1=scanner.nextFloat();
        y1=scanner.nextFloat();
        System.out.println("请输入第二个点的x,y");
        x2=scanner.nextFloat();
        y2=scanner.nextInt();
        System.out.println("请输入第三个点的x,y");
        x3=scanner.nextFloat();
        y3=scanner.nextFloat();
        p1.setLocation(x1,y1);
        p2.setLocation(x2,y2);
        p3.setLocation(x3,y3);
        double dis1,dis2,dis3;
        dis1=p1.distance(p2);
        dis2=p2.distance(p3);
        dis3=p3.distance(p1);

        if(dis1+dis2>dis3&&dis2+dis3>dis1
        &&dis1-dis2<dis3&&dis2-dis3<dis1){
            double area = (x1*y3+x2*y1+x3*y2-x1*y2-x2*y3-x3*y1)*1/2;
            area = Math.abs(area);
            System.out.println("此三角形面积为:" + area);

        }else{
            System.out.println("不能构成三角形");
        }
    }
}
