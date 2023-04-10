package Library.Users;

import java.util.ArrayList;

import Library.Book.Book;

public class parentUser {

    protected String id;
    protected String email;
    protected String password;
    protected int grade;
    protected String accType;

    public void parentUser(String id, String email, String password, int grade) throws IllegalArgumentException{
        this.id = id;
        this.email = email;
        this.password = password;
        this.grade = grade;
    }

    public String getID(){
        return id;
    }

    public String getEmail(){
        return email;
    }

    public boolean checkPassword(String passwordCheck){
        return password.equals(passwordCheck);
    }

    public void updateBookList(){

    }

    public String getPassword() {
        return password;
    }



    

}
