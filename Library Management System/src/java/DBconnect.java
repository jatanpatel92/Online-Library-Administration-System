/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Jatan
 */
import java.sql.*;

public class DBconnect {
    static Connection conn = null;
        /**
         * @param args
         */
        public static void main(String[] args) {

            // Initialize variables for fields by data type
            String book_id;
            String title;

            int linect = 0;

        try {
            // Create a connection to the local MySQL server, with the "company" database selected.
            //        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/company", "root", "mypassword");
            // Create a connection to the local MySQL server, with the NO database selected.
            //class.forName("com.mysql.jdbc.Driver");     
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", "1234");
    
            // Create a SQL statement object and execute the query.
            Statement stmt = conn.createStatement();
        
            // Set the current database, if not already set in the getConnection
            // Execute a SQL statement
            stmt.execute("use Library;");

            // Execute a SQL query using SQL as a String object
            ResultSet rs = stmt.executeQuery("SELECT book_id,title FROM book;");

            // Iterate through the result set using ResultSet class's next() method
            while (rs.next()) {
                // Keep track of the line/tuple count
                linect++;
                // Populate field variables
                
                book_id = rs.getString("book_id");
                title = rs.getString("title");
            
                // Do something with the data
                System.out.print(linect + ".\t");
                System.out.print(book_id + "\t");
                System.out.print(title + "\t");
                System.out.println();
            } // End while(rs.next())

            // Always close the recordset and connection.
            rs.close();
            conn.close();
            System.out.println("Success!!");
        } 
        catch(SQLException ex) {
            System.out.println("Error in connection: " + ex.getMessage());
        }
    }

    /*
     *
     */
    static void newln() {
        System.out.println();
    }
}