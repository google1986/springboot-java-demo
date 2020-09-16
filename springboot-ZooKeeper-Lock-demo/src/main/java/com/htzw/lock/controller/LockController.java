package com.htzw.lock.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 创建一个用于 测试的Controller类
 * 来提供测试接口，里面使用线程池来模拟多个线程调用，并且使用 Curator 锁方法实现 分布式锁。
 *
 *
 * Zookeeper Lock 测试接口
 *
 * @author Administrator
 */
@Slf4j
@RestController
public class LockController {

    /**
     * curatorFramework对象
     */
    @Autowired
    CuratorFramework curatorFramework;

    /**
     * 线程池
     */
    ExecutorService executor = Executors.newFixedThreadPool(10);

    @GetMapping("/lock")
    public void lockTest() {
        for (int i = 0; i < 1000; i++) {
            executor.submit(() -> {
                try {
                    // 创建锁对象
                    InterProcessMutex interProcessMutex = new InterProcessMutex(curatorFramework, "/lock-space123");
                    // 获取锁
                    interProcessMutex.acquire(10, TimeUnit.SECONDS);
                    // 如果获取锁成功，则执行对应逻辑
                    log.info(Thread.currentThread().getName() + "获取分布式锁，执行对应逻辑1");
                    log.info(Thread.currentThread().getName() + "获取分布式锁，执行对应逻辑2");
                    log.info(Thread.currentThread().getName() + "获取分布式锁，执行对应逻辑3");
                    // 释放锁
                    interProcessMutex.release();
                    Thread.sleep(20000);
                } catch (Exception e) {
                    log.error("", e);
                }
            });
        }
    }
}
