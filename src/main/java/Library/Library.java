package Library;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.text.NumberFormat.Style;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.UUID;

import com.opencsv.exceptions.CsvValidationException;
import Library.Book.Book;
import Library.Book.Request;
import Library.JsonUtil.JsonUtil;
import Library.Users.faculty;
import Library.Users.librarian;
import Library.Users.parentUser;
import Library.Users.student;
import Library.Book.Assignment;


public class Library {

    public static Map<String,parentUser> users;
    public static ArrayList<Book> books;
    public static ArrayList<Request> requests;
    public static ArrayList<Assignment> assignments;


    /**
     * The main function initializes variables and objects, calls the initializer function, and then
     * calls the deInitializer function.
     */
    public static void main(String[] args) throws CsvValidationException, IOException, NoSuchAlgorithmException {
        users = new HashMap<String,parentUser>();
        books = new ArrayList<Book>();
        requests = new ArrayList<Request>();
        assignments = new ArrayList<Assignment>();


        initializer();
        // StartUI();
        deInitializer();
    }



/**
 * This function writes the contents of a JSON file containing book data to a text file.
 */
    public static void 
    writeBookstoTxt() throws IOException{        
        JsonUtil.toJsonFile("src\\main\\java\\Library\\Data\\books.json", books);
    }

/**
 * This function reads a list of books from a JSON file using a JsonUtil class.
 */
    public static void readBooksFromTxt() throws IOException{
        books=(ArrayList<Book>) JsonUtil.listFromJsonFile("src\\main\\java\\Library\\Data\\books.json",Book.class);
    }


/**
 * This function writes a list of parentUser objects to a JSON file.
 */
    public static void writeUserstoTxt() throws IOException{
        List<parentUser> allUsers = new ArrayList<parentUser>(users.values());
        JsonUtil.toJsonFile("src\\main\\java\\Library\\Data\\usersData.json", allUsers);
    }

    /**
     * This function reads user data from a JSON file and populates a HashMap with the user objects.
     */
    public static void readUsersFromTxt() throws IOException, NoSuchAlgorithmException{

        ArrayList<parentUser> tempUsers=(ArrayList<parentUser>) JsonUtil.listFromJsonFile("src\\main\\java\\Library\\Data\\usersData.json",parentUser.class);
        for(parentUser user : tempUsers){
            System.out.println(user.getAccountType());
            if(user.getAccountType().equals("student")){
                student temp =  user.toStudent(user);
                users.put(temp.getEmail(), temp);
            }
            if(user.getAccountType().equals("faculty")){
                faculty temp = user.toFaculty(user);
                users.put(temp.getEmail(), temp);
            }
            if(user.getAccountType().equals("librarian")){
                librarian temp = user.toLibrarian(user);
                users.put(temp.getEmail(), temp);
            }
        }
    }



    /**
     * @throws IOException
     * @throws CsvValidationException
     * @throws NoSuchAlgorithmException
     * @post intializes all data from the CSV files to the data structures
     */
    public static void initializer() throws IOException, CsvValidationException, NoSuchAlgorithmException{
        
        readBooksFromTxt();
        readUsersFromTxt();
    }

    /**
     * @throws IOException
     * @throws CsvValidationException
     * @post writes all the data from the data structures to the CSV files
     */
    public static void deInitializer() throws IOException, CsvValidationException{
        
        writeBookstoTxt();
        writeUserstoTxt();
    }


    public static void refreshData() throws IOException, NoSuchAlgorithmException{
        writeBookstoTxt();
        writeUserstoTxt();
        readBooksFromTxt();
        readUsersFromTxt();
    }

    /**
     * @throws NoSuchAlgorithmException
     * @throws IOException
     * @post Start the CLI UI loops
     */
    private static void StartUI() throws NoSuchAlgorithmException, IOException{
        Scanner sc = new Scanner(System.in);
        boolean run = true;

        while(run){
            System.out.println("********************************");
            System.out.println("Welcome to the Library System!");
            System.out.println("Please select an option to continue:");
            System.out.println("(1) Login");
            System.out.println("(2) Forgot Password");
            System.out.println("(3) Exit");
            System.out.println("Enter Number:");
            int input = sc.nextInt();

            switch(input){
                case 1 :
                    logIn(sc);
                    break;
                case 2 :
                    System.out.println("********************************");
                    System.out.println("feature work in progress!");
                    System.out.println("********************************");
                    break;
                case 3 :
                    run = false;
                    System.out.println("Closing application...");
                    break;
            }
            System.out.println("********************************");
        }
    }

    private static void logIn(Scanner sc) throws NoSuchAlgorithmException, IOException{
        boolean run = true;

        while(run){
            System.out.println("********************************");
            System.out.println("Login");
            System.out.println("Enter Email to continue or type \"Back\" to go back:");
            String input = sc.next();

            if(input.equals("back")){
                run = false;
                break;
            }
            else{
                parentUser currentUser =  users.get(input);
                if(currentUser == null){
                    System.out.println("Sorry!");
                    System.out.println("Could not find an account with the email:"+ input);
                    System.out.println("Please try again and if this persists please contact the Librarian.");
                }else{
                    System.out.println("Enter your Password:");
                    String input2 = sc.next();
                    if(currentUser.checkPassword(input2)){
                        mainMenu(sc,currentUser);
                    }
                    run = false;
                    break;

                }
            }


            System.out.println("********************************");
        }
    }



    // Main menu
    private static void mainMenu(Scanner sc, parentUser currentuser) throws NoSuchAlgorithmException, IOException{
        boolean run = true;

        while(run){
            System.out.println("********************************");
            System.out.println("Welcome "+currentuser.getName()+"!");
            // student main menu
            System.out.println("Please select an option to continue:");
            if(currentuser.getAccountType().equals("student")){
                System.out.println("(1) Search Book");
                System.out.println("(2) see assigned Books");
                System.out.println("(3) Exit");
                System.out.println("Enter Number:");
                int input = sc.nextInt();

                switch(input){
                    case 3 :
                        run = false;
                        System.out.println("Logging out...");
                        break;
                }

            }
            // faculty main menu 
            if(currentuser.getAccountType().equals("faculty")){
                System.out.println("(1) Search Book");
                System.out.println("(2) See assigned Books");
                System.out.println("(3) Exit");
                System.out.println("Enter Number:");
                int input = sc.nextInt();

                switch(input){
                    case 3 :
                        run = false;
                        System.out.println("Logging out...");
                        break;
                }   

            }
            // librarian main menu
            if(currentuser.getAccountType().equals("librarian")){
                currentuser = (librarian)currentuser;
                System.out.println("(1) Add Book");
                System.out.println("(2) Add account");
                System.out.println("(3) Delete Book");
                System.out.println("(4) Delete account");
                System.out.println("(5) Approve book checkout request");
                System.out.println("(6) Checkout book");
                System.out.println("(7) Exit");
                System.out.println("Enter Number:");
                int input = sc.nextInt();

                switch(input){

                    case 2: librarian_addAccount(sc);
                            break;

                    case 7 :
                        run = false;
                        System.out.println("Logging out...");
                        break;
                }

            }
            System.out.println("********************************");
        }
    }



    // librarian options

    // add account 
    private static void librarian_addAccount(Scanner sc) throws NoSuchAlgorithmException, IOException{
        boolean run = true;

        while(run){
            System.out.println("********************************");
            System.out.println("Add Account");
            System.out.println("Please enter all the details.");
            System.out.println("Enter \"back\" to go back at any point.");

            System.out.println("Enter Email:");
            String email = sc.next();
            if(email.equals("back")){run = false; break;}

            System.out.println("Enter Name:");
            String name = sc.next();
            if(name.equals("back")){run = false; break;}

            boolean passwordLoop = true;
            String password="";
            String password2="";
            while(passwordLoop){
                System.out.println("Enter password:");
                password = sc.next();
                if(password.equals("back")){run = false;passwordLoop=false; break;}
                System.out.println("Re-Enter password:");
                password2 = sc.next();
                if(password2.equals("back")){run = false;passwordLoop=false; break;}
                if(password.equals(password2)){passwordLoop=false; break;
                }else{System.out.println("Password do not match!\nTry again!");}
            }

            System.out.println("Select an account type:");
            System.out.println("(1) Student");
            System.out.println("(2) Faculty");
            System.out.println("(3) Librarian");
            int accTypSelection = sc.nextInt();
            int grade = 0;
            if(accTypSelection == 1){
                System.out.println("Enter the grade of the student:");
                grade = sc.nextInt();
            }

            if(accTypSelection == 1){
                parentUser newAcc =  librarian.makeAccount(email, password, grade,"student", name);
                users.put(email, newAcc);
                run = false;
                refreshData();
                System.out.println("Account Created!");
                break;
            }
            if(accTypSelection == 2){
                parentUser newAcc =  librarian.makeAccount(email, password, grade,"faculty", name);
                users.put(email, newAcc);
                run = false;
                refreshData();
                System.out.println("Account Created!");
                break;
            }
            if(accTypSelection == 3){
                parentUser newAcc =  librarian.makeAccount(email, password, grade,"librarian", name);
                users.put(email, newAcc);
                run = false;
                refreshData();
                System.out.println("Account Created!");
                break;
            }

            System.out.println("********************************");
        }
    }



}
