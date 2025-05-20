package org.example.network;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

    private Socket socket;
    private DataOutputStream out = null;
    private BufferedReader in = null;
    private BufferedReader c = null;
    String line = null;

    public Client(String host, int port) {
        try {
            System.out.println("Client pornit");
            this.socket = new Socket("192.168.1.131", 1555);//new Socket(host, port);
            System.out.println("Socket info " + socket);

            this.in = new BufferedReader(
                    new InputStreamReader(
                            socket.getInputStream()
                    )
            );
            this.out = new DataOutputStream(
                    socket.getOutputStream()
            );
            this.c = new BufferedReader(
                    new InputStreamReader(System.in)
            );
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void talk() {
        try {
            while(true) {
                line = c.readLine();
                out.writeBytes(line + "\n");
                out.flush();
                System.out.println("Trimis la server: " + line + "\n");
                line= in.readLine();
                System.out.println("Receptionat de  la  server: " + line);
            }
        } catch (IOException e) {
        }
    }
}
