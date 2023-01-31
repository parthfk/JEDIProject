package com.flipkart.client;

public class CRSAdminMenu {
    // 1. approve student
    // 2. add professor
    // 3. generate grade card
    // 4. add admin
    // 5. add course
    // 6. delete course
    // 7. logout
    private String adminUsername;
    public CRSAdminMenu(String username) {
        this.adminUsername = username;
        displayMenu();
    }

    public void displayMenu() {
        System.out.println("Welcome to admin portal, " + this.adminUsername);
        System.out.println("Press 1 to approve student ");
        System.out.println("Press 2 to add professor");
        System.out.println("Press 3 to generate grade card");
        System.out.println("Press 4 to add admin");
        System.out.println("Press 5 to add course");
        System.out.println("Press 6 to delete course");
        System.out.println("Press 7 to logout");
    }
}
