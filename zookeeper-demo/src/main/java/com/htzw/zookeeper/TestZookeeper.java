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
    private static final String CONNECT_STRING = "CentOS001:2181,CentOS004:2181,CentOS005:2181";
    private int SESSION_TIMEOUT = 2000;
    private ZooKeeper zkClient;

    @Before
    public void init() throws IOException {

        zkClient = new ZooKeeper(CONNECT_STRING, SESSION_TIMEOUT, new Watcher() {

            @Override
            public void process(WatchedEvent event) {

				System.out.println("---------start----------");
                // 收到事件通知后的回调函数（用户的业务逻辑）
                System.out.println("====================:"+event.getType() + "--" + event.getPath());
				List<String> children;
                // 再次启动监听
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

    /**
     * 1 创建节点
     * @throws KeeperException
     * @throws InterruptedException
     */
    @Test
    public void createNode() throws KeeperException, InterruptedException {
        String path = zkClient.create("/htzw", "hangtian".getBytes(), Ids.OPEN_ACL_UNSAFE,
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
        // 延时阻塞
        Thread.sleep(Long.MAX_VALUE);
    }

    /**
     * 3 判断节点是否存在
     * @throws KeeperException
     * @throws InterruptedException
     */
    @Test
    public void exist() throws KeeperException, InterruptedException {
        Stat stat = zkClient.exists("/htzw", false);
        System.out.println(stat == null ? "not exist" : "exist");
    }
}
