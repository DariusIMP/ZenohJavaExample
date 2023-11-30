package org.example;

import io.zenoh.Session;
import io.zenoh.exceptions.ZenohException;
import io.zenoh.keyexpr.KeyExpr;
import io.zenoh.publication.Publisher;

public class Main {
    public static void main(String[] args) throws ZenohException, InterruptedException {

        System.out.println("Opening session...");
        try (Session session = Session.open()) {
            try (KeyExpr keyExpr = KeyExpr.tryFrom("demo/example/zenoh-java-pub")) {
                System.out.println("Declaring publisher on '" + keyExpr + "'...");
                try (Publisher publisher = session.declarePublisher(keyExpr).res()) {
                    String payload = "Pub from Java!";
                    int idx = 0;
                    while (true) {
                        Thread.sleep(1000);
                        System.out.println("Putting Data ('" + keyExpr + "': '[" + String.format("%4s", idx) + "] " + payload + "')...");
                        publisher.put(payload).res();
                        idx++;
                    }
                }
            }
        }
    }
}
