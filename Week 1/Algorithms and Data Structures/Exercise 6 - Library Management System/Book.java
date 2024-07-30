package library;

public class Book 
{
	private String bookId,title,author;
	public Book(String bookId, String title, String author) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
    }

    public String getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    @Override
    public String toString() {
        return String.format("Book ID: %s, Title: %s, Author: %s", bookId, title, author);
    }
}
