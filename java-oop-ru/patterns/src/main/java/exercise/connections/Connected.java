package exercise.connections;

import exercise.ConnectionContext;

// BEGIN
public class Connected implements Connection {
    @Override
    public String getCurrentState() {
        return "connected";
    }

    @Override
    public void connect(ConnectionContext context) {
        System.out.println("Error! Connection already connected");
    }

    @Override
    public void disconnect(ConnectionContext context) {
        context.setState(new Disconnected());
    }
}
// END
