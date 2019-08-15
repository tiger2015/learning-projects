package tiger.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import tiger.module.User;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    private KafkaTemplate<Long, User> kafkaTemplate;

    public void sendToKafka(User user) {
        ListenableFuture<SendResult<Long, User>> future = kafkaTemplate.sendDefault(user.getId(), user);
        future.addCallback(new ListenableFutureCallback<SendResult<Long, User>>() {
            @Override
            public void onFailure(Throwable ex) {
                log.info("send message errot", ex);
            }
            @Override
            public void onSuccess(SendResult<Long, User> result) {
                log.info("send message success");
            }
        });
    }
}
