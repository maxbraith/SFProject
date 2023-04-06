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
