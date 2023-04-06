package Library.Users;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;

import Library.Library;

import java.util.List;


import Library.Book.Book;

public class librarian extends parentUser{

    public librarian(int id, String email, String password, int grade){
        this.id = id;
        this.email = email;
        this.password = password;
        this.grade = grade;
        this.accType = "Librarian";
    }
    
    public String accType(){
        return accType;
    }

    public parentUser makeAccount(int id, String email, String password, int grade, String accType){
        if(accType.equals("Student")){
            student user = new student(id, email, password, grade);
            return user;
        }
        else if(accType.equals("Faculty")){
            faculty user = new faculty();
            return user;
        }   
        else{
            librarian user = new librarian(id, email, password, grade);
            return user;
        }
        
    }


    public HashMap<Integer, Integer> getRequests() {
        return Library.requestList;
    }

    /**
     * @post checks out a book if the book exists in the request list
     * @param bookId - ID of book
     * NOTE: Does not have a handler for if key does not exist
     */
    public void confirmRequestCheckout(int bookId){

        int accountID = Library.requestList.get(bookId);
        Book bookToCheckout = Library.books.get(bookId);

        if (bookToCheckout == null) {
            System.out.println("Error: Book with ID " + bookId + " not found in Library.books map");
            return;
        }

        Library.books.remove(bookId);
        Library.checkedOutBooks.put(bookId, bookToCheckout);
        bookToCheckout.setTakenOut(true);

        if (accountID != 0) {
            bookToCheckout.setAccount(accountID);
        }
/* 
        int accountID = Library.requestList.get(bookId);
        Book bookToCheckout = Library.books.get(bookId);
        Library.books.remove(bookId);
        Library.checkedOutBooks.put(bookId, bookToCheckout);
        bookToCheckout.setAccount(accountID);
        bookToCheckout.setTakenOut(true);
*/

    public Book checkOutBook(int bookId, parentUser acc, ArrayList<Book> books){
        for(int i=0; i<books.size(); i++){
            Book myBook = books.get(i);
            if(bookId == myBook.getId()){
                return myBook;
            }
        }
        return null;
        

    }

    
}
