package Library.Users;

public class parentUser {

    public int ID;


    public int getID(){
        return ID;
    }

    private String email;
    private String password;


    public String getEmail(){
        return email;
    }

    public boolean checkPassword(String passwordCheck){
        return password.equals(passwordCheck);
    }



    

}
