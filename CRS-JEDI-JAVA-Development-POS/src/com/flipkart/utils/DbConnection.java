package com.flipkart.utils;
import com.flipkart.constant.DBConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import static com.flipkart.constant.DBConnection.*;

public class DbConnection {
    private static Connection conn = null;
    public static Connection getConnectionInstance() {
        try{
            Class.forName(DBConnection.JDBC_DRIVER);
            if (conn == null) return conn = DriverManager.getConnection(DB_URL,USER,PASS);
            else return conn;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(e);
        }
        return null;
    }
}
