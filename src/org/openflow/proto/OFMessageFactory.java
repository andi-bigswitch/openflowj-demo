package org.openflow.proto;

import org.jboss.netty.buffer.ChannelBuffer;
import org.openflow.proto.match.Match;
import org.openflow.proto.match.MatchBuilder;

public class OFMessageFactory {

    private static final byte MY_VERSION = 1;

    public OFFlowModBuilder createFlowModBuilder() {
        return new OFFlowModBuilder();
    }

    public OFAction actionOutput(int i) {
        return new OFAction();
    }

    public MatchBuilder createMatchBuilder() {
        // TODO Auto-generated method stub
        return null;
    }

    OFMessageReader[] readers = { new OFFlowModReaderVer10() };

    public OFGenericMessage readFrom(ChannelBuffer channelBuffer) {
        byte version = channelBuffer.getByte(0);
        if (version != MY_VERSION)
            throw new IllegalArgumentException("Cannot support version " + version);
        short type = channelBuffer.getUnsignedByte(1);

        if (type >= readers.length)
            throw new IllegalArgumentException("Unknown OpenFlow message type " + type);

        return readers[type].read(channelBuffer);
    }

    public OFFlowModBuilder createFlowDeleteBuilder() {
        // TODO Auto-generated method stub
        return null;
    }

    public OFFlowMod createFlowAdd(int cookie, Match outMatch, OFActionOutput ofActionOutput) {
        // TODO Auto-generated method stub
        return null;
    }

    public OFFlowMod createFlowDelete(Match all) {
        // TODO Auto-generated method stub
        return null;
    }

    public FlowStatsRequestBuilder createFlowStatsBuilder() {
        // TODO Auto-generated method stub
        return null;
    }

}
