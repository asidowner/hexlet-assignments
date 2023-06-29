package exercise.connections;

import exercise.ConnectionContext;

import java.util.List;

public interface Connection {
    // BEGIN
    String getCurrentState();
    void connect(ConnectionContext context);
    void disconnect(ConnectionContext context);
    // END
}
