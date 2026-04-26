import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class CustomerServiceQueue {
    // Queue berbasis LinkedList untuk menyimpan antrean pelanggan.
    private static final Queue<Pelanggan> antrean = new LinkedList<>();
    private static int nomorAntreanBerikutnya = 1;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int pilihan;

        // Iterasi menu utama sampai pengguna memilih keluar.
        do {
            tampilkanMenu();
            System.out.print("Pilih menu: ");

            // Validasi agar input menu hanya menerima angka.
            while (!scanner.hasNextInt()) {
                System.out.println("Input harus berupa angka.");
                System.out.print("Pilih menu: ");
                scanner.next();
            }

            pilihan = scanner.nextInt();
            scanner.nextLine();

            switch (pilihan) {
                case 1:
                    System.out.print("Masukkan nama pelanggan: ");
                    String nama = scanner.nextLine();
                    ukurWaktuEksekusi(() -> tambahPelanggan(nama), "Tambah pelanggan");
                    break;
                case 2:
                    ukurWaktuEksekusi(CustomerServiceQueue::layaniPelanggan, "Layani pelanggan");
                    break;
                case 3:
                    ukurWaktuEksekusi(CustomerServiceQueue::tampilkanAntrean, "Tampilkan antrean");
                    break;
                case 4:
                    System.out.println("Program selesai.");
                    break;
                default:
                    System.out.println("Pilihan tidak tersedia.");
                    break;
            }

            System.out.println();
        } while (pilihan != 4);

        scanner.close();
    }

    private static void tampilkanMenu() {
        // Menampilkan pilihan menu.
        System.out.println("=== Sistem Manajemen Antrean Customer Service ===");
        System.out.println("1. Tambah pelanggan baru");
        System.out.println("2. Layani pelanggan");
        System.out.println("3. Tampilkan daftar antrean");
        System.out.println("4. Keluar");
    }

    private static void tambahPelanggan(String nama) {
        // Menolak nama kosong agar data pelanggan tetap valid.
        if (nama.trim().isEmpty()) {
            System.out.println("Nama pelanggan tidak boleh kosong.");
            return;
        }

        // Operasi enqueue: pelanggan baru masuk ke belakang antrean.
        Pelanggan pelanggan = new Pelanggan(nomorAntreanBerikutnya, nama.trim());
        antrean.add(pelanggan);
        nomorAntreanBerikutnya++;

        System.out.println("Pelanggan ditambahkan: " + pelanggan);
    }

    private static void layaniPelanggan() {
        // Mengecek kondisi antrean sebelum melakukan dequeue.
        if (antrean.isEmpty()) {
            System.out.println("Antrean kosong. Tidak ada pelanggan yang dapat dilayani.");
            return;
        }

        // Operasi dequeue: pelanggan paling depan dilayani dan dihapus.
        Pelanggan pelangganDilayani = antrean.poll();
        System.out.println("Pelanggan sedang dilayani: " + pelangganDilayani);
    }

    private static void tampilkanAntrean() {
        // Menampilkan pesan khusus jika tidak ada pelanggan dalam antrean.
        if (antrean.isEmpty()) {
            System.out.println("Antrean kosong.");
            return;
        }

        // Traversal: menelusuri isi antrean dari depan ke belakang.
        System.out.println("Daftar pelanggan dalam antrean:");
        for (Pelanggan pelanggan : antrean) {
            System.out.println(pelanggan);
        }
    }

    private static void ukurWaktuEksekusi(Runnable operasi, String namaOperasi) {
        // Mengukur durasi operasi menggunakan System.nanoTime().
        long waktuMulai = System.nanoTime();
        operasi.run();
        long waktuSelesai = System.nanoTime();

        System.out.println("Waktu eksekusi " + namaOperasi + ": "
                + (waktuSelesai - waktuMulai) + " ns");
    }

    // Menyimpan data pelanggan.
    private static class Pelanggan {
        private final int nomorAntrean;
        private final String nama;

        // Membuat data pelanggan dengan nomor antrean dan nama.
        private Pelanggan(int nomorAntrean, String nama) {
            this.nomorAntrean = nomorAntrean;
            this.nama = nama;
        }

        @Override
        // Tampilan pelanggan saat dicetak ke layar.
        public String toString() {
            return "No. " + nomorAntrean + " - " + nama;
        }
    }
}
