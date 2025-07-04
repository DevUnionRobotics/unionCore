package dev.unionrobotics.server;

public abstract class Errors {
    public static Error notAuthenticated = new Error("Not authenticated");
    public static Error invalidPayload = new Error("Invalid payload");
    public static Error methodNotExists = new Error("Method do not exist");
    public static Error incorrectAuth = new Error("Incorrect credentials");
}
