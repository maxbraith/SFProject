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
import java.util.UUID;

// import Library.Book.Book;

public class getAllBooked {
    
    public static void main(String[] args) throws IOException {
        
        // List<Book> Books = new LinkedList<Book>();

        // FileWriter myWriter = new FileWriter("bookData.csv");
        // myWriter.write("id,bookName,author,published");
        // myWriter.close();
        




        String line = "";  
        String splitBy = ",";  
        LinkedList<String[]> test = new LinkedList<String[]>();
        BufferedReader br = new BufferedReader(new FileReader("bookData.csv")); 
        while ((line = br.readLine()) != null){  
            test.push(line.split(splitBy));
        }  

        // System.out.println(test.get(1).length);
        // for(String elem:test.get(1)){
        //     System.out.print(elem+"\t");
        // }



        FileWriter myWriter = new FileWriter("bookDataFiltered.csv");
        for(String[] book: test){
            String uuid = UUID.randomUUID().toString();

            myWriter.write(uuid+","+book[0]+","+book[1]+","+book[2]+","+"false,null\n");
        }
        myWriter.close();




    }


    void addBook(){

    }

    void removeBook(){

    }

    void getAllBooks(){

    }




}
