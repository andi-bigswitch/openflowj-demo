package org.openflow.proto.type;

public class OFPort {
    private final int port;

    private OFPort(int port) {
        super();
        this.port = port;
    }

    public int getPort() {
        return port;
    }

    public static OFPort of(int i) {
        return new OFPort(i);
    }
}
