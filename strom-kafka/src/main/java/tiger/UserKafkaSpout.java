package tiger;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.LongDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.storm.kafka.spout.KafkaSpout;
import org.apache.storm.kafka.spout.KafkaSpoutConfig;
import org.apache.storm.kafka.spout.SimpleRecordTranslator;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Values;
import tiger.module.User;
import tiger.module.UserJsonDeserializer;

public class UserKafkaSpout {
    private static final String bootStrapServer = "192.168.100.201:9092,192.168.100.202:9092,192.168.100.203:9092";
    private static final String topic = "user";
    private static final KafkaSpoutConfig<Long, User> kafkaSpoutConfig;

    static {
        KafkaSpoutConfig.Builder<Long, User> builder = new KafkaSpoutConfig.Builder<>(bootStrapServer, LongDeserializer.class, UserJsonDeserializer.class, topic);
        builder.setProp(ConsumerConfig.GROUP_ID_CONFIG, "group1");
        builder.setProp(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");
        builder.setFirstPollOffsetStrategy(KafkaSpoutConfig.FirstPollOffsetStrategy.LATEST);
        builder.setRecordTranslator(new SimpleRecordTranslator<>((r) -> new Values(r.key(), r.value()), new Fields("id", "user")));
        kafkaSpoutConfig = builder.build();
    }

    public static KafkaSpout getInstance() {
        return new KafkaSpout<>(kafkaSpoutConfig);
    }
}
