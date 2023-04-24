package Library.Users;

import java.util.ArrayList;

import Library.Book.Assignment;
import Library.Book.Book;

public class faculty extends parentUser {

    // This is a constructor method for the `faculty` class that takes in six parameters: `id`,
    // `email`, `password`, `grade`, `name`, and `accountType`. It sets the values of these parameters
    // to the corresponding instance variables of the `faculty` class using the `this` keyword.
    public faculty(String id, String email, String passwordHash, int grade, String name, String accountType,String salt,String secretQuestion,String secretAns){
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

    public void assignBooks(String grade,ArrayList<Assignment> assignments,Book book){
        assignments.add(new Assignment(this,grade,book)) ;
    }
    public void assignBooks(parentUser student,ArrayList<Assignment> assignments,Book book){
        assignments.add(new Assignment(this,student,book)) ;
    }
    
}
