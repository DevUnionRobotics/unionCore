package dev.unionrobotics.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;

public class TCPServer {

    public static ArrayList<Client> onlineClients = new ArrayList<>();

    public TCPServer() throws IOException {
        ServerSocket server = new ServerSocket(5000);
        System.out.println("Listening on port :5000/TCP");

        while(true) {
            Socket client = server.accept();

            TCPThread tcpThread = new TCPThread(client, this);
            Thread thread = new Thread(tcpThread);
            thread.start();
        }
    }

    class TCPThread implements Runnable {

        private Socket clientSocket;
        private TCPServer tcpServer;
        private Client client;

        public TCPThread(Socket clientSocket, TCPServer tcpServer) {
            this.clientSocket = clientSocket;
            this.tcpServer = tcpServer;
            this.client = new Client(clientSocket);
        }

        @Override
        public void run() {
            try {

                onlineClients.add(client);
                System.out.println("Connected (" + client.getUuid() + ") at " + clientSocket.getInetAddress());

                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream()), true);


                while(clientSocket.isConnected() && !clientSocket.isClosed()) {
                    try {
                        String text = in.readLine();
                        System.out.println(client.getUuid() + " sent '" + text + "';");
                        String output = JsonInterpreter.onMessageReceived(client, text);
                        out.println(output);
                    }catch (SocketException e) {
                        clientSocket.close();
                    }
                }

                System.out.println("Disconnected (" + client.getUuid() + ") at " + clientSocket.getInetAddress());

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
