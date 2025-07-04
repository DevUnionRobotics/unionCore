package dev.unionrobotics.server;

import dev.unionrobotics.entities.User;

import java.net.Socket;
import java.util.UUID;

public class Client {
    private UUID uuid;
    private boolean authenticated;
    private User user;
    private Socket socket;

    public UUID getUuid() {
        return uuid;
    }

    public boolean isAuthenticated() {
        return authenticated;
    }

    public User getUser() {
        return user;
    }

    public Socket getSocket() {
        return socket;
    }

    public Client(Socket socket) {
        this.uuid = UUID.randomUUID();
        this.authenticated = false;
        this.user = null;
        this.socket = socket;
    }

    @Override
    public String toString() {
        return "Client{" +
                "uuid=" + uuid +
                ", authenticated=" + authenticated +
                ", user=" + user +
                ", socket=" + socket +
                '}';
    }
}
