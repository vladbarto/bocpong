package org.example.network;

import java.io.IOException;
import java.net.ServerSocket;

public class Server {
    private ServerSocket serverSocket = null;
    private boolean listening;

    Server(int port) {
        init(port);
        this.listening = true;
        listen();
    }

    private void init(int port) {
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            System.err.println("Eroare pe port " + port);
            System.exit(-1);
        }
    }

    private void listen() {
        try {
            while (listening) {
                new ServerWorker(this.serverSocket.accept()).start();
            }
            this.serverSocket.close();
            this.listening = false;
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
