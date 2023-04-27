package Users;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import Library.Library;

import org.junit.jupiter.api.Test;

import Library.Book.Assignment;
import Library.Book.Book;
import Library.Users.faculty;
import Library.Users.student;


public class studentAccoutTest {
    @Test
public void testConstructor(){
    String id = "1";
    String email = "test@example.com";
    String passwordHash = "hash";
    int grade = 10;
    String name = "John";
    String accountType = "student";
    String salt = "salt";
    String secretQuestion = "What's your favorite color?";
    String secretAns = "blue";
    
    student s = new student(id, email, passwordHash, grade, name, accountType, salt, secretQuestion, secretAns);
    
    assertEquals(id, s.getId());
    assertEquals(email, s.getEmail());
    assertEquals(passwordHash, s.getPasswordHash());
    assertEquals(grade, s.getGrade());
    assertEquals(name, s.getName());
    assertEquals(accountType, s.getAccountType());
    assertEquals(salt, s.getSalt());
    assertEquals(secretQuestion, s.getSecretQuestion());
    assertEquals(secretAns, s.getSecretAns());
}
   

 
@Test
public void testGetAssignedBooks() {
    // Create some test data
    Book book1 = new Book("1", "Title1", "Author 1", "ISBN 1", false, null);
    Book book2 = new Book("2", "Title2", "Author 2", "ISBN 2", false, null);
    student student1 = new student("S001", "email1", "passwordHash1", 10, "name1", "student", "salt1", "question1", "answer1");
    student student2 = new student("S002", "email1", "passwordHash1", 10, "name1", "student", "salt1", "question1", "answer1");
    faculty teacher1 = new faculty("S003", "email1", "passwordHash1", 10, "name1", "student", "salt1", "question1", "answer1");
    Assignment assignment1 = new Assignment(teacher1, student1, book1);
    Assignment assignment2 = new Assignment(teacher1, student1, book2);
    Assignment assignment3 = new Assignment(teacher1, student2, book1);
    Assignment assignment4 = new Assignment(teacher1, "Grade1", book2, 3);

    // Assignments array list
    ArrayList<Assignment> assignments = new ArrayList<>();
    assignments.add(assignment1);
    assignments.add(assignment2);
    assignments.add(assignment3);
    assignments.add(assignment4);

    // Create a student object
    student testStudent = new student("S001", "email1", "passwordHash1", 10, "name1", "student", "salt1", "question1", "answer1");

    // Test getAssignedBooks method
    ArrayList<String> assignedBooks = student.getAssignedBooks(assignments, testStudent);
    assertEquals(2, assignedBooks.size());
    assertTrue(assignedBooks.contains("Title1"));
    assertTrue(assignedBooks.contains("Title2"));
}
  
    @Test
    public void testGetGrade(){
        student s = new student("1", "test@example.com", "hash", 10, "John", "student", "salt", "What's your favorite color?", "blue");
        
        assertEquals(10, s.getGrade());
    }
   
    
    
    
    
    
    
    
        
}

