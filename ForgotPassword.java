import java.util.Scanner;

public class ForgotPassword {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Simulasi data pengguna
        String registeredEmail = "user@example.com";
        String storedPassword = "password123";

        System.out.print("Masukkan email Anda: ");
        String email = scanner.nextLine();

        if (email.equals(registeredEmail)) {
            System.out.println("Kami telah mengirimkan email untuk mereset password Anda.");
            // Di dunia nyata, Anda akan mengirimkan email disini.
            // Untuk contoh ini, kita hanya menampilkan link reset.
            System.out.println("Link reset password: http://reset-link.com");
        } else {
            System.out.println("Email tidak ditemukan.");
        }
    }
}
