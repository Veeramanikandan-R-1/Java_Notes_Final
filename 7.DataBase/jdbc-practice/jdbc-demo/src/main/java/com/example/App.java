package com.example;

public class App {

    public static void main(String[] args) {

        UserDAO userDAO = new UserDAO();

        // CREATE
        userDAO.createUser("Arun", "arun@gmail.com");
        userDAO.createUser("Mani", "mani@gmail.com");

        // READ
        System.out.println("\nUsers:");
        userDAO.getUsers();

        // UPDATE
        userDAO.updateUser(1, "Arun Kumar");

        // READ
        System.out.println("\nAfter Update:");
        userDAO.getUsers();

        // DELETE
        userDAO.deleteUser(2);

        // READ
        System.out.println("\nAfter Delete:");
        userDAO.getUsers();
    }
}