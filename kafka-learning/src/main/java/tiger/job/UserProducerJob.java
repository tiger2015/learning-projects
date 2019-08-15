package tiger.job;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import tiger.module.User;
import tiger.service.UserService;

import java.util.Random;

@Slf4j
public class UserProducerJob {
    @Autowired
    private UserService userService;
    private Random random = new Random();

    public void sendMessage() throws InterruptedException {
        long l = random.nextLong();
        User user = new User();
        user.setId(l);
        user.setAge(18 + random.nextInt(18));
        user.setName("user-" + user.getId());
        userService.sendToKafka(user);
        log.info("send user info");
    }
}
