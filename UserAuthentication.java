import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class UserAuthentication {
    // Simulasi data pengguna yang sudah terdaftar
    private static Map<String, String> users = new HashMap<>();
    private static Map<String, Integer> failedAttempts = new HashMap<>();

    // Maksimal percobaan login yang gagal
    private static final int MAX_ATTEMPTS = 3;

    public static void main(String[] args) {
        // Menambahkan pengguna ke dalam sistem
        users.put("user1", "password123");
        users.put("user2", "password456");

        Scanner scanner = new Scanner(System.in);

        // Memasukkan username dan password
        System.out.print("Username: ");
        String username = scanner.nextLine();

        if (isAccountLocked(username)) {
            System.out.println("Akun terkunci karena terlalu banyak percobaan login yang gagal.");
            return;
        }

        System.out.print("Password: ");
        String password = scanner.nextLine();

        if (login(username, password)) {
            System.out.println("Login berhasil!");
        } else {
            System.out.println("Login gagal. Cek kembali username dan password.");
        }
    }

    // Mengecek apakah akun terkunci
    private static boolean isAccountLocked(String username) {
        return failedAttempts.getOrDefault(username, 0) >= MAX_ATTEMPTS;
    }

    // Fungsi untuk login
    private static boolean login(String username, String password) {
        if (users.containsKey(username) && users.get(username).equals(password)) {
            failedAttempts.put(username, 0);  // Reset gagal login jika berhasil
            return true;
        } else {
            failedAttempts.put(username, failedAttempts.getOrDefault(username, 0) + 1);
            return false;
        }
    }
}
