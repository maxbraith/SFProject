package Library.Users;

import java.util.List;

public class librarian extends parentUser{

    public librarian(int id, String email, String password, int grade){
        this.id = id;
        this.email = email;
        this.password = password;
        this.grade = grade;
    }
    
    public String accType(){
        return null;
    }

    public void makeAccount(){

    }
    public void checkOutBook(String bookId, parentUser acc, List Books ){

    }
}
