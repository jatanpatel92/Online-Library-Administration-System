package Servlet;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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



@WebServlet("/smallchkin")
public class smallchkin extends HttpServlet {
	private static final long serialVersionUID = 1L;
    //private Object stmt;
        public smallchkin() {
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
        write.println("<body><center> <h2>System Message</h2>");
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(smallchkin.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Library", "root","1234");
            Statement stmt = (Statement) con.createStatement();
            stmt.execute("use Library;");
            String isbn = request.getParameter("bid");
            String brid = request.getParameter("brid");
            String cno = request.getParameter("cno");
            int up = stmt.executeUpdate("update book_loans set date_in=current_date where book_id='"+isbn+"' and card_no='"+cno+"' and branch_id='"+brid+"';");
            if(up==1)
                write.println("Check In Successful!");
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(smallchkin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
        }
}