package Library.Users;

import java.util.ArrayList;
import java.util.HashMap;

import Library.Book.Book;
import Library.Library;


public class student extends parentUser{



    public student(String id, String email, String passwordHash, int grade, String name, String accountType,String salt){

        this.id = id;
        this.email = email;
        this.passwordHash = passwordHash;
        this.grade = grade;
        this.name =name;
        this.accountType = accountType;
        this.salt = salt;
    }



    public ArrayList<Book> getAssignedBooks(){
        ArrayList<Book> assignedBooks = new ArrayList<Book>();
        return assignedBooks;
    }

    public int getGrade(){
        return grade;
    }
 
 
    public void requestBook(String id){
        String[] array = new String[2];
        array[0] = id;
        array[1] = this.id;
        Library.requestList.add(array);
    }
 
 
 

    
}
