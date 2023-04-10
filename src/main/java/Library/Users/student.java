package Library.Users;

import java.util.ArrayList;
import java.util.HashMap;

import Library.Book.Book;
import Library.Library;


public class student extends parentUser{
    ArrayList<Book> assignedBooks;
    public student(String id, String email, String password, int grade){
        this.id = id;
        this.email = email;
        this.password = password;
        this.grade = grade;
        this.accType = "Student";
        this.assignedBooks = new ArrayList<>();
        
    }

    public String accType(){
        return this.accType;
    }

    public ArrayList<Book> getAssignedBooks(){
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
