import java.util.Scanner;
import java.util.Stack;

/**
 * Part C: Manajemen Data Mahasiswa dengan Linked List
 */

class StudentNode {
    private String nim;
    private String nama;
    private int nilai;
    private StudentNode next;

    public StudentNode(String nim, String nama, int nilai) {
        this.nim = nim;
        this.nama = nama;
        this.nilai = nilai;
        this.next = null;
    }

    // Getter dan Setter
    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public int getNilai() {
        return nilai;
    }

    public void setNilai(int nilai) {
        this.nilai = nilai;
    }

    public StudentNode getNext() {
        return next;
    }

    public void setNext(StudentNode next) {
        this.next = next;
    }
}

public class StudentManagement {
    private StudentNode head;

    public StudentManagement() {
        this.head = null;
    }

    // Fitur: Menambahkan mahasiswa baru
    public void addStudent(String nim, String nama, int nilai) {
        long startTime = System.nanoTime();
        StudentNode newNode = new StudentNode(nim, nama, nilai);

        if (head == null) {
            head = newNode;
        } else {
            StudentNode temp = head;
            while (temp.getNext() != null) {
                temp = temp.getNext();
            }
            temp.setNext(newNode);
        }

        long endTime = System.nanoTime();
        System.out.println("Mahasiswa ditambahkan: " + nama + " | Durasi: " + (endTime - startTime) + " ns");
    }

    // Fitur: Menghapus mahasiswa dari daftar
    public void deleteStudent(String nim) {
        long startTime = System.nanoTime();

        if (head == null) {
            System.out.println("Daftar kosong.");
            return;
        }

        if (head.getNim().equals(nim)) {
            head = head.getNext();
            long endTime = System.nanoTime();
            System.out.println("Mahasiswa dengan NIM " + nim + " dihapus. | Durasi: " + (endTime - startTime) + " ns");
            return;
        }

        StudentNode current = head;
        while (current.getNext() != null && !current.getNext().getNim().equals(nim)) {
            current = current.getNext();
        }

        if (current.getNext() != null) {
            current.setNext(current.getNext().getNext());
            long endTime = System.nanoTime();
            System.out.println("Mahasiswa dengan NIM " + nim + " dihapus. | Durasi: " + (endTime - startTime) + " ns");
        } else {
            System.out.println("Mahasiswa dengan NIM " + nim + " tidak ditemukan.");
        }
    }

    // Fitur: Mengupdate nilai mahasiswa
    public void updateNilai(String nama, int nilaiBaru) {
        long startTime = System.nanoTime();
        StudentNode temp = head;
        boolean found = false;

        while (temp != null) {
            if (temp.getNama().equalsIgnoreCase(nama)) {
                temp.setNilai(nilaiBaru);
                found = true;
                break;
            }
            temp = temp.getNext();
        }

        long endTime = System.nanoTime();
        if (found) {
            System.out.println("Mengupdate nilai mahasiswa (" + nama + " -> " + nilaiBaru + ") | Durasi: "
                    + (endTime - startTime) + " ns");
        } else {
            System.out.println("Mahasiswa dengan nama " + nama + " tidak ditemukan.");
        }
    }

    // Fitur: Menampilkan daftar mahasiswa
    public void displayAll() {
        System.out.println("\nDaftar Mahasiswa:");
        if (head == null) {
            System.out.println("(Kosong)");
            return;
        }

        StudentNode temp = head;
        int i = 1;
        while (temp != null) {
            System.out.println(
                    i + ". NIM: " + temp.getNim() + ", Nama: " + temp.getNama() + ", Nilai: " + temp.getNilai());
            temp = temp.getNext();
            i++;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudentManagement sm = new StudentManagement();
        int pilihan = 0;

        while (pilihan != 5) {
            System.out.println("\n=== Menu Manajemen Mahasiswa (Linked List) ===");
            System.out.println("1. Tambah Mahasiswa");
            System.out.println("2. Hapus Mahasiswa (berdasarkan NIM)");
            System.out.println("3. Update Nilai");
            System.out.println("4. Tampilkan Semua Mahasiswa");
            System.out.println("5. Keluar");
            System.out.print("Pilihan Anda: ");

            try {
                pilihan = Integer.parseInt(scanner.nextLine());

                switch (pilihan) {
                    case 1:
                        System.out.print("Masukkan NIM: ");
                        String nim = scanner.nextLine();
                        System.out.print("Masukkan Nama: ");
                        String nama = scanner.nextLine();
                        System.out.print("Masukkan Nilai: ");
                        int nilai = Integer.parseInt(scanner.nextLine());
                        sm.addStudent(nim, nama, nilai);
                        break;
                    case 2:
                        System.out.print("Masukkan NIM mahasiswa yang akan dihapus: ");
                        String nimHapus = scanner.nextLine();
                        sm.deleteStudent(nimHapus);
                        break;
                    case 3:
                        System.out.print("Masukkan Nama mahasiswa yang akan diupdate: ");
                        String namaUpdate = scanner.nextLine();
                        System.out.print("Masukkan Nilai baru: ");
                        int nilaiBaru = Integer.parseInt(scanner.nextLine());
                        sm.updateNilai(namaUpdate, nilaiBaru);
                        break;
                    case 4:
                        sm.displayAll();
                        break;
                    case 5:
                        System.out.println("Keluar dari program.");
                        break;
                    default:
                        System.out.println("Pilihan tidak valid.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Masukkan input yang valid!");
            }
        }
        scanner.close();
    }
}
