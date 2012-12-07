package org.openflow.proto.match;


public interface MatchBuilder extends Match {
    public <F> void setField(MatchField<F, ?> match, F value);
    public <M> void setMaskedMatch(MatchField<?, M> match, M value);

    public Match getMatch();
}