package org.openflow.proto;

import java.util.Collections;
import java.util.List;

import org.jboss.netty.buffer.ChannelBuffer;

public class OFActionListReaderVer10 {

    public List<OFAction> read(ChannelBuffer buf, int index, int length) {
        if (length == 0)
            return Collections.emptyList();

        int end = index + length;
        return null;
    }

}
