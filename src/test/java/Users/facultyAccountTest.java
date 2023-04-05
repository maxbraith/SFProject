package Users;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import Library.Users.faculty;

public class facultyAccountTest {
    @Test
    void getEmailTest(){
        faculty facultyAcc = new faculty(1, "a@b.com", "abc123", 0);
        assertEquals("a@b.com", facultyAcc.getEmail());
    }

    @Test
    void getID(){
        faculty studentAcc = new faculty(1, "a@b.com", "abc123", 0);
        assertEquals("a@b.com", studentAcc.getID());
    }

    @Test
    void accTypeTest(){

    }
}
