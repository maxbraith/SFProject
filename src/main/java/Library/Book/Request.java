package Library.Book;

import Library.Users.parentUser;

public class Request {
    
    String User;
    String book;
    String bookName;


    public Request(){}

    public Request(parentUser user, Book book_){
        this.User = user.getId();
        this.book = book_.id;
        this.bookName = book_.getTitle();
    }

    public String getUser() {
        return this.User;
    }
    public void setUser(String User) {
        this.User = User;
    }


    public String getBook() {
    	return this.book;
    }
    public void setBook(String book) {
    	this.book = book;
    }

    public String getBookName() {
        return this.bookName;
    }
    public void setBookName(String bookName) {
        this.bookName = bookName;
    }



}
