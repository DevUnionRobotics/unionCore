package dev.unionrobotics.server.methods;

import dev.unionrobotics.server.Client;

public class Hello extends Method {

    @Override
    public String toString() {
        return "Hello{}";
    }

    @Override
    public String call(Client client) {
        return "Hello, world!";
    }
}
