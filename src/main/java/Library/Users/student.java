package Library.Users;

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


    public String[] getAssignedBooks(){
        return null;
    }

    
}
