package tiger;

import lombok.extern.slf4j.Slf4j;
import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Tuple;

import java.util.Map;

@Slf4j
public class MyBolt extends BaseRichBolt {
    private TopologyContext context;
    private OutputCollector collector;

    @Override
    public void prepare(Map map, TopologyContext topologyContext, OutputCollector outputCollector) {
        this.context = topologyContext;
        this.collector = outputCollector;
        log.info("init bolt");
    }

    @Override
    public void execute(Tuple tuple) {
        String message = tuple.getStringByField("message");
        long id = tuple.getLongByField("id");
        log.info("receive message:" + message + ", id:" + id);
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
    }
}
