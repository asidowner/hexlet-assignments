package exercise.connections;

import exercise.ConnectionContext;

// BEGIN
public class Disconnected implements Connection {
    @Override
    public String getCurrentState() {
        return "disconnected";
    }

    @Override
    public void connect(ConnectionContext context) {
        context.setState(new Connected());
    }

    @Override
    public void disconnect(ConnectionContext context) {
        System.out.println("Error! Connection already disconnected");
    }
}
// END
