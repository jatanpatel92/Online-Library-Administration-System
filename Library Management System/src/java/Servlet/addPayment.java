/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.Collections;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;



@WebServlet("/addPayment")
public class addPayment extends HttpServlet {
	private static final long serialVersionUID = 1L;
    //private Object stmt;
        public addPayment() {
        super();
        // TODO Auto-generated constructor stub
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        java.sql.Connection con;
        PrintWriter write = response.getWriter();
        response.setContentType("text/html");
        write.println("<html>");
        write.println("<head>");
        write.println("<link rel='stylesheet'	type='text/css' href='style.css'>");
        write.println("<script src='jquery-1.7.1.min.js' type='text/javascript'></script>");
        write.println("<script src='script.js' type='text/javascript'></script>");
        write.println("</head>");
        write.println("<body> <center><h2>Fine Payment</h2>");
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(addPayment.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Library", "root","1234");
            Statement stmt = (Statement) con.createStatement();
            stmt.execute("use Library;");
            //write.println("1");
            
            int cno = Integer.parseInt(request.getParameter("cno"));
            //double amount = Double.parseDouble(request.getParameter("amount"));
            ResultSet rs = stmt.executeQuery("select b.card_no as cno,f.loan_id as loan_id,bo.fname as fname,bo.lname as lname,sum(f.fine_amt) as total_fine,f.paid as paid from (book_loans b join fines f on b.loan_id=f.loan_id) join borrower bo on bo.card_no=b.card_no where bo.card_no="+cno+" and paid=0 group by bo.card_no;");
            
            int loan_id=0;
            double fine=0;
            int paid=0;
            String fname="";
            String lname="";
            int i=0;
            int up = 0;
            while(rs.next())
            {
                loan_id=Integer.parseInt(rs.getString("loan_id"));
                fine=rs.getDouble("total_fine");
                paid=Integer.parseInt(rs.getString("paid"));
                fname = rs.getString("fname");
                lname = rs.getString("lname");
            }
            rs.close();
            write.println("<table><tr>");
                write.println("<th>Card No</th>");
                write.println("<th>Loan ID</th>");
                write.println("<th>Borrower's First Name</th>");
                write.println("<th>Borrower's Last Name</th>");
                write.println("<th>Total Fine Amount</th>");
                write.println("<th>Pay Now</th>");
            write.println("</tr>");
            write.println("<tr>");
                write.println("<td>"+cno+"</td>");
                write.println("<td>"+loan_id+"</td>");
                write.println("<td>"+fname+"</td>");
                write.println("<td>"+lname+"</td>");
                write.println("<td>"+fine+"</td>");
                write.println("<td><form id=\"form2\" method=\"get\" action=\"paynow\"><input type='text' style='visibility:hidden;position:absolute;' name='loanid' id='loanid' value='"+loan_id+"'><input class=\"submit\" class='button' type=\"submit\" value=\"Pay\" id=\"paynow\"></form></td>");
            write.println("</tr>");
            write.println("</center></body>");
            write.println("</html>");
            stmt.close();
            con.close();
            
            
        } catch (SQLException ex) {
            Logger.getLogger(addPayment.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
        }
}