package Book;

import org.junit.jupiter.api.Test;

import com.opencsv.CSVReaderHeaderAware;
import com.opencsv.exceptions.CsvValidationException;

import Library.Book.Book;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BookTest {
    
    @Test
    public void bookConstructorTest() throws CsvValidationException, FileNotFoundException, IOException, ClassNotFoundException{
        FileReader file = new FileReader("bookData.csv");
        CSVReaderHeaderAware reader = new CSVReaderHeaderAware(file);
        List books = new ArrayList<Book>();
        for(int i=0; i<10; i++){
            Map<String, String> values = reader.readMap();
            System.out.println(values.get("title"));
            String id = "" + i;
            String title = values.get("title");
            String author = values.get("author");
            String x = values.get("isbn");
            String isbn = x;
            boolean takenOut = false;
            LocalDate returnDate = LocalDate.of(2023, 4, 1);;
            Book myBook = new Book(id, title, author, isbn, takenOut, returnDate);
            books.add(myBook);
            assertEquals(values.get("title"), myBook.getTitle());
        }
        for(int i =0; i<books.size(); i++){
            System.out.println(books.get(i));
        }

/*
        String path = "books.csv";
        FileOutputStream fos = new FileOutputStream(path, true);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        //oos.writeInt(books.size());
        for(Object book : books){
            oos.writeObject(book);
        }
        System.out.println("New Record has been written!");
        
        FileInputStream fis = new FileInputStream("books.txt");
        ObjectInputStream ois = new ObjectInputStream(fis);
        //int size = ois.readInt();
        int size = books.size();
        List newBooks = new ArrayList<Book>();
        for(int i=0; i<size; i++){
            Book b = (Book) ois.readObject();
            newBooks.add(b);
        }
        
        for(int i =0; i<newBooks.size(); i++){
            System.out.println(newBooks.get(i));
        }

 */
    
    }

}
