import java.io.*;
import java.util.*;
import java.sql.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class DBConnect
{  
    private Connection con;
    private Statement st;
    private ResultSet rs;  
    private String sql1;
    public DBConnect()
    {
      try
      {  
       Class.forName("com.mysql.jdbc.Driver"); 
       con=DriverManager.getConnection("jdbc:mysql://localhost/shahina","root","shahina@123");  
       st=con.createStatement(); 
       //rs=st.executeQuery("select * from student");
      }
      catch(Exception e)
      {
        System.out.println("Error :"+e);
      }
    }
    public void inser()
    {
        try
        { 
        
      System.out.println("Enter rollno");
      Scanner c=new Scanner(System.in);
      int r=c.nextInt();
      System.out.println("Enter name");
      Scanner s=new Scanner(System.in);
      String n=s.nextLine();
      System.out.println("Enter age");
      Scanner q=new Scanner(System.in);
      int a=q.nextInt();
      System.out.println("Enter marks");
      int m=q.nextInt();
         sql1="INSERT INTO student VALUES ("+r+", '"+n+"', "+a+", "+m+")";
      st.executeUpdate(sql1);
      System.out.println("Inserted records into the table...");
        }
        catch(Exception e)
        {
            System.out.println("Error :"+e);
        }
    }
  
    public void getData()
    {
      try
      {
        int d=0;
        String query="select * from student";
        rs=st.executeQuery(query);
        System.out.println("Records of student table");
        while(rs.next())
        {
            d++;
            String rollno=rs.getString("rollno");
            String name=rs.getString("name");
            String age=rs.getString("age");
            String marks=rs.getString("marks");
            System.out.println("Rollno : "+rollno+" Name : "+name+" Age : "+age+" Marks : "+marks);
        }
        if(d==0)
        {
            System.out.println("TABLE IS EMPTY");
        }
      }
      catch(Exception e)
      {
        System.out.println("Error :"+e);
      }
     }
     public void searchData()
     {
        try
        {
            int f=0;
            System.out.println("Enter the rollno to be searched");
            Scanner in=new Scanner(System.in);
            int r=in.nextInt();
            String query="select * from student where rollno="+r;
            rs=st.executeQuery(query);
            while(rs.next())
            {
             f++;
            String rollno=rs.getString("rollno");
            String name=rs.getString("name");
            String age=rs.getString("age");
            String marks=rs.getString("marks");
            System.out.println("Rollno : "+rollno+" Name : "+name+" Age : "+age+" Marks : "+marks);
            }
            if(f==0)
            {
                System.out.println("ROLLNO NOT FOUND IN TABLE STUDENT");
            }
        }
        catch(Exception e)
        {
            System.out.println("Error :"+e);
        }
     
   }
}
class BtnPgm extends JFrame implements ActionListener
{
    private JButton button1,button2,button3;
    public BtnPgm()
    {
        super("Student Record");
        setLayout(new FlowLayout());
        button1=new JButton("Insert");
        button2=new JButton("Search by rollno");
        button3=new JButton("Print all details");
        add(button1);
        add(button2);
        add(button3);
        button1.addActionListener(this);
        button2.addActionListener(this);
        button3.addActionListener(this);
        setSize(300,300);
        setVisible(true);
    }
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource()==button1)
        {
          DBConnect connect=new DBConnect();
          connect.inser();
        }
          if(e.getSource()==button2)
        {
          DBConnect connect=new DBConnect();
          connect.searchData();
        }
        if(e.getSource()==button3)
        {
          DBConnect connect=new DBConnect();
          connect.getData();
        }
       
    }
}


class student1
{ 
    public static void main (String args[])throws IOException
    { 
      BtnPgm b=new BtnPgm();
    }
}  
