package exercise;

import exercise.connections.Connection;
import exercise.connections.Disconnected;

import java.util.ArrayList;
import java.util.List;

// BEGIN
class TcpConnection implements ConnectionContext {
    private Connection state = new Disconnected();
    private String ipAddress;
    private int port;
    private List<String> buffer = new ArrayList<>();

    public TcpConnection(String ipAddress, int port) {
        this.ipAddress = ipAddress;
        this.port = port;
    }

    @Override
    public void connect() {
        state.connect(this);
    }

    @Override
    public void disconnect() {
        state.disconnect(this);
    }

    @Override
    public String getCurrentState() {
        return state.getCurrentState();
    }

    @Override
    public void setState(Connection state) {
        this.state = state;
    }

    @Override
    public void write(String data) {
        if (state.getCurrentState().equals("disconnected")) {
            System.out.println("Error! Connection is disconnected");
        }
        buffer.add(data);
    }
}
// END
