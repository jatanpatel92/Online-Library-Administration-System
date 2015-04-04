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



@WebServlet("/searchBook")
public class searchBook extends HttpServlet {
	private static final long serialVersionUID = 1L;
    //private Object stmt;
        public searchBook() {
        super();
        // TODO Auto-generated constructor stub
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String book_id;
        String title;
        java.sql.Connection con;
        PrintWriter write = response.getWriter();
        response.setContentType("text/html");
        Boolean flag = true;
        write.println("<html>");
        write.println("<head>");
        write.println("<link rel='stylesheet'	type='text/css' href='style.css'>");
        write.println("<script src='jquery-1.7.1.min.js' type='text/javascript'></script>");
        write.println("<script src='script.js' type='text/javascript'></script>");
        write.println("</head>");
        write.println("<body><center> <h2>Search Results:</h2>");
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(searchBook.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Library", "root","1234");
            Statement stmt = (Statement) con.createStatement();
            stmt.execute("use Library;");
            ResultSet rs1 = stmt.executeQuery("select bc.book_id as bid,bc.branch_id as brid,bc.n as num,bc.n-count(bl.book_id and bl.branch_id) as book_left from book_copies bc, book_loans bl where (bc.book_id=bl.book_id and bc.branch_id=bl.branch_id) and bl.date_in is NULL group by bl.book_id,bl.branch_id;");
            ArrayList <String> book_id1 = new ArrayList<>();
            ArrayList <String> branch_id1 = new ArrayList<>();
            ArrayList <String> book_left1 = new ArrayList<>();
            while(rs1.next())
            {
                book_id1.add(rs1.getString("bid"));
                branch_id1.add(rs1.getString("brid"));
                book_left1.add(rs1.getString("book_left"));
            }
            rs1.close();
            ResultSet rs;
            rs = stmt.executeQuery("SELECT distinct b.book_id as isbn, b.title as ttl, ba.author_name as aname, ba.role as Role, bc.branch_id as BranchID, bc.n as No_Of_Copies from (book b join book_authors ba on (b.book_id=ba.book_id) join book_copies bc on (b.book_id=bc.book_id));");
            String isbn = request.getParameter("isbn");
            String bname = request.getParameter("btitle");
            String aname = request.getParameter("aname");
            boolean flag1 = false;
            boolean flag2 = false;
            String tt="";
            if(isbn!="" || bname!="" || aname!="")
            {
                write.println("<table><thade><tr>");
                    write.println("<th>ISBN</th>");
                    write.println("<th>Title</th>");
                    write.println("<th>Author</th>");
                    write.println("<th>Role</th>");
                    write.println("<th>Branch ID</th>");
                    write.println("<th>No of Copies</th>");
                    write.println("<th>No of Available Copies</th>");
                    write.println("<th>Check Out Option</th>");
                write.println("</tr>");
                write.println("</thead>");
                int k;
                while(rs.next())
                {
                    if(!bname.isEmpty())
                        flag1 = rs.getString("ttl").toLowerCase().contains(bname.toLowerCase());
                    if(!aname.isEmpty())
                        flag2 = rs.getString("aname").toLowerCase().contains(aname.toLowerCase());

                    if(rs.getString("isbn").equals(isbn) || flag1 || flag2)
                    {

                        tt = rs.getString("No_Of_Copies");
                        k = book_id1.indexOf(rs.getString("isbn"));
                        if(book_id1.contains(rs.getString("isbn")) && branch_id1.get(k).equals(rs.getString("BranchID")))
                        tt = book_left1.get(k);
                        write.println("<tr>");
                                write.println("<td>"+rs.getString("isbn")+"</td>");
                                write.println("<td>"+rs.getString("ttl")+"</td>");
                                write.println("<td>"+rs.getString("aname")+"</td>");
                                write.println("<td>"+rs.getString("Role")+"</td>");
                                write.println("<td>"+rs.getString("BranchID")+"</td>");
                                write.println("<td>"+rs.getString("No_Of_Copies")+"</td>");
                                write.println("<td>"+tt+"</td>");
                                write.println("<td><form id=\"form2\" method=\"get\" action=\"chkout\"><input type='text' style='visibility:hidden;position:absolute;' name='bookid' id='bookid' value='"+rs.getString("isbn")+"'><input type='text' style='visibility:hidden;position:absolute;' name='branchid' id='brid' value='"+rs.getString("BranchID")+"'><span>Card No : </span><input type='text' name='cardno' id='cardno'><input class=\"submit\" class='button' type=\"submit\" value=\"Check Out\" id=\"chkOut\"></form></td>");
                        write.println("</tr>");
                    }
                }
                write.println("</table>");
            }
            else
                write.println("<div>Please Enter Appropiate Search Content!</div>");
            write.println("</center></body>");
            write.println("</html>");
            rs.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(searchBook.class.getName()).log(Level.SEVERE, null, ex);
        }
    }/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
        }
}