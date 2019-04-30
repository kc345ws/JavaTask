package Task3_1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.*;

/**
 * @ClassName Calculater
 * @Description TODO
 * @Author kc345ws
 * @Date 2019-4-28 9:19
 * @Version 1.0
 **/
public class Calculater extends JFrame implements ActionListener{
    public static void main(String[]args){

        Calculater calculater = new Calculater("计算器");
    }

    private JFrame jFrame;
    private JButton []button;
    String []texts ={
            "7","8","9","÷",
            "4","5","6","*",
            "1","2","3","-",
            "0",".","=","+"};
    JTextField jTextField;
    //JTextArea jTextArea;
    private String showtext="";//显示的字符串
    double tempcount[];//存放输入的数字
    int numcount = 0;//输入的数字个数
    int symcount = 0;//输入的符号个数
    String symbols[];//存放输入的符号
    boolean issymbol=true;//判断是否输入了符号而没有接着输入数字
    double result =0;//最后的计算结果
    double onenumber = 0;//存储完整的一个数字
    int clicknum = 0;//一个完整数字的每次*=10
    int []clicknumber;
    boolean isfinshed = false;
    String onenumstr ="";

    Stack<Object> vecobj;

    public Calculater(String tit){
        jFrame = new JFrame(tit);
        jFrame.setSize(700,500);
        jFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        tempcount = new double[1000];
        symbols = new String[1000];
        clicknumber = new int[1000];
        vecobj = new Stack<>();

        Container container = getContentPane();
        //container.setLayout(new GridLayout(2,1,3,3));
        container.setLayout(new BorderLayout());

        JPanel jPanel = new JPanel(new GridLayout(1,1));
        JPanel jPanel1 = new JPanel(new GridLayout(4,4,2,2));
        JPanel clearjpane = new JPanel(new GridLayout(1,1));

        jPanel.setPreferredSize(new Dimension(500,100));
        jPanel1.setPreferredSize(new Dimension(500,400));
        clearjpane.setPreferredSize(new Dimension(200,50));

        jTextField = new JTextField();
        Font font1 = new Font("微软雅黑",Font.BOLD,50);
        jTextField.setFont(font1);
        jTextField.setEditable(false);//设置不可编辑
        jTextField.setText("by吉大17级软工9班陈鸿超");

        //jTextField.setSize(500,50);

        jPanel.add(jTextField);
        button = new JButton[texts.length];
        for(int i = 0 ; i < texts.length ;i++){
            //ImageIcon icon = new ImageIcon("C:\\Users\\Administrator\\Pictures\\dm\\img02.jpg");
            button[i]=new JButton(texts[i]);
            button[i].addActionListener(this);
            //button[i].setSize(50,400);
            Font font = new Font("微软雅黑",Font.BOLD,30);
            button[i].setFont(font);
            button[i].setBackground(new Color(55, 150, 231));
            button[i].setOpaque(true);

            jPanel1.add(button[i]);
        }
        //container.add(jPanel);
        //container.add(jPanel1);
        JButton clearbutton = new JButton("AC");
        Font clearfont = new Font("微软雅黑",Font.BOLD,30);
        clearbutton.setFont(clearfont);
        clearbutton.addActionListener(this);
        clearjpane.add(clearbutton);
        container.add(jPanel,BorderLayout.NORTH);
        container.add(jPanel1,BorderLayout.CENTER);
        container.add(clearjpane,BorderLayout.SOUTH);
        jFrame.add(container);

        jFrame.setVisible(true);
        jFrame.setLocationRelativeTo(null);
        buttonlistener();

        clearbutton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                jTextField.setText(0.0+"");
                showtext="";
                result = 0;
                symcount = 0;
                numcount = 0;
                showtext = "";
                symbols = new String[1000];
                tempcount = new double[1000];
                onenumber = 0;
                clicknum = 1;
                isfinshed =false;
                clicknumber = new int[1000];
                onenumstr ="";
                vecobj = new Stack<>();
                issymbol=true;


            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
    }

    public void buttonlistener(){
        for(int i = 0 ; i < texts.length ; i ++){
            button[i].addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {

                }
                @Override
                public void mousePressed(MouseEvent e) {
                    /*if(!((JButton)e.getSource()).getText().equals("=")
                            &&!((JButton)e.getSource()).getText().equals("+")
                            &&!((JButton)e.getSource()).getText().equals("-")
                            &&!((JButton)e.getSource()).getText().equals("*")
                            &&!((JButton)e.getSource()).getText().equals("÷")){

                    }*/
                    if((((JButton) e.getSource()).getText().equals("+"))&&issymbol==false){
                        if(!onenumstr.equals("")) {
                            tempcount[numcount] = Double.parseDouble(onenumstr);
                            vecobj.push(Double.parseDouble(onenumstr));
                        }
                        onenumstr ="";

                        issymbol = true;
                        symbols[symcount] = "+";
                        symcount++;
                        numcount++;
                        showtext+="+";
                        jTextField.setText(showtext);

                        vecobj.push("+");


                    }else if((((JButton) e.getSource()).getText().equals("-"))&&issymbol==false){
                        if(!onenumstr.equals("")) {
                            tempcount[numcount] = Double.parseDouble(onenumstr);
                            vecobj.push(Double.parseDouble(onenumstr));
                        }
                        onenumstr ="";

                        issymbol = true;
                        symbols[symcount] = "-";
                        symcount++;
                        numcount++;
                        showtext+="-";
                        jTextField.setText(showtext);

                        vecobj.push("-");


                    }else if((((JButton) e.getSource()).getText().equals("÷"))&&issymbol==false){
                        if(!onenumstr.equals("")) {
                            tempcount[numcount] = Double.parseDouble(onenumstr);
                            vecobj.push(Double.parseDouble(onenumstr));
                        }
                        onenumstr ="";

                        issymbol = true;
                        symbols[symcount] = "/";
                        symcount++;
                        numcount++;
                        showtext+="÷";
                        jTextField.setText(showtext);

                        vecobj.push("/");


                    }else if((((JButton) e.getSource()).getText().equals("*"))&&issymbol==false){
                        if(!onenumstr.equals("")) {
                            tempcount[numcount] = Double.parseDouble(onenumstr);
                            vecobj.push(Double.parseDouble(onenumstr));
                        }
                        onenumstr ="";

                        issymbol = true;
                        symbols[symcount] = "*";
                        symcount++;
                        numcount++;
                        showtext+="*";
                        jTextField.setText(showtext);

                        vecobj.push("*");


                    }else if((((JButton) e.getSource()).getText().equals("."))&&issymbol==false){

                        issymbol = true;
                        showtext+=".";
                        jTextField.setText(showtext);

                        onenumstr+=".";
                    }

                    else if(!((JButton)e.getSource()).getText().equals("=")//输入了数字
                            &&!((JButton)e.getSource()).getText().equals("+")
                            &&!((JButton)e.getSource()).getText().equals("-")
                            &&!((JButton)e.getSource()).getText().equals("*")
                            &&!((JButton)e.getSource()).getText().equals("÷")
                            &&!((JButton)e.getSource()).getText().equals(".")  ){
                        issymbol = false;
                        showtext+=((JButton)e.getSource()).getText();
                        jTextField.setText(showtext);
                        String str = ((JButton)e.getSource()).getText();
                        //tempcount[numcount] = Double.parseDouble(str);
                        //numcount++;
                        //onenumber += Double.parseDouble(str)*clicknum;
                        onenumstr += str;
                    }else if(((JButton)e.getSource()).getText().equals("=")
                    &&numcount != symcount){//如果最后一位为符号

                        if(!vecobj.isEmpty())
                        {
                            comResult();
                        }
                        jTextField.setText(result+"");
                        result = 0;
                        symcount = 0;
                        numcount = 0;
                        showtext = "";
                        symbols = new String[1000];
                        tempcount = new double[1000];
                        onenumber = 0;
                        clicknum = 1;

                        issymbol =true;


                    }else if(((JButton)e.getSource()).getText().equals("=")
                    &&numcount == symcount){

                        if(!onenumstr.equals("")&&isfinshed==false){
                        tempcount[numcount] = Double.parseDouble(onenumstr);
                        vecobj.push(Double.parseDouble(onenumstr));
                        onenumstr ="";
                        numcount++;
                        isfinshed =true;
                        }
                        if(!vecobj.isEmpty())
                        {
                        comResult();
                        }
                        jTextField.setText(result+"");
                        result = 0;
                        symcount = 0;
                        numcount = 0;
                        showtext = "";
                        symbols = new String[1000];
                        tempcount = new double[1000];
                        onenumber = 0;
                        clicknum = 1;
                        issymbol = true;
                    }
                }

                @Override
                public void mouseReleased(MouseEvent e) {

                }

                @Override
                public void mouseEntered(MouseEvent e) {

                }

                @Override
                public void mouseExited(MouseEvent e) {

                }
            });
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
    }

    public void comResult(){

        String popsymbol;
        //Queue<Object> queobj = new LinkedList<>();
        Vector<Object> tempnum = new Stack<>();
        Vector<Object> tempsymbol = new Stack<>();
        Vector<Object> tempresult = new Stack<>();
        for(Object obj:vecobj){
            if(obj =="+"||obj=="-"||obj=="*"||obj=="/"){
                String topsym="";
                if(!((Stack<Object>) tempsymbol).isEmpty()){//如果栈顶不空
                    topsym = (String) ((Stack<Object>) tempsymbol).peek();
                }
                String addedsym = (String)obj;
                if(addedsym == "*"&&topsym=="+"||addedsym=="*"&&topsym=="-"){
                    //如果要加入的符号优先级比栈顶元素高
                    ((Stack<Object>) tempsymbol).push(obj);
                }else if(addedsym == "/"&&topsym=="+"||addedsym=="/"&&topsym=="-"){
                    ((Stack<Object>) tempsymbol).push(obj);
                }else if(((Stack<Object>) tempsymbol).isEmpty()){//如果栈顶为空直接压入
                    ((Stack<Object>) tempsymbol).push(obj);
                }else{//否则弹出直到与到低优先级或者空后压入
                    while(true) {
                        popsymbol = (String)((Stack<Object>) tempsymbol).pop();
                        ((Stack<Object>) tempnum).push(popsymbol);
                        if(addedsym == "*"&&topsym=="+"||addedsym=="*"&&topsym=="-"){
                            //如果要加入的符号优先级比栈顶元素高
                            ((Stack<Object>) tempsymbol).push(obj);
                            break;
                        }else if(addedsym == "/"&&topsym=="+"||addedsym=="/"&&topsym=="-"){
                            ((Stack<Object>) tempsymbol).push(obj);
                            break;
                        }else if(tempsymbol.isEmpty()){//如果栈顶为空直接压入
                            ((Stack<Object>) tempsymbol).push(obj);
                            break;
                        }
                    }
                }
            }else{//数字直接压入
                ((Stack<Object>) tempnum).push(obj);
            }
        }//((Stack<Object>) tempnum).push(((Stack<Object>) tempsymbol).pop());
        while(!tempsymbol.isEmpty()){
            ((Stack<Object>) tempnum).push(((Stack<Object>) tempsymbol).pop());
        }

        for(Object obj : tempnum) {
            if (obj == "+" || obj == "-" || obj == "*" || obj == "/") {
                double temp1 = (double) ((Stack<Object>) tempresult).pop();
                double temp2 = (double) ((Stack<Object>) tempresult).pop();
                if (obj == "+") {
                    result = temp2 + temp1;//次顶 运算 栈顶
                    ((Stack<Object>) tempresult).push(temp2 + temp1);
                } else if (obj == "-") {
                    result = temp2 - temp1;
                    ((Stack<Object>) tempresult).push(temp2 - temp1);
                } else if (obj == "*") {
                    result = temp2 * temp1;
                    ((Stack<Object>) tempresult).push(temp2 * temp1);
                } else if (obj == "/") {
                    result = temp2 / temp1;
                    ((Stack<Object>) tempresult).push(temp2 / temp1);
                }
            } else {
                ((Stack<Object>) tempresult).push(obj);
            }
        }

        //Vector<String>symboltemp = new Stack<String>();
        //Vector<Double>numtemp = new Stack<Double>();
        //Map<Integer,String> symbolmap = new LinkedHashMap<>();
        //Map<Integer,Double> nummap = new LinkedHashMap<>();
        //Vector<String> symboltemp = new Stack<>();
        /*for(int i = 0 ; i < numcount ; i ++){
            nummap.put(i,tempcount[i]);
        }
        for(int i = 0;i < symcount ; i ++){
            //numtemp.add(tempcount[i]);
            //symboltemp.add(symbols[i]);
            if(symbols[i]=="*"||symbols[i]=="/"){
                //((Stack<String>) symboltemp).push(symbols[i]);//将符号压入栈
                symbolmap.put(i,symbols[i]);
                //((Stack<String>) symboltemp).push(str);
            }
            //((Stack<Double>) numtemp).push(tempcount[i]);
        }
        /*if(numcount > symcount){//如果最后一位为符号
            ((Stack<Double>) numtemp).push(tempcount[numcount-1]);
        }*/
        /*for(int i = 0;i < symcount ; i ++){

            if(symbols[i]=="+"||symbols[i]=="-"){
                symbolmap.put(i,symbols[i]);
            }

        }

        for(Map.Entry<Integer,String> entry:symbolmap.entrySet()){
            if(entry.getValue() =="*"){
                result += nummap.get(entry.getKey())*nummap.get(entry.getKey()+1);

            }
        }

        /*for(int i = 0;i < symcount ; i ++){
            //numtemp.add(tempcount[i]);
            //symboltemp.add(symbols[i]);
            if(symbols[i]=="*"||symbols[i]=="/"){
                ((Stack<String>) symboltemp).push(symbols[i]);//将符号压入栈
            }
        }

        for(int i = 0 ; i <symcount ; i++){
            String str = ((Stack<String>) symboltemp).pop();

        }
        for(int i = 0;i < symcount ; i ++){
            if(symboltemp.get(i)=="*"){
                result += numtemp.get(i)*numtemp.get(i+1);
                numtemp.set(i,numtemp.get(i)*numtemp.get(i+1));
                numtemp.remove(i+1);//运算后清除
            }else if(symboltemp.get(i)=="/"){
                result+=numtemp.get(i)/numtemp.get(i+1);
                numtemp.set(i,numtemp.get(i)/numtemp.get(i+1));
                numtemp.remove(i+1);//运算后清除
            }
        }

        for(int i = 0;i < symcount ; i ++){
            if(symboltemp.get(i)=="+"){
                result += numtemp.get(i)+numtemp.get(i+1);
                numtemp.set(i,numtemp.get(i)+numtemp.get(i+1));
                numtemp.remove(i+1);//运算后清除
            }else if(symboltemp.get(i)=="-"){
                result+=numtemp.get(i)-numtemp.get(i+1);
                numtemp.set(i,numtemp.get(i)-numtemp.get(i+1));
                //numtemp.remove(i+1);//运算后清除
                numtemp.removeElementAt(i+1);
            }
        }

        /*for(int i = 0 ; i < minlength; i ++){
            if(symbols[i]=="+"){
            }else if(symbols[i]=="-"){

            }else  if(symbols[i]=="*"){

            }else  if(symbols[i]=="/"){

            }
        }*/
    }
}
