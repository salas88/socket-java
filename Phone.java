import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Phone {

    ServerSocket serverSocket;
    Socket client;
    BufferedReader reader;
    BufferedWriter writer;

    public Phone(String port) {
        try {
            serverSocket = new ServerSocket(Integer.parseInt(port));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Phone(String ip, String port) {
        try {
            client = new Socket(ip, Integer.parseInt(port));
            createStreams();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void accept() {
        try {
            client = serverSocket.accept();
            createStreams();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createStreams(){
        try {
            reader = new BufferedReader(
                    new InputStreamReader( client.getInputStream()));
            writer = new BufferedWriter(
                    new OutputStreamWriter(client.getOutputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public String readLine() {
        try {
            return reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

   

    public void close() {
        try {
            reader.close();
            writer.close();
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    public void writeLine(String message) {
        try {
            writer.write(message);
            writer.newLine();
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
