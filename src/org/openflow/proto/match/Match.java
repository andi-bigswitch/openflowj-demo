package org.openflow.proto.match;


public interface Match {
    public <F> F getField(MatchField<F, ?> match);
    public <M> M getFieldWithWildcard(MatchField<?, M> match);

    public boolean supportsField(MatchField<?, ?> field);

    public boolean supportsWildcards(MatchField<?, ?> field);

    public MatchBuilder getBuilder();
}
