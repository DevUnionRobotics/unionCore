package dev.unionrobotics.server;

public class Error {
    private String errorMsg;

    public Error(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    @Override
    public String toString() {
        return "Error[" + errorMsg + "]";
    }
}
