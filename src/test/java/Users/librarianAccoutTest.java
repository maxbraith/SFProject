package Users;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import Library.Library;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.opencsv.CSVReaderHeaderAware;
import com.opencsv.exceptions.CsvValidationException;

import Library.Book.Book;
import Library.Users.faculty;
import Library.Users.librarian;
import Library.Users.student;
import Library.Users.parentUser;


public class librarianAccoutTest {
    ArrayList<Book> books;

    @BeforeEach
    void setUp() {
        books = new ArrayList<Book>();
        books.add(new Book("1", "Title 1", "Author 1", "ISBN 1", false, null));
        books.add(new Book("2", "Title 2", "Author 2", "ISBN 2", false, null));
    }

    @Test
    void testBookReturn() {
        Book book = new Book("1", "Title 1", "Author 1", "ISBN 1", false, null);
        librarian.bookReturn(books, book);
        assertFalse(book.getTakenOut());
        assertEquals("", book.getTakeOutBy());
        assertNull(book.getReturnDate());
    }

    @Test
    void testMakeAccount() throws NoSuchAlgorithmException {
        parentUser user = librarian.makeAccount("test@example.com", "password", 10, "student", "Test User","What is your favorite color?","Blue");
        assertEquals("test@example.com", user.getEmail());
        assertEquals(10, user.getGrade());
        assertEquals("student", user.getAccountType());
        assertEquals("Test User", user.getName());
        assertEquals("What is your favorite color?",user.getSecretQuestion());
        assertEquals("Blue",user.getSecretAns());
    }

    @Test
    void testAddBook() {
        librarian.addBook(books, "Title 3", "Author 3", "ISBN 3");
        assertEquals(3, books.size());
        assertEquals("Title 3", books.get(2).getTitle());
        assertEquals("Author 3", books.get(2).getAuthor());
        assertEquals("ISBN 3", books.get(2).getIsbn());
        assertFalse(books.get(2).getTakenOut());
        assertNull(books.get(2).getReturnDate());
    }

    @Test
    void testDeleteBook() {
        Book bookToRemove = books.get(0);
        librarian.deleteBoo(books, bookToRemove);
        assertEquals(1, books.size());
        assertFalse(books.contains(bookToRemove));
    }

    @Test
    void testCheckOutBook() {
        boolean result = librarian.checkOutBook("1", "User 2", books);
        assertTrue(result);
        assertTrue(books.get(0).getTakenOut());
        assertEquals("User 2", books.get(0).getTakeOutBy());
        assertNotNull(books.get(0).getReturnDate());
    }
}
 
