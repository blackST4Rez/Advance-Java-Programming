import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        FileReaderWriter frw = new FileReaderWriter();
        Scanner sc = new Scanner(System.in);

        try {
            File textFile = new File("hello.txt");
            File copySource = new File("\\Advance-Java-Programming\\Lab 1\\Question 14\\src");
            File copyDest = new File("copied_img.png");
            File userFile = new File("user_input.txt");
            File randomFile = new File("random.txt");
            File studentFile = new File("student.ser");

            // 1. Basic write & read using streams
            String content = "Hello world using FileOutputStream!";
            frw.writeInFile(textFile, content);
            System.out.println("Read back: " + frw.readContent(textFile));

            // 2. File copy
            if (copySource.exists()) {
                frw.copyFile(copySource, copyDest);
            } else {
                System.out.println("Source image not found: " + copySource);
            }

            // 3. User input → FileWriter / FileReader
            System.out.print("Enter content to save in file: ");
            String userContent = sc.nextLine();  // safe after no previous nextInt()
            frw.writeFile(userFile, userContent);
            System.out.println("You wrote: " + frw.readFile(userFile));

            // 4. Serialization
            Student student = new Student("Raka Maharjan", 7);
            frw.serializeData(student, studentFile);
            Student loaded = frw.deserializeData(studentFile);
            System.out.println("Deserialized student: " + loaded);

            // 5. RandomAccessFile demo
            frw.randomFile(randomFile);

        } catch (IOException e) {
            System.err.println("I/O Error: " + e.getMessage());
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.err.println("Class not found during deserialization: " + e.getMessage());
        } finally {
            sc.close();
        }
        System.out.println();
        System.out.println("Lab No : 1");
        System.out.println("Name : Raka Maharjan");
        System.out.println("ID : 2308-1002");
    }
}