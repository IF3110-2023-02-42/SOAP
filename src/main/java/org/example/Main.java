package org.example;

import javax.xml.ws.Endpoint;

public class Main {
    public static void main(String[] args) {
        try {
            Endpoint.publish("http://0.0.0.0:6060/ws/sabun", new TestingService());
            System.out.println("Gacor");
        } catch (Exception e){
            System.out.println("Something Wlong");
        }
    }
}