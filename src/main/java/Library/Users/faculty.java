package Library.Users;

public class faculty extends parentUser {

    // This is a constructor method for the `faculty` class that takes in six parameters: `id`,
    // `email`, `password`, `grade`, `name`, and `accountType`. It sets the values of these parameters
    // to the corresponding instance variables of the `faculty` class using the `this` keyword.
    public faculty(String id, String email, String passwordHash, int grade, String name, String accountType,String salt){
        this.id = id;
        this.email = email;
        this.passwordHash = passwordHash;
        this.grade = grade;
        this.name =name;
        this.accountType = accountType;
        this.salt = salt;
    }

    public void assignBooks(String grade){

    }
    public void assignBooks(parentUser student){

    }
    
}
