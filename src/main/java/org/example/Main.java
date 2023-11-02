package org.example;

import javax.xml.ws.Endpoint;

public class Main {
    public static void main(String[] args) {
        try {
            Endpoint.publish("http://localhost:6969/ws/sabun", new TestingService());
            System.out.println("Gacor");
        } catch (Exception e){
            System.out.println("Something Wlong");
        }
    }
}