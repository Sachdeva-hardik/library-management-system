package org.example;
import java.util.*;
import java.util.stream.Collectors;
class Book {
    private String book_name;
    private int book_id;
    private String author;
    private int at_hand_copies;
    private int total_copies;
    private Date issued_date;
    private static final int MAX_LOAN_PERIOD=10;
    private static final int FINE_PER_DAY=3;
    public Book(String book_name, int book_id, String author, int at_hand_copies, int total_copies, Date issued_date) {
        this.book_name = book_name;
        this.book_id = book_id;
        this.author = author;
        this.at_hand_copies = at_hand_copies;
        this.total_copies = total_copies;
        this.issued_date = issued_date;
    }
    public String getBook_name() {
        return book_name;
    }
    public int getBook_id() {
        return book_id;
    }
    public String getAuthor() {
        return author;
    }
    public int getAt_hand_copies() {
        return at_hand_copies;
    }
    public void setAt_hand_copies(int at_hand_copies) {
        this.at_hand_copies = at_hand_copies;
    }
    public int getTotal_copies() {
        return total_copies;
    }
    public Date getIssued_date() {
        return issued_date;
    }
    public void setIssued_date(Date issued_date) {
        this.issued_date = issued_date;
    }
    public static int getMaxLoanPeriod() {
        return MAX_LOAN_PERIOD;
    }
    public static int getFinePerDay() {
        return FINE_PER_DAY;
    }
}
class Student {
    private String name;
    private int age;
    private int rno;
    private String college_email_id;
    private int fine = 0;
    public Student(String name, int age, int rno, String college_email_id, int fine) {
        this.name = name;
        this.age = age;
        this.rno = rno;
        this.college_email_id = college_email_id;
        this.fine = fine;
    }
    public String getName() {
        return name;
    }
    public int getAge() {
        return age;
    }
    public int getRno() {
        return rno;
    }
    public String getCollege_email_id() {
        return college_email_id;
    }
    public void update_fine(int pay) {
        this.fine += pay;
    }
    public int getFine() {
        return fine;
    }
}
class Librarian {
    public ArrayList<Student> members;
    ArrayList<Book> books;
    public Librarian() {
        members = new ArrayList<>();
        books = new ArrayList<>();
    }
    public void addBook(String book_name, int book_id, String author, int at_hand_copies, int total_copies) {
        Date issued_date = new Date();
        Book b1 = new Book(book_name, book_id, author, at_hand_copies, total_copies, issued_date);
        books.add(b1);
    }
    public void removeBook(int book_id) {
        Book book = books.stream()
                .filter(b -> b.getBook_id() == book_id)
                .findFirst()
                .orElse(null);
        if (book != null) {
            if (book.getAt_hand_copies()>1) {
                book.setAt_hand_copies(book.getAt_hand_copies() - 1);
            }
            else {
                books.remove(book);
            }
        }
    }
    public void registerMember(String name, int age, int rno, String college_email_id, int fine) {
        Student member = new Student(name, age, rno, college_email_id, fine);
        members.add(member);
    }
    public void removeMember(String college_email_id) {
        Student member = members.stream()
                .filter(m -> m.getCollege_email_id().equals(college_email_id))
                .findFirst()
                .orElse(null);

        if (member != null) {
            members.remove(member);
        }
    }
    public void listBooks() {
        int unique_BookId = 1;
        for (Book book : books) {
            for (int i = 0; i < book.getAt_hand_copies(); i++) {
                System.out.println("Book ID - " + unique_BookId);
                System.out.println("Name - " + book.getBook_name());
                System.out.println("Author - " + book.getAuthor());
                unique_BookId++;
            }
        }
    }
    public void listMembers() {
        for (Student member : members) {
            System.out.println(member.getName() + " " + member.getAge() + " " + member.getRno() + " " + member.getCollege_email_id() + " " + member.getFine());
        }
    }
}
public class LibraryManagementSystem {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Librarian librarian = new Librarian();
        int choice;
        do {
            System.out.println("Welcome to the Library Management System!");
            System.out.println("1. Add Book");
            System.out.println("2. Remove Book");
            System.out.println("3. Register Member");
            System.out.println("4. Remove Member");
            System.out.println("5. Enter as a member");
            System.out.println("6. Issue Book");
            System.out.println("7. Return Book");
            System.out.println("8. List Books");
            System.out.println("9. List Members");
            System.out.println("10. Calculate Fine");
            System.out.println("11. Pay fine ");
            System.out.println("12. Exit");
            System.out.println("Enter your choice: ");
            choice = input.nextInt();
            switch (choice) {
                case 1 -> {
                    input.nextLine();
                    System.out.print("Enter book name: ");
                    String bookName = input.nextLine();
                    System.out.print("Enter book ID: ");
                    int bookId = input.nextInt();
                    input.nextLine();
                    System.out.print("Enter author: ");
                    String author = input.nextLine();
                    System.out.print("Enter at hand copies: ");
                    int atHandCopies = input.nextInt();
                    System.out.print("Enter total copies: ");
                    int totalCopies = input.nextInt();
                    librarian.addBook(bookName, bookId, author, atHandCopies, totalCopies);
                }
                case 2 -> {
                    System.out.print("Enter book ID to remove: ");
                    int removeBookId = input.nextInt();
                    input.nextLine();
                    librarian.removeBook(removeBookId);
                }
                case 3 -> {
                    input.nextLine();
                    System.out.print("Enter name: ");
                    String memberName = input.nextLine();
                    System.out.print("Enter age: ");
                    int age = input.nextInt();
                    input.nextLine();
                    System.out.println("Enter roll number: ");
                    int roll_no = input.nextInt();
                    input.nextLine();
                    System.out.print("Enter college email ID: ");
                    String collegeEmailId = input.nextLine();
                    System.out.print("Enter fine: ");
                    int fine = input.nextInt();
                    input.nextLine();
                    librarian.registerMember(memberName, age, roll_no, collegeEmailId, fine);
                }
                case 4 -> {
                    input.nextLine();
                    System.out.print("Enter college email ID to remove: ");
                    String removeCollegeEmailId = input.nextLine();
                    librarian.removeMember(removeCollegeEmailId);
                }
                case 5 -> {
                    input.nextLine();
                    System.out.println("Enter your name: ");
                    String name = input.nextLine();
                    System.out.println("Enter your college email ID: ");
                    String collegeEmailId = input.nextLine();
                    Student member = librarian.members.stream()
                            .filter(m -> m.getName().equalsIgnoreCase(name) && m.getCollege_email_id().equalsIgnoreCase(collegeEmailId))
                            .findFirst()
                            .orElse(null);

                    if (member == null) {
                        System.out.println("Invalid name or email ID!");
                        break;
                    }
                    System.out.println("Login successful!");
                }

                case 6 -> {
                    System.out.println("Available books:");
                    List<Book> availableBooks = librarian.books.stream()
                            .filter(b -> b.getAt_hand_copies() > 0)
                            .collect(Collectors.toList());

                    if (availableBooks.isEmpty()) {
                        System.out.println("No books available for issuance.");
                        break;
                    }
                    for (Book book : availableBooks) {
                        System.out.println("Book ID: " + book.getBook_id());
                        System.out.println("Name: " + book.getBook_name());
                        System.out.println("Author: " + book.getAuthor());
                    }
                    System.out.println("Enter the book ID: ");
                    int bookId = input.nextInt();
                    Book book = availableBooks.stream()
                            .filter(b -> b.getBook_id() == bookId)
                            .findFirst()
                            .orElse(null);
                    if (book == null) {
                        System.out.println("Book not found!");
                        break;
                    }
                    Student member = getCurrentMember(librarian.members);
                    if (member.getFine() > 0) {
                        System.out.println("You have pending fines! Please clear them before issuing a book.");
                        break;
                    }
                    book.setAt_hand_copies(book.getAt_hand_copies() - 1);
                    long daysLate = (System.currentTimeMillis() - book.getIssued_date().getTime()) / (24*3600);
                    if (daysLate > Book.getMaxLoanPeriod()) {
                        int fine = (int) (daysLate - Book.getMaxLoanPeriod()) * Book.getFinePerDay();
                        member.update_fine(fine);
                    }
                    System.out.println("Book issued!");
                }

                case 7 -> {
                    Student member = getCurrentMember(librarian.members);
                    if (member == null) {
                        System.out.println("Login as a member first!");
                        break;
                    }
                    System.out.print("Enter the book ID: ");
                    int bookId = input.nextInt();
                    Book book = null;
                    for (Book b : librarian.books) {
                        if (b.getBook_id() == bookId) {
                            book = b;
                            break;
                        }
                    }
                    if (book == null) {
                        System.out.println("Book not found!");
                        break;
                    }
                    if (book.getAt_hand_copies() == book.getTotal_copies()) {
                        System.out.println("Book is not issued!");
                        break;
                    }
                    long daysLate =(System.currentTimeMillis() -book.getIssued_date().getTime())/(24*3600)+10;
                    if (daysLate<=Book.getMaxLoanPeriod()) {
                        System.out.println("Book returned!");
                    }
                    else{
                        int fine=(int)(daysLate-Book.getMaxLoanPeriod())*Book.getFinePerDay();
                        member.update_fine(fine);
                        System.out.println("Book returned. " + fine + "Rupees has been charged for a delay of " + (daysLate-10) + " days.");
                    }
                    book.setAt_hand_copies(book.getAt_hand_copies() + 1);
                }
                case 8 -> librarian.listBooks();
                case 9 -> librarian.listMembers();
                case 10 -> {
                    System.out.println("Enter the book ID: ");
                    int bookId = input.nextInt();
                    Book book = null;
                    for (Book b : librarian.books) {
                        if (b.getBook_id() == bookId) {
                            book = b;
                            break;
                        }
                    }
                    if (book == null) {
                        System.out.println("Book not found!");
                        break;
                    }
                    int fine = 0;
                    long days_late=(System.currentTimeMillis()-book.getIssued_date().getTime())/(3600*24)+10;
                    if (days_late>Book.getMaxLoanPeriod()){
                        fine=(int)days_late*Book.getFinePerDay();
                    }
                    System.out.println("Fine: " + fine);
                }
                case 11 -> {
                    Student member = getCurrentMember(librarian.members);
                    System.out.println("Enter the payment you want to pay:");
                    if (member == null) {
                        System.out.println("Login as a member first!");
                        break;
                    }

                    int currentFine = member.getFine();
                    if (currentFine == 0) {
                        System.out.println("You have no fines to pay.");
                    } else {
                        int payment = Math.min(currentFine, input.nextInt());
                        member.update_fine(-payment);
                        System.out.println("Payment of " + payment + " Rupees has been successfully made.");
                    }
                }
                case 12 -> {
                    System.out.println("Thank you for using the Library Management System!");
                    System.exit(0);
                }
                default -> System.out.println("Invalid choice!");
            }
        } while (choice != 12);
    }
    private static Student getCurrentMember(ArrayList<Student> members) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter your name: ");
        String name = input.nextLine();
        System.out.println("Enter your college email id: ");
        String collegeEmailId = input.nextLine();
        int start = 0;
        int end = members.size() - 1;
        int mid;
        Student currentMember = null;
        while (start <= end) {
            mid = (start + end) / 2;
            Student member = members.get(mid);
            if (member.getName().equals(name) && member.getCollege_email_id().equals(collegeEmailId)) {
                currentMember = member;
                break;
            } else if (member.getName().compareTo(name) < 0) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        if (currentMember == null) {
            System.out.println("Invalid name or email ID! Please try again.");
        }
        return currentMember;
    }
}

