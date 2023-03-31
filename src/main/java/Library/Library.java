package Library;

import java.util.ArrayList;

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
