package com.tiger.akka;

import akka.actor.AbstractActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

public class HelloActor extends AbstractActor {
    private final LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

    //定义消息处理
    @Override
    public Receive createReceive() {
        return receiveBuilder().match(String.class, s -> {
            log.info("receive string message:{}", s);
        }).matchAny(o -> {
            log.info("receive unknown message");
        }).build();
    }
}
