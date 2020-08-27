package com.htzw.zookeeper;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 */
public class DistributeClient {

    private String CONNECT_STRING = "CentOS001:2181,CentOS004:2181,CentOS005:2181";
    private int SESSION_TIMEOUT = 2000;
    private ZooKeeper zkClient;

    /**
     * 业务功能
     * @throws InterruptedException
     */
    private void business() throws InterruptedException {
        System.out.println("client is working ...");
        Thread.sleep(Long.MAX_VALUE);
    }

    /**
     * 获取服务器列表信息
     * @throws KeeperException
     * @throws InterruptedException
     */
    private void getServerList() throws KeeperException, InterruptedException {
        //获取服务器子节点信息，并且对父节点进行监听
        List<String> children = zkClient.getChildren("/servers", true);
        // 存储服务器节点主机名称集合
        ArrayList<String> hosts = new ArrayList<>();
        // 遍历所有节点，获取节点中的主机名称信息
        for (String child : children) {
            byte[] data = zkClient.getData("/servers/" + child, false, null);
            hosts.add(new String(data));
        }
        // 将所有在线主机名称打印到控制台
        System.out.println("*****************************");
        System.out.println(hosts);
        System.out.println("*****************************");
    }
    /**
     * 创建到zk的客户端连接
     * @throws IOException
     */
    private void getConnect() throws IOException {
        zkClient = new ZooKeeper(CONNECT_STRING, SESSION_TIMEOUT, event -> {
            // 再次启动监听
            try {
                getServerList();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
    public static void main(String[] args) throws Exception {

        DistributeClient client = new DistributeClient();
        // 1 获取zookeeper集群连接
        client.getConnect();
        // 2 注册监听
        client.getServerList();
        // 3 业务逻辑处理
        client.business();
    }
}
