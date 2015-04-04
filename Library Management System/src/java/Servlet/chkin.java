/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
import java.lang.String;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Servlet implementation class CheckIn
 */
@WebServlet("/chkin")
public class chkin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public chkin() {
        super();
        // TODO Auto-generated constructor stub
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, NullPointerException {
       
        java.sql.Connection con;
        PrintWriter write = response.getWriter();
        response.setContentType("text/html");
        String isbn = request.getParameter("isbn");
                String brid = request.getParameter("brid");
                String cno = request.getParameter("cno");
                String fname1;
                fname1 = request.getParameter("brofname");
                String lname1;
                lname1 = request.getParameter("brolname");
        write.println("<html>");
        write.println("<body><center> <h2>Search Results:</h2></center>");
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(chkin.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Library", "root","1234");
            Statement stmt = (Statement) con.createStatement();
            stmt.execute("use Library;");
            ResultSet rs;
            try{
                rs = stmt.executeQuery("select b.book_id as isbn,b.branch_id as brid,b.card_no as cno,c.fname as fname,c.lname as lname from book_loans b join borrower c on b.card_no=c.card_no where b.date_in is null;");
                
                boolean flag1 = false;
                boolean flag2 = false;
                write.println("<html>");
                write.println("<head>");
                write.println("<link rel='stylesheet'	type='text/css' href='style.css'>");
                write.println("<script src='jquery-1.7.1.min.js' type='text/javascript'></script>");
                write.println("<script src='script.js' type='text/javascript'></script>");
                write.println("</head>");
                write.println("<body><center>");
                if((isbn!=null && !isbn.isEmpty()) || (brid!=null && !brid.isEmpty()) || (cno!=null && !cno.isEmpty()) || ((fname1!=null && !fname1.isEmpty()) || (lname1!=null && !lname1.isEmpty())))
                {
                    write.println("<table><thade><tr>");
                            write.println("<th>ISBN</th>");
                            write.println("<th>Branch ID</th>");
                            write.println("<th>Card No</th>");
                            write.println("<th>Borrower's First Name</th>");
                            write.println("<th>Borrower's Last Name</th>");
                    write.println("</tr>");
                    write.println("</thead>");
                    while(rs.next())
                    {
                        if(fname1!=null && !fname1.isEmpty())
                            flag1 = rs.getString("fname").toLowerCase().equals(fname1.toLowerCase());
                        if(lname1!=null && !lname1.isEmpty())
                            flag2 = rs.getString("lname").toLowerCase().equals(lname1.toLowerCase());
                        if(rs.getString("isbn").equals(isbn) || flag1 || flag2 || rs.getString("cno").equals(cno) || rs.getString("brid").equals(brid))
                            {
                                    write.println("<tr>");
                                            write.println("<td>"+rs.getString("isbn")+"</td>");
                                            write.println("<td>"+rs.getString("brid")+"</td>");
                                            write.println("<td>"+rs.getString("cno")+"</td>");
                                            write.println("<td>"+rs.getString("fname")+"</td>");
                                            write.println("<td>"+rs.getString("lname")+"</td>");
                                            write.println("<td><form id=\"form2\" method=\"get\" action=\"smallchkin\"><input type='text' style='visibility:hidden;position:absolute;' name='bid' id='bid' value='"+rs.getString("isbn")+"'><input type='text' style='visibility:hidden;position:absolute;' name='brid' id='brid' value='"+rs.getString("brid")+"'><input type='text' style='visibility:hidden;position:absolute;' name='cno' id='cno' value='"+rs.getString("cno")+"'><input class=\"submit\" class='button' type=\"submit\" value=\"Check In\" id=\"chkIn\"></form></td>");
                                    write.println("</tr>");
                            }
                    }
                        write.println("</table>");    
                }
                else
                write.println("Please Enter Valid Content!!");
                write.println("</center></body>");
                write.println("</html>"); 
                rs.close();
                con.close();
            }
            catch(NullPointerException nnn)
            {
                Logger.getLogger(chkin.class.getName()).log(Level.SEVERE, null, nnn);
            }           
        }catch (SQLException ex) {
            Logger.getLogger(chkin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
