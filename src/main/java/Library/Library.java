package Library;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
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

    /**
     * @post confirms credentials of faculty
     * @param email - email associated with account
     * @param password - password associated with account
     * @return true if account exists, false if not
     */
    public boolean facultyLogIn(String email, String password){
        for(int i=0; i<faculty.size(); i++){
            if(faculty.get(i).getEmail() == email && faculty.get(i).getPassword() == password){
                return true;
            }
        }
        return false;
    }

    /**
     * @post confirms credentials of librarian
     * @param email - email associated with account
     * @param passoword - password associated with account
     * @return true if account exists, false if not
     */
    public boolean librarianLogIn(String email, String password){
        for(int i=0; i<librarians.size(); i++){
            if(librarians.get(i).getEmail() == email && librarians.get(i).getPassword() == password){
                return true;
            }
        }
        return false;
    }

    /**
     * @post confirms credentials of student
     * @param email - email associated with account
     * @param passoword - password associated with account
     * @return true if account exists, false if not
     */
    public boolean studentLogIn(String email, String password){
        for(int i=0; i<students.size(); i++){
            if(students.get(i).getEmail() == email && students.get(i).getPassword() == password){
                return true;
            }
        }
        return false;
    }

    /**
     * @throws IOException
     * @throws CsvValidationException
     * @post intializes an account into the library database
     */
    public void initalizer() throws IOException, CsvValidationException{
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
            boolean takenOu = false;
            LocalDate returnDate = LocalDate.of(2023, 4, 1);;
            Book myBook = new Book(id, title, author, isbn, takenOu, returnDate);
            books.add(myBook);
        }
    }

}
