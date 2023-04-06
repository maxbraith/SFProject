package Users;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import com.opencsv.CSVReaderHeaderAware;
import com.opencsv.exceptions.CsvValidationException;

import Library.Book.Book;
import Library.Users.faculty;
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
        librarian  libAcc = new librarian(1, "a@b.com", "abc123", 5);
        assertEquals(1, libAcc.getID());
    }
    @Test
    void accTypeTest(){
        librarian  libAcc = new librarian(1, "a@b.com", "abc123", 5);
        assertEquals("Librarian", libAcc.accType());
    }

    @Test
    void makeAccountTest(){
        librarian  libAcc = new librarian(1, "a@b.com", "abc123", 5);
        student user = (student) libAcc.makeAccount(1, "ebarry@ithaca.edu", "Valley13", 4, "Student");
        assertEquals("ebarry@ithaca.edu", user.getEmail());
        
        Library.Users.parentUser facUser = libAcc.makeAccount(2, "jbarr@ic.edu", "barr", 10, "Faculty");
        assertEquals("jbarr@ic.edu", facUser.getEmail());
    }

    @Test 
    void checkOutBookTest() throws IOException, CsvValidationException{
        librarian  libAcc = new librarian(1, "a@b.com", "abc123", 5);
        student student = new student(3, "ebarry@ithaca.edu", "Valley13", 4);
        FileReader file = new FileReader("bookData.csv");
        CSVReaderHeaderAware reader = new CSVReaderHeaderAware(file);
        ArrayList<Book> books = new ArrayList<Book>();
        for(int i=0; i<10; i++){
            Map<String, String> values = reader.readMap();
            System.out.println(values.get("title"));
            int id = i;
            String title = values.get("title");
            String author = values.get("author");
            String x = values.get("isbn");
            Long isbn = Long.parseLong(x);
            boolean takenOut = false;
            LocalDate returnDate = LocalDate.of(2023, 4, 1);;
            Book myBook = new Book(id, title, author, isbn, takenOut, returnDate);
            books.add(myBook);
        }

        Book bookOut = libAcc.checkOutBook(3, libAcc, books);
        assertEquals("Pride and Prejudice", bookOut.getTitle());
    }

}
