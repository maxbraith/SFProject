package Users;

import static org.junit.jupiter.api.Assertions.assertEquals;

import Library.Library;

import org.junit.jupiter.api.Test;

import Library.Book.Book;
import Library.Users.student;


public class studentAccoutTest {
    @Test
    void constructorTest(){
        student s = new student("1", "a@b.com", "password", 10);
        student studentAcc = new student("1", "a@b.com", "abc123", 5);
        assertEquals("1", studentAcc.getID());
        assertEquals("a@b.com", studentAcc.getEmail());
        assertEquals("abc123", studentAcc.getPassword());
        assertEquals(5, studentAcc.getGrade());
    }

    @Test
    void getEmailTest(){
        student studentAcc = new student("1", "a@b.com", "abc123", 5);
        assertEquals("a@b.com", studentAcc.getEmail());
    }

    @Test
    void getID(){
        student studentAcc = new student("1", "a@b.com", "abc123", 5);
        assertEquals("1", studentAcc.getID());
    }
 
 
    @Test
    void requestBook(){
 
 
        // Add a book to the library's books
        Book myBook = new Book("1", "The Great Gatsby", "F. Scott Fitzgerald", "9780684801520L", false, null);
        Library.books.add(myBook);
 
 
        // Initialize a student account
        student studentAcc = new student("1", "a@b.com", "abc123", 5);
 
 
        // Request the book
        studentAcc.requestBook("1");
 
 
        // Check that the book is in the student's requested books
        assertEquals(1, Library.requestList.size());
        assertEquals(myBook.getId(), Library.requestList.get(1)[0]);
    }
 
        
}

