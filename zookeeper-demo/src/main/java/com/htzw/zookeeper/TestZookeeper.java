package com.htzw.zookeeper;

import org.apache.zookeeper.*;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.data.Stat;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

/**
 * @author Administrator
 */
public class TestZookeeper {

    //注意：在connectString中，不能有空格，否则会报java.net.UnknownHostException异常
    private String connectString = "CentOS001:2181,CentOS004:2181,CentOS005:2181";
    private int sessionTimeout = 2000;
    private ZooKeeper zkClient;

    @Before
    public void init() throws IOException {

        zkClient = new ZooKeeper(connectString, sessionTimeout, new Watcher() {

            @Override
            public void process(WatchedEvent event) {

				System.out.println("---------start----------");
				List<String> children;
				try {
					children = zkClient.getChildren("/", true);

					for (String child : children) {
						System.out.println(child);
					}
					System.out.println("---------end----------");
				} catch (KeeperException |InterruptedException e) {
					e.printStackTrace();
				}
            }
        });
    }

    // 1 创建节点
    @Test
    public void createNode() throws KeeperException, InterruptedException {
        String path = zkClient.create("/atguigu", "dahaigezuishuai".getBytes(), Ids.OPEN_ACL_UNSAFE,
                CreateMode.PERSISTENT);
        System.out.println(path);
    }

    /**
     * 2 获取子节点 并监控节点的变化
     * @throws KeeperException
     * @throws InterruptedException
     */
    @Test
    public void getDataAndWatch() throws KeeperException, InterruptedException {
        List<String> children = zkClient.getChildren("/", true);
        for (String child : children) {
            System.out.println(child);
        }
        Thread.sleep(Long.MAX_VALUE);
    }

    /**
     * 3 判断节点是否存在
     * @throws KeeperException
     * @throws InterruptedException
     */
    @Test
    public void exist() throws KeeperException, InterruptedException {
        Stat stat = zkClient.exists("/atguigu", false);
        System.out.println(stat == null ? "not exist" : "exist");
    }
}
