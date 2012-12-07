package org.openflow.proto;

import java.util.Collections;
import java.util.List;

import org.openflow.proto.match.Match;

class OFFlowModVer10 extends OFMessageBase implements OFFlowMod {
    private final long cookie;
    private final Match match;

    private final int idleTimeOut;
    private final int outport;
    private final List<OFAction> actions;

    OFFlowModVer10(int xid, long cookie, Match match, int idleTimeOut, int inport,
            List<OFAction> actions) {
        super(xid);
        this.cookie = cookie;
        if (match == null)
            throw new NullPointerException("OFFlowMod (Version 1.0): Must provide match");
        this.match = match;
        this.idleTimeOut = idleTimeOut;
        this.outport = inport;

        if (actions != null)
            this.actions = Collections.unmodifiableList(actions);
        else
            this.actions = Collections.emptyList();
    }

    OFFlowModVer10(long cookie, Match match, int idleTimeOut, int inport,
            List<OFAction> actions) {
        super();
        this.cookie = cookie;
        if (match == null)
            throw new NullPointerException("OFFlowMod (Version 1.0): Must provide match");
        this.match = match;
        this.idleTimeOut = idleTimeOut;
        this.outport = inport;

        if (actions != null)
            this.actions = Collections.unmodifiableList(actions);
        else
            this.actions = Collections.emptyList();
    }

    /**
     * this constructor is super-ugly. That's why you never invoke it directly.
     * Use a builder, or the wireformat reader.
     *
     * @param cookie
     * @param tableId
     * @param match
     * @param idleTimeOut
     * @param inport
     * @param actions
     */
    OFFlowModVer10(int cookie, Match match, int idleTimeOut,
            int inport, List<OFAction> actions) {
        super();
        this.cookie = cookie;
        if (match == null)
            throw new NullPointerException("OFFlowMod (Version 1.0): Must provide match");
        this.match = match;
        this.idleTimeOut = idleTimeOut;
        this.outport = inport;

        if (actions != null)
            this.actions = Collections.unmodifiableList(actions);
        else
            this.actions = Collections.emptyList();
    }

    public long getCookie() {
        return cookie;
    }

    public Match getMatch() {
        return match;
    }

    public int getIdleTimeOut() {
        return idleTimeOut;
    }

    public int getInport() {
        return outport;
    }

    public List<OFAction> getActions() {
        return actions;
    }

    @Override
    public OFType getType() {
        return OFType.FLOW_MOD;
    }

    @Override
    public OFFlowModBuilder createBuilder() {
        return new OFFlowModBuilder(this);
    }

}
