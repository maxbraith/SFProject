package Library;


import java.io.BufferedReader;

import java.io.FileNotFoundException;

import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;


import java.util.List;

import java.util.LinkedList;

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
    public ArrayList<Book> checkedOutBooksRequests;

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

        checkedOutBooksRequests = new ArrayList<Book>();


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
        String line = "";  
        String splitBy = ",";  
        BufferedReader br = new BufferedReader(new FileReader("bookDataFiltered.csv")); 
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
        while ((line = br.readLine()) != null){  
            String[] temp = line.split(splitBy);
            Book tempBook = new Book(Integer.parseInt(temp[0]),temp[1],temp[2],Long. parseLong(temp[3]),Boolean.parseBoolean(temp[4]),LocalDate.parse(temp[5], formatter));
            books.add(tempBook);
        }  
    }

}
