package org.openflow.proto;

public interface OFPacketIn extends OFMessage {
    public OFMatch getMatch();
}
