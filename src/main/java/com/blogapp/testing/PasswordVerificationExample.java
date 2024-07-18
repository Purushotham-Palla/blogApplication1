package com.blogapp.testing;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordVerificationExample {

    public static void main(String[] args) {
        String storedHash = "$2a$10$NrYc3GTVqUfzk0Ll5GhO6OJ5ZS8pF1p9sA.MfQ6x0TlY3JoF79vCS"; // Retrieved from the database
        String userInputPassword = "password123"; // Password provided by the user during login

        // Check if the provided password matches the stored hash
        if (BCrypt.checkpw(userInputPassword, storedHash)) {
            System.out.println("Password match!");
        } else {
            System.out.println("Password does not match.");
        }
    }
}
