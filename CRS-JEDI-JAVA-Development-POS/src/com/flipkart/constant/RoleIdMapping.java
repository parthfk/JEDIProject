package com.flipkart.constant;

public class RoleIdMapping {
    public static String fromId(int id) {
        if (id == 1) return "admin";
        else if (id == 2) return "professor";
        else if (id == 3) return "student";
        return "";
    }
}
