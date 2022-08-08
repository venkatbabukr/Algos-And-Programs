package com.venkat.design.algos.chash;

import com.venkat.utils.ext.ObjectsExt;

public class VirtualNode<N extends Node> implements Node {

    private final N myRealNode;

    private final int replicaIdx;

    public VirtualNode(N rn, int replicaIdx) {
        if (rn == null)
            throw new IllegalArgumentException("Real node can't be null!");
        if (replicaIdx < 0)
        	throw new IllegalArgumentException("Replica index can't be negative!");
        this.myRealNode = rn;
        this.replicaIdx = replicaIdx;
    }

    @Override
    public String getNodeKeyForRing() {
        return String.join(":",
                            myRealNode.getNodeKeyForRing(),
                            String.valueOf(replicaIdx));
    }

    public boolean isVirtual(N node) {
        return ObjectsExt.nullSafeEquals(myRealNode, node, Node::getNodeKeyForRing);
    }

    public N getRealNode() {
    	return myRealNode;
    }

}
