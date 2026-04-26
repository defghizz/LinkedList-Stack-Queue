import java.util.Stack;
import java.util.Scanner;

/**
 * Part B: Fitur Undo/Redo dalam Editor Teks (Stack)
 */
public class TextEditorStack {
    private String currentText;
    private Stack<String> undoStack;
    private Stack<String> redoStack;

    public TextEditorStack() {
        this.currentText = "";
        this.undoStack = new Stack<>();
        this.redoStack = new Stack<>();
    }

    public void addText(String newText) {
        long startTime = System.nanoTime();

        // Simpan status saat ini ke undo stack sebelum diperbarui
        undoStack.push(currentText);
        // Hapus redo stack karena aksi baru memutus rantai redo
        redoStack.clear();

        // Perbarui teks (penggabungan atau penggantian penuh)
        // Berdasarkan contoh "Selamat" -> "Selamat datang", saya asumsikan penggabungan
        if (currentText.isEmpty()) {
            currentText = newText;
        } else {
            currentText += newText;
        }

        long endTime = System.nanoTime();
        System.out.println("Ditambahkan: \"" + newText + "\" | Durasi: " + (endTime - startTime) + " ns");
    }

    public void undo() {
        if (undoStack.isEmpty()) {
            System.out.println("Tidak ada yang bisa di-undo.");
            return;
        }

        long startTime = System.nanoTime();

        redoStack.push(currentText);
        currentText = undoStack.pop();

        long endTime = System.nanoTime();
        System.out.println("Undo berhasil dilakukan. | Durasi: " + (endTime - startTime) + " ns");
    }

    public void redo() {
        if (redoStack.isEmpty()) {
            System.out.println("Tidak ada yang bisa di-redo.");
            return;
        }

        long startTime = System.nanoTime();

        undoStack.push(currentText);
        currentText = redoStack.pop();

        long endTime = System.nanoTime();
        System.out.println("Redo berhasil dilakukan. | Durasi: " + (endTime - startTime) + " ns");
    }

    public void showText() {
        System.out.println("Teks saat ini: \"" + currentText + "\"");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TextEditorStack editor = new TextEditorStack();
        int pilihan = 0;

        while (pilihan != 5) {
            System.out.println("\n=== Menu Editor Teks (Stack) ===");
            System.out.println("1. Tambah Teks");
            System.out.println("2. Undo");
            System.out.println("3. Redo");
            System.out.println("4. Tampilkan Teks");
            System.out.println("5. Keluar");
            System.out.print("Pilihan Anda: ");

            try {
                pilihan = Integer.parseInt(scanner.nextLine());

                switch (pilihan) {
                    case 1:
                        System.out.print("Masukkan teks baru: ");
                        String input = scanner.nextLine();
                        editor.addText(input);
                        break;
                    case 2:
                        editor.undo();
                        break;
                    case 3:
                        editor.redo();
                        break;
                    case 4:
                        editor.showText();
                        break;
                    case 5:
                        System.out.println("Keluar dari program.");
                        break;
                    default:
                        System.out.println("Pilihan tidak valid.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Masukkan angka yang valid!");
            }
        }
        scanner.close();
    }
}
