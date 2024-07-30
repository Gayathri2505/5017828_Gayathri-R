package library;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        
    	// Sample data to ensure search functionality
        List<Book> books = new ArrayList<>();
        books.add(new Book("1", "Introduction to Algorithms", "aaa"));
        books.add(new Book("2", "Database Design", "bbb"));
        books.add(new Book("3", "Computer Networking", "ccc"));
        books.add(new Book("4", "Introduction to Operating systems", "ddd"));
        books.add(new Book("5", "Design Patterns", "eee"));

        LinearSearch ls = new LinearSearch(books);
        BinarySearch bs = new BinarySearch(books);

        Scanner sc = new Scanner(System.in);
        int ch;
        String title;
        Book res=null;

        do {
            System.out.println("Search Book by Title:\n 1.Linear Search\n 2.Binary Search\n 3.Exit");
            System.out.print("Option: ");
            ch = sc.nextInt();
            sc.nextLine();
            
            switch (ch) {
                case 1:
                	System.out.print("Enter title to search: ");
                    title = sc.nextLine();
                    res = ls.linearSearch(title);
                    display(res);
                    break;

                case 2:
                	System.out.print("Enter title to search: ");
                    title = sc.nextLine();
                    res = bs.binarySearch(title);
                    display(res);
                    break;

                case 3:
                    System.out.println("Exiting!");
                    break;

                default:
                    System.out.println("Invalid option");
                    break;
            }
        } while (ch != 3);

        sc.close();
    }

    private static void display(Book book) {
        if (book != null) {
            System.out.printf("%-10s %-30s %-20s%n", "Book ID", "Title", "Author");
            System.out.println("------------------------------------------------------------");
            System.out.printf("%-10s %-30s %-20s%n", book.getBookId(), book.getTitle(), book.getAuthor());
        } else {
            System.out.println("No book found with the given title.");
        }
    }
}
