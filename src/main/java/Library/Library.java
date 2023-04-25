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
        StartUI();
        deInitializer();
    }


    public static void writeData() throws IOException{
        // write all books 
        JsonUtil.toJsonFile("src\\main\\java\\Library\\Data\\books.json", books);

        //write all users
        List<parentUser> allUsers = new ArrayList<parentUser>(users.values());
        JsonUtil.toJsonFile("src\\main\\java\\Library\\Data\\usersData.json", allUsers);

        // write all requests 
        JsonUtil.toJsonFile("src\\main\\java\\Library\\Data\\requests.json", requests);

        // write all assignments 
        JsonUtil.toJsonFile("src\\main\\java\\Library\\Data\\assignments.json", assignments);
        
    }

    public static void readData() throws IOException{
        // read books
        books=(ArrayList<Book>) JsonUtil.listFromJsonFile("src\\main\\java\\Library\\Data\\books.json",Book.class);

        // read users
        ArrayList<parentUser> tempUsers=(ArrayList<parentUser>) JsonUtil.listFromJsonFile("src\\main\\java\\Library\\Data\\usersData.json",parentUser.class);
        for(parentUser user : tempUsers){
            users.put(user.getEmail(), user);
        }

        // read requests
        requests=(ArrayList<Request>) JsonUtil.listFromJsonFile("src\\main\\java\\Library\\Data\\requests.json",Request.class);

        // read requests
        assignments=(ArrayList<Assignment>) JsonUtil.listFromJsonFile("src\\main\\java\\Library\\Data\\assignments.json",Assignment.class);
    }

/**




    /**
     * @throws IOException
     * @throws CsvValidationException
     * @throws NoSuchAlgorithmException
     * @post intializes all data from the CSV files to the data structures
     */
    public static void initializer() throws IOException, CsvValidationException, NoSuchAlgorithmException{
        
        readData();
    }

    /**
     * @throws IOException
     * @throws CsvValidationException
     * @post writes all the data from the data structures to the CSV files
     */
    public static void deInitializer() throws IOException, CsvValidationException{
        
        writeData();
    }


    public static void refreshData() throws IOException, NoSuchAlgorithmException, CsvValidationException{
        initializer();
        deInitializer();
    }

    /**
     * @throws NoSuchAlgorithmException
     * @throws IOException
     * @throws CsvValidationException
     * @post Start the CLI UI loops
     */
    private static void StartUI() throws NoSuchAlgorithmException, IOException, CsvValidationException{
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
                    accountRecovery(sc);
                    break;
                case 3 :
                    run = false;
                    System.out.println("Closing application...");
                    break;
            }
            System.out.println("********************************");
        }
    }

    private static void accountRecovery(Scanner sc) throws NoSuchAlgorithmException{

        boolean run = true;
        while(run){
            System.out.println("********************************");
            System.out.println("Forgot your password, No problem!");
            System.out.println("Enter \"back\" to go back at any point.");
            System.out.println("Enter email:");
            String email = sc.next();
            if(email.equals("back")){run = false; break;}
            parentUser curUser = users.get(email);
            if(curUser == null){
                System.out.println("No user found with email: "+email);
                System.out.println("Please contact your librarian for assistance!");
                break;
            }
            System.out.println(curUser.getSecretQuestion());
            System.out.println("Enter answer:");
            String ans = sc.next();
            if(email.equals("back")){run = false; break;}

            if(curUser.isSecretAnsCOrrect(ans)){
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
                curUser.setHashpassword(password);
                System.out.println("Password has been reset, please try loging in!");
            }else{
                System.out.println("Your entered answer is incorrect!");
            }
            break;


        }

    }

    private static void logIn(Scanner sc) throws NoSuchAlgorithmException, IOException, CsvValidationException{
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
    private static void mainMenu(Scanner sc, parentUser currentuser) throws NoSuchAlgorithmException, IOException, CsvValidationException{
        boolean run = true;

        while(run){
            System.out.println("********************************");
            System.out.println("Welcome "+currentuser.getName()+"!");
            // student main menu
            System.out.println("Please select an option to continue:");
            if(currentuser.getAccountType().equals("student")){
                System.out.println("(1) Search Book (to request book or to just browse)");
                System.out.println("(2) View assigned books");
                System.out.println("(3) View requested books");
                System.out.println("(4) View checked out books");
                System.out.println("(5) Exit");
                System.out.println("Enter Number:");
                int input = sc.nextInt();

                switch(input){

                    case 1:
                        searchBook(currentuser, sc);
                        break;

                    case 2:
                        showAssignedBooks(currentuser);
                        break;

                    case 3:
                        showRequestedBooks(currentuser, sc);
                        break;

                    case 4:
                        showCheckedoutBooks(currentuser);
                        break;
                    
                    case 5 :
                        run = false;
                        System.out.println("Logging out...");
                        break;
                }

            }
            // faculty main menu 
            if(currentuser.getAccountType().equals("faculty")){
                System.out.println("(1) Search Book (to request book or to assign book or to just browse)");
                System.out.println("(2) View All assignments");
                System.out.println("(3) View requested books");
                System.out.println("(4) View checked out books");
                System.out.println("(5) Exit");
                System.out.println("Enter Number:");
                int input = sc.nextInt();

                switch(input){
                    case 1 :
                        searchBook(currentuser, sc);
                        break;

                    case 2:
                        facultyAssignmentView(currentuser, sc);
                        break;

                    case 3:
                        showRequestedBooks(currentuser, sc);
                        break;

                    case 4:
                        showCheckedoutBooks(currentuser);
                        break;

                    case 5 :
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
                System.out.println("(6) View all checkout books");
                System.out.println("(7) View all assignments");
                System.out.println("(8) View requested books");
                System.out.println("(9) View checked out books for returning");
                
                System.out.println("(10) Exit");
                System.out.println("Enter Number:");
                int input = sc.nextInt();

                switch(input){
                    case 1:
                        addBook(currentuser,sc);
                        break;

                    case 2: 
                        librarian_addAccount(sc);
                        break;
                    case 4:
                        deleteAccount(sc);
                        break;
                    case 8:
                        

                        break;
                    case 9 :
                        viewCheckoutBooks(currentuser,sc);
                        break;
                    case 10 :
                        run = false;
                        System.out.println("Logging out...");
                        break;
                }

            }
            System.out.println("********************************");
        }
    }

    



    



    


    // all account options
    private static void searchBook(parentUser currentuser, Scanner sc) {
        boolean run = true;
        while(run){
            System.out.println("********************************");
            System.out.println("Search Books");
            System.out.println("Select how you want to search books");
            System.out.println("Search by");
            System.out.println("(1) Title");
            System.out.println("(2) Author");
            System.out.println("(3) ISBN");
            System.out.println("(4) Go Back");
            System.out.println("Enter your selection:");
            int selection = sc.nextInt();
            if(selection == 4){break;}
            String selectionString="";
            if(selection == 3){selectionString ="ISBN"; }
            if(selection == 2){selectionString ="Author"; }
            if(selection == 1){selectionString ="Title"; }

            System.out.println("Enter "+selectionString+" :");
            Scanner specialSC = new Scanner(System.in);
            String searchByInfo = specialSC.nextLine();
            
            ArrayList<Book> curBookSelections = new ArrayList<Book>();
            for(Book book: books){
                if(selection == 3 && book.getIsbn().contains(searchByInfo)){curBookSelections.add(book); }
                if(selection == 2 && book.getAuthor().contains(searchByInfo)){curBookSelections.add(book); }
                if(selection == 1 && book.getTitle().contains(searchByInfo)){curBookSelections.add(book); }
            }
            boolean run2 = true;
            while(run2){
                System.out.println("********************************");
                System.out.println("Ind#\t Avail\t\tTitle ");
                int count = 0;
                for(Book book : curBookSelections){
                    String temp = "";
                    if(book.getTakenOut()){
                        temp = "No";
                    }else{
                        temp ="Yes";
                    }
                    System.out.println("("+String.valueOf(++count)+")\t "+ temp+"\t\t"+book.getTitle());
                }
                System.out.println("********************************");
                System.out.println("Enter \"back\" to go back at any point.");
                System.out.println("Enter a book index number to select a book:");
                String bookInd = sc.next();
                int bookIndInt = 0;
                if(bookInd.equals("back")){run2=false;run=false; break;}
                else{
                    try{
                        bookIndInt = Integer.parseInt(bookInd);
                    }catch(Exception e){
                        System.out.println("Could not parser the book index number!");
                        continue;
                    }
                }
                if(bookIndInt == 0){
                    System.out.println("Index number out of range!");
                    continue;
                }
                // student options
                if(currentuser.getAccountType().equals("student")){
                    System.out.println("********************************");
                    System.out.println("Select an action");
                    System.out.println("(1) Request a book");
                    System.out.println("(2) Go Back");
                    System.out.println("Enter Selection:");
                    int actionSelections = sc.nextInt();
                    
                    switch(actionSelections){
                        case 1 :
                            currentuser.requestBook(curBookSelections.get(bookIndInt-1),requests);
                            System.out.println("Book Requested!");
                            run = false;
                            run2 =  false;
                            break;
                        case 2: 
                            System.out.print("Going back....");
                            run = false;
                            run2 =  false;
                            break;
                    }
                }  

                // faculty options
                if(currentuser.getAccountType().equals("faculty")){
                    System.out.println("********************************");
                    System.out.println("Select an action");
                    System.out.println("(1) Request a book");
                    System.out.println("(2) Assign book to student");
                    System.out.println("(3) Assign book to grade");
                    System.out.println("(4) Go Back");
                    System.out.println("Enter Selection:");
                    int actionSelections = sc.nextInt();
                    
                    switch(actionSelections){
                        case 1 :
                            currentuser.requestBook(curBookSelections.get(bookIndInt-1),requests);
                            System.out.println("Book Requested!");
                            run = false;
                            run2 =  false;
                            break;

                        case 2:
                            System.out.println("Enter \"back\" to go back.");
                            System.out.println("Enter the email of the student:");
                            String email = sc.next();
                            if(email.equals("back")){
                                run = false;
                                run2 =  false;
                                break;}
                            parentUser studentUser = users.get(email);
                            if(studentUser == null){
                                System.out.println("Student account not found!");
                            }else{
                                boolean status = faculty.assignBooks(currentuser, studentUser, assignments, curBookSelections.get(bookIndInt-1), curBookSelections);
                                if(status){
                                    System.out.println("("+curBookSelections.get(bookIndInt-1).getTitle()+") Has been assigned to "+ studentUser.getName());
                                }else{
                                    System.out.println("Book it not available to request!");
                                    System.out.println("Try again later...");
                                }
                            }
                            break;
                        case 3:
                            System.out.println("Enter \"back\" to go back.");
                            System.out.println("Enter the grade:");
                            String grade = sc.next();

                            boolean checkGradeExists = true;
                            for(parentUser temp : users.values()){
                                if(String.valueOf(temp.getGrade()).equals(grade)){
                                    checkGradeExists=false;
                                }
                            }
                            System.out.println("Enter the number of copies to be assigned to the grade!");
                            int NOcopies= sc.nextInt();
                            if( checkGradeExists){
                                System.out.println("Student accounts with the grade ("+grade+") was not found!");
                            }else{
                                boolean status = faculty.assignBooks(currentuser,grade, assignments, curBookSelections.get(bookIndInt-1), NOcopies,curBookSelections);
                                if(status){
                                    System.out.println("("+curBookSelections.get(bookIndInt-1).getTitle()+") Has been assigned to grade: "+ grade);
                                }else{
                                    System.out.println("Book it not available to request!");
                                    System.out.println("Try again later...");
                                }
                            }
                            break;


                        case 4: 
                            System.out.print("Going back....");
                            run = false;
                            run2 =  false;
                            break;
                    }
                }  


            }

        }
    }


    // show requested books 
    public static void showRequestedBooks(parentUser user, Scanner sc){
        ArrayList<Request> userRequests = user.getRequestedListOfUser(requests);
        if(userRequests.size()<=0){
            System.out.println("Your request list is empty!");

        }else{
            int count =1;
            for(Request req :userRequests){
                System.out.println("("+String.valueOf(count++) +") "+req.getBookName());
            }
        }

        while(true){
            System.out.println("Enter \"back\" to go back at any point.");
            System.out.println("Enter the index number of the request to delete:");
            String index = sc.next();
            if(index.equals("back")){break;}
            user.deleteRequest(userRequests, userRequests.get(Integer.parseInt(index)));
            System.out.println("Request deleted successfully!");
        }
    }

    // show checkedout books
    public static void showCheckedoutBooks(parentUser user){
        ArrayList<Book> userCheckedOut = user.getCheckedoutListOfUser(books);
        if(userCheckedOut.size()<=0){
            System.out.println("Your request list is empty!");

        }else{
            int count =1;
            for(Book curBook :userCheckedOut){
                System.out.println("("+String.valueOf(count++) +") "+curBook.getTitle());
            }
        }
    }


    // faculty options
    // faculty Assignment View
    public static void facultyAssignmentView(parentUser curuser, Scanner sc){
        ArrayList<Assignment> allAssignments = faculty.viewAllAssignments(assignments, curuser);
        System.out.println("All assignments made by you:");

        if(allAssignments.size()<=0){
            System.out.println("Your request list is empty!");

        }else{
            int count =1;
            for(Assignment assign :allAssignments){
                    // get the book
                    Book tempBook = new Book();
                    for(Book temp : books){
                        if(temp.getId().equals(assign.getBook())){
                            tempBook = temp;
                        }
                    }


                if(assign.isAssignedToStudent()){
                    System.out.println("("+String.valueOf(count++) +")\t "+assign.getAssignedTo()+"\t"+ tempBook.getTitle() +"\t x"+assign.getNoCopies()); 
                }else{
                    System.out.println("("+String.valueOf(count++) +")\t Grade:"+assign.getAssignedTo()+"\t"+ tempBook.getTitle() +"\t x"+assign.getNoCopies()); 
                }
            }
            while(true){
                System.out.println("Enter \"back\" to go back at any point.");
                System.out.println("Enter the index number of the assignment to delete:");
                String index = sc.next();
                if(index.equals("back")){break;}
                faculty.deleteAssignments(assignments, allAssignments.get(Integer.valueOf(index)-1));
                System.out.println("Assignment deleted successfully!");
            }   
        }
    }

    // student options
    public static void showAssignedBooks(parentUser user){
  
        ArrayList<String> userRequests = student.getAssignedBooks(assignments, user);
        if(userRequests.size()<=0){
            System.out.println("Your request list is empty!");

        }else{
            int count =1;
            for(String req :userRequests){
                System.out.println("("+String.valueOf(count++) +") "+req);
            }
        }

    }


    // librarian options

    // view request books to approve checkout
    private static void viewRequestedBooks(parentUser currentuser, Scanner sc){

        System.out.println("Requests");
        System.out.println("Ind#\t Requested By\t Book Title");
        int count=0;
        for(Request req : requests){
            parentUser tempUser = new parentUser();
            for(parentUser user : users.values()){
                if(user.getId().equals(req.getUser())){
                    tempUser = user;
                }
            }
            System.out.println("("+String.valueOf(++count)+")\t"+ tempUser.getName()+"\t"+req.getBookName());
        }
        while(true){
            System.out.println("Enter \"back\" to go back at any point.");
            System.out.println("Enter the index number of the request to approve checkout:");
            String index = sc.next();
            if(index.equals("back")){break;}
            
            
            System.out.println("Book Returned successfully!");
            break;
        }  


    }
    
    //view Checkout Books to return book
    private static void viewCheckoutBooks(parentUser currentuser, Scanner sc) {
        ArrayList<Book> allCheckedoutBooks = librarian.viewAllCheckedoutBooks(books);
        int counter =0;
        System.out.println("Ind#\t taken by\t Book Title");
        for(Book book : allCheckedoutBooks){
            parentUser tempUser = new parentUser();
            for(parentUser user : users.values()){
                if(user.getId().equals(book.getTakeOutBy())){
                    tempUser = user;
                }
            }
            System.out.println("("+String.valueOf(++counter)+")\t"+ tempUser.getName()+"\t"+book.getTitle());
        }
        while(true){
            System.out.println("Enter \"back\" to go back at any point.");
            System.out.println("Enter the index number of the book to return:");
            String index = sc.next();
            if(index.equals("back")){break;}
            librarian.bookReturn(books, allCheckedoutBooks.get(Integer.valueOf(index)-1));
            System.out.println("Book Returned successfully!");
            break;
        }  

    }

    // delete account 
    private static void deleteAccount(Scanner sc){
        boolean run = true;
        while(run){
            System.out.println("********************************");
            System.out.println("Delete account");
            System.out.println("Enter \"back\" to go back at any point.");

            System.out.println("Enter email of the account to be deleted:");
            String email = sc.next();
            if(email.equals("back")){ break;}
            parentUser curAccount = users.get(email);
            if(curAccount == null){
                System.out.println("Account not Found!");
                break;
            }else{
                System.out.println(curAccount.toString());
                System.out.println("Do you want to delete this account?");
                System.out.println("Enter \"yes\" to continue:");
                String confirmation = sc.next();
                if(confirmation.equals("back")){ break;}
                if(confirmation.equals("yes")){
                    librarian.deleteAccount(users,curAccount);
                    System.out.println("Account is deleted!");   
                }else{
                    System.out.println("Confirmation was denied.");
                    System.out.println("Account has NOT been deleted!.");
                }
            }

        }
    }

    // add book

    private static void addBook(parentUser currentuser, Scanner sc) {
        System.out.println("********************************");
        System.out.println("Add Book");
        System.out.println("Enter \"back\" to go back at any point.");
        System.out.println("Enter the details of the book to add book:");
        while(true){
            System.out.println("Enter Title:");
            String title = sc.next();
            if(title.equals("back")){ break;}

            System.out.println("Enter Author:");
            String author = sc.next();
            if(author.equals("back")){ break;}
            
            System.out.println("Enter ISBN:");
            String isbn = sc.next();
            if(isbn.equals("back")){break;}
            librarian.addBook(books, title, author, isbn);
            break;

        }
    }

    // add account 
    private static void librarian_addAccount(Scanner sc) throws NoSuchAlgorithmException, IOException, CsvValidationException{
        boolean run = true;

        while(run){
            System.out.println("********************************");
            System.out.println("Add Account");
            System.out.println("Please enter all the details.");
            System.out.println("Enter \"back\" to go back at any point.");

            System.out.println("Enter Email:");
            String email = sc.next();
            if(email.equals("back")){run = false; break;}

            if(users.containsKey(email)){
                System.out.println("User with email - "+email+" already exists!");
                break;
            }

            System.out.println("Enter First name:");
            String name1 = sc.next();
            if(name1.equals("back")){run = false; break;}
            System.out.println("Enter Last name:");
            String name2 = sc.next();
            if(name2.equals("back")){run = false; break;}
            String name = name1 +" "+ name2;

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
            System.out.println("Enter a Secret Question for account recovery:");
            String secretQuestion = sc.next();
            if(secretQuestion.equals("back")){run = false; break;}

            System.out.println("Enter a Secret Answer to the question you entered earlier:");
            String secretAns = sc.next();
            if(secretAns.equals("back")){run = false; break;}

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
                parentUser newAcc =  librarian.makeAccount(email, password, grade,"student", name,secretQuestion,secretAns);
                users.put(email, newAcc);
                run = false;
                refreshData();
                System.out.println("Account Created!");
                break;
            }
            if(accTypSelection == 2){
                parentUser newAcc =  librarian.makeAccount(email, password, grade,"faculty", name,secretQuestion,secretAns);
                users.put(email, newAcc);
                run = false;
                refreshData();
                System.out.println("Account Created!");
                break;
            }
            if(accTypSelection == 3){
                parentUser newAcc =  librarian.makeAccount(email, password, grade,"librarian", name,secretQuestion,secretAns);
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
