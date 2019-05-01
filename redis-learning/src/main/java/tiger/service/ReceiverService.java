package tiger.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ReceiverService {

    public void handleMessage(String message, String channel) {
        log.info("channel:" + channel);
        log.info("receive message:" + message);

    }
}
