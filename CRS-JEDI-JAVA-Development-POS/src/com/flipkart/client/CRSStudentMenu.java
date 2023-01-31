package com.flipkart.client;

public class CRSStudentMenu {
    // 1. sem reg
    // 2. request grade card
    // 3. logout
    private String studentUsername;
    public CRSStudentMenu(String username) {
        this.studentUsername = username;
        displayMenu();
    }

    public void displayMenu() {
        System.out.println("Welcome to student portal, " + this.studentUsername);
        System.out.println("Press 1 to proceed with Sem Registration, view catalog, and pay fees");
        System.out.println("Press 2 to request grade card");
        System.out.println("Press 3 to logout");
    }
}
