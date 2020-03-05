import neumont.edu.csc150.c.controller.PersonalZooController;
import neumont.edu.csc150.c.models.ProcessorHook;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
//        Runtime.getRuntime().addShutdownHook(new ProcessorHook());
        PersonalZooController zooGame = new PersonalZooController();
        zooGame.start();

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                zooGame.serializeUser();
            } catch(Exception e) {
                System.out.println(e);
            }
        }, "Shutdown-thread"));
    }
}