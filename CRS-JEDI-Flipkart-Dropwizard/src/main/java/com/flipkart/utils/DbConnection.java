package com.flipkart.utils;
import com.flipkart.constant.DBConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import static com.flipkart.constant.DBConnection.*;

/**
 * Connection with Database
 */

public class DbConnection {
    private static Connection conn;
    private static DbConnection dbConnection;

    /**
     * Singleton pattern for DbConnection class
     */
    private DbConnection() {
        try{
            Class.forName(DBConnection.JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(e);
        }
    }

    public static DbConnection getInstance() {
        if (dbConnection == null) dbConnection = new DbConnection();
        return dbConnection;
    }
    /**
     *  Creates Singleton instance of connection with database
     * @return Return Connection instance with Database
     */

    public Connection getConnection() {
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
