import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Main {
    //Reading Input File
    public static String[] readFile(String path, boolean discardEmptyLines, boolean trim) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(path));
            if (discardEmptyLines) {
                lines.removeIf(line -> line.trim().equals(""));
            }
            if (trim) {
                lines.replaceAll(String::trim);
            }
            return lines.toArray(new String[0]);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    //Writing output file
    public static void writeFile(String path, String content, boolean append, boolean newLine) {
        PrintStream ps = null;
        try {
            ps = new PrintStream(new FileOutputStream(path, append));
            ps.print(content + (newLine ? "\n" : ""));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (ps != null) {
                ps.flush();
                ps.close();
            }
        }
    }
    //Borrowing a book method
    public static void Borrow(Library library, String outPath, Book book, Member member, String[] split, int index){
        String str = "The book ["+split[1]+"] was borrowed by member ["+split[2]+"] at "+split[3];
        LocalDate borrowDate = LocalDate.parse(split[3]);
        book.setBorrowSTR(str);
        book.setBorrowed(true);
        book.setBorrowDate(borrowDate);
        book.setWhoBorrowed(member.getId());
        library.borrowedList[index] = book;
        member.setLimit(member.getLimit()+1);
        writeFile(outPath,str, true, true);
    }
    //Reading in library method
    public static void Read(Library library, String outPath, Book book, Member member, String[] split, int index){
        String str = "The book ["+split[1]+"] was read in library by member ["+split[2]+"] at "+split[3];
        book.setReadingSTR(str);;
        book.setReading(true);
        book.setWhoRead(member.getId());
        library.readingList[index] = book;
        writeFile(outPath,str, true, true);
    }
    public static void main (String[] args){
        //args[0] should be input.txt and args[1] should be output.txt
        String[] lines = readFile(args[0],true,true);
        String outPath = args[1];
        int bookid = 1;
        int memberid = 1;
        int printedIndex = 0;
        int handIndex = 0;
        int studentIndex = 0;
        int academicIndex = 0;
        int borrowedIndex = 0;
        int borrowedNum = 0;
        int readingIndex = 0;
        int readingNum = 0;
        Library library = new Library();
        writeFile(outPath,"",false,false);
        for (String line : lines){
            String [] split = line.split("\t");
            //Adding a new book
            if (split[0].equals("addBook")){
                Book Book = new Book();
                String type = split[1];
                Book.setId(bookid);
                //If printed book
                if (type.equals("P")){
                    Book.setType("Printed");
                    library.printedList[printedIndex] = Book;
                    printedIndex += 1;
                }
                //If handwritten book
                else if (type.equals("H")){
                    Book.setType("Handwritten");
                    library.handList[handIndex] = Book;
                    handIndex += 1;
                }
                bookid += 1;
                writeFile(outPath,"Created new book: "+Book.getType()+" [id: "+Book.getId()+"]", true, true);
                //If there are more books than 6 digit number
                if (bookid>999999){
                    System.exit(1);
                }
            }
            //Adding a new member
            if (split[0].equals("addMember")){
                Member Member = new Member();
                String type = split[1];
                Member.setId(memberid);
                //If student
                if (type.equals("S")){
                    Member.setType("Student");
                    library.studentList[studentIndex] = Member;
                    studentIndex += 1;
                }
                //If academic
                else if (type.equals("A")){
                    Member.setType("Academic");
                    library.academicList[academicIndex] = Member;
                    academicIndex += 1;
                }
                memberid += 1;
                writeFile(outPath,"Created new member: "+Member.getType()+" [id: "+Member.getId()+"]", true, true);
                //If there are more members than 6 digit number
                if (memberid>999999){
                    System.exit(1);
                }
            }
            //Borrowing a book
            if (split[0].equals("borrowBook")){
                int bookId = Integer.parseInt(split[1]);
                int memberId = Integer.parseInt(split[2]);
                //If book is handwritten it can't be borrowed
                for (Book book : library.handList){
                    if (book !=null && book.getId() == bookId){
                        writeFile(outPath,"You cannot borrow this book!", true, true);
                    }
                }
                //If book is printed
                for (Book book : library.printedList){
                    if (book !=null && book.getId() == bookId){
                        //If book is not borrowed or read in library at the moment
                        if (!book.isBorrowed() && !book.isReading()){
                            //If member is student
                            for (Member member : library.studentList){
                                if (member !=null && member.getId() == memberId){
                                    //If student doesn't exceed the borrowing limit
                                    if (member.getLimit()<2){
                                        Borrow(library, outPath, book, member, split, borrowedIndex);
                                        borrowedIndex += 1;
                                        borrowedNum += 1;
                                    } else {
                                        writeFile(outPath,"You have exceeded the borrowing limit!", true, true);
                                    }
                                }
                            }
                            //If member is academic
                            for (Member member : library.academicList){
                                if (member !=null && member.getId() == memberId){
                                    //If academic doesn't exceed the borrowing limit
                                    if (member.getLimit()<4){
                                        Borrow(library, outPath, book, member, split, borrowedIndex);
                                        borrowedIndex += 1;
                                        borrowedNum += 1;
                                    } else {
                                        writeFile(outPath,"You have exceeded the borrowing limit!", true, true);
                                    }
                                }
                            }
                        }
                        //If book is already borrowed or read in library
                        else {
                            writeFile(outPath,"You cannot borrow this book!", true, true);
                        }
                    }
                }
            }
            //Returning a book
            if (split[0].equals("returnBook")){
                int bookId = Integer.parseInt(split[1]);
                int memberId = Integer.parseInt(split[2]);
                long Fee = 0;
                LocalDate returnDate = LocalDate.parse(split[3]);
                boolean error = true;
                //If book is borrowed
                for (int i=0; i<library.borrowedList.length; i++){
                    Book book = library.borrowedList[i];
                    if (book!=null && book.getId()==bookId){
                        Long days = ChronoUnit.DAYS.between(book.getBorrowDate(), returnDate);
                        //For student deadline
                        for (Member member : library.studentList){
                            //If the member matches with who actually borrowed the book
                            if (member !=null && member.getId() == memberId && book.getWhoBorrowed()==member.getId()){
                                error=false;
                                if (days>7){
                                    writeFile(outPath,"You must pay a penalty!", true, true);
                                    Fee = days-7;
                                }
                            }
                        }
                        //For academic deadline
                        for (Member member : library.academicList){
                            //If the member matches with who actually borrowed the book
                            if (member !=null && member.getId() == memberId && book.getWhoBorrowed()==member.getId()){
                                error=false;
                                if (days>14){
                                    writeFile(outPath,"You must pay a penalty!", true, true);
                                    Fee = days-14;
                                }
                            }
                        }
                        //If that certain member can return the borrowed book
                        if (error==false){
                            book.setBorrowed(false);
                            library.borrowedList[i]=null;
                            borrowedNum -= 1;
                            writeFile(outPath,"The book ["+bookId+"] was returned by member ["+memberId+"] at "+split[3]+" Fee: "+Fee, true, true);    
                        } else {
                            writeFile(outPath,"You cannot return this book", true, true); 
                        }            
                    }
                }
                //If book is read in library
                for (int i=0; i<library.readingList.length; i++){
                    Book book = library.readingList[i];
                    //If the member matches with who actually read the book in library
                    if (book!=null && book.getId()==bookId && book.getWhoRead()==memberId){
                        book.setReading(false);
                        library.readingList[i]=null;
                        readingNum -= 1;
                        writeFile(outPath,"The book ["+bookId+"] was returned by member ["+memberId+"] at "+split[3]+" Fee: 0", true, true);
                    }
                }
            }
            //Extending a book
            if (split[0].equals("extendBook")){
                int bookId = Integer.parseInt(split[1]);
                int memberId = Integer.parseInt(split[2]);
                LocalDate extendDate = LocalDate.parse(split[3]);
                boolean error = true;
                //If book is borrowed
                for (int i=0; i<library.borrowedList.length; i++){
                    Book book = library.borrowedList[i];
                    if (book!=null && book.getId()==bookId){
                        //For student deadline
                        for (Member member : library.studentList){
                            //If the member matches with who actually borrowed the book
                            if (member !=null && member.getId() == memberId && book.getWhoBorrowed()==member.getId()){
                                error=false;
                                //If book is extended once already
                                if (book.isExtended()){
                                    writeFile(outPath,"You cannot extend the deadline!", true, true);
                                } 
                                //If book is not extended once already
                                else {
                                    LocalDate newDate = extendDate.plusDays(7);
                                    book.setBorrowDate(newDate);
                                    book.setExtended(true);
                                    writeFile(outPath,"The deadline of book ["+bookId+"] was extended by member ["+memberId+"] at "+split[3], true, true);
                                    writeFile(outPath,"New deadline of book ["+bookId+"] is "+newDate, true, true);
                                }   
                            }
                        }
                        //For academic deadline
                        for (Member member : library.academicList){
                            //If the member matches with who actually borrowed the book
                            if (member !=null && member.getId() == memberId && book.getWhoBorrowed()==member.getId()){
                                error=false;
                                //If book is extended once already
                                if (book.isExtended()){
                                    writeFile(outPath,"You cannot extend this book", true, true);
                                }
                                //If book is not extended once already
                                else {
                                    LocalDate newDate = extendDate.plusDays(7);
                                    book.setBorrowDate(newDate);
                                    book.setExtended(true);
                                    writeFile(outPath,"The deadline of book ["+bookId+"] was extended by member ["+memberId+"] at "+split[3], true, true);
                                    writeFile(outPath,"New deadline of book ["+bookId+"] is "+newDate, true, true);
                                }
                            }
                        }
                        //If that member can't extend the certain book
                        if (error==true){
                            writeFile(outPath,"You cannot extend this book", true, true);
                        }
                    }
                }
            }
            //Read in library
            if (split[0].equals("readInLibrary")){
                int bookId = Integer.parseInt(split[1]);
                int memberId = Integer.parseInt(split[2]);
                //If book is handwritten
                for (Book book : library.handList){
                    if (book !=null && book.getId() == bookId){
                        //If book is not borrowed or read in library at the moment
                        if (!book.isBorrowed() && !book.isReading()){
                            //If member is student
                            for (Member member : library.studentList){
                                if (member !=null && member.getId() == memberId){
                                    writeFile(outPath,"Students can not read handwritten books!", true, true);
                                }
                            }
                            //If member is academic
                            for (Member member : library.academicList){
                                if (member !=null && member.getId() == memberId){
                                    Read(library, outPath, book, member, split, readingIndex);
                                    readingIndex += 1;
                                    readingNum += 1;
                                }
                            }
                        } 
                        //If book is already borrowed or read in library
                        else {
                            writeFile(outPath,"You can not read this book!", true, true);
                        }
                    }      
                }
                //If book is printed
                for (Book book : library.printedList){
                    if (book !=null && book.getId() == bookId){
                        //If book is not borrowed or read in library at the moment
                        if (!book.isBorrowed() && !book.isReading()){
                            //If member is student
                            for (Member member : library.studentList){
                                if (member !=null && member.getId() == memberId){
                                    if (book.isBorrowed()){
                                        writeFile(outPath,"You can not read this book!", true, true);
                                    } else {
                                        Read(library, outPath, book, member, split, readingIndex);
                                        readingIndex += 1;
                                        readingNum += 1;
                                    }
                                }
                            }
                            //If member is academic
                            for (Member member : library.academicList){
                                if (member !=null && member.getId() == memberId){
                                    if (book.isBorrowed()){
                                        writeFile(outPath,"You can not read this book!", true, true);
                                    } else {
                                        Read(library, outPath, book, member, split, readingIndex);
                                        readingIndex += 1;
                                        readingNum += 1;
                                    }
                                }
                            }
                        } 
                        //If book is already borrowed or read in library
                        else {
                            writeFile(outPath,"You can not read this book!", true, true);
                        }
                    }
                }
            }
            //Getting the history
            if (split[0].equals("getTheHistory")){
                writeFile(outPath,"History of library:", true, true);
                //Students
                writeFile(outPath,"\nNumber of students: "+studentIndex, true, true);
                for (Member student : library.studentList){
                    if (student!=null){
                        writeFile(outPath,student.getType()+" [id: "+student.getId()+"]", true, true);
                    }
                }
                //Academics
                writeFile(outPath,"\nNumber of academics: "+academicIndex, true, true);
                for (Member academic : library.academicList){
                    if (academic!=null){
                        writeFile(outPath,academic.getType()+" [id: "+academic.getId()+"]", true, true);
                    }
                }
                //Printed Books
                writeFile(outPath,"\nNumber of printed books: "+printedIndex, true, true);
                for (Book printed : library.printedList){
                    if (printed!=null){
                        writeFile(outPath,printed.getType()+" [id: "+printed.getId()+"]", true, true);
                    }
                }
                //Handwritten Books
                writeFile(outPath,"\nNumber of handwritten books: "+handIndex, true, true);
                for (Book hand : library.handList){
                    if (hand!=null){
                        writeFile(outPath,hand.getType()+" [id: "+hand.getId()+"]", true, true);
                    }
                }
                //Borrowed Books
                writeFile(outPath,"\nNumber of borrowed books: "+borrowedNum, true, true);
                for (Book book : library.borrowedList){
                    if (book!=null){
                        writeFile(outPath, book.getBorrowSTR(), true, true);
                    }
                }
                //Books Read in Library
                writeFile(outPath,"\nNumber of books read in library: "+readingNum, true, true);
                for (Book book : library.readingList){
                    if (book!=null){
                        writeFile(outPath, book.getReadingSTR(), true, true);
                    } 
                }
            }
        }
    }
}