package org.example.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.example.network.client.ClientBean;
import org.example.network.server.ServerBean;

public class MainController {

    @FXML
    private TextField port;
    private TextField host;
    private TextField clientPort;

    public void createServer() {
        int numericPort = Integer.parseInt(port.getText());
        ServerBean server = new ServerBean(numericPort);
        server.listen();
    }

    public void connectToServer() {
        int numericPort = Integer.parseInt(clientPort.getText());
        String hostname = host.getText();
        ClientBean client = new ClientBean(hostname, numericPort);
        client.talk();
    }
}
