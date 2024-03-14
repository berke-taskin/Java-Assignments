import java.time.LocalDate;

public class Book extends Library {
    //Variables
    private boolean borrowed=false;
    private boolean reading=false;
    private boolean extended=false;
    private String borrowSTR;
    private String readingSTR;
    private int whoBorrowed;
    private int whoRead;
    private LocalDate borrowDate;
    //Getters and Setters
    public boolean isBorrowed() {
        return borrowed;
    }
    public void setBorrowed(boolean borrowed) {
        this.borrowed = borrowed;
    }
    public String getBorrowSTR() {
        return borrowSTR;
    }
    public void setBorrowSTR(String borrowSTR) {
        this.borrowSTR = borrowSTR;
    }
    public boolean isReading() {
        return reading;
    }
    public void setReading(boolean reading) {
        this.reading = reading;
    }
    public String getReadingSTR() {
        return readingSTR;
    }
    public void setReadingSTR(String readingSTR) {
        this.readingSTR = readingSTR;
    }
    public LocalDate getBorrowDate() {
        return borrowDate;
    }
    public void setBorrowDate(LocalDate borrowDate) {
        this.borrowDate = borrowDate;
    }
    public boolean isExtended() {
        return extended;
    }
    public void setExtended(boolean extended) {
        this.extended = extended;
    }
    public int getWhoBorrowed() {
        return whoBorrowed;
    }
    public void setWhoBorrowed(int whoBorrowed) {
        this.whoBorrowed = whoBorrowed;
    }
    public int getWhoRead() {
        return whoRead;
    }
    public void setWhoRead(int whoRead) {
        this.whoRead = whoRead;
    }
}
