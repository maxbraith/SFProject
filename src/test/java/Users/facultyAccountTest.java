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
}
