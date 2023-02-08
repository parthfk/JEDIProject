package com.flipkart.dao;

import com.flipkart.constant.SQLConstants;
import com.flipkart.exception.UserNotFoundException;
import com.flipkart.utils.DbConnection;

import java.sql.*;

public class UserDAOImpl implements UserDAO {
    private Connection conn = null;
    private PreparedStatement stmt = null;

    UserDAOImpl() {
        conn = DbConnection.getConnectionInstance();
    }

    //Singleton pattern
    private static UserDAOImpl dao = null;

    public static UserDAOImpl getInstance() {
        if (dao == null) {
            dao = new UserDAOImpl();
        }
        return dao;
    }

    public boolean verifyCredentials(String userId, String password) throws UserNotFoundException {
        try {
            stmt = conn.prepareStatement(SQLConstants.VERIFY_CREDENTIALS_QUERY);
            stmt.setString(1, userId);
            ResultSet resultSet = stmt.executeQuery();

            if (!resultSet.next())
                throw new UserNotFoundException(userId);
            else if (password.equals(resultSet.getString("password"))) {
                return true;
            } else {
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public String getRole(String userId) {
        try {
            String sql = "select role from user where userId='" + userId + "'";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, userId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getString("role");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean updatePassword(String userId, String newPassword) {
        try {
            stmt = conn.prepareStatement(SQLConstants.UPDATE_PASSWORD_QUERY);
            stmt.setString(1, newPassword);
            stmt.setString(2, userId);
            int row = stmt.executeUpdate();

            if (row == 1)
                return true;
            else
                return false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
