package Task5_1;

import com.mysql.jdbc.*;

import javax.xml.transform.Result;
import java.sql.*;
import java.util.Scanner;

/**
 * @ClassName Task5_1
 * @Description TODO
 * @Author kc345ws
 * @Date 2019-5-15 21:25
 * @Version 1.0
 **/
public class Task5_1 {
    public static void main(String[]args){
        MyDB myDB = new MyDB();
        //myDB.insert(new Employee());
        //myDB.select();
        //myDB.Update();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("请输入选项");
            System.out.println("1.查看所有男性信息");
            System.out.println("2.增加信息");
            System.out.println("3.更改编号2001的工资为900");
            System.out.println("4.查看所有信息");
            int ch = scanner.nextInt();
            switch (ch) {
                case 1:
                    myDB.selectMan();
                    break;
                case 2:
                    myDB.insert(new Employee());
                    break;
                case 3:
                    myDB.Update();
                    break;
                case 4:
                    myDB.select();
                    break;
            }
        }
    }
}

class MyDB {
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/MyDB?serverTimezone=GMT%2B8";
    private static final String USER ="root";
    private static final String PWD ="qhw739t";
    private Connection conn = null;
    //private Statement stmt = null;

    public MyDB(){

        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            //e.printStackTrace();
            System.out.println("初始化JDBC驱动失败");
        }
        //初始化JDBC驱动

        System.out.println("开始连接数据库...");
        try {
            conn = DriverManager.getConnection(DB_URL,USER,PWD);
            System.out.println("连接数据库成功");
        } catch (SQLException e) {
            e.printStackTrace();
            conn = null;
            System.out.println("连接数据库失败");
        }


    }

    public Connection getConn() {//连接数据库
        return conn;
    }

    public boolean insert(Employee employee){
        Connection connection = getConn();
        String sql = "insert into employee values(?,?,?,?)";
        PreparedStatement preparedStatement;

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,employee.getNo());
            preparedStatement.setString(2,employee.getName());
            preparedStatement.setString(3,employee.getSex());
            preparedStatement.setFloat(4,employee.getSalary());

            preparedStatement.executeUpdate();

            preparedStatement.close();
           // connection.close();
            return true;
        } catch (SQLException e) {
            //e.printStackTrace();
            System.out.println("获取PreparedStatement失败");
        }

        return  false;
    }

    public boolean selectMan(){
        Connection connection = getConn();
        String sql = "select * from employee where sex = '男'";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            java.sql.ResultSet result = preparedStatement.executeQuery();
            int col = result.getMetaData().getColumnCount();//获取表中数据的列数
            System.out.println("******************");
            System.out.println("以下为表中所有男性信息");
            System.out.println("编号\t\t姓名\t\t性别\t\t工资");
            while(result.next()){
                for(int i = 1; i <=col ; i++){
                    System.out.print(result.getString(i)+"\t");
                }
                System.out.print("\n");
            }
            System.out.println("******************");

            preparedStatement.close();
            //connection.close();
            return  true;

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("获取PreparedStatement失败");
        }
        return false;
    }

    public boolean select(){
        Connection connection = getConn();
        String sql = "select * from employee";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            java.sql.ResultSet result = preparedStatement.executeQuery();
            int col = result.getMetaData().getColumnCount();//获取表中数据的列数
            System.out.println("******************");
            System.out.println("以下为表中所有信息");
            System.out.println("编号\t\t姓名\t\t性别\t\t工资");
            while(result.next()){
                for(int i = 1; i <=col ; i++){
                    System.out.print(result.getString(i)+"\t");
                }
                System.out.print("\n");
            }
            System.out.println("******************");

            preparedStatement.close();
            //connection.close();
            return  true;

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("获取PreparedStatement失败");
        }
        return false;
    }

    public boolean Update(){
        Connection connection =getConn();
        String sql = "update employee set salary = 900 where no = '2001'" ;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();

            preparedStatement.close();
            //connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}

class Employee{
    private String no;//序号
    private String name;
    private String sex;
    private float salary;

    public Employee(String no,String name,String sex,float salary){
        this.no = no;
        this.name = name;
        this.sex = sex;
        this.salary = salary;
    }

    public Employee(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入编号");
        this.no = scanner.next();
        System.out.println("请输入姓名");
        this.name = scanner.next();
        System.out.println("请输入性别");
        this.sex = scanner.next();
        System.out.println("请输入工资");
        this.salary = scanner.nextFloat();
    }

    public String getName() {
        return name;
    }

    public float getSalary() {
        return salary;
    }

    public String getNo() {
        return no;
    }

    public String getSex() {
        return sex;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
