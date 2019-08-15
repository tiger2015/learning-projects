package tiger;

import lombok.extern.slf4j.Slf4j;
import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Tuple;

import java.util.Map;

@Slf4j
public class UserStatisticBolt extends BaseRichBolt {
    private TopologyContext context;
    private OutputCollector collector;
    private long total;
    private double sumAge;

    @Override
    public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {
        this.collector = collector;
        this.context = context;
        total = 0;
        sumAge = 0;
    }

    @Override
    public void execute(Tuple input) {
        total++;
        sumAge += input.getInteger(1);
        log.info(String.format("total:%s,avg:%.4f", total, sumAge / total));
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {

    }
}
