package com.example;

import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

public class BruteForcePrevention {
    private static final int MAX_ATTEMPTS = 5;
    private static final int LOCK_TIME = 300000; // 5 menit
    private static HashMap<String, Integer> loginAttempts = new HashMap<>();
    private static HashMap<String, Long> accountLocks = new HashMap<>();

    public static void main(String[] args) {
        String email = "user@example.com";
        String password = "correct_password";

        // Simulasi percobaan login
        for (int i = 0; i < 7; i++) {
            attemptLogin(email, "wrong_password", password);
        }
    }

    public static void attemptLogin(String email, String enteredPassword, String correctPassword) {
        if (isAccountLocked(email)) {
            System.out.println("Akun terkunci. Coba lagi setelah beberapa menit.");
            return;
        }

        if (enteredPassword.equals(correctPassword)) {
            System.out.println("Login berhasil!");
            resetLoginAttempts(email);
        } else {
            System.out.println("Password salah.");
            incrementLoginAttempts(email);
        }
    }

    public static boolean isAccountLocked(String email) {
        if (!accountLocks.containsKey(email)) {
            return false;
        }

        long lockTime = accountLocks.get(email);
        if (System.currentTimeMillis() - lockTime > LOCK_TIME) {
            // Akun sudah dibuka setelah waktu kunci
            accountLocks.remove(email);
            resetLoginAttempts(email);
            return false;
        }

        return true;
    }

    public static void incrementLoginAttempts(String email) {
        int attempts = loginAttempts.getOrDefault(email, 0) + 1;
        loginAttempts.put(email, attempts);

        if (attempts >= MAX_ATTEMPTS) {
            accountLocks.put(email, System.currentTimeMillis());
            System.out.println("Akun terkunci karena terlalu banyak percobaan login gagal.");
        }
    }

    public static void resetLoginAttempts(String email) {
        loginAttempts.put(email, 0);
    }
}
