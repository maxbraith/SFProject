package Library.Users;

import java.util.ArrayList;
import java.util.List;

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
    


    // public parentUser makeAccount(int id, String email, String password, int grade, String accType){
    //     if(accType.equals("Student")){
    //         student user = new student(id, email, password, grade);
    //         return user;
    //     }
    //     else if(accType.equals("Faculty")){
    //         faculty user = new faculty();
    //         return user;
    //     }   
    //     else{
    //         librarian user = new librarian(id, email, password, grade);
    //         return user;
    //     }
        
    // }
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
