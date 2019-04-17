package com.tiger.akka;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.routing.BalancingPool;

import java.util.concurrent.TimeUnit;

public class App {

    public static void main(String[] args) {
        ActorSystem actorSystem = ActorSystem.create("test");
        ActorRef testActor = actorSystem.actorOf(new BalancingPool(1).props(Props.create(HelloActor.class)), "testActor");
        new Thread(()->{
            while(true){
                testActor.tell("hello", ActorRef.noSender());
                try {
                    TimeUnit.MILLISECONDS.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }
}
