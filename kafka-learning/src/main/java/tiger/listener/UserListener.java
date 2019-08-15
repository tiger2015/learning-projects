package tiger.listener;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.listener.MessageListener;
import org.springframework.kafka.support.Acknowledgment;
import tiger.module.User;

import java.util.List;

@Slf4j
public class UserListener implements MessageListener<Long, User> {
    @Override
    public void onMessage(ConsumerRecord<Long, User> data) {
        log.info(String.format("receive user info:id=%d, name=%s, age=%d", data.value().getId(),data.value().getName(),data.value().getAge()));
    }


   // @KafkaListener(id = "client", topics = "test",containerFactory = "batchContainerFactory")
    public void batchMessage(List<ConsumerRecord<Long, User>> data){
        log.info("receive message count:"+data.size());
    }


    @KafkaListener(id = "client",topics = {"test"}, containerFactory = "ackContainerFactory")
    public void onAckMessage(ConsumerRecord<Long, User> data, Acknowledgment ack){
        log.info(String.format("receive user info:id=%d, name=%s, age=%d", data.value().getId(),data.value().getName(),data.value().getAge()));
        ack.acknowledge();
    }



}
