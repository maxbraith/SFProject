package Library.Users;

public class faculty extends parentUser{

    public faculty(int id, String email, String password, int grade){
        this.id = id;
        this.email = email;
        this.password = password;
        this.grade = grade;
    }
    

    public String accType(){
        return null;
    }

    public void assignBooks(String grade){

    }
    public void assignBooks(parentUser student){

    }
    
}
