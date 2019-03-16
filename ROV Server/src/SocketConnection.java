
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import arduino.*;

public class SocketConnection {

    ServerSocket srv;
    Socket recieveSoc;
    Scanner sc;
    PrintWriter pw;
    Arduino ard ; 

    public SocketConnection(int port) throws IOException {

        srv = new ServerSocket(port);

    }

    public Socket getSocket() throws IOException {
        recieveSoc = srv.accept();
        return recieveSoc;
    }

    public String Getdata() throws IOException {
        sc = new Scanner(recieveSoc.getInputStream());
        String recived = sc.next();
        return recived;
    }

    public void SendData(String data) throws IOException {

        pw = new PrintWriter(recieveSoc.getOutputStream());
        pw.write(data);

    }

    public void connectToArduino(){
        ard=new Arduino("COM3", 9600);
        ard.openConnection();
    }
    public void sendToArduino(String command) {
        ard.serialWrite(command);
    }

    public void sendToArduino(String movementCommand, String ButtonCommand) throws InterruptedException {
        ard.serialWrite(movementCommand);
        Thread.sleep(500);
        ard.serialWrite(ButtonCommand);
    }

    public String recieveFromArduino() {
        return ard.serialRead();
    }

}
