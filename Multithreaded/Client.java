import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class Client {
    public Runnable getRunnable() {
       return new Runnable() {
            public void run() {
                try {
                    InetAddress address = InetAddress.getByName("localhost");
                    try (
                        Socket socket = new Socket(address, 8010);
                        BufferedReader fromSocket = new BufferedReader(new InputStreamReader(socket.getInputStream()))
                    ) {
                        String line;
                        while ((line = fromSocket.readLine()) != null) { // keep reading until server closes
                            System.out.println(line);                    // print each line received
                        }
                    }
                } catch (Exception e) {
                    System.err.println("Error: " + e.getMessage());
                }
            }
        };
    }

    public static void main(String[] args) {
        Client client = new Client();
        for (int i = 0; i < 100; i++) {
            Thread thread = new Thread(client.getRunnable());
            thread.start();
        }
    }
}