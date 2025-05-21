package org.example.network.server;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.ServerSocket;
import java.net.UnknownHostException;

@Slf4j
public class ServerBean implements Server {
    private ServerSocket serverSocket = null;
    private boolean listening;
    private String hostAddress;

    public ServerBean(int port) {
        log.info("Creating a server on host and port {} and start listening", port);
        init(port);
        this.listening = true;
    }

    private void init(int port) {
        try {
            serverSocket = new ServerSocket(port);
            this.hostAddress = Inet4Address.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            log.error("Host unknown; {}", e.getMessage());
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Eroare pe port " + port);
            System.exit(-1);
        }
    }

    @Override
    public void listen() {
        try {
            while (listening) {
                ServerWorker worker = new ServerWorker(this.serverSocket.accept());
                worker.start();
            }

            this.serverSocket.close();
            this.listening = false;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
