package Library.Users;

import java.util.ArrayList;

import Library.Book.Book;

public class student extends parentUser{
    ArrayList<Book> assignedBooks;
    public student(int id, String email, String password, int grade){
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
 
 
    public void requestBook(int id){
        Library.requestList.put(id, this.id);
    }
 
 
    public HashMap<Integer, Integer> getRequestedBooks() {
        return Library.requestList;
    }
 
    
}
