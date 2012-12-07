package org.openflow.proto.match;

import org.openflow.proto.type.IPv4;
import org.openflow.proto.type.IPv4Mask;
import org.openflow.proto.type.IPv6;
import org.openflow.proto.type.IPv6Mask;
import org.openflow.proto.type.MacAddress;
import org.openflow.proto.type.MacAddressMask;
import org.openflow.proto.type.NoMatch;
import org.openflow.proto.type.OFPort;

public class MatchField<F, M> {
    private final Class<F> clazz;
    private final String name;
    private final Class<M> matchClazz;

    public MatchField(Class<F> clazz, Class<M> matchClazz, String name) {
        this.clazz = clazz;
        this.matchClazz = matchClazz;
        this.name = name;
    }

    public final static MatchField<OFPort, NoMatch> IN_PORT = new MatchField<OFPort, NoMatch>(
            OFPort.class, NoMatch.class, "in_port");

    public final static MatchField<MacAddress, MacAddressMask> ETH_DST =
            new MatchField<MacAddress, MacAddressMask>(MacAddress.class,
                    MacAddressMask.class, "eth_dst");

    public final static MatchField<MacAddress, MacAddressMask> ETH_SRC =
            new MatchField<MacAddress, MacAddressMask>(MacAddress.class,
                    MacAddressMask.class, "eth_src");

    public final static MatchField<IPv4, IPv4Mask> IPV4_DST =
            new MatchField<IPv4, IPv4Mask>(IPv4.class, IPv4Mask.class, "ipv4_dst");

    public final static MatchField<IPv4, IPv4Mask> IPV4_SRC =
            new MatchField<IPv4, IPv4Mask>(IPv4.class, IPv4Mask.class, "ipv4_src");

    public final static MatchField<IPv6, IPv6Mask> IPV6_DST =
            new MatchField<IPv6, IPv6Mask>(IPv6.class, IPv6Mask.class, "ipv6_dst");

    public final static MatchField<IPv6, IPv6Mask> IPV6_SRC =
            new MatchField<IPv6, IPv6Mask>(IPv6.class, IPv6Mask.class, "ipv6_src");

    public Class<F> getClazz() {
        return clazz;
    }

    public String getName() {
        return name;
    }

    public Class<M> getMatchClazz() {
        return matchClazz;
    }

}