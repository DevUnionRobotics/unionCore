package dev.unionrobotics.server;

import com.google.common.reflect.ClassPath;
import com.google.gson.Gson;
import com.google.gson.stream.MalformedJsonException;
import dev.unionrobotics.server.methods.Method;
import dev.unionrobotics.server.methods.Payload;

import java.io.IOException;
import java.util.HashMap;
import java.util.Set;

public class JsonInterpreter {
    private static Gson gson = new Gson();
    public static HashMap<String,Class<? extends Method>> payloads = new HashMap<>();

    static {
        try {
            ClassPath.from(ClassLoader.getSystemClassLoader())
                    .getAllClasses()
                    .stream()
                    .filter(clazz -> clazz.getPackageName().startsWith(Payload.class.getPackage().getName()))
                    .map(ClassPath.ClassInfo::load)
                    .forEach((clazz) -> {
                        payloads.put(clazz.getCanonicalName().replace(Payload.class.getPackage().getName(), "").replace(".", "/"), (Class<? extends Method>) clazz);
                    });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String onMessageReceived(Client client, String message) {

        String response = "";

        try {
            String path = gson.fromJson(message, SerializedPath.class).path;
            Class<? extends Method> clazz = payloads.get(path);

            Method o = (Method) gson.fromJson(message, clazz);
            System.out.println(client.toString() + " invoked " + o.toString());
            response = o.call(client);
        }catch (Exception ex) {
            response = Errors.invalidPayload.toString();
        }

        if(response.isEmpty() && !client.isAuthenticated()) {
            response = Errors.notAuthenticated.toString();
        }

        return response;
    }

    class SerializedPath {
        private String path;

        public String getPath() {
            return path;
        }

        public SerializedPath(String path) {
            this.path = path;
        }
    }
}
