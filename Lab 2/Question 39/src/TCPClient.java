import java.io.*;
import java.net.*;

public class TCPClient {
    public static void main(String[] args) {
        String serverAddress = "localhost"; // or IP of server
        int port = 12345;

        try (Socket socket = new Socket(serverAddress, port)) {
            System.out.println("Connected to server.");

            // Set up input and output streams
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            // Thread to read messages from server and print them
            Thread readThread = new Thread(() -> {
                try {
                    String message;
                    while ((message = in.readLine()) != null) {
                        System.out.println("Server: " + message);
                        if (message.equalsIgnoreCase("bye")) {
                            System.out.println("Server ended the chat.");
                            break;
                        }
                    }
                } catch (IOException e) {
                    System.out.println("Connection lost.");
                } finally {
                    System.exit(0);
                }
            });

            // Thread to read user input from console and send to server
            Thread writeThread = new Thread(() -> {
                try (BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in))) {
                    String userInput;
                    while ((userInput = consoleReader.readLine()) != null) {
                        out.println(userInput);
                        if (userInput.equalsIgnoreCase("bye")) {
                            System.out.println("You ended the chat.");
                            break;
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    System.exit(0);
                }
            });

            readThread.start();
            writeThread.start();

            readThread.join();
            writeThread.join();

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}