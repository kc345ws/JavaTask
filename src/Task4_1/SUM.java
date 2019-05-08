package Task4_1;

import java.util.Scanner;

/**
 * @ClassName SUM
 * @Description TODO
 * @Author kc345ws
 * @Date 2019-5-8 19:47
 * @Version 1.0
 **/
public class SUM {

    public static  void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        int sum = 0;
        System.out.println("请输入整数，999停止输入");
        while(true){
            int temp = scanner.nextInt();
            if(temp == 999){
                break;
            }else if(temp < 0){
                throw new Exception("不能输入负数");
            }else{
                sum += temp;
            }
        }
        System.out.println("求和结果为:"+sum);
    }
}
