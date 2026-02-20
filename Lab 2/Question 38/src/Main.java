import java.net.*;
import java.io.*;
import java.util.*;

public class Main {
    static void readLines(InputStream in, int maxLines) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(in))) {
            for (int i = 0; i < maxLines; i++) {
                String line = br.readLine();
                if (line == null) break;
                System.out.println(line);
            }
        }
    }

    public static void main(String[] args) {
        try {
            // --- a) URL Creation & Parsing ---
            System.out.println("=== A) URL CREATION & PARSING ===");
            URL url1 = new URL("http://example.com:8080/search?q=java#top");
            URL url2 = new URL("http", "example.com", "/index.html");
            URL url3 = new URL("http", "example.com", 8443, "/api");
            URL url4 = new URL(new URL("http://example.com/base/"), "file.html");

            System.out.println("url1: " + url1 + "\nurl2: " + url2 + "\nurl3: " + url3 + "\nurl4: " + url4);
            System.out.printf("Parsing url1: protocol=%s, host=%s, port=%d, path=%s, query=%s, fragment=%s%n",
                    url1.getProtocol(), url1.getHost(), url1.getPort(), url1.getPath(), url1.getQuery(), url1.getRef());

            // --- b) Reading Directly from URL ---
            System.out.println("\n=== B) DIRECT URL READING ===");
            URL target = new URL("http://example.com");
            System.out.println("First 3 lines:");
            readLines(target.openStream(), 3);

            // --- c) Reading from URLConnection ---
            System.out.println("\n=== C) URLCONNECTION ===");
            URLConnection conn = target.openConnection();
            System.out.printf("Content-Type: %s, Length: %d, Date: %s%n",
                    conn.getContentType(), conn.getContentLength(), new Date(conn.getDate()));
            System.out.println("First 3 lines via connection:");
            readLines(conn.getInputStream(), 3);

            // --- d) InetAddress Class ---
            System.out.println("\n=== D) INETADDRESS ===");
            InetAddress local = InetAddress.getLocalHost();
            InetAddress google = InetAddress.getByName("google.com");
            System.out.printf("Local: %s - %s%n", local.getHostName(), local.getHostAddress());
            System.out.println("google.com IPs:");
            for (InetAddress addr : InetAddress.getAllByName("google.com"))
                System.out.println("  " + addr.getHostAddress());
            System.out.println("Reachable? " + google.isReachable(3000));

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}