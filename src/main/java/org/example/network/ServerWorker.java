package org.example.network;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ServerWorker extends Thread {

    private Socket socket = null;
    public DataOutputStream out = null;
    public BufferedReader in = null;
    private boolean flag;
    private String line;

    public ServerWorker(Socket socket) throws IOException {
        super("Server Worker");
        this.socket = socket;
        System.out.println("Socket info "+socket);
        in = new BufferedReader(
                new InputStreamReader(
                        socket.getInputStream()
                )
        );
        out = new DataOutputStream(
                socket.getOutputStream()
        );
    }

    public void run() {

        try {

            System.out.println("Client acceptat");
            flag = true;
            while (flag) {
                line = in.readLine();
                System.out.println("Primit de la client: " + line+ "\n");
                flag = (line.compareTo("bye") != 0);
                out.writeBytes(line + "_echo back \n");
            }

            out.close();
            in.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
