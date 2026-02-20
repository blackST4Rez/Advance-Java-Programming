import java.io.*;
import java.net.*;

public class TCPServer {
    public static void main(String[] args) {
        int port = 12345; // choose any free port

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server listening on port " + port);
            Socket clientSocket = serverSocket.accept();
            System.out.println("Client connected: " + clientSocket.getInetAddress());

            // Set up input and output streams
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

            // Thread to read messages from client and print them
            Thread readThread = new Thread(() -> {
                try {
                    String message;
                    while ((message = in.readLine()) != null) {
                        System.out.println("Client: " + message);
                        if (message.equalsIgnoreCase("bye")) {
                            System.out.println("Client ended the chat.");
                            break;
                        }
                    }
                } catch (IOException e) {
                    System.out.println("Connection lost.");
                } finally {
                    System.exit(0); // exit when client disconnects
                }
            });

            // Thread to read user input from console and send to client
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

            // Wait for threads to finish (they call System.exit, so this line may not be reached)
            readThread.join();
            writeThread.join();

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}