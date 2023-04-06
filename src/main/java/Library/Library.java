package Library;


import java.io.BufferedReader;
import java.io.FileNotFoundException;

import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

import java.util.List;

import java.util.LinkedList;


import java.util.Map;
import java.util.Scanner;

import com.opencsv.CSVReaderHeaderAware;
import com.opencsv.exceptions.CsvValidationException;

import Library.Book.Book;
import Library.Users.faculty;
import Library.Users.parentUser;


public class Library {

    public HashMap<String, parentUser> accounts;
    public static HashMap<Integer, Book> books;
    public static HashMap<Integer, Book> checkedOutBooks;
    public static HashMap<Integer, Integer> requestList;

    public Library(){
        accounts = new HashMap<String, parentUser>();
        books = new HashMap<Integer, Book>();
        checkedOutBooks = new HashMap<Integer, Book>();
        requestList = new HashMap<Integer, Integer>();

    public static HashMap<String,parentUser> users;
    public static ArrayList<Book> books;
    public static ArrayList<Book> checkedOutBooksRequests;

    // public Library(){
    //     users = new HashMap<String,parentUser>();
    //     books = new ArrayList<Book>();
    //     checkedOutBooksRequests = new ArrayList<Book>();
    // }

    public static void main(String[] args) throws CsvValidationException, IOException {
        users = new HashMap<String,parentUser>();
        books = new ArrayList<Book>();
        checkedOutBooksRequests = new ArrayList<Book>();
        initalizer();
        StartUI();


    }


    // /*
    //  * Function that reads in books to our book list
    //  */
    // public void firstPopulateBooks() throws CsvValidationException, IOException{
    //     FileReader file = new FileReader("books_1.Best_Books_Ever.csv");
    //     CSVReaderHeaderAware reader = new CSVReaderHeaderAware(file);
    //     for(int i=0; i<10; i++){
    //         Map<String, String> values = reader.readMap();
    //         System.out.println(values.get("title"));
    //         int id = i;
    //         String title = values.get("title");
    //         String author = values.get("author");
    //         String x = values.get("isbn");
    //         Long isbn = Long.parseLong(x);
    //         boolean takenOut = false;
    //         LocalDate returnDate = LocalDate.of(2023, 4, 1);;
    //         Book myBook = new Book(id, title, author, isbn, takenOut, returnDate);
    //         books.add(myBook);
    //     }
    // }

    /*
     * This function to be used at the end of main when the app shuts down
     * reads the current book list to a text file to be used again 
     * when the app restarts
     */

    public boolean logIn(String email, String password){
        parentUser account = accounts.get(email);
        if(account != null){
            if(account.checkPassword(password)==true){
                return true;
            }
        }
        return false;


        

    public static void writeBookstoTxt(){

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




    /**
     * @throws IOException
     * @throws CsvValidationException
     * @post intializes all data from the CSV files to the data structures
     */
    public static void initalizer() throws IOException, CsvValidationException{
        
        readBooksFromTxt();

    }


    /**
     * @throws IOException
     * @throws CsvValidationException
     * @post writes all the data from the data structures to the CSV files
     */
    public void initalizer() throws IOException, CsvValidationException{
        FileReader file = new FileReader("books_1.Best_Books_Ever.csv");
        CSVReaderHeaderAware reader = new CSVReaderHeaderAware(file);
        for(int i=0; i<10; i++){
            Map<String, String> values = reader.readMap();
            System.out.println(values.get("title"));
            int id = i;
            String title = values.get("title");
            String author = values.get("author");
            String x = values.get("isbn");
            Long isbn = Long.parseLong(x);
            boolean takenOu = false;
            LocalDate returnDate = LocalDate.of(2023, 4, 1);;
            Book myBook = new Book(id, title, author, isbn, takenOu, returnDate);
            books.put(myBook.getId(), myBook);
    public static void deInitalizer() throws IOException, CsvValidationException{
        
        writeBookstoTxt();
    }

    /**
     * @post Start the CLI UI loops
     */
    private static void StartUI(){
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

    private static void logIn(Scanner sc){
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
                
            }


            System.out.println("********************************");
        }
    }



}
