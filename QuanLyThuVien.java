import java.util.ArrayList;
import java.util.Scanner;

// Lớp Sách
class Book {
    private String id;
    private String title;
    private String author;
    private boolean isBorrowed;

    public Book(String id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isBorrowed = false;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isBorrowed() {
        return isBorrowed;
    }

    public void borrowBook() {
        isBorrowed = true;
    }

    public void returnBook() {
        isBorrowed = false;
    }

    @Override
    public String toString() {
        return "Mã sách: " + id + ", Tên sách: " + title + ", Tác giả: " + author + ", Trạng thái: " + (isBorrowed ? "Đã mượn" : "Còn");
    }
}

// Lớp Bạn đọc
class Reader {
    private String readerId;
    private String name;

    public Reader(String readerId, String name) {
        this.readerId = readerId;
        this.name = name;
    }

    public String getReaderId() {
        return readerId;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Mã bạn đọc: " + readerId + ", Họ tên: " + name;
    }
}

// Lớp Quản lý mượn trả
class Borrow {
    private Reader reader;
    private Book book;

    public Borrow(Reader reader, Book book) {
        this.reader = reader;
        this.book = book;
    }

    @Override
    public String toString() {
        return reader.getName() + " đã mượn sách \"" + book.getTitle() + "\"";
    }
}

// Lớp chính
public class QuanLyThuVien {
    private static ArrayList<Book> books = new ArrayList<>();
    private static ArrayList<Reader> readers = new ArrayList<>();
    private static ArrayList<Borrow> borrows = new ArrayList<>();
    private static Scanner sc = new Scanner(System.in);

    // Thêm sách
    private static void addBook() {
        System.out.print("Nhập mã sách: ");
        String id = sc.nextLine();
        System.out.print("Nhập tên sách: ");
        String title = sc.nextLine();
        System.out.print("Nhập tác giả: ");
        String author = sc.nextLine();
        books.add(new Book(id, title, author));
        System.out.println(">> Thêm sách thành công!");
    }

    // Thêm bạn đọc
    private static void addReader() {
        System.out.print("Nhập mã bạn đọc: ");
        String readerId = sc.nextLine();
        System.out.print("Nhập họ tên: ");
        String name = sc.nextLine();
        readers.add(new Reader(readerId, name));
        System.out.println(">> Thêm bạn đọc thành công!");
    }

    // Mượn sách
    private static void borrowBook() {
        System.out.print("Nhập mã bạn đọc: ");
        String readerId = sc.nextLine();
        Reader reader = null;
        for (Reader r : readers) {
            if (r.getReaderId().equals(readerId)) {
                reader = r;
                break;
            }
        }
        if (reader == null) {
            System.out.println(">> Không tìm thấy bạn đọc!");
            return;
        }

        System.out.print("Nhập mã sách: ");
        String bookId = sc.nextLine();
        Book book = null;
        for (Book b : books) {
            if (b.getId().equals(bookId)) {
                book = b;
                break;
            }
        }
        if (book == null) {
            System.out.println(">> Không tìm thấy sách!");
            return;
        }

        if (book.isBorrowed()) {
            System.out.println(">> Sách đã được mượn!");
        } else {
            book.borrowBook();
            borrows.add(new Borrow(reader, book));
            System.out.println(">> Mượn sách thành công!");
        }
    }

    // Trả sách
    private static void returnBook() {
        System.out.print("Nhập mã sách cần trả: ");
        String bookId = sc.nextLine();
        for (Book b : books) {
            if (b.getId().equals(bookId)) {
                if (b.isBorrowed()) {
                    b.returnBook();
                    System.out.println(">> Trả sách thành công!");
                } else {
                    System.out.println(">> Sách chưa được mượn!");
                }
                return;
            }
        }
        System.out.println(">> Không tìm thấy sách!");
    }

    // Hiển thị danh sách
    private static void showBooks() {
        System.out.println("=== DANH SÁCH SÁCH ===");
        for (Book b : books) {
            System.out.println(b);
        }
    }
    // Danh sách bạn đọc
    private static void showReaders() {
        System.out.println("=== DANH SÁCH BẠN ĐỌC ===");
        for (Reader r : readers) {
            System.out.println(r);
        }
    }
    // Dach sách mượn sách
    private static void showBorrows() {
        System.out.println("=== DANH SÁCH MƯỢN SÁCH ===");
        for (Borrow br : borrows) {
            System.out.println(br);
        }
    }

    // Menu
    public static void main(String[] args) {
        while (true) {
            System.out.println("\n====== QUẢN LÝ THƯ VIỆN ======");
            System.out.println("1. Thêm sách");
            System.out.println("2. Thêm bạn đọc ");
            System.out.println("3. Mượn sách");
            System.out.println("4. Trả sách");
            System.out.println("5. Hiển thị sách");
            System.out.println("6. Hiển thị bạn đọc");
            System.out.println("7. Hiển thị danh sách mượn");
            System.out.println("0. Thoát");
            System.out.print("Chọn chức năng: ");
            String choice = sc.nextLine();

            switch (choice) {
                case "1": addBook(); break;
                case "2": addReader(); break;
                case "3": borrowBook(); break;
                case "4": returnBook(); break;
                case "5": showBooks(); break;
                case "6": showReaders(); break;
                case "7": showBorrows(); break;
                case "0": 
                    System.out.println(">> Thoát chương trình!"); 
                    return;
                default: 
                    System.out.println(">> Lựa chọn không hợp lệ!");
            }
        }
    }
}
