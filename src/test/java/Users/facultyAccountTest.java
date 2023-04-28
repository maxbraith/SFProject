package Users;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import Library.Book.Assignment;
import Library.Book.Book;
import Library.Users.faculty;
import Library.Users.student;

public class facultyAccountTest {
    @Test
    void testConstructor(){
        String id = "1";
        String email = "test@example.com";
        String passwordHash = "hash";
        int grade = 10;
        String name = "John";
        String accountType = "faculty";
        String salt = "salt";
        String secretQuestion = "What's your favorite color?";
        String secretAns = "blue";

        faculty f = new faculty(id, email, passwordHash, grade, name, accountType, salt, secretQuestion, secretAns);

        assertEquals(id, f.getId());
        assertEquals(email, f.getEmail());
        assertEquals(passwordHash, f.getPasswordHash());
        assertEquals(grade, f.getGrade());
        assertEquals(name, f.getName());
        assertEquals(accountType, f.getAccountType());
        assertEquals(salt, f.getSalt());
        assertEquals(secretQuestion, f.getSecretQuestion());
        assertEquals(secretAns, f.getSecretAns());
    }

    @Test
    public void testCheckNoCopiesAvaliable() {
        Book book = new Book("1", "Title 1", "Author 1", "ISBN 1", false, null);
        ArrayList<Book> books = new ArrayList<Book>();
        books.add(book);
        assertFalse(faculty.checkNoCopiesAvaliable(books, book, 2));
        assertTrue(faculty.checkNoCopiesAvaliable(books, book, 1));
    }

    @Test
    public void testAssignBooks() {
        Book book = new Book("1", "Title 1", "Author 1", "ISBN 1", false, null);
        ArrayList<Book> books = new ArrayList<Book>();
        books.add(book);
        ArrayList<Assignment> assignments = new ArrayList<Assignment>();
        student student = new student("S002", "email1", "passwordHash1", 10, "name1", "student", "salt1", "question1", "answer1");
        faculty faculty = new faculty("S003", "email1", "passwordHash1", 10, "name1", "student", "salt1", "question1", "answer1");
        assertTrue(faculty.assignBooks(faculty, student, assignments, book, books));
        assertTrue(faculty.assignBooks(faculty, student, assignments, book, books));
        assertEquals(2, assignments.size());
        assertFalse(faculty.assignBooks(faculty, "1", assignments, book, 2, books));
        assertTrue(faculty.assignBooks(faculty, "1", assignments, book, 1, books));
        assertEquals(3, assignments.size());
    }

    @Test
    public void testViewAllAssignments() {
        Book book1 = new Book("1", "Title 1", "Author 1", "ISBN 1", false, null);
        Book book2 = new Book("1", "Title 1", "Author 1", "ISBN 1", false, null);
        ArrayList<Book> books = new ArrayList<Book>();
        books.add(book1);
        books.add(book2);
        ArrayList<Assignment> assignments = new ArrayList<Assignment>();
        student student = new student("S002", "email1", "passwordHash1", 10, "name1", "student", "salt1", "question1", "answer1");
        faculty faculty = new faculty("S003", "email1", "passwordHash1", 10, "name1", "student", "salt1", "question1", "answer1");
        assignments.add(new Assignment(faculty, student, book1));
        assignments.add(new Assignment(faculty, "1", book2, 1));
        ArrayList<Assignment> result = faculty.viewAllAssignments(assignments, faculty);
        assertEquals(2, result.size());
        result = faculty.viewAllAssignments(assignments, student);
        assertEquals(0, result.size());
    }

    @Test
    void assignBooksTest2(){
        ArrayList<Book> books = new ArrayList<Book>();
        books.add(new Book("1", "Title 1", "Author 1", "ISBN 1", false, null));
        books.add(new Book("2", "Title 2", "Author 2", "ISBN 2", false, null));
        
        faculty f = new faculty("1", "Test@test.com", "123", 10, "Name", "Faculty", "salt", "What is your dog's name?", "Dog");
        student s = new student("1", "Student@test.com", "123", 5, "Name", "Student", "salt", "What is your favorite color?", "Blue");
        student s1 = new student("2", "Student1@test.com", "123", 5, "Name", "Student", "salt", "Favorite color?", "Red");

        ArrayList<Assignment> assignments = new ArrayList<Assignment>();
        Book book = new Book("1", "Title 1", "Author 1", "ISBN 1", false, null);
        Book book2 = new Book("2", "Title 2", "Author 2", "ISBN 2", false, null);
        books.add(book2);

        //Book is assigned to specific student
        assertTrue(faculty.assignBooks(f, s, assignments, book, books));
        ArrayList<Assignment> assignmentsComp = new ArrayList<Assignment>();
        assignmentsComp.add(new Assignment(f, s, book));
        assertEquals(assignmentsComp.size(), assignments.size());

        //Book is assigned to entire grade
        assertTrue(faculty.assignBooks(f, "5", assignments, book2, 2, books));
        assignmentsComp.add(new Assignment(f, "5", book2, 2));
        assertEquals(assignmentsComp.size(), assignments.size());

        //Book not availble
        assertFalse(faculty.assignBooks(f, s, assignments, new Book("3", "Title3", "Aithor3", "ISBN 3", false, null), books));

        //Not enough copies
        assertFalse(faculty.assignBooks(f, "5", assignments, book, 5, books));
    }

    @Test
    void viewAllAssignmentsTest2(){
        ArrayList<Book> books = new ArrayList<Book>();
        books.add(new Book("1", "Title 1", "Author 1", "ISBN 1", false, null));
        books.add(new Book("2", "Title 2", "Author 2", "ISBN 2", false, null));
        ArrayList<Assignment> assignments = new ArrayList<Assignment>();
        faculty f = new faculty("1", "Test@test.com", "123", 10, "Name", "Faculty", "salt", "What is your dog's name?", "Dog");
        faculty f1 = new faculty("2", "Test@test.com", "123", 10, "Name", "Faculty", "salt", "What is your dog's name?", "Dog");
        student s = new student("1", "Student@test.com", "123", 5, "Name", "Student", "salt", "What is your favorite color?", "Blue");
        Book book = new Book("1", "Title 1", "Author 1", "ISBN 1", false, null);

        //Assignment list empty
        assertEquals(assignments.size(), faculty.viewAllAssignments(assignments, f).size());

        //Assignment list not empty
        assignments.add(new Assignment(f, s, book));
        assertEquals(assignments.size(), faculty.viewAllAssignments(assignments, f).size());

        //Assignment list not empt and includes assignments from other accounts
        assignments.add(new Assignment(f1, s, book));
        assertEquals(1, faculty.viewAllAssignments(assignments, f).size());

    }

    @Test
    void deleteAssignmentsTest(){
        faculty f = new faculty("1", "Test@test.com", "123", 10, "Name", "Faculty", "salt", "What is your dog's name?", "Dog");
        faculty f1 = new faculty("2", "Test@test.com", "123", 10, "Name", "Faculty", "salt", "What is your dog's name?", "Dog");
        student s = new student("1", "Student@test.com", "123", 5, "Name", "Student", "salt", "What is your favorite color?", "Blue");
        Book book = new Book("1", "Title 1", "Author 1", "ISBN 1", false, null);

        ArrayList<Assignment> assignments = new ArrayList<Assignment>();
        Assignment assignmentToDelete = new Assignment(f, s, book);

        assignments.add(assignmentToDelete);
        assignments.add(new Assignment(f1, s, book));
        assertEquals(2, assignments.size());

        faculty.deleteAssignments(assignments, assignmentToDelete);
        assertEquals(1, assignments.size());
        

    }




}
