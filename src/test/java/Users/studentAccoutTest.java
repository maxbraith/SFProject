package Users;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import Library.Users.student;


public class studentAccoutTest {
    @Test
    void constructorTest(){
        student s = new student(1, "a@b.com", "password", 10);
        student studentAcc = new student(1, "a@b.com", "abc123", 5);
        assertEquals(1, studentAcc.getID());
        assertEquals("a@b.com", studentAcc.getEmail());
        assertEquals("abc123", studentAcc.getPassword());
        assertEquals(5, studentAcc.getGrade());
    }

    @Test
    void getEmailTest(){
        student studentAcc = new student(1, "a@b.com", "abc123", 5);
        assertEquals("a@b.com", studentAcc.getEmail());
    }

    @Test
    void getID(){
        student studentAcc = new student(1, "a@b.com", "abc123", 5);
        assertEquals("a@b.com", studentAcc.getID());
    }
 
 
    @Test
    void requestBook(){
        // Initialize a library object
        Library library = new Library();
 
 
        // Add a book to the library's books
        Book myBook = new Book(1, "The Great Gatsby", "F. Scott Fitzgerald", 9780684801520L, false, null);
        library.books.put(myBook.getId(), myBook);
 
 
        // Initialize a student account
        student studentAcc = new student(1, "a@b.com", "abc123", 5);
 
 
        // Request the book
        studentAcc.requestBook(1);
 
 
        // Check that the book is in the student's requested books
        assertEquals(1, studentAcc.getRequestedBooks().size());
        assertEquals(myBook.getId(), studentAcc.getRequestedBooks().get(1));
    }
 
        
}

