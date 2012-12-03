package org.openflow.proto;


public interface OFGenericMessage extends OFMessage {

    public OFPacketIn asPacketIn();

    public OFEchoRequest asEchoRequest();

    public OFFlowStatsReply asFlowStatsReply();

}
