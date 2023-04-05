package Library.Data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

// import Library.Book.Book;

public class getAllBooked {
    
    public static void main(String[] args) throws IOException {
        
        // List<Book> Books = new LinkedList<Book>();

        FileWriter myWriter = new FileWriter("bookData.csv");
        myWriter.write("id,bookName,author,published");
        myWriter.close();
        




        String line = "";  
        String splitBy = ",";  
        LinkedList<String[]> test = new LinkedList<String[]>();
        BufferedReader br = new BufferedReader(new FileReader("bookData.csv")); 
        while ((line = br.readLine()) != null){  
            test.push(line.split(splitBy));
        }  


        for(String text: test.get(0)){
            System.out.print(text+"\t");
        }




    }


    void addBook(){

    }

    void removeBook(){

    }

    void getAllBooks(){
        
    }




}
