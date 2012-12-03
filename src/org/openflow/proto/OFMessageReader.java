package org.openflow.proto;

import org.jboss.netty.buffer.ChannelBuffer;

public interface OFMessageReader {
    OFGenericMessage read(ChannelBuffer buf);
}
