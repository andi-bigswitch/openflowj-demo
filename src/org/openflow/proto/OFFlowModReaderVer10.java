package org.openflow.proto;

import java.util.List;

import org.jboss.netty.buffer.ChannelBuffer;

public class OFFlowModReaderVer10 implements OFMessageReader {

    private static final int MIN_LENGTH = 72;
    private static final short OFPT_FLOW_MOD = 0;
    private static final byte MY_VERSION = 1;

    private final OFMatchReaderVer10 matchReader = new OFMatchReaderVer10();
    private final OFActionListReaderVer10 actionListReader = new OFActionListReaderVer10();

    @Override
    public OFGenericMessage read(ChannelBuffer buf) {
        byte version = buf.getByte(0);

        if (version != MY_VERSION)
            throw new IllegalArgumentException("Cannot support version " + version);
        short type = buf.getUnsignedByte(1);

        if (type != OFPT_FLOW_MOD)
            throw new IllegalArgumentException("Wrong message type");

        int len = buf.getUnsignedShort(2);
        if (len < 72)
            throw new IllegalArgumentException("Wrong length "+ len + " < 72");

        int xid = buf.getInt(4);
        OFMatch match = matchReader.read(buf, 6);
        long cookie = buf.getInt(46);

        int idleTimeOut = buf.getUnsignedShort(48);

        /** etc.etc. */
        int outport = buf.getUnsignedShort(62);

        List<OFAction> actions = actionListReader.read(buf, 72, len - MIN_LENGTH);

        return new OFFlowModVer10(xid, cookie, match, idleTimeOut, outport, actions);
    }

}
