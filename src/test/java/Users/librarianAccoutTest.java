package Users;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import Library.Users.librarian;


public class librarianAccoutTest {


    @Test
    void getEmailTest(){
        librarian libAcc = new librarian(1, "a@b.com", "abc123", 5);
        assertEquals("a@b.com", libAcc.getEmail());
    }

    @Test
    void getID(){
        librarian  libAcc = new librarian(1, "a@b.com", "abc123", 5);
        assertEquals("a@b.com", libAcc.getID());
    }
    @Test
    void accTypeTest(){
        
    }

    @Test
    void makeAccountTest(){

    }

    @Test 
    void checkOutBookTest(){

    }

}
