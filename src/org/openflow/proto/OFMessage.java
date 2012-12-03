package org.openflow.proto;

public interface OFMessage {
    public OFType getType();

    public int getXid();

    public boolean isXidSet();
}
