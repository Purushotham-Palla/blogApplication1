package com.blogapp.testing;

import java.util.Scanner;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordHashingExample {

    public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
    	System.out.println("Enter a password");
    	String s1 = sc.next();
    	
    	System.out.println("Enter a password");
    	String s2 = sc.next();
    	
    	
//        String plainPassword = "password123"; // Replace with user input or stored password

        // Generate a salt for hashing
        String salt = BCrypt.gensalt();

        // Hash the password
        String hashedPassword1 = BCrypt.hashpw(s1, salt);
        String hashedPassword2 = BCrypt.hashpw(s2, salt);

        // Print out the hashed password (this is what you store in your database)
        System.out.println("Hashed Password1: " + hashedPassword1);
        System.out.println("Hashed Password2: " + hashedPassword2);
        
        
        if(hashedPassword1.equals(hashedPassword2))
        {
        	System.out.println("Match");
        }
        else {
        	System.out.println("Not match");
        }
        
//        String storedHash = "$2a$10$NrYc3GTVqUfzk0Ll5GhO6OJ5ZS8pF1p9sA.MfQ6x0TlY3JoF79vCS"; // Retrieved from the database
        String userInputPassword = s1; // Password provided by the user during login

        // Check if the provided password matches the stored hash
        
        if (BCrypt.checkpw(s1, hashedPassword1)) {
//            System.out.println("Password match!");
            
            
        } else {
//            System.out.println("Password does not match.");
        }
        
        System.out.println();
    }
}
