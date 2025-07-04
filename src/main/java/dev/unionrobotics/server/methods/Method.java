package dev.unionrobotics.server.methods;

import dev.unionrobotics.server.Client;

public abstract class Method {
    public abstract String call(Client client);
}
