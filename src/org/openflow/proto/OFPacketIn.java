package org.openflow.proto;

import org.openflow.proto.match.Match;

public interface OFPacketIn extends OFMessage {
    public Match getMatch();
}
