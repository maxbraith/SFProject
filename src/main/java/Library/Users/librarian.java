package Library.Users;

import java.util.List;

public class librarian extends parentUser{

    public librarian(String id, String email, String passwordHash, int grade, String name, String accountType, String salt){
        this.id = id;
        this.email = email;
        this.passwordHash = passwordHash;
        this.grade = grade;
        this.name =name;
        this.accountType = accountType;
        this.salt = salt;
    }
    


    public void makeAccount(){

    }
    public void checkOutBook(String bookId, parentUser acc, List Books ){

    }
}
