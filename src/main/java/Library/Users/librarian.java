package Library.Users;

import java.util.ArrayList;
import java.util.List;

import Library.Library;
import Library.Book.Book;

public class librarian extends parentUser{

    public librarian(int id, String email, String password, int grade){
        this.id = id;
        this.email = email;
        this.password = password;
        this.grade = grade;
    }
    
    public String accType(){
        return null;
    }

    public void makeAccount(){

    }

    /**
     * @post checks out a book if the book exists in the request list
     * @param bookId - ID of book
     * NOTE: Does not have a handler for if key does not exist
     */
    public void confirmRequestCheckout(int bookId){
        int accountID = Library.requestList.get(bookId);
        Book bookToCheckout = Library.books.get(bookId);
        Library.books.remove(bookId);
        Library.checkedOutBooks.put(bookId, bookToCheckout);
        bookToCheckout.setAccount(accountID);
        bookToCheckout.setTakenOut(true);

    }

    
}
