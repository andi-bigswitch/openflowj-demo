package org.openflow.proto;

public abstract class OFMessageBase implements OFMessage, OFGenericMessage {

    private final boolean xidSet;
    private final int xid;

    public OFMessageBase() {
        xidSet = false;
        xid = 0;
    }

    public OFMessageBase(int xid) {
        this.xid = xid;
        xidSet = true;
    }

    @Override
    public int getXid() {
        return xid;
    }

    @Override
    public boolean isXidSet() {
        return xidSet;
    }

    @Override
    public OFPacketIn asPacketIn() {
        return (OFPacketIn) this;
    }

    @Override
    public OFEchoRequest asEchoRequest() {
        return (OFEchoRequest) this;
    }

    @Override
    public OFFlowStatsReply asFlowStatsReply() {
        return (OFFlowStatsReply) this;
    }

}
