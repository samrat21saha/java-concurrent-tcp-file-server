import java.io.*;
import java.net.*;

public class Server {
    public void run() throws IOException {
        int port = 8080;
        ServerSocket socket = new ServerSocket(port);
        // REMOVED setSoTimeout — server must run forever for JMeter test

        System.out.println("SingleThreaded Server is listening on port " + port);

        while (true) {
            try {
                Socket acceptedConnection = socket.accept();
                System.out.println("Connection accepted from: " + acceptedConnection.getRemoteSocketAddress());

                PrintWriter toClient = new PrintWriter(acceptedConnection.getOutputStream(), true);
                BufferedReader fileReader = new BufferedReader(new FileReader("file.txt"));

                String line;
                while ((line = fileReader.readLine()) != null) {
                    toClient.println(line); // send file line by line
                }

                fileReader.close();
                toClient.close();
                acceptedConnection.close();

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Server server = new Server();
        try {
            server.run();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}