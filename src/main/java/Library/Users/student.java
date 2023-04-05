package Library.Users;

public class student extends parentUser{

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

    public void requestCheckout(){
        
    }
    
}
