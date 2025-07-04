package dev.unionrobotics.server.methods;

import java.util.Set;
import java.util.stream.Collectors;

public class Payload<T> {

    public T payloadType;

    public String path;

    public void setPayloadType(T payloadType) {
        this.payloadType = payloadType;
    }

    public T getPayloadType() {
        return payloadType;
    }

    public void call() {

    }

}
