package com.tiger.akka;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.routing.BalancingPool;

public class App {

    public static void main(String[] args) {
        ActorSystem actorSystem = ActorSystem.create("test");
        ActorRef testActor = actorSystem.actorOf(new BalancingPool(3).props(Props.create(HelloActor.class)), "testActor");
        testActor.tell(1, ActorRef.noSender());
    }
}
