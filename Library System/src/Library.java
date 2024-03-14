public class Library {
    //Variables
    public Book[] printedList = new Book[999999];
    public Book[] handList = new Book[999999];
    public Member[] studentList = new Member[999999];
    public Member[] academicList = new Member[999999];
    public Book[] borrowedList = new Book[999999];
    public Book[] readingList = new Book[999999];
    private int id;
    private String type;
    //Getters and Setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }  
}
