package com.example.new_coin;

import java.sql.*;
import java.util.ArrayList;

public class SQL {

    // environment variables would be used in a production application
    private static String SQLurl = "jdbc:mysql://localhost:3306/twoupdb?autoReconnect=true&useSSL=false";
    private static String SQLuser = "root";
    private static String SQLpassword = "root";

    public static ArrayList<User> getDatabaseUsers() {
        ArrayList<User> data = new ArrayList<>();

        try (
                Connection myConn = DriverManager.getConnection(SQLurl, SQLuser, SQLpassword);
                Statement myStmt = myConn.createStatement();
                ResultSet rs = myStmt.executeQuery("SELECT * FROM twoupdb.users");
        ) {
            while (rs.next()) {
                String userId = rs.getString("userId");
                String username = rs.getString("username");
                String passwordHash = rs.getString("passwordHash");

                User u = new User(userId, username, passwordHash);
                data.add(u);
                System.out.println(data.toString());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return data;
    }

    public static User getUserByUsername(String authUsername) {
        User user = null;
        String sql = "SELECT * FROM twoupdb.users WHERE username = ?";

        try (
                Connection myConn = DriverManager.getConnection(SQLurl, SQLuser, SQLpassword);
                PreparedStatement pstmt = myConn.prepareStatement(sql);
        ) {
            pstmt.setString(1, authUsername);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    String userId = rs.getString("userId");
                    String username = rs.getString("username");
                    String passwordHash = rs.getString("passwordHash");

                    user = new User(userId, username, passwordHash);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return user;
    }


    public static void addUser(User user) {
        String sql = "INSERT INTO twoupdb.users (username, passwordHash) VALUES (?, ?)";

        try (
                Connection myConn = DriverManager.getConnection(SQLurl, SQLuser, SQLpassword);
                PreparedStatement pstmt = myConn.prepareStatement(sql);
        ) {
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getPasswordHash());

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                System.out.println("User added successfully!");
            } else {
                System.out.println("Failed to add user.");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
