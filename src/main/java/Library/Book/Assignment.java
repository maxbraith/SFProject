package Library.Book;

import Library.Users.parentUser;

public class Assignment {
    String assignedBy;
    String assignedTo;
    Boolean assignedToStudent;
    String bookName;
    String bookID;


    int noCopies;

    public int getNoCopies() {
        return this.noCopies;
    }
    public void setNoCopies(int noCopies) {
        this.noCopies = noCopies;
    }

    public String getAssignedTo() {
        return this.assignedTo;
    }
    public void setAssignedTo(String assignedTo) {
        this.assignedTo = assignedTo;
    }
    public Assignment(){}

    public Assignment(parentUser assignedBy,parentUser assignedStudent,Book book){
        this.assignedBy = assignedBy.getId();
        this.assignedTo = assignedStudent.getId();
        this.bookName = book.getTitle();
        this.assignedToStudent = true;
        this.bookID = book.getId();
    }
    public Assignment(parentUser assignedBy,String grade,Book book,int noCopies){
        this.assignedBy = assignedBy.getId();
        this.assignedTo= grade;
        this.bookName = book.getTitle();
        this.bookID = book.getId();
        this.assignedToStudent = false;
        this.noCopies = noCopies;
    }

    public String getAssignedBy() {
        return this.assignedBy;
    }
    public void setAssignedBy(String assignedBy) {
        this.assignedBy = assignedBy;
    }




    public Boolean isAssignedToStudent(){
        return this.assignedToStudent;
    }



    public String getBookName() {
        return this.bookName;
    }
    public void setBookName(String bookName) {
        this.bookName = bookName;
    }
    
    
        public String getBookID() {
            return this.bookID;
        }
        public void setBookID(String bookID) {
            this.bookID = bookID;
        }



}
