package com.flipkart.dao;

import com.flipkart.bean.User;
import com.flipkart.constant.SQLConstants;
import com.flipkart.exception.PasswordMismatchException;
import com.flipkart.exception.UserNotFoundException;
import com.flipkart.utils.DbConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.flipkart.constant.SQLConstants.SELECT_UNAPPROVED_STUDENTS_QUERY;

public class UserDAOImpl implements UserDAO {
    private Connection conn = null;
    private PreparedStatement stmt = null;
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_CYAN = "\u001B[36m";

    UserDAOImpl() {
        conn = DbConnection.getInstance().getConnection();
    }

    //Singleton pattern
    private static UserDAOImpl dao = null;

    public static UserDAOImpl getInstance() {
        if (dao == null) {
            dao = new UserDAOImpl();
        }
        return dao;
    }

    public boolean verifyCredentials(String userId, String password) throws UserNotFoundException,PasswordMismatchException,SQLException {
        String verifyQuery = "select password from User where email = '" + userId + "'";
            stmt = conn.prepareStatement(verifyQuery);
            ResultSet resultSet = stmt.executeQuery();

            if (!resultSet.next()) {
                throw new UserNotFoundException(userId);
            }
            else if (password.equals(resultSet.getString("password"))) {
                return true;
            }
            throw new PasswordMismatchException(userId);
    }

    public String getRole(String userId) throws  SQLException{
            String sql = "select role from user where userId='" + userId + "'";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, userId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getString("role");
            }
        return null;
    }

    public boolean updatePassword(String userId, String newPassword) throws SQLException{
            stmt = conn.prepareStatement(SQLConstants.UPDATE_PASSWORD_QUERY);
            stmt.setString(1, newPassword);
            stmt.setString(2, userId);
            int row = stmt.executeUpdate();
            System.out.println(row);
            if (row == 1)
                return true;
            else
                return false;
        }

    @Override
    public List<User> getAllUsers() throws SQLException {
        List<User> allUsers = new ArrayList<>();
            PreparedStatement stmt = conn.prepareStatement(SQLConstants.GET_ALL_USERS);
            ResultSet rs = stmt.executeQuery();

            if (!rs.next()) {
                return allUsers;
            }

            do {
                User u = new User();
                String eid = rs.getString("userId");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String password = rs.getString("password");
                int roleId = rs.getInt("roleId");
                u.setUserId(eid);
                u.setName(name);
                u.setEmail(email);
                u.setPassword(password);
                if(roleId==1)u.setUserType("admin");
                else if(roleId ==2)u.setUserType("professor");
                u.setUserType("student");
                allUsers.add(u);

            } while (rs.next());

        return allUsers;
    }
}
