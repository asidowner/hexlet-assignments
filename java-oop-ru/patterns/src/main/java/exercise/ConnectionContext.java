package exercise;

import exercise.connections.Connection;

public interface ConnectionContext {
    String getCurrentState();

    void connect();

    void disconnect();

    void setState(Connection connection);

    void write(String data);
}
