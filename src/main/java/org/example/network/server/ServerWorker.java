package org.example.network.server;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

@Slf4j
public class ServerWorker extends Thread {

    private Socket socket = null;
    public DataOutputStream out = null;
    public BufferedReader in = null;
    private boolean flag;
    private String line;

    public ServerWorker(Socket socket) throws IOException {
        super("Server Worker");
        this.socket = socket;

        log.info("Server Worker started");
        log.info("Socket info: {}", socket);

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

            log.info("Client acceptat");
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
