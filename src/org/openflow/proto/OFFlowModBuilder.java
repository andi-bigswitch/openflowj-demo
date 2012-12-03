package org.openflow.proto;

import java.util.ArrayList;
import java.util.List;

public class OFFlowModBuilder {
    private int cookie;
    private boolean cookieSet;

    private int tableId;
    private boolean tableIdSet;

    private OFMatch match;

    private int idleTimeOut;
    private boolean idleTimeOutSet;

    private int inport;
    private boolean inportSet;

    private List<OFAction> actions;

    private final OFFlowModVer10 sourceObject;


    public OFFlowModBuilder(OFFlowModVer10 source) {
        this.sourceObject = source;
    }

    public OFFlowModBuilder() {
        sourceObject = null;
    }

    public OFFlowModBuilder setCookie(int i) {
        this.cookie = i;
        this.cookieSet = true;
        return this;
    }

    public OFFlowModBuilder tableId(int i) {
        this.tableId = i;
        this.tableIdSet = true;
        return this;
    }

    public OFFlowModBuilder setMatch(OFMatch match) {
        this.match = match;
        return this;
    }

    public OFFlowModBuilder idleTimeOut(int i) {
        this.idleTimeOut = i;
        this.idleTimeOutSet = true;
        return this;
    }

    public OFFlowModBuilder setInport(int i) {
        this.inport = i;
        this.inportSet = true;
        return this;
    }

    public OFFlowModBuilder addAction(OFAction action) {
        if (actions == null) {
            if (sourceObject != null)
                actions = new ArrayList<OFAction>(sourceObject.getActions());
            else
                actions = new ArrayList<OFAction>();
        }
        actions.add(action);
        return this;
    }

    public OFFlowMod getMessage() {
        return new OFFlowModVer10(
                cookieSet ? cookie : sourceObject != null ? sourceObject.getCookie() : null,
                match != null ? match : sourceObject != null ? sourceObject.getMatch() : null,
                idleTimeOutSet ? idleTimeOut : sourceObject != null ? sourceObject.getIdleTimeOut() : -1,
                inportSet ? inport : sourceObject != null ? sourceObject.getInport() : -1,
                actions != null ? actions : sourceObject != null ? sourceObject.getActions() : null
                );
    }


}
