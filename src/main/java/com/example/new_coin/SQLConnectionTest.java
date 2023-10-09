package com.example.new_coin;

import java.sql.*;

public class SQLConnectionTest {

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/twoupdb?autoReconnect=true&useSSL=false";
        String user = "root";
        String password = "root";

        Connection myConn = null;
        Statement myStmt = null;
        ResultSet rs = null;

        try {
            myConn = DriverManager.getConnection(url, user, password);
            myStmt = myConn.createStatement();
            String sql = "SELECT * FROM twoupdb.users";
            rs = myStmt.executeQuery(sql);

            ResultSetMetaData rsmd = rs.getMetaData();
            int numColumns = rsmd.getColumnCount();

            while (rs.next()) {
                for (int i = 1; i <= numColumns; i++) {
                    String columnValue = rs.getString(i);
                    String columnName = rsmd.getColumnName(i);
                    System.out.print(columnName + ": " + columnValue + "\t");
                }
                System.out.println();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            // Close resources
            try {
                if (rs != null) {
                    rs.close();
                }
                if (myStmt != null) {
                    myStmt.close();
                }
                if (myConn != null) {
                    myConn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }
}
