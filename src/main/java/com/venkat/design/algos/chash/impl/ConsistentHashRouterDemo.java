package com.venkat.design.algos.chash.impl;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import com.venkat.design.algos.chash.ConsistentHashRouter;
import com.venkat.utils.NetworkUtils;

public class ConsistentHashRouterDemo {

    public static void main(String[] args) {
        List<ServerNode> serverNodes = new Random()
                                            .ints(NetworkUtils.STANDARD_PORT_NUMBERS_MAX, 10000)
                                            .limit(4)
                                            .boxed()
                                            .map(portNum -> new ServerNode(NetworkUtils.randomIpAddr(), portNum))
                                            .collect(Collectors.toList());
        ConsistentHashRouter<ServerNode> testCHR = new ConsistentHashRouterImpl<>(100, serverNodes, 10);
        System.out.format("CHR Ring: %s%n", ((ConsistentHashRouterImpl<ServerNode>)testCHR).getRing());

        for (int i = 0 ; i < 10 ; i++) {
            String randomIp = NetworkUtils.randomIpAddr();
            System.out.format("Routing %s to node %s%n", randomIp, testCHR.routeToNode(randomIp));
        }
    }

}
