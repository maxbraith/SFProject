package Library.Users;

import java.util.ArrayList;
import java.util.HashMap;

import Library.Book.Assignment;
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



    public ArrayList<String> getAssignedBooks(ArrayList<Assignment> assignments){
        ArrayList<String> assignedBooks = new ArrayList<String>();
        for(Assignment assignment : assignments){
            if(assignment.isAssignedToStudent()){
                if(assignment.getAssignedTo().equals(this.id)){
                    assignedBooks.add(assignment.getBook());
                }
            }else{
                if(assignment.getAssignedTo().equals(this.grade)){
                    assignedBooks.add(assignment.getBook());
                }
            }
        }
        return assignedBooks;
    }

    public int getGrade(){
        return grade;
    }
 
 
 
 

    
}
