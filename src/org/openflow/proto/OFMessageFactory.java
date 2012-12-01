package org.openflow.proto;

import org.jboss.netty.buffer.ChannelBuffer;

public class OFMessageFactory {

    public FlowModBuilder createFlowModBuilder() {
        return new FlowModBuilder();
    }

    public OFAction actionOutput(int i) {
        return new OFAction();
    }

    public OFMatchBuilder createMatchBuilder() {
        // TODO Auto-generated method stub
        return null;
    }

    public OFGenericMessage readFrom(ChannelBuffer channelBuffer) {
        // TODO Auto-generated method stub
        return null;
    }

    public FlowModBuilder createFlowDeleteBuilder() {
        // TODO Auto-generated method stub
        return null;
    }

    public OFFlowMod createFlowAdd(int cookie, OFMatch outMatch, OFActionOutput ofActionOutput) {
        // TODO Auto-generated method stub
        return null;
    }

    public OFFlowMod createFlowDelete(OFMatch all) {
        // TODO Auto-generated method stub
        return null;
    }

    public FlowStatsRequestBuilder createFlowStatsBuilder() {
        // TODO Auto-generated method stub
        return null;
    }

}
