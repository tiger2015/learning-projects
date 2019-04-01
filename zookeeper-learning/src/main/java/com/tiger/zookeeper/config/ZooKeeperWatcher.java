package com.tiger.zookeeper.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.CountDownLatch;

/**
 * @Auther: Administrator
 * @Date: 2019/3/30 15:36
 * @Description:
 */
@Slf4j
@Component
public class ZooKeeperWatcher implements Watcher {
    private CountDownLatch countDownLatch;

    @PostConstruct
    public void init(){
        countDownLatch = new CountDownLatch(1);
    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        if(Event.KeeperState.SyncConnected == watchedEvent.getState()){
            Event.EventType type = watchedEvent.getType();
            switch (type) {
                case NodeCreated:
                    log.info("create node");
                    break;
                case NodeDeleted:
                    log.info("delete node");
                    break;
                case NodeDataChanged:
                    log.info("change node");
                    break;
                case NodeChildrenChanged:
                    log.info("node children changed");
                    break;
                case None:
                    // 成功连接上
                    countDownLatch.countDown();
                    log.info("none");
                    break;
            }
        }
    }

    public void startWatch(){
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
