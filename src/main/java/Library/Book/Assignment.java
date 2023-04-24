package Library.Book;

import Library.Users.parentUser;

public class Assignment {
    String assignedBy;
    String assignedStudent;
    String assignedGrade;
    Boolean assignedToStudent;
    String book;
    int noCopies;

    public Assignment(){}

    public Assignment(parentUser assignedBy,parentUser assignedStudent,Book book){
        this.assignedBy = assignedBy.getId();
        this.assignedStudent = assignedStudent.getId();
        this.book = book.getTitle();
        this.assignedToStudent = true;
    }
    public Assignment(parentUser assignedBy,String grade,Book book,int noCopies){
        this.assignedBy = assignedBy.getId();
        this.assignedGrade= grade;
        this.book = book.getTitle();
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


    public String getBook() {
    	return this.book;
    }
    public void setBook(String book) {
    	this.book = book;
    }


}
