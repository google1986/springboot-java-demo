package com.htzw.zookeeper;

import org.apache.zookeeper.*;
import org.apache.zookeeper.ZooDefs.Ids;

import java.io.IOException;

/**
 * @author Administrator
 */
public class DistributeServer {

    public static void main(String[] args) throws Exception {

        DistributeServer server = new DistributeServer();

        // 1 连接zookeeper集群
        server.getConnect();

        // 2 注册节点
        server.regist("10.0.1.55");

        // 3 业务逻辑处理
        server.business();
    }

    private void business() throws InterruptedException {

        Thread.sleep(Long.MAX_VALUE);
    }

    private void regist(String hostname) throws KeeperException, InterruptedException {

        String path = zkClient.create("/servers/server", hostname.getBytes(),
				Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);

        System.out.println(hostname + "is online ---psth:"+path);

    }

    private static final String CONNECT_STRING = "CentOS001:2181,CentOS004:2181,CentOS005:2181";
    private static final int SESSION_TIMEOUT = 2000;
    private ZooKeeper zkClient;

    private void getConnect() throws IOException {

        zkClient = new ZooKeeper(CONNECT_STRING, SESSION_TIMEOUT, event -> {

		});
    }
}
