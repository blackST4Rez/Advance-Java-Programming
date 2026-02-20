import java.net.*;
import java.io.*;

public class UDPClient {
    public static void main(String[] args) {
        final String SERVER_ADDRESS = "localhost"; // or IP of server
        final int SERVER_PORT = 9876;

        try (DatagramSocket clientSocket = new DatagramSocket();
             BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in))) {

            InetAddress serverIP = InetAddress.getByName(SERVER_ADDRESS);
            byte[] sendBuffer;
            byte[] receiveBuffer = new byte[1024];

            System.out.println("UDP Client started. Type messages (type 'bye' to quit):");

            while (true) {
                // Read user input
                System.out.print("Enter message: ");
                String userInput = consoleReader.readLine();

                // Send packet to server
                sendBuffer = userInput.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, serverIP, SERVER_PORT);
                clientSocket.send(sendPacket);

                // Receive response from server
                DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
                clientSocket.receive(receivePacket);

                String serverResponse = new String(receivePacket.getData(), 0, receivePacket.getLength());
                System.out.println("Server replied: " + serverResponse);

                if (userInput.equalsIgnoreCase("bye")) {
                    System.out.println("Closing client.");
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}