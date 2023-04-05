package Users;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import Library.Users.student;


public class studentAccoutTest {
    @Test
    void constructorTest(){
        student s = new student(1, "a@b.com", "password", 10);
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
        
}

