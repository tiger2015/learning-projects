package tiger;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

@SpringBootApplication
@Slf4j
public class MyApplication implements CommandLineRunner {

    private static final ReentrantLock LOCK = new ReentrantLock();
    private static final Condition STOP = LOCK.newCondition();
    private static ConfigurableApplicationContext context;

    public static void main(String[] args) {
        context = SpringApplication.run(MyApplication.class, args);
        log.info("start");
        addHook(context);
        try {
            LOCK.lock();
            STOP.await();
        } catch (InterruptedException e) {
            log.warn(" service   stopped, interrupted by other thread!", e);
        } finally {
            LOCK.unlock();
        }
    }

    @Override
    public void run(String... args) throws Exception {  //在初始化之前执行，即在main函数之前执行
        log.info("run function");
    }


    private static void addHook(ConfigurableApplicationContext context) {
        // JVM进程结束时会执行
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                context.stop();
            } catch (Exception e) {
                log.error("StartMain stop exception ", e);
            }

            log.info("jvm exit, all service stopped.");
            try {
                LOCK.lock();
                STOP.signal();
            } finally {
                LOCK.unlock();
            }
        }, "StartMain-shutdown-hook"));


    }
}
