package exercise.connections;

import exercise.ConnectionContext;

public interface Connection {
    // BEGIN
    String getCurrentState();

    void connect(ConnectionContext context);

    void disconnect(ConnectionContext context);
    // END
}
