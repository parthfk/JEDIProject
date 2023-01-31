package com.flipkart.client;

public class CRSProfessorMenu {
    // 1. select courses to teach
    // 2. view course catalog
    // 3. view enrolled students
    // 4. add grade
    // 5. logout
    private String professorUsername;
    public CRSProfessorMenu(String username) {
        this.professorUsername = username;
        displayMenu();
    }

    public void displayMenu() {
        System.out.println("Welcome to professor portal, " + this.professorUsername);
        System.out.println("Press 1 to select courses to teach");
        System.out.println("Press 2 to view course catalog");
        System.out.println("Press 3 to view enrolled students");
        System.out.println("Press 4 to add grades");
        System.out.println("Press 5 to logout");
    }
}
