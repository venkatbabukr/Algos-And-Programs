package com.venkat.design.algos.chash;

import java.util.List;

public interface ConsistentHashRouter<N extends Node> {

    void addNode(N realNode, int numVirtual);

    void removeNode(N realNode);

    List<VirtualNode<N>> getAllVirtualNodes(N realNode);

    N routeToNode(String objKey);

}
