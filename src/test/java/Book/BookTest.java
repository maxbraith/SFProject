package Book;

import org.junit.jupiter.api.Test;

import com.opencsv.CSVReaderHeaderAware;
import com.opencsv.exceptions.CsvValidationException;

import Library.Book.Book;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BookTest {
    
    @Test
    public void bookConstructorTest() throws CsvValidationException, FileNotFoundException, IOException{
        FileReader file = new FileReader("books_1.Best_Books_Ever.csv");
        CSVReaderHeaderAware reader = new CSVReaderHeaderAware(file);
        List books = new ArrayList<Book>();
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
            books.add(myBook);
            assertEquals(values.get("title"), myBook.getTitle());
        }
        for(int i =0; i<books.size(); i++){
            System.out.println(books.get(i));
        }

    
    }

}
