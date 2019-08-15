package tiger;

import lombok.extern.slf4j.Slf4j;
import org.apache.storm.spout.SpoutOutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichSpout;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Values;

import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

@Slf4j
public class MySpout extends BaseRichSpout {
    private TopologyContext context;
    private SpoutOutputCollector collector;
    private static AtomicLong count = new AtomicLong(0);

    @Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
        outputFieldsDeclarer.declare(new Fields("message", "id"));
    }

    @Override
    public void open(Map map, TopologyContext topologyContext, SpoutOutputCollector spoutOutputCollector) {
        this.context = topologyContext;
        this.collector = spoutOutputCollector;
        log.info("init my spout");
    }

    @Override
    public void nextTuple() {
        long value = count.getAndIncrement();
        this.collector.emit(new Values("msg-" + value, value % 15));
        log.info("send message:" + "msg-" + value);
        try {
            TimeUnit.MILLISECONDS.sleep(500);
        } catch (InterruptedException e) {
            log.error("send message error", e);
        }
    }
}
