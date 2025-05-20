package org.example.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.net.*;

public class MainController {

    @FXML
    private TextField port;

    public void getAddress() throws UnknownHostException {
        System.out.println(Inet4Address.getLocalHost().getHostAddress());
    }

    public void getPort() throws NullPointerException {

        System.out.println(port.getText());
    }
}
