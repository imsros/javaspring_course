import com.mysql.cj.x.protobuf.MysqlxPrepare;

import java.sql.DriverManager;
import java.util.Scanner;
import java.sql.*;

public class Student {
    public static Connection connection(){
        String url = "jdbc:mysql://localhost:3306/db_java_spring";
        String username = "root";
        String password = "";

        try{
            System.out.println("Successfully connected");
            return DriverManager.getConnection(url,username,password);

        }catch (Exception e){
            System.out.println("Failed connection...!!");
            e.printStackTrace();
        }
        return null;
    }
    public static void main(String[] args){
        Student.connection();
        Scanner scanner = new Scanner(System.in);
        int ch;
        int id;
        String name;
        String gender;
        float java,dsa,dc,total,avg;
        char grade;
        do{
            System.out.println("=======Option=====");
            System.out.println("1.Insert ");
            System.out.println("2.Select");
            System.out.println("3.Search by ID");
            System.out.println("4.Search by Name");
            System.out.println("5.Update");
            System.out.println("6.Delete by ID");
            System.out.println("7.Sort ID in DESC");
            System.out.println("8.Sort Name A-Z");
            System.out.print("Please choose one option : ");
            ch = scanner.nextInt();
            switch(ch){
                case 1->{
                    System.out.println("=======Insert Case========");
                    int n;
                    System.out.print("Enter Number of Students : ");
                    n = scanner.nextInt();
                    for(int i = 0; i < n; i++ ){
                        System.out.println("Student #"+(i+1)+"   ");
                        System.out.print("Enter Student ID : ");
                        id = scanner.nextInt();
                        System.out.print("Enter Student Name : ");
                        scanner.nextLine();
                        name = scanner.nextLine();
                        System.out.print("Enter Student Gender : ");
                        gender = scanner.nextLine();
                        System.out.print("Enter Java Score : ");
                        java = scanner.nextFloat();
                        System.out.print("Enter DSA Score : ");
                        dsa = scanner.nextFloat();
                        System.out.print("Enter DC Score : ");
                        dc = scanner.nextFloat();
                        total = java + dsa + dc;
                        avg = total / 3;
                        if(avg>=90){
                            grade = 'A';
                        }else if(avg>=80){
                            grade = 'B';
                        }else if(avg>=70){
                            grade = 'C';
                        }else if(avg>=60){
                            grade = 'D';
                        }else if(avg>=50){
                            grade = 'E';
                        }else
                            grade = 'F';
                        String sql = "INSERT into studentnew(id,name,gender,java,dsa,dc,total,average,grade) VALUES(?,?,?,?,?,?,?,?,?)";
                        try{
                            Connection con = connection();
                            PreparedStatement ps = con.prepareStatement(sql);
                            ps.setInt(1,id);
                            ps.setString(2,name);
                            ps.setString(3,gender);
                            ps.setFloat(4,java);
                            ps.setFloat(5,dsa);
                            ps.setFloat(6,dc);
                            ps.setFloat(7,total);
                            ps.setFloat(8,avg);
                            ps.setString(9,grade+"");
                            ps.executeUpdate();
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                    System.out.println("Successfully inserted...");
                }
                case 2->{
                    System.out.println("========Selection case ===========");
                    try{
                        Connection con = connection();
                        String sql = "SELECT * FROM studentnew";
                        Statement st = con.createStatement();
                        ResultSet rs = st.executeQuery(sql);
                       while(rs.next()){
                           System.out.println("ID : "+rs.getInt("id"));
                           System.out.println("Name : "+rs.getString("name"));
                           System.out.println("Gender : "+rs.getString("gender"));
                           System.out.println("Java : "+rs.getFloat("java"));
                           System.out.println("DSA : "+rs.getFloat("dsa"));
                           System.out.println("DC : "+rs.getFloat("dc"));
                           System.out.println("Total : "+rs.getFloat("total"));
                           System.out.println("Average : "+rs.getFloat("average"));
                           System.out.println("Grade : "+rs.getString("grade"));
                       }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                case 3->{
                    System.out.println("==========Search By ID case=============");
                    System.out.println("Enter ID to search : ");
                    int Id = scanner.nextInt();
                    String sql ="SELECT * FROM studentnew WHERE id = ?" +Id;
                    try{
                        Connection con = connection();
                        Statement st = con.createStatement();
                        ResultSet rs = st.executeQuery(sql);
                        while(rs.next()){
                            System.out.println("ID : "+rs.getInt("id"));
                            System.out.println("Name : "+rs.getString("name"));
                            System.out.println("Gender : "+rs.getString("gender"));
                            System.out.println("Java : "+rs.getFloat("java"));
                            System.out.println("DSA : "+rs.getFloat("dsa"));
                            System.out.println("DC : "+rs.getFloat("dc"));
                            System.out.println("Total : "+rs.getFloat("total"));
                            System.out.println("Average : "+rs.getFloat("average"));
                            System.out.println("Grade : "+rs.getString("grade"));
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                case 4->{
                    System.out.println("=============Search By Name case================");
                    System.out.print("Enter Name to search : ");
                    scanner.nextLine();
                    String names = scanner.nextLine();
                    String sql = "SELECT * FROM studentnew WHERE name LIKE ?";
                    try{
                        Connection con = connection();
                        PreparedStatement ps = con.prepareStatement(sql);
                        ps.setString(1,"%"+names+"%");
                        ResultSet rs = ps.executeQuery();
                        while(rs.next()){
                            System.out.println("ID : "+rs.getInt("id"));
                            System.out.println("Name : "+rs.getString("name"));
                            System.out.println("Gender : "+rs.getString("gender"));
                            System.out.println("Java : "+rs.getFloat("java"));
                            System.out.println("DSA : "+rs.getFloat("dsa"));
                            System.out.println("DC : "+rs.getFloat("dc"));
                            System.out.println("Total : "+rs.getFloat("total"));
                            System.out.println("Average : "+rs.getFloat("average"));
                            System.out.println("Grade : "+rs.getString("grade"));
                        }
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }
                case 5->{
                    System.out.println("==========Update by ID case=================");
                    System.out.print("Enter ID to update : ");
                    int upid = scanner.nextInt();
                    System.out.print("Enter Student Name : ");
                    scanner.nextLine();
                    name = scanner.nextLine();
                    System.out.print("Enter Student Gender : ");
                    gender = scanner.nextLine();
                    System.out.print("Enter Java Score : ");
                    java = scanner.nextFloat();
                    System.out.print("Enter DSA Score : ");
                    dsa = scanner.nextFloat();
                    System.out.print("Enter DC Score : ");
                    dc = scanner.nextFloat();
                    total = java + dsa + dc;
                    avg = total / 3;
                    if(avg>=90){
                        grade = 'A';
                    }else if(avg>=80){
                        grade = 'B';
                    }else if(avg>=70){
                        grade = 'C';
                    }else if(avg>=60){
                        grade = 'D';
                    }else if(avg>=50){
                        grade = 'E';
                    }else
                        grade = 'F';
                    String sql = "INSERT into studentnew(id,name,gender,java,dsa,dc,total,average,grade) VALUES(?,?,?,?,?,?,?,?,?)";
                    try{
                        Connection con = connection();
                        PreparedStatement ps = con.prepareStatement(sql);
                        ps.setString(1,name);
                        ps.setString(2,gender);
                        ps.setFloat(3,java);
                        ps.setFloat(4,dsa);
                        ps.setFloat(5,dc);
                        ps.setFloat(6,total);
                        ps.setFloat(7,avg);
                        ps.setString(8,grade+"");
                        ps.executeUpdate();
                        System.out.println("Successfully updated");
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
                case 6->{
                    System.out.println("=========Delete by ID case=============");
                    id = scanner.nextInt();
                    String sql = "DELETE FROM studentnew WHERE id = ?" +id;
                    try{
                        Connection con = connection();
                        Statement st = con.createStatement();
                        st.execute(sql);
                        System.out.println("Successfully deleted");

                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
                case 7->{
                    System.out.println("=========Sort by ID case ============");
                    String sql = "SELECT * FROM studentnew ORDER BY id DESC";
                    try{
                        Connection con = connection();
                        Statement st = con.createStatement();
                        ResultSet rs = st.executeQuery(sql);
                        while(rs.next()){
                            System.out.println("ID : "+rs.getInt("id"));
                            System.out.println("Name : "+rs.getString("name"));
                            System.out.println("Gender : "+rs.getString("gender"));
                            System.out.println("Java : "+rs.getFloat("java"));
                            System.out.println("DSA : "+rs.getFloat("dsa"));
                            System.out.println("DC : "+rs.getFloat("dc"));
                            System.out.println("Total : "+rs.getFloat("total"));
                            System.out.println("Average : "+rs.getFloat("average"));
                            System.out.println("Grade : "+rs.getString("grade"));
                        }
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }
                case 8->{
                    System.out.println("==========Sort by name A-Z case=============");
                    String sql = "SELECT * FROM studentnew ORDER BY NAME ASC";
                    try{
                        Connection con = connection();
                        Statement st = con.createStatement();
                        ResultSet rs = st.executeQuery(sql);
                        while(rs.next()){
                            System.out.println("ID : "+rs.getInt("id"));
                            System.out.println("Name : "+rs.getString("name"));
                            System.out.println("Gender : "+rs.getString("gender"));
                            System.out.println("Java : "+rs.getFloat("java"));
                            System.out.println("DSA : "+rs.getFloat("dsa"));
                            System.out.println("DC : "+rs.getFloat("dc"));
                            System.out.println("Total : "+rs.getFloat("total"));
                            System.out.println("Average : "+rs.getFloat("average"));
                            System.out.println("Grade : "+rs.getString("grade"));
                        }
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }

            }
        }while(ch !=0);
    }
}
