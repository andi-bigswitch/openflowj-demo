package org.openflow.proto;

import java.util.Iterator;

public interface OFFlowStatsReply extends OFMessage, Iterable<FlowStat> {

    @Override
    public Iterator<FlowStat> iterator();
}
