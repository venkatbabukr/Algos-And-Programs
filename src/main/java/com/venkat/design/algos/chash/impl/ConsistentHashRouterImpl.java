package com.venkat.design.algos.chash.impl;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.stream.Collectors;

import com.venkat.design.algos.chash.ConsistentHashRouter;
import com.venkat.design.algos.chash.Node;
import com.venkat.design.algos.chash.VirtualNode;

public class ConsistentHashRouterImpl<N extends Node> implements ConsistentHashRouter<N> {

	private long ringSize;

	private SortedMap<Long, VirtualNode<N>> ring;

    private Lock ringReadLock;

    private Lock ringWriteLock;

    public ConsistentHashRouterImpl(long ringSize, Collection<N> realNodes, int virtualReplicasCount) {
    	this.ringSize = ringSize;
        this.ring = new TreeMap<>();
        ReadWriteLock ringLock = new ReentrantReadWriteLock(true);
        ringReadLock = ringLock.readLock();
        ringWriteLock = ringLock.writeLock();
        if (realNodes != null) {
            for (N node : realNodes) {
                addNode(node, virtualReplicasCount);
            }
        }
    }

    /*
     * Either have these or have a unique hash generation algorithm like MD5 message digest or so, so that hashes won't collide!
     */
    private Long ringUniqueHash(String ringKey) {
        Random rsalt = new Random();
        Long ringUniqueHash = Math.abs(ringKey.hashCode()) % ringSize;
        while (ring.containsKey(ringUniqueHash))
            ringUniqueHash = Math.abs(ringKey.hashCode() + rsalt.nextInt()) % ringSize;
        return ringUniqueHash;
    }

    private Long ringHash(String ringKey) {
        return Math.abs(ringKey.hashCode()) % ringSize;
    }

    Map<Long, VirtualNode<N>> getRing() {
        return Collections.unmodifiableMap(ring);
    }

    @Override
    public void addNode(N realNode, int numVirtual) {
        int numExisting = getAllVirtualNodes(realNode).size();
        ringWriteLock.lock();
        Random r = new Random();
        for (int i = numExisting; i < numVirtual; i++) {
            VirtualNode<N> vNode = new VirtualNode<>(realNode, i);
            ring.put(ringUniqueHash(vNode.getNodeKeyForRing()) , vNode);
        }
        ringWriteLock.unlock();
    }

    @Override
    public void removeNode(N realNode) {
        ringWriteLock.lock();
        try {
        	for(Iterator<Entry<Long, VirtualNode<N>>> it = ring.entrySet().iterator();
                    it.hasNext(); ) {
                Entry<Long, VirtualNode<N>> e = it.next();
                if (e.getValue().isVirtual(realNode)) {
                    it.remove();
                }
        	}
        } finally {
            ringWriteLock.unlock();
        }
    }

    @Override
    public List<VirtualNode<N>> getAllVirtualNodes(N realNode) {
        ringReadLock.lock();
        List<VirtualNode<N>> allVNodes = ring.values()
                        .stream()
                        .filter(n -> n.isVirtual(realNode))
                        .collect(Collectors.toList());
        ringReadLock.unlock();
        return allVNodes;
    }

    @Override
    public N routeToNode(String key) {
        N serverNode = null;
        ringReadLock.lock();
        if (!ring.isEmpty()) {
            SortedMap<Long, VirtualNode<N>> ringTail = ring.tailMap(ringHash(key));
            Long vnHashVal = ringTail.isEmpty() ? ring.firstKey() : ringTail.firstKey();
            serverNode = ring.get(vnHashVal).getRealNode();
        }
        ringReadLock.unlock();
        return serverNode;
    }

}
