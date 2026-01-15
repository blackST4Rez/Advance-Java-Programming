import java.io.*;
import java.nio.charset.StandardCharsets;

public class FileReaderWriter {

    // Using FileOutputStream - byte stream
    public void writeInFile(File file, String content) throws IOException {
        try (FileOutputStream os = new FileOutputStream(file)) {
            os.write(content.getBytes(StandardCharsets.UTF_8));
            System.out.println("Content written using FileOutputStream");
        }
    }

    // Reading using FileInputStream - byte stream
    public String readContent(File file) throws IOException {
        try (FileInputStream fis = new FileInputStream(file)) {
            StringBuilder sb = new StringBuilder();
            int ch;
            while ((ch = fis.read()) != -1) {
                sb.append((char) ch);
            }
            return sb.toString();
        }
    }

    // Simple file copy using byte streams
    public void copyFile(File source, File destination) throws IOException {
        try (FileInputStream is = new FileInputStream(source);
             FileOutputStream os = new FileOutputStream(destination)) {

            int ch;
            while ((ch = is.read()) != -1) {
                os.write(ch);
            }
            System.out.println("File copied successfully: " + destination.getName());
        }
    }

    // Demonstration of RandomAccessFile
    public void randomFile(File file) throws IOException {
        try (RandomAccessFile raf = new RandomAccessFile(file, "rw")) {
            raf.writeUTF("Hello");
            raf.writeUTF("Java");
            raf.writeUTF("World");

            raf.seek(0);  // go back to beginning
            System.out.println("Reading from RandomAccessFile:");
            System.out.println(raf.readUTF());
            System.out.println(raf.readUTF());
            System.out.println(raf.readUTF());
        }
    }

    // Using character streams - FileReader
    public String readFile(File file) throws IOException {
        try (FileReader fr = new FileReader(file)) {
            StringBuilder sb = new StringBuilder();
            int ch;
            while ((ch = fr.read()) != -1) {
                sb.append((char) ch);
            }
            return sb.toString();
        }
    }

    // Using character streams - FileWriter
    public void writeFile(File file, String content) throws IOException {
        try (FileWriter fw = new FileWriter(file)) {
            fw.write(content);
            System.out.println("Content written using FileWriter");
        }
    }

    // Object Serialization
    public void serializeData(Student student, File file) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(student);
            System.out.println(student.name() + " has been serialized to: " + file.getName());
        }
    }

    // Object Deserialization - fixed version (uses the parameter file)
    public Student deserializeData(File file) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return (Student) ois.readObject();
        }
    }

    // Bonus: More efficient buffered copy (recommended for large files)
    public void copyFileBuffered(File source, File destination) throws IOException {
        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(source));
             BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(destination))) {

            byte[] buffer = new byte[8192];
            int bytesRead;
            while ((bytesRead = bis.read(buffer)) != -1) {
                bos.write(buffer, 0, bytesRead);
            }
            System.out.println("Large file copied efficiently using buffers");
        }
    }
}