import com.mysql.cj.protocol.Resultset;

import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.util.Scanner;

public class Lecturer {
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
        Lecturer.connection();
        Scanner scanner = new Scanner(System.in);
        String name;
        String gender;
        int phone;
        int choose;
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
            choose = scanner.nextInt();
        switch(choose){
            case 1->{
                System.out.println("=========Insert Case==========");
                System.out.print("Enter Number of Lecturer : ");
                int n = scanner.nextInt();
                for(int i =0; i<n; i++){
                    System.out.println("Lecturer #"+(i+1)+" ");
                    System.out.print("Enter Name : ");
                    scanner.nextLine();
                    name = scanner.nextLine();
                    System.out.print("Enter Gender : ");
                    gender = scanner.nextLine();
                    System.out.print("Enter Phone Number : ");
                    phone = scanner.nextInt();
                    String sql = "INSERT INTO tbllecturer(name,gender,phone) VALUES(?,?,?)";
                    try{
                        Connection con = connection();
                        PreparedStatement ps = con.prepareStatement(sql);
                        ps.setString(1,name);
                        ps.setString(2,gender);
                        ps.setInt(3,phone);
                        ps.executeUpdate();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("Successfull...");
            }
            case 2->{
                System.out.println("=========Select Case==========");
                try{
                    Connection con = connection();
                    String sql = "SELECT * FROM tbllecturer";
                    Statement st = con.createStatement();
                    ResultSet rs = st.executeQuery(sql);
                    while(rs.next()){
                        System.out.println("ID : "+rs.getInt("id"));
                        System.out.println("Name : "+rs.getString("name"));
                        System.out.println("Gender : "+rs.getString("gender"));
                        System.out.println("Phone Number : "+rs.getInt("phone"));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            case 3->{
                System.out.println("========Search by ID=========");
                System.out.println("Enter ID to search : ");
                int ID = scanner.nextInt();
                String sql = "SELECT * FROM tbllecturer WHERE id = ?";
                try{
                    Connection con = connection();
                    Statement st = con.createStatement();
                    ResultSet rs = st.executeQuery(sql);
                    while(rs.next()){
                        System.out.println("ID : "+rs.getInt("id"));
                        System.out.println("Name : "+rs.getString("name"));
                        System.out.println("Gender : "+rs.getString("gender"));
                        System.out.println("Phone Number : "+rs.getInt("phone"));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            case 4->{
                System.out.println("=======Search by Name=========");
                System.out.println("Enter name to search : ");
                scanner.nextLine();
                String Name = scanner.nextLine();
                String sql = "SELECT * FROM tbllecturer WHERE NAME LIKE ?";
                try{
                    Connection con = connection();
                    PreparedStatement ps = con.prepareStatement(sql);
                    ps.setString(1,"%"+Name+"%");
                    ResultSet rs = ps.executeQuery();
                    while(rs.next()){
                        System.out.println("ID : "+rs.getInt("id"));
                        System.out.println("Name : "+rs.getString("name"));
                        System.out.println("Genddr : "+rs.getString("gender"));
                        System.out.println("Phone Number : "+rs.getInt(phone));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            case 5->{
                System.out.println("=========Update by ID======");
                System.out.println("Enter ID to update : ");
                int upid = scanner.nextInt();
                System.out.println("Enter Name : ");
                scanner.nextLine();
                name = scanner.nextLine();
                System.out.println("Enter Gender : ");
                gender = scanner.nextLine();
                System.out.println("Enter Phone Number : ");
                phone = scanner.nextInt();
                String sql = "INSERT INTO tbllecturer(name,gender,phone) VALUES(?,?,?)";
                try{
                    Connection con = connection();
                    PreparedStatement ps = con.prepareStatement(sql);
                    ps.setString(1,name);
                    ps.setString(2,gender);
                    ps.setInt(3,phone);
                    ps.executeUpdate();
                    System.out.println("Successfull....");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            case 6->{
                System.out.println("=========Delete by ID===========");
                int id = scanner.nextInt();
                String sql = "DELETE FROM tbllecturer WHERE id = ?" +id;
                try{
                    Connection con = connection();
                    Statement st = con.createStatement();
                    st.execute(sql);
                    System.out.println("Successfull....");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            case 7->{
                System.out.println("===========Sort by ID case============");
                String sql = "SELECT * FROM tbllecturer ORDER BY  id DESC";
                try{
                    Connection con = connection();
                    Statement st = con.createStatement();
                    ResultSet rs = st.executeQuery(sql);
                    while(rs.next()){
                        System.out.println("ID : "+rs.getInt("id"));
                        System.out.println("Name : "+rs.getString("name"));
                        System.out.println("Gender : "+rs.getString("gender"));
                        System.out.println("Phone Number : "+rs.getInt("phone"));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            case 8->{
                System.out.println("========Sort By Nama=========");
                String sql = "SELECT * FROM tbllecturer ORDER BY name ASC";
                try{
                    Connection con = connection();
                    Statement st = con.createStatement();
                    ResultSet rs = st.executeQuery(sql);
                    while(rs.next()){
                        System.out.println("ID : "+rs.getInt("id"));
                        System.out.println("Name : "+rs.getString("name"));
                        System.out.println("Gender : "+rs.getString("gender"));
                        System.out.println("Phone Number : "+rs.getInt("phone"));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        }while(choose <9);
    }
}
