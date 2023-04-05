package Library;

import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.opencsv.CSVReaderHeaderAware;
import com.opencsv.exceptions.CsvValidationException;

import Library.Book.Book;
import Library.Users.parentUser;

public class Library {
    public ArrayList<parentUser> students;
    public ArrayList<parentUser> librarians;
    public ArrayList<parentUser> faculty;
    public ArrayList<Book> books;
    public ArrayList<Book> checkedOutBooks;

    public Library(){
        students = new ArrayList<parentUser>();
        librarians = new ArrayList<parentUser>();
        faculty = new ArrayList<parentUser>();
        books = new ArrayList<Book>();
        checkedOutBooks = new ArrayList<Book>();
    }

    /*
     * Function that reads in books to our book list
     */
    public void firstPopulateBooks() throws CsvValidationException, IOException{
        FileReader file = new FileReader("books_1.Best_Books_Ever.csv");
        CSVReaderHeaderAware reader = new CSVReaderHeaderAware(file);
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
    }

    /*
     * This function to be used at the end of main when the app shuts down
     * reads the current book list to a text file to be used again 
     * when the app restarts
     */
    public void readBookstoTxt(){

    }
    /*
     * This function to be used at the beginning of main
     * reads the old book list from the text file 
     * and enters it into our bookList
     */
    public void readBooksFromTxt(){

    }

    /**
     * @post confirms credentials of faculty
     * @param id - id associated with account
     * @param password - password associated with account
     * @return true if account exists, false if not
     */
    public boolean facultyLogIn(String id, String password){
        return false;
    }

    /**
     * @post confirms credentials of librarian
     * @param id - id associated with account
     * @param passoword - password associated with account
     * @return true if account exists, false if not
     */
    public boolean librarianLogIn(String id, String password){
        return false;
    }

    /**
     * @post confirms credentials of student
     * @param id - id associated with account
     * @param passoword - password associated with account
     * @return true if account exists, false if not
     */
    public boolean studentLogIn(String id, String password){
        return false;
    }

}
