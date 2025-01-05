package com.example;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordHashing {
    public static void main(String[] args) {
        String password = "my_secure_password";

        // Hash password menggunakan bcrypt
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt(12)); // 12 adalah cost factor
        System.out.println("Hashed password: " + hashedPassword);

        // Verifikasi password
        boolean isPasswordCorrect = BCrypt.checkpw(password, hashedPassword);
        System.out.println("Password valid: " + isPasswordCorrect);
    }
}
