package com.flipkart.data;

import com.flipkart.bean.User;

import java.util.ArrayList;
import java.util.List;

public class UserData {
    List<User> userList = new ArrayList<User>();
    UserData(){
        for(int i=0;i<10;i++){
            User u = new User();
            u.setUserId(String.valueOf(i));
            u.setEmail(i + "@gmail.com");
            u.setName(i+  "jagaa");
            u.setPassword(i + "ysfysiuf");
            if(i%3 == 0) {
                u.setUserType("Admin");
            }else if(i%3 == 1){
                u.setUserType("Professor");
            }else{
                u.setUserType("Student");
            }
        }
    }
    public List<User> getUserList(){
        return  userList;
    }
}
