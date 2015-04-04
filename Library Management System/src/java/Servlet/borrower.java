/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Jatan
 */
package Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/borrower")
public class borrower extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public borrower() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
            java.sql.Connection con;
            PrintWriter write = response.getWriter();
            response.setContentType("text/html");
            write.println("<html>");
            write.println("<head>");
            write.println("<link rel='stylesheet'	type='text/css' href='style.css'>");
            write.println("<script src='jquery-1.7.1.min.js' type='text/javascript'></script>");
            write.println("<script src='script.js' type='text/javascript'></script>");
            write.println("</head>");
            write.println("<body> <center><h2>System Message</h2>");
            try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(searchBook.class.getName()).log(Level.SEVERE, null, ex);
        }
        try{
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Library", "root","1234");
            Statement stmt = (Statement) con.createStatement();
            stmt.execute("use Library;");
            ResultSet rs = stmt.executeQuery("select card_no,fname,lname,address,phone from borrower;");
            //ArrayList <Integer> bcno = new ArrayList<>();
            ArrayList <String> bfname = new ArrayList<>();
            ArrayList <String> blname = new ArrayList<>();
            ArrayList <String> baddress = new ArrayList<>();
            
            while(rs.next())
            {
                bfname.add(rs.getString("fname"));
                blname.add(rs.getString("lname"));
                baddress.add(rs.getString("address"));
            }
            
            String new_fname = request.getParameter("brofname");
            String new_lname = request.getParameter("brolname");
            String new_address = request.getParameter("broaddress");
            String new_phone = request.getParameter("brono");
            
            rs.close();
            if(!new_fname.isEmpty() && !new_lname.isEmpty() && !new_address.isEmpty())
            {
                if(bfname.contains(new_fname) && blname.contains(new_lname) && baddress.contains(new_address))
                {
                    write.println("Use already exists!!");
                }
                else
                {
                    int up = stmt.executeUpdate("insert into borrower(fname,lname,address,phone) values (\'"+new_fname+"\',\'"+new_lname+"\',\'"+new_address+"\',\'"+new_phone+"\');");
                    ResultSet rs1 = stmt.executeQuery("select card_no from borrower where fname=\'"+new_fname+"\' and lname=\'"+new_lname+"\' and address=\'"+new_address+"\';");
                    if(up==1)
                        if(rs1.next())
                        write.println("Borrower Successfully added into System with Card Number : "+rs1.getString("card_no")+"!!");
                    rs1.close();
                }
            }
            else
            {
                write.println("Please Enter Appropiate Content!!");
            }
            write.println("</center></body>");
            write.println("</html>");
            
            con.close();
	}catch (SQLException ex) {
            Logger.getLogger(searchBook.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}

