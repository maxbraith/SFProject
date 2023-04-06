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
import java.util.UUID;

import com.opencsv.exceptions.CsvValidationException;
import Library.Book.Book;
import Library.Users.faculty;
import Library.Users.librarian;
import Library.Users.parentUser;
import Library.Users.student;

public class Library {
    public static Map<String,parentUser> users;
    public static ArrayList<Book> books;
    public static ArrayList<Book> checkedOutBooksRequests;

    // public Library(){
    //     users = new HashMap<String,parentUser>();
    //     books = new ArrayList<Book>();
    //     checkedOutBooksRequests = new ArrayList<Book>();
    // }

    public static void main(String[] args) throws CsvValidationException, IOException, NoSuchAlgorithmException {
        users = new HashMap<String,parentUser>();
        books = new ArrayList<Book>();
        checkedOutBooksRequests = new ArrayList<Book>();
        // System.out.println(parentUser.passwordHash("00")[0]);
        // System.out.println(parentUser.passwordHash("00")[1]);
        initalizer();
        // StartUI();
        deInitalizer();
    }


    /*
     * This function to be used at the end of main when the app shuts down
     * reads the current book list to a text file to be used again 
     * when the app restarts
     */
    public static void writeBookstoTxt() throws FileNotFoundException{
        // clearFile("src\\main\\java\\Library\\Data\\bookDataFiltered.csv");

    }
    /*
     * This function to be used at the beginning of main
     * reads the old book list from the text file 
     * and enters it into our bookList
     */
    public static void readBooksFromTxt() throws IOException{

        String line = "";  
        String splitBy = ",";  
        BufferedReader br = new BufferedReader(new FileReader("src\\main\\java\\Library\\Data\\bookDataFiltered.csv")); 
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        while ((line = br.readLine()) != null){  
            String[] temp = line.split(splitBy);
            String temp2 = "01/01/0001";
            if(!temp[5].equals("null")){
                temp2 = temp[5];
            }

            Book tempBook = new Book(temp[0],temp[1],temp[2],temp[3],Boolean.parseBoolean(temp[4]),LocalDate.parse(temp2, formatter));
            books.add(tempBook);
        }  


    }

        /*
     * This function to be used at the end of main when the app shuts down
     * reads the current book list to a text file to be used again 
     * when the app restarts
     */
    public static void writeUserstoTxt() throws IOException{
        if(clearFile("src\\main\\java\\Library\\Data\\accounts.csv")){
            FileWriter myWriter = new FileWriter("src\\main\\java\\Library\\Data\\accounts.csv");
            List<parentUser> allUsers = new ArrayList<parentUser>(users.values());
            System.out.println(allUsers.size());
            for(parentUser user: allUsers){

                myWriter.write(user.getId()+","+user.getEmail()+","+user.getPasswordHash()+","+String. valueOf(user.getGrade())+","+user.getName()+","+user.getAccountType()+","+user.getSalt()+"\n");
            }
            myWriter.close();
        }
    }
    /*
     * This function to be used at the beginning of main
     * reads the old book list from the text file 
     * and enters it into our bookList
     */
    public static void readUsersFromTxt() throws IOException, NoSuchAlgorithmException{

        String line = "";  
        String splitBy = ",";  
        BufferedReader br = new BufferedReader(new FileReader("src\\main\\java\\Library\\Data\\accounts.csv")); 
        while ((line = br.readLine()) != null){  
            String[] temp = line.split(splitBy);
            parentUser currAcc =new parentUser();
            // String uuid = UUID.randomUUID().toString();
            // String[] tempHash = parentUser.passwordHash(temp[2]);
            if(temp[5].equals("student")){
                currAcc = new student(temp[0],temp[1],temp[2],Integer.parseInt( temp[3]),temp[4],temp[5],temp[6]);
            }
            if(temp[5].equals("faculty")){
                currAcc = new faculty(temp[0],temp[1],temp[2],Integer.parseInt( temp[3]),temp[4],temp[5],temp[6]);
            }
            if(temp[5].equals("librarian")){
                currAcc = new librarian(temp[0],temp[1],temp[2],Integer.parseInt( temp[3]),temp[4],temp[5],temp[6]);
            }
            
            users.put(currAcc.getEmail(),currAcc);
        }  
    }


    private static boolean clearFile(String path) throws FileNotFoundException{
        PrintWriter writer = new PrintWriter(path);
        writer.print("");
        writer.close();
        return true;
    }



    /**
     * @throws IOException
     * @throws CsvValidationException
     * @throws NoSuchAlgorithmException
     * @post intializes all data from the CSV files to the data structures
     */
    public static void initalizer() throws IOException, CsvValidationException, NoSuchAlgorithmException{
        
        readBooksFromTxt();
        readUsersFromTxt();
    }

    /**
     * @throws IOException
     * @throws CsvValidationException
     * @post writes all the data from the data structures to the CSV files
     */
    public static void deInitalizer() throws IOException, CsvValidationException{
        
        writeBookstoTxt();
        writeUserstoTxt();
    }

    /**
     * @throws NoSuchAlgorithmException
     * @post Start the CLI UI loops
     */
    private static void StartUI() throws NoSuchAlgorithmException{
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
                    break;
            }
            System.out.println("********************************");
        }
    }

    private static void logIn(Scanner sc) throws NoSuchAlgorithmException{
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
    private static void mainMenu(Scanner sc, parentUser currentuser){
        boolean run = true;

        while(run){
            System.out.println("********************************");
            System.out.println("Welcome "+currentuser.getName()+"!");
            System.out.println("Please select an option to continue:");
            // System.out.println("(1) Login");
            // System.out.println("(2) Forgot Password");
            System.out.println("(3) Exit");
            System.out.println("Enter Number:");
            int input = sc.nextInt();

            switch(input){
                case 3 :
                    run = false;
                    break;
            }
            System.out.println("********************************");
        }
    }



}
