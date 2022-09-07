package com.venkat.design.algos.chash.impl;

import java.util.StringJoiner;

import com.venkat.design.algos.chash.Node;
import com.venkat.utils.NetworkUtils;

public class ServerNode implements Node {

    private final String ipAddr;

    private final int port;

    private final String ringNodeKey;

    public ServerNode(String ip, int p) {
        if (!NetworkUtils.isValidIp(ip))
            throw new IllegalArgumentException("IP address is not valid!");
        if (p < NetworkUtils.STANDARD_PORT_NUMBERS_MAX)
            throw new IllegalArgumentException("Port number has to be above " + NetworkUtils.STANDARD_PORT_NUMBERS_MAX);
        this.ipAddr = ip;
        this.port = p;
        this.ringNodeKey = new StringJoiner(":")
                               .add(ipAddr)
                               .add(String.valueOf(port))
                               .toString();
    }

    public String getIpAddr() {
        return ipAddr;
    }

    public int getPort() {
        return port;
    }

    @Override
    public String getNodeKeyForRing() {
        return ringNodeKey;
    }

    public String toString() {
        return String.format("SN: %s", getNodeKeyForRing());
    }

}
