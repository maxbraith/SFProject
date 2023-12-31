package Library.Users;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;

import java.util.ArrayList;

import Library.Book.Assignment;
import Library.Book.Book;
import Library.Book.Request;

public class parentUser {

    // These are instance variables declared in the `parentUser` class with the `protected` access
    // modifier, which means they can be accessed by any subclass of `parentUser`. They represent the
    // attributes of a user object, such as their ID, email, password hash, grade, name, account type,
    // and salt.

    protected String id;
    protected String email;
    protected String passwordHash;

    protected int grade;
    protected String name;
    protected String accountType;
    protected String salt;
    protected String secretQuestion;
    protected String secretAns;



    public parentUser(){


    }
    public parentUser(String id, String email, String passwordHash, int grade, String name, String accountType, String salt,String secretQuestion,String secretAns){

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
    public student toStudent(parentUser student){
        return new student(student.getId(),student.getEmail(),student.getPasswordHash(),student.getGrade(),student.getName(),student.getAccountType(),student.getSalt(),student.getSecretQuestion(),student.getSecretAns());
    }
    public faculty toFaculty(parentUser student){
        return new faculty(student.getId(),student.getEmail(),student.getPasswordHash(),student.getGrade(),student.getName(),student.getAccountType(),student.getSalt(),student.getSecretQuestion(),student.getSecretAns());
    }
    public librarian toLibrarian(parentUser student){
        return new librarian(student.getId(),student.getEmail(),student.getPasswordHash(),student.getGrade(),student.getName(),student.getAccountType(),student.getSalt(),student.getSecretQuestion(),student.getSecretAns());
    }

    
   /**
    * This function returns the password hash of an object.
    * 
    * @return The method is returning a String value which is the password hash.
    */
    public String getPasswordHash() {
    	return this.passwordHash;

    }
    
    public void setHashpassword(String pass) throws NoSuchAlgorithmException{
        String[] hashSalt = parentUser.passwordHash(pass);
        this.passwordHash = hashSalt[0];
        this.salt = hashSalt[1];
    }

    /**
     * This function adds a new request for a book to an ArrayList of requests.
     * 
     * @param Book The Book parameter is an object of the Book class, which represents a book that is
     * being requested by a user.
     * @param requests An ArrayList of Request objects, which is used to store all the book requests
     * made by users.
     */
    public void requestBook(Book Book, ArrayList<Request> requests){
        requests.add(new Request(this, Book));
    }

    /**
     * The function returns a list of book names requested by a specific user from a list of requests.
     * 
     * @param requests an ArrayList of Request objects, which contains information about book requests
     * made by users.
     * @return An ArrayList of Strings containing the names of books requested by the user with the ID
     * equal to the ID of the current object.
     */
    public ArrayList<Request> getRequestedListOfUser(ArrayList<Request> requests){
        ArrayList<Request> reqList = new ArrayList<Request>();
        for(Request req : requests){
            if(req.getUser().equals(this.id)){
                reqList.add(req);
            }
        }
        return reqList;
    }

    public boolean deleteRequest(ArrayList<Request> requests, Request reqToRemove){
        return requests.remove(reqToRemove);
    }

    public ArrayList<Book> getCheckedoutListOfUser(ArrayList<Book> books){
        ArrayList<Book> reqList = new ArrayList<Book>();
        for(Book book : books){
            if(book.getTakenOut() && book.getTakeOutBy().equals(this.id)){
                reqList.add(book);
            }
        }
        return reqList;
    }

    public static void deleteAssignments(ArrayList<Assignment> assignments, Assignment assignmentToRemove){
        assignments.remove(assignmentToRemove);
    }



    public String getSecretQuestion() {
        return this.secretQuestion;
    }
    public void setSecretQuestion(String secretQuestion) {
        this.secretQuestion = secretQuestion;
    }
    
    
    public String getSecretAns() {
        return this.secretAns;
    }
    public void setSecretAns(String secretAns) {
        this.secretAns = secretAns;
    }

    public Boolean isSecretAnsCOrrect(String attempt){
        return attempt.equals(this.secretAns);
    }

    /**
     * The function checks if a given password matches the stored password.
     * 
     * @param passwordCheck The parameter `passwordCheck` is a String that represents the password that
     * needs to be checked against the stored password. The method `checkPassword` returns a boolean
     * value indicating whether the `passwordCheck` matches the stored password or not.
     * @return A boolean value is being returned. The value will be true if the input parameter
     * `passwordCheck` is equal to the instance variable `password`, and false otherwise.
     * @throws NoSuchAlgorithmException
     */
    public boolean checkPassword(String passwordCheck) throws NoSuchAlgorithmException{
        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(this.salt.getBytes());
            byte[] bytes = md.digest(passwordCheck.getBytes());
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16)
                        .substring(1));
            }
            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return this.passwordHash.equals(generatedPassword);
    }

    /**
     * This function returns the salt value of an object.
     * 
     * @return The method `getSalt()` is returning a `String` which represents the salt value.
     */
    public String getSalt() {
        return this.salt;
    }
    /**
     * The function returns the value of the "id" variable.
     * 
     * @return The method `getId()` is returning an integer value which represents the `id` of an
     * object.
     */
    public String getId() {
        return this.id;
    }
    /**
     * This function sets the value of the "id" variable in a Java class.
     * 
     * @param id The parameter "id" is an integer that represents the unique identifier of an object.
     * The method "setId" sets the value of the "id" variable of an object to the specified integer
     * value.
     */
    public void setId(String id) {
        this.id = id;
    }
   /**
    * This function returns the account type of an object.
    * 
    * @return The method is returning the value of the instance variable `accountType`.
    */
    public String getAccountType() {
        return this.accountType;
    }
    /**
     * This function sets the account type for an object.
     * 
     * @param accountType The parameter `accountType` is a String variable that represents the type of
     * account. This method sets the value of the `accountType` instance variable to the value passed
     * as a parameter.
     */
    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    /**
     * This function returns the email of an object.
     * 
     * @return The method `getEmail()` is returning the email address as a `String`.
     */
    public String getEmail() {
    	return this.email;
    }
    /**
     * This function sets the email address for a given object.
     * 
     * @param email The parameter "email" is a String type variable that represents the email address
     * of a user. The method "setEmail" sets the value of the email address for an object instance of a
     * class.
     */
    public void setEmail(String email) {
    	this.email = email;
    }

    /**
     * The function returns the grade of an object.
     * 
     * @return The method `getGrade()` is returning an integer value which is the grade of the object
     * on which the method is called.
     */
    public int getGrade() {
    	return this.grade;
    }
    /**
     * This function sets the grade of an object.
     * 
     * @param grade The parameter "grade" is an integer value that represents the grade of a student.
     * This method sets the value of the "grade" instance variable to the value passed as an argument
     * to the method.
     */
    public void setGrade(int grade) {
    	this.grade = grade;
    }


    /**
     * The function returns the name of an object.
     * 
     * @return The method `getName()` is returning the value of the instance variable `name`.
     */
    public String getName() {
    	return this.name;
    }
    /**
     * This function sets the name of an object.
     * 
     * @param name The parameter "name" is a String type variable that represents the name of an object
     * or entity. The method "setName" takes a String argument and sets the value of the instance
     * variable "name" to the value of the argument passed to the method.
     */
    public void setName(String name) {
    	this.name = name;
    }


/**
 * The function generates a salt and hashes a given password using SHA-256 algorithm, returning an
 * array containing the hashed password and the raw salt.
 * 
 * @param passwordToHash The password that needs to be hashed.
 * @return An array of two strings, where the first string is the hashed password and the second string
 * is the raw salt used for hashing.
 */
    public static String[] passwordHash(String passwordToHash) throws NoSuchAlgorithmException {
        String rawsalt = getrawSalt();
        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(rawsalt.getBytes());
            byte[] bytes = md.digest(passwordToHash.getBytes());
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16)
                        .substring(1));
            }
            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        String[] hashSalt = {generatedPassword,rawsalt};
        return  hashSalt;
    }
    // Add salt

  /**
   * This function generates a random 16-byte salt using the SHA1PRNG algorithm.
   * 
   * @return The method is returning a string representation of the byte array `rawsalt`.
   */
    private static String getrawSalt() throws NoSuchAlgorithmException {
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        byte[] rawsalt = new byte[16];
        sr.nextBytes(rawsalt);
        return rawsalt.toString();
    }
    



}
