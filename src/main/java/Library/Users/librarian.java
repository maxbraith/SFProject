package Library.Users;

import java.security.NoSuchAlgorithmException;
import java.time.Clock;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import Library.Book.Book;
import Library.Book.Request;
import Library.Library;


public class librarian extends parentUser{

    public librarian(String id, String email, String passwordHash, int grade, String name, String accountType, String salt,String secretQuestion,String secretAns){

        this.id = id;
        this.email = email;
        this.passwordHash = passwordHash;
        this.grade = grade;
        this.name =name;
        this.accountType = accountType;
        this.salt = salt;
        this.secretQuestion = secretQuestion;
        this.secretAns = secretAns;
    }

    public String toString(){
        String response="";
        response = response+"ID :"+this.id+"\n";
        response = response+"Email :"+this.email+"\n";
        response = response+"Name :"+this.name+"\n";
        response = response+"Account Type :"+this.accountType+"\n";
        if(this.accountType.equals("student")){
            response = response+"Grade :"+this.grade+"\n";
        }

        return response;
    }

    /**
     * The function takes an ArrayList of books and a specific book, and sets its return date, take out
     * status, and borrower information to null or false.
     * 
     * @param books An ArrayList of Book objects representing the collection of books in a library or a
     * bookstore.
     * @param book The specific Book object that is being returned and needs to have its return date,
     * take out status, and borrower information updated in the ArrayList of books.
     */
    public static void bookReturn(ArrayList<Book> books, Book book){
        for(Book curBook : books){
            if(curBook.getId().equals(book.getId())){
                curBook.setReturnDate(null);
                curBook.setTakeOutBy("");
                curBook.setTakenOut(false);
            }
        }
    }
    



    /**
     * This function creates a new user account with a unique ID, hashed password, and specified
     * account type (student, faculty, or librarian).
     * 
     * @param email The email address of the user creating the account.
     * @param password The password for the user account being created.
     * @param grade The grade parameter is an integer representing the grade level of a student or the
     * level of faculty or librarian.
     * @param accType The type of account being created, which can be "Student", "Faculty", or
     * "Librarian".
     * @param name The name of the user being created.
     * @return The method is returning an instance of the parentUser class, which can be either a
     * student, faculty, or librarian object depending on the value of the accType parameter.
     */
    public static parentUser makeAccount( String email, String password, int grade, String accType, String name,String secretQuestion,String secretAns) throws NoSuchAlgorithmException{
        String uuid = UUID.randomUUID().toString();
        String[] hashSalt = parentUser.passwordHash(password);

        if(accType.equals("student")){
            student user = new student(uuid, email, hashSalt[0], grade,name,accType, hashSalt[1],secretQuestion,secretAns);
            return user;
        }
        else if(accType.equals("faculty")){
            faculty user = new faculty(uuid,email, hashSalt[0], grade,name,accType, hashSalt[1],secretQuestion,secretAns);
            return user;
        }   
        else{
            librarian user = new librarian(uuid, email, hashSalt[0], grade,name,accType, hashSalt[1],secretQuestion,secretAns);
            return user;
        }
        
    
    }

    /**
     * The function adds a new book to an ArrayList of books with a unique ID generated using UUID.
     * 
     * @param books an ArrayList of Book objects, which is the collection of books to which the new
     * book will be added.
     * @param title The title of the book being added to the ArrayList.
     * @param author The author parameter is a String that represents the name of the author of the
     * book being added to the ArrayList.
     * @param ISBN ISBN stands for International Standard Book Number. It is a unique identifier
     * assigned to each edition and variation of a book. It is usually a 10 or 13 digit number that can
     * be used to identify a book in a library or bookstore.
     */
    public static void addBook(ArrayList<Book>books, String title, String author, String ISBN){
        String uuid = UUID.randomUUID().toString();
        books.add(new Book(uuid, title, author, ISBN, false, null));
    }
    /**
     * The function removes a specific book from an ArrayList of books.
     * 
     * @param books an ArrayList of Book objects, representing a collection of books
     * @param bookToRemove The book object that needs to be removed from the ArrayList of books.
     */
    public static void deleteBoo(ArrayList<Book>books,Book bookToRemove){
        books.remove(bookToRemove);
    }



    public static Boolean checkOutBook(String bookId, String checkedOutBy, ArrayList<Book> books){
        LocalDate returnDate =  LocalDate.now().plusDays(14);
        for(Book book : books){
            if(bookId.equals(book.getId())){
                if(!book.getTakenOut()){
                    book.setReturnDate(returnDate);
                    book.setTakenOut(true);
                    book.setTakeOutBy(checkedOutBy);
                    return true;
                }
            }
        }
        return false;
        
    }

    public static ArrayList<Book> viewAllCheckedoutBooks(ArrayList<Book> books){
        ArrayList<Book> temp = new ArrayList<Book>();
        for(Book book: books){
            if(book.getTakenOut()){
                temp.add(book);
            }
        }
        return temp;
    }

    
    
 
 
    /**
     * @post checks out a book if the book exists in the request list
     * @param bookId - ID of book
     * NOTE: Does not have a handler for if key does not exist
     */

    public static boolean confirmRequestCheckout(Request request,ArrayList<Book> books, ArrayList<Request> requests){

        boolean checkOutConfirmation = checkOutBook(request.getBook(),request.getUser(),books);
        if(checkOutConfirmation){
            requests.remove(request);
        }

        return checkOutConfirmation;
    }


	public static void deleteAccount(Map<String, parentUser> users, parentUser curAccount) {
        users.remove(curAccount.getEmail());
	}

}
