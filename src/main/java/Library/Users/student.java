package Library.Users;

import java.util.ArrayList;

import java.util.HashMap;

import Library.Library;


import Library.Book.Book;

public class student extends parentUser{
    ArrayList<Book> assignedBooks;

    public student(int id, String email, String password, int grade){
        this.id = id;
        this.email = email;
        this.password = password;
        this.grade = grade;
    }

    public String accType(){
        return null;
    }

    public String[] getAssignedBooks(){
        return null;
    }


    public void requestBook(int id){
        Library.requestList.put(id, this.id);
    }

    public HashMap<Integer, Integer> getRequestedBooks() {
        return Library.requestList;
    }


    
}
