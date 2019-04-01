
import com.tiger.zookeeper.config.ZooKeeperConfig;
import com.tiger.zookeeper.config.ZooKeeperWatcher;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ZooKeeperConfig.class);
        ZooKeeper zk = context.getBean(ZooKeeper.class);
        ZooKeeperWatcher watcher = context.getBean(ZooKeeperWatcher.class);
        try {
            zk.register(watcher);
            watcher.startWatch();
            zk.exists("/test",true);
            zk.delete("/test",1);
            zk.delete("/test/node1",1);
            zk.create("/test","test".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            zk.create("/test/node1","node1".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
