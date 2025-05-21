package org.example.network.client;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

@Slf4j
public class ClientBean {

    private Socket socket;
    private DataOutputStream out = null;
    private BufferedReader in = null;
    private BufferedReader c = null;
    String line = null;

    public ClientBean(String host, int port) {
        try {
            log.info("Client pornit");
            this.socket = new Socket(host, port);
            log.info("Socket info: {}", socket);

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
