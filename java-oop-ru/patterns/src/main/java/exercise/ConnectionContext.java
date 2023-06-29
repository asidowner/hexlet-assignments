package exercise;

import exercise.connections.Connection;

import java.util.List;

public interface ConnectionContext {
    String getCurrentState();
    void connect();
    void disconnect();
    void setState(Connection connection);
    void write(String data);
}
