package Client;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

    public static void main(String[] args) {
        int port = 6666;
        String IP = "127.0.0.1";

        try(Socket socketToServer = new Socket(IP,port);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socketToServer.getOutputStream())) {

            while (true) {
                //Code goes here
            }
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
