package Library.Book;

import java.time.LocalDate;

public class Book implements java.io.Serializable {
    String id;
    String title;
    String author;
    String isbn;
    boolean takenOut;
    LocalDate returnDate;
    

    public Book(String id, String title, String author, 
    String isbn, boolean takenOut, LocalDate returnDate){
        this.id = id;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.takenOut = takenOut;
        this.returnDate = returnDate;
        
    }

    public boolean getTakenOut(){
        return takenOut;
    }

    public String getTitle() {
        return title;
    }
    public String getAuthor() {
        return author;
    }
    public String getId() {
        return id;
    }
    public String getIsbn() {
        return isbn;
    }
    public LocalDate getReturnDate() {
        return returnDate;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public void setId(String id) {
        this.id = id;
    }
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }
    public void setTakenOut(boolean takenOut) {
        this.takenOut = takenOut;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    
    
    
}
