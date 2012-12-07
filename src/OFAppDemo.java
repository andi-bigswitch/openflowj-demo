
import static org.openflow.proto.match.MatchField.ETH_DST;
import static org.openflow.proto.match.MatchField.IN_PORT;
import static org.openflow.proto.match.MatchField.IPV6_DST;

import org.jboss.netty.buffer.ChannelBuffer;
import org.openflow.proto.FlowStat;
import org.openflow.proto.OFActionFactory;
import org.openflow.proto.OFFactory;
import org.openflow.proto.OFFlowMod;
import org.openflow.proto.OFFlowModBuilder;
import org.openflow.proto.OFFlowStatsReply;
import org.openflow.proto.OFFlowStatsRequest;
import org.openflow.proto.OFGenericMessage;
import org.openflow.proto.OFMessage;
import org.openflow.proto.OFMessageFactory;
import org.openflow.proto.OFPacketIn;
import org.openflow.proto.OFVersion;
import org.openflow.proto.match.Match;
import org.openflow.proto.match.MatchBuilder;
import org.openflow.proto.match.OFMatchFactory;
import org.openflow.proto.type.IPv6;
import org.openflow.proto.type.IPv6Mask;
import org.openflow.proto.type.MacAddressMask;
import org.openflow.proto.type.OFPort;

public class OFAppDemo {

    private Statistics myStatistics;

    OFFactory factory = OFFactory.forVersion(OFVersion.OF_1_0);

    OFMessageFactory messageFactory = factory.getMessageFactory();
    OFMatchFactory matchFactory = factory.getMatchFactory();
    OFActionFactory actionFactory = factory.getActionFactory();

    public void myController(ChannelBuffer channelBuffer) {

        // messages are immutable and can be reused
        // unset xid's are inserted during wire conversion
        // either use a builder to create your message, or one of the factory
        // convenience methods
        OFFlowMod deleteAll = messageFactory.createFlowDelete(matchFactory.forWildcardAll());
        send(deleteAll);

        // keep this reoccuring request around
        OFFlowStatsRequest flowStatsForPort15 =
                messageFactory.createFlowStatsBuilder().setOutPort(15)
                        .setMatch(matchFactory.forWildcardAll()).getMessage();

        boolean enoughTimePassed = false;

        while (true) {
            // read from wire
            OFGenericMessage message = messageFactory.readFrom(channelBuffer);

            switch (message.getType()) {
                case ECHO_REQUEST:
                    // genericMessage has 'asFoobar' converters
                    // requests have 'createReply' factories that create
                    // matching replies
                    send(message.asEchoRequest().createReply());
                    break;
                case PACKET_IN:
                    handlePacketIn(channelBuffer, message.asPacketIn());
                    break;
                case FLOW_STATS_REPLY:
                    updateStats(message.asFlowStatsReply());
                    break;
                case FLOW_MOD:
                    throw new IllegalArgumentException(
                            "WAT - a switch sending me a flow_mod?");
            }

            // every 10 secs or so
            if (enoughTimePassed)
                send(flowStatsForPort15);
        }

    }

    private void updateStats(OFFlowStatsReply flowStats) {
        for (FlowStat flowStat : flowStats) {
            myStatistics.update(flowStat);
        }
    }

    private void handlePacketIn(ChannelBuffer channelBuffer, OFPacketIn packetIn) {
        MatchBuilder builder = matchFactory.createBuilder();

        if (builder.supportsField(IN_PORT)) {
            builder.setField(IN_PORT, OFPort.of(12));
        }

        if (builder.supportsWildcards(ETH_DST)) {
            builder.setMaskedMatch(ETH_DST,
                    MacAddressMask.of("01:02:03:04:05:00", "ff:ff:ff:00:ff:00"));
        }

        if (builder.supportsWildcards(IPV6_DST)) {
            builder.setMaskedMatch(IPV6_DST, IPv6Mask.of("00:01:02::/64"));
        }


        // matches are immutable and could be reused / cached
        // this uses a one-shot convenience method from the matchFactory
        Match outMatch = builder.getMatch();

        IPv6 field = outMatch.getField(IPV6_DST);

        int cookie = 123;

        // use one-shot message creation method from factory
        OFFlowMod flowMod =
                messageFactory.createFlowAdd(cookie, outMatch,
                        actionFactory.createActionOutput(10));

        // alternatively:
        // FlowBuilders are mutable,t single threaded.
        // they are dirt cheap to construct
        OFFlowModBuilder fb = messageFactory.createFlowModBuilder();
        fb.setCookie(123);
        fb.setMatch(outMatch);
        fb.addAction(messageFactory.actionOutput(10));
        flowMod = fb.getMessage();

        send(flowMod);

        // can use the builder inline to modify an object
        OFFlowMod modifiedFlowMod = flowMod.createBuilder().setInport(12).getMessage();
        send(modifiedFlowMod);
    }

    private void send(OFMessage flowMod) {
        // TODO Auto-generated method stub

    }

}
