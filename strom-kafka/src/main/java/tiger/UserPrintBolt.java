package tiger;

import lombok.extern.slf4j.Slf4j;
import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;
import tiger.module.User;

import java.util.Map;

@Slf4j
public class UserPrintBolt extends BaseRichBolt {
    private TopologyContext context;
    private OutputCollector collector;

    @Override
    public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {
        this.context = context;
        this.collector = collector;
    }

    @Override
    public void execute(Tuple input) {
        User user = (User) input.getValue(1);
        log.info("user:" + user.getId() + ", name:" + user.getName() + ", age:" + user.getAge());
        this.collector.emit(new Values(user.getId(), user.getAge()));
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        declarer.declare(new Fields("id", "age"));
    }
}
