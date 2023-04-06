package Library;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.opencsv.CSVReaderHeaderAware;
import com.opencsv.exceptions.CsvValidationException;

import Library.Book.Book;
import Library.Users.parentUser;

public class Library {
    public HashMap<String, parentUser> accounts;
    public static HashMap<Integer, Book> books;
    public static HashMap<Integer, Book> checkedOutBooks;
    public static HashMap<Integer, Integer> requestList;

    public Library(){
        accounts = new HashMap<String, parentUser>();
        books = new HashMap<Integer, Book>();
        checkedOutBooks = new HashMap<Integer, Book>();
        requestList = new HashMap<Integer, Integer>();

    }

    /**
     * @post confirms credentials of faculty
     * @param email - email associated with account
     * @param password - password associated with account
     * @return true if account exists, false if not
     */
    public boolean logIn(String email, String password){
        parentUser account = accounts.get(email);
        if(account != null){
            if(account.checkPassword(password)==true){
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
            books.put(myBook.getId(), myBook);
        }
    }

}
