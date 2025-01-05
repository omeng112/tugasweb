package com.example;

import java.util.UUID;
import java.util.HashMap;

public class PasswordReset {
    // Simulasi penyimpanan token dan user
    private static HashMap<String, String> userTokens = new HashMap<>();
    private static HashMap<String, String> users = new HashMap<>(); // Email -> Password

    public static void main(String[] args) {
        // Simulasi email pengguna dan password
        String email = "user@example.com";
        String oldPassword = "old_password";

        // Simulasi reset password
        requestPasswordReset(email);
        resetPassword(email, "new_secure_password");
    }

    public static void requestPasswordReset(String email) {
        // Membuat token reset password unik
        String token = UUID.randomUUID().toString();
        userTokens.put(email, token);

        // Kirim email dengan link reset (simulasi)
        System.out.println("Link reset password dikirim ke " + email + ":");
        System.out.println("http://example.com/reset?token=" + token);
    }

    public static void resetPassword(String email, String newPassword) {
        // Verifikasi token yang diterima
        String token = userTokens.get(email);
        if (token == null) {
            System.out.println("Token tidak valid atau sudah kedaluwarsa.");
            return;
        }

        // Ganti password dengan yang baru
        users.put(email, newPassword);
        System.out.println("Password berhasil diubah untuk " + email);
    }
}
