package Library;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
