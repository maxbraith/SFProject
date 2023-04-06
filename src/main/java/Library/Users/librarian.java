package Library.Users;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import Library.Book.Book;

public class librarian extends parentUser{

    public librarian(String id, String email, String passwordHash, int grade, String name, String accountType, String salt){
        this.id = id;
        this.email = email;
        this.passwordHash = passwordHash;
        this.grade = grade;
        this.name =name;
        this.accountType = accountType;
        this.salt = salt;
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
    public static parentUser makeAccount( String email, String password, int grade, String accType, String name) throws NoSuchAlgorithmException{
        String uuid = UUID.randomUUID().toString();
        String[] hashSalt = parentUser.passwordHash(password);
        if(accType.equals("Student")){
            student user = new student(uuid, email, hashSalt[0], grade,name,accType, hashSalt[1]);
            return user;
        }
        else if(accType.equals("Faculty")){
            faculty user = new faculty(uuid,email, hashSalt[0], grade,name,accType, hashSalt[1]);
            return user;
        }   
        else{
            librarian user = new librarian(uuid, email, hashSalt[0], grade,name,accType, hashSalt[1]);
            return user;
        }
        
    }
    // public Book checkOutBook(int bookId, parentUser acc, ArrayList<Book> books){
    //     for(int i=0; i<books.size(); i++){
    //         Book myBook = books.get(i);
    //         if(bookId == myBook.getId()){
    //             return myBook;
    //         }
    //     }
    //     return null;
        
    // }
}
