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



@WebServlet("/fines")
public class fines extends HttpServlet {
	private static final long serialVersionUID = 1L;
    //private Object stmt;
        public fines() {
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
        write.println("<body><center> <h2>Updated Fines</h2>");
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(fines.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Library", "root","1234");
            Statement stmt = (Statement) con.createStatement();
            stmt.execute("use Library;");
            //write.println("1");
            ResultSet rs = stmt.executeQuery("select loan_id,paid from fines;");
            
            ArrayList <Integer> loan_id = new ArrayList<>();
            ArrayList <Integer> loan_id1 = new ArrayList<>();
            ArrayList <Integer> loan_id2 = new ArrayList<>();
            ArrayList <Double> fine1 = new ArrayList<>();
            ArrayList <Double> fine2 = new ArrayList<>();
            ArrayList <Integer> pay = new ArrayList<>();
            //write.println("2");
            while(rs.next())
            {
                loan_id.add(Integer.parseInt(rs.getString("loan_id")));
                pay.add(Integer.parseInt(rs.getString("paid")));
                //write.println(rs.getString("loan_id"));
            }
            rs.close();
            int i = 0;
            int up;
            //boolean flag = false;
            Statement stmt1 = (Statement) con.createStatement();
            ResultSet rs1 = stmt1.executeQuery("select loan_id, TIMESTAMPDIFF(Day, due_date,current_date)*0.25 as fine from book_loans where date_in is null and current_date>due_date;");
            Statement stmt11 = (Statement) con.createStatement();
            Statement stmt12 = (Statement) con.createStatement();
            //write.println(rs1);
            while(rs1.next())
            {
                //write.println(rs1.next());
                loan_id1.add(Integer.parseInt(rs1.getString("loan_id")));
                fine1.add(Double.parseDouble(rs1.getString("fine")));
                //write.println(loan_id.contains(loan_id1.get(i)));
                if(!loan_id.contains(loan_id1.get(i)))
                {
                    up = stmt11.executeUpdate("insert into fines(loan_id,fine_amt,paid) values ('"+Integer.toString(loan_id1.get(i))+"','"+fine1.get(i)+"',0);");
                }
                else
                {
                    up = stmt12.executeUpdate("update fines set fine_amt='"+fine1.get(i)+"' where loan_id='"+Integer.toString(loan_id1.get(i))+"';");
                }
                
                ++i;  
            }
           rs1.close();
            Statement stmt2 = (Statement) con.createStatement();
            Statement stmt21 = (Statement) con.createStatement();
            ResultSet rs2 = stmt2.executeQuery("select loan_id, TIMESTAMPDIFF(Day, due_date,date_in)*0.25 as fine from book_loans where date_in is not null and date_in>due_date;");
            //write.println(rs2);
            i = 0;
            int j=0;
            while(rs2.next())
            {
                loan_id2.add(Integer.parseInt(rs2.getString("loan_id")));
                fine2.add(Double.parseDouble(rs2.getString("fine")));
                //write.println(loan_id2.get(i));
                //write.println(!loan_id.contains(loan_id2.get(i)));
                if(!loan_id.contains(loan_id2.get(i)))
                {
                    up = stmt21.executeUpdate("insert into fines(loan_id,fine_amt,paid) values (\'"+loan_id2.get(i)+"\',\'"+fine2.get(i)+"\',0);");
                }
                ++i;  
            }
            rs2.close();
            Statement stmt3 = (Statement) con.createStatement();
            ResultSet rs3 = stmt3.executeQuery("select b.card_no as cno,f.loan_id as loan_id,bo.fname as fname,bo.lname as lname,sum(f.fine_amt) as total_fine,f.paid as paid from (book_loans b join fines f on b.loan_id=f.loan_id) join borrower bo on bo.card_no=b.card_no group by bo.card_no;");
            write.println("<table><tr>");
                write.println("<th>Card No</th>");
                write.println("<th>Loan ID</th>");
                write.println("<th>Borrower's First Name</th>");
                write.println("<th>Borrower's Last Name</th>");
                write.println("<th>Total Fine Amount</th>");
                write.println("<th>Paid</th>");
            write.println("</tr>");
            while(rs3.next())
            {
                write.println("<tr>");
                    write.println("<td>"+rs3.getString("cno")+"</td>");
                    write.println("<td>"+rs3.getString("loan_id")+"</td>");
                    write.println("<td>"+rs3.getString("fname")+"</td>");
                    write.println("<td>"+rs3.getString("lname")+"</td>");
                    write.println("<td>"+rs3.getString("total_fine")+"</td>");
                    write.println("<td>"+rs3.getString("paid")+"</td>");
                write.println("</tr>");
            }
            write.println("</table>");
            write.println("</center></body>");
            write.println("</html>");
            rs3.close();
            stmt.close();
            stmt1.close();
            stmt11.close();
            stmt12.close();
            stmt2.close();
            stmt21.close();
            stmt3.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(fines.class.getName()).log(Level.SEVERE, null, ex);
        }
    }/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
        }
}