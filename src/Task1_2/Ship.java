package Task1_2;

/**
 * @ClassName Ship
 * @Description TODO
 * @Author kc345ws
 * @Date 2019-4-28 0:33
 * @Version 1.0
 **/
public class Ship implements Common{
    int A,B,C;
    float speed;
    public Ship(int a , int b , int c){
        A=a;B=b;C=c;
        speed = A*B/C;
    }
    @Override
    public float getSpeed() {
        return speed;
    }

    @Override
    public void setA(int a) {
        A =a;
    }

    @Override
    public void setB(int b) {
        B =b;
    }

    @Override
    public void setC(int c) {
        C = c;
    }
}
