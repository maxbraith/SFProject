package Library.Users;

import java.util.ArrayList;
import java.util.HashMap;

import Library.Book.Assignment;
import Library.Book.Book;
import Library.Library;


public class student extends parentUser{



    public student(String id, String email, String passwordHash, int grade, String name, String accountType,String salt,String secretQuestion,String secretAns){

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



    public static ArrayList<String> getAssignedBooks(ArrayList<Assignment> assignments, parentUser curUser){
        ArrayList<String> assignedBooks = new ArrayList<String>();
        for(Assignment assignment : assignments){
            if(assignment.isAssignedToStudent()){
                if(assignment.getAssignedTo().equals(curUser.getId())){
                    assignedBooks.add(assignment.getBookName());
                }
            }else{
                if(assignment.getAssignedTo().equals(String.valueOf(curUser.getGrade()))){
                    assignedBooks.add(assignment.getBookName());
                }
            }
        }
        return assignedBooks;
    }

    public int getGrade(){
        return grade;
    }
 
 
 
 

    
}
