package Library.Users;

import java.util.ArrayList;
import java.util.HashMap;

import java.util.List;

import Library.Book.Book;
import Library.Library;


public class librarian extends parentUser{

    public librarian(String id, String email, String password, int grade){
        this.id = id;
        this.email = email;
        this.password = password;
        this.grade = grade;
        this.accType = "Librarian";
    }
    
    public String accType(){
        return accType;
    }

    public parentUser makeAccount(String id, String email, String password, int grade, String accType){
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
    public Book checkOutBook(String bookId, parentUser acc, ArrayList<Book> books){
        for(int i=0; i<books.size(); i++){
            Book myBook = books.get(i);
            if(bookId == myBook.getId()){
                return myBook;
            }
        }
        return null;
        
    }
    
    
    public ArrayList<String[]> getRequests() {
        return Library.requestList;
    }
 
 
    /**
     * @post checks out a book if the book exists in the request list
     * @param bookId - ID of book
     * NOTE: Does not have a handler for if key does not exist
     */
    public void confirmRequestCheckout(String bookId){
        String accountID = null;
        Book bookToCheckout = null;
        int index = -1;
        for(int i=0; i<Library.requestList.size(); i++){
            if(Library.requestList.get(i)[0] == bookId){
                accountID = Library.requestList.get(i)[1];
                index = i;
            }
        }
 
        if (accountID == null) {
            System.out.println("Error: Book with ID " + bookId + " not found in Library.books map");
            return;
        }
        else{
            for(int i=0; i<Library.books.size(); i++){
                if(Library.books.get(i).getId() == bookId){
                    bookToCheckout = Library.books.get(i);
                }
            }
            Library.checkedOutBooks.add(bookToCheckout);
            bookToCheckout.setTakenOut(true);
            Library.requestList.remove(index);
        }

 
    }
 
}
