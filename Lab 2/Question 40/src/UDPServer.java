import java.net.*;

public class UDPServer {
    public static void main(String[] args) {
        final int PORT = 9876; // port to listen on

        try (DatagramSocket serverSocket = new DatagramSocket(PORT)) {
            System.out.println("UDP Server is running on port " + PORT);

            byte[] receiveBuffer = new byte[1024];
            byte[] sendBuffer;

            while (true) {
                // Receive packet from client
                DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
                serverSocket.receive(receivePacket);

                String clientMessage = new String(receivePacket.getData(), 0, receivePacket.getLength());
                System.out.println("Received from client: " + clientMessage);

                // Prepare response
                InetAddress clientAddress = receivePacket.getAddress();
                int clientPort = receivePacket.getPort();
                String response = "Server received: " + clientMessage;
                sendBuffer = response.getBytes();

                // Send response back to client
                DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, clientAddress, clientPort);
                serverSocket.send(sendPacket);

                // Exit if client says "bye"
                if (clientMessage.equalsIgnoreCase("bye")) {
                    System.out.println("Client ended communication. Server shutting down.");
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}