package library;

import java.util.List;
import java.util.Collections;
import java.util.Comparator;

class LinearSearch 
{
	private List<Book> books;

    public LinearSearch(List<Book> books) {
        this.books = books;
    }

    public Book linearSearch(String title) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                return book;
            }
        }
        return null;
    }
}

class BinarySearch {
    private List<Book> books;

    public BinarySearch(List<Book> books) {
        this.books = books;
        Collections.sort(this.books, Comparator.comparing(Book::getTitle));
    }

    public Book binarySearch(String title) {
        int low = 0;
        int high = books.size() - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            Book midBook = books.get(mid);
            int comparison = midBook.getTitle().compareToIgnoreCase(title);

            if (comparison == 0) 
                return midBook;
            else if (comparison < 0)
                low = mid + 1;
            else 
                high = mid - 1;
        }
        return null;
    }
}