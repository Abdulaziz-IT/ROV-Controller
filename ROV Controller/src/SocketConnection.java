
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class SocketConnection {

    Socket soc;
    String serverAddress = "127.0.0.1";

    public SocketConnection(String ip, int port) throws IOException {
        soc = new Socket(ip, port);
    }

    public void sendToRov(String data) throws IOException {
        PrintWriter pw = new PrintWriter(soc.getOutputStream(), true);
        pw.println(data);
    }

    public String recieveFromRov() throws IOException {
        Scanner sc = new Scanner(soc.getInputStream());
        String recieved = sc.nextLine();
        return recieved;
    }

    public void closeConnection() throws IOException {
        soc.close();
        System.out.println("Connection closed");
    }

   
}
