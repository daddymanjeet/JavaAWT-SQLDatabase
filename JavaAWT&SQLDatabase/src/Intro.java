import java.awt.event.*;
import java.awt.*;
import java.sql.*;

import javax.swing.JFrame;

public class Intro implements ActionListener
{
	TextField tf1,tf2;
	Label l1,l2,l3,l4,l5;
	Checkbox cb1,cb2,cb3;
	Choice c;
	Button b;
	JFrame f;
	TextArea a,a2;
	Connection con;
	PreparedStatement ps;
	Intro()
	{
		f= new JFrame("INTRO");
		l1=new Label("Enter your first Name: ");
		l1.setBounds(50,50,150,20);
		tf1=new TextField();
		tf1.setBounds(50,75,200,20);
		f.add(l1);
		f.add(tf1);
		l2=new Label("Enter your Last Name: ");
		l2.setBounds(50,100,250,20);
		tf2=new TextField();
		tf2.setBounds(50,125,200,20);
		f.add(l2);
		f.add(tf2);
		l3=new Label("Mark Your Interest:");
		l3.setBounds(50,150,150,20);
		f.add(l3);
		cb1=new Checkbox("C++");
		cb1.setBounds(50, 175, 175, 20);
		f.add(cb1);
		cb2=new Checkbox("Java");
		cb2.setBounds(50, 195, 200, 20);
		f.add(cb2);
		cb3=new Checkbox("Python");
		cb3.setBounds(50, 205, 225,50);
		f.add(cb3);
		l4=new Label("Choose your Location:");
		l4.setBounds(50,235,275,50);
		f.add(l4);
		c=new Choice();
		c.setBounds(50, 290, 300, 20);
		c.add("Hisar");
		c.add("Chandigarh");
		c.add("Ambala");
		f.add(c);
		b=new Button("Submit");
		b.setBounds(100, 340, 50, 20);
		f.add(b);
		b.addActionListener(this);
		l5= new Label("Report:");
		l5.setBounds(50, 370, 325, 20);
		f.add(l5);
		a=new TextArea();
		a.setBounds(50, 400, 330, 20);
		a.setEditable(false);
		f.add(a);
		f.setSize(400,500);
		f.setLayout(null);
		f.setVisible(true);
	}
	public void actionPerformed(ActionEvent e)
	{
		String s1=tf1.getText();
		String s2=tf2.getText();
		String s3=c.getItem(c.getSelectedIndex());
		String s4="";
		if(cb1.getState()==true && cb2.getState()==false && cb3.getState()==false)
		{
			s4=cb1.getLabel();
		}
		if(cb2.getState()==true && cb1.getState()==false && cb3.getState()==false)
		{
			s4=cb2.getLabel();
		}
		if(cb3.getState()==true && cb1.getState()==false && cb2.getState()==false)
		{
			s4=cb3.getLabel();
		}
		if(cb1.getState()==true && cb2.getState()==true && cb3.getState()==false)
		{
			s4=cb1.getLabel()+" and "+cb2.getLabel();
		}
		if(cb1.getState()==true && cb2.getState()==false && cb3.getState()==true)
		{
			s4=cb1.getLabel()+" and "+cb3.getLabel();
		}
		if(cb1.getState()==false && cb2.getState()==true && cb3.getState()==true)
		{
			s4=cb2.getLabel()+" and "+cb3.getLabel();
		}
		if(cb1.getState()==true && cb2.getState()==true && cb3.getState()==true)
		{
			s4=cb1.getLabel()+", "+cb2.getLabel()+" and "+cb3.getLabel();
		}
		if (cb1.getState()==false && cb2.getState()==false && cb3.getState()==false)
		{
			s4="Nothing";
		}
		String ss=s1+" "+s2+" "+" from "+s3+" chooses "+s4;
		a.setText(ss);
		int t=0;
		 try
		  {
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","");
		   ps=con.prepareStatement("insert into info values(?,?,?,?)");
		   ps.setString(1,s1);
		   ps.setString(2,s2);
		   ps.setString(3,s4);
		   ps.setString(4, s3);
		   t=ps.executeUpdate();
//		   System.out.println("t is: "+t);
//		   System.out.println(sqlDate);
		   
		  }
		  catch(SQLException e1)
		  {
		   e1.printStackTrace();
		   //return "FAIL";
		  } 
		 catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		new Intro();

	}

}

