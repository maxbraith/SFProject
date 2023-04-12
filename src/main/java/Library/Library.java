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

    public static ArrayList<String[]> requestList;


    public static ArrayList<Book> books;
    public static ArrayList<Book> checkedOutBooksRequests;

    // public Library(){
    //     users = new HashMap<String,parentUser>();
    //     books = new ArrayList<Book>();
    //     checkedOutBooksRequests = new ArrayList<Book>();
    // librarian.checkoutbook(bookid, books) // 
    // }

    /**
     * The main function initializes variables and objects, calls the initializer function, and then
     * calls the deInitializer function.
     */
    public static void main(String[] args) throws CsvValidationException, IOException, NoSuchAlgorithmException {
        users = new HashMap<String,parentUser>();
        books = new ArrayList<Book>();

        checkedOutBooksRequests = new ArrayList<Book>();
        // System.out.println(parentUser.passwordHash("00")[0]);
        // System.out.println(parentUser.passwordHash("00")[1]);

        requestList = new ArrayList<String[]>();
        initializer();
        StartUI();
        deInitializer();
    }


    /*
     * This function to be used at the end of main when the app shuts down
     * reads the current book list to a text file to be used again 
     * when the app restarts
     */
    public static void // `writeBookstoTxt` is a method that writes the current list of books to a text
    // file to be used again when the application restarts. It first clears the
    // contents of the file, then writes each book's ID, title, author, ISBN,
    // whether it is taken out or not, and its return date (if applicable) to the
    // file.
    writeBookstoTxt() throws IOException{
        if(clearFile("src\\main\\java\\Library\\Data\\accounts.csv")){
            FileWriter myWriter = new FileWriter("src\\main\\java\\Library\\Data\\bookDataFiltered.csv");
            List<parentUser> allUsers = new ArrayList<parentUser>(users.values());
            // System.out.println(allUsers.size());
            for(Book book: books){

                myWriter.write(book.getId()+","+book.getTitle()+","+book.getAuthor()+","+book.getIsbn()+","+String.valueOf(book.getTakenOut())+","+book.getReturnDate().toString()+"\n");
            }
            myWriter.close();
        }

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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        while ((line = br.readLine()) != null){  
            String[] temp = line.split(splitBy);
            // String temp2 = "01/02/0001";
            // if(!temp[5].equals("null")){
            //     temp2 = temp[5];
            // }

            Book tempBook = new Book(temp[0],temp[1],temp[2],temp[3],Boolean.parseBoolean(temp[4]),LocalDate.parse(temp[5], formatter));
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
            // System.out.println(allUsers.size());
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
