package Users;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import Library.Library;
import Library.Book.Book;
import Library.Users.librarian;
import Library.Users.student;


public class librarianAccoutTest {


    @Test
    void getEmailTest(){
        librarian libAcc = new librarian(1, "a@b.com", "abc123", 5);
        assertEquals("a@b.com", libAcc.getEmail());
    }

    @Test
    void getID(){
        librarian  libAcc = new librarian(45, "a@b.com", "abc123", 5);
        assertEquals(45, libAcc.getID());
    }
    @Test
    void accTypeTest(){
        
    }

    @Test
    void makeAccountTest(){

    }

    @Test 
    void confirmRequestCheckoutTest(){

        Library library = new Library(); // Create a new instance of the Library class
        librarian libAcc = new librarian(1, "a@b.com", "abc123", 5);
        student studentAcc = new student(2, "c@d.com", "def456", 10);
        Book book = new Book(1, "The Great Gatsby", "F. Scott Fitzgerald", 9780684801520L, false, null);
        studentAcc.requestBook(book.getId());
        assertEquals(1, studentAcc.getRequestedBooks().size());
        assertEquals(1, libAcc.getRequests().size()); // initial request count
        
        libAcc.confirmRequestCheckout(book.getId());
        //assertEquals(0, libAcc.getRequests().size()); // request count should be 0 after confirmation
        //assertTrue(book.getTakenOut()); // the book should be marked as taken out
        //assertEquals(studentAcc, book.getId()); // the account should be set to the student's account
        //assertEquals(studentAcc.getRequestedBooks().size(), 0); // student's requested book count should be 0
        
    }

}
