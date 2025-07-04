package dev.unionrobotics;

import dev.unionrobotics.server.TCPServer;

import java.io.IOException;

public class Main {

    private static TCPServer tcpServer;

    public static void main(String[] args) throws IOException {
        tcpServer = new TCPServer();
    }

}
