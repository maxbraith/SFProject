package Library.Book;

import Library.Users.parentUser;

public class Assignment {
    String assignedBy;
    String assignedStudent;
    String assignedGrade;
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


    public Assignment(){}

    public Assignment(parentUser assignedBy,parentUser assignedStudent,Book book){
        this.assignedBy = assignedBy.getId();
        this.assignedStudent = assignedStudent.getId();
        this.bookName = book.getTitle();
        this.assignedToStudent = true;
        this.bookID = book.getId();
    }
    public Assignment(parentUser assignedBy,String grade,Book book,int noCopies){
        this.assignedBy = assignedBy.getId();
        this.assignedGrade= grade;
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


    public String getAssignedStudent() {
    	return this.assignedStudent;
    }
    public void setAssignedStudent(String assignedStudent) {
    	this.assignedStudent = assignedStudent;
    }

    public Boolean isAssignedToStudent(){
        return this.assignedToStudent;
    }

    public String getAssignedTo(){
        if(this.assignedToStudent){
            return this.assignedStudent;
        }else{
            return this.assignedGrade;
        }
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
