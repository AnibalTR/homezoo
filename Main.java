import neumont.edu.csc150.c.controller.PersonalZooController;

import java.io.IOException;

public class Main {
    private static PersonalZooController zooGame = new PersonalZooController();
    public static void main(String[] args) throws IOException {
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            public void run() {
                try {
                    zooGame.start();
                    zooGame.serializeUser();
                } catch(Exception e) {
                    System.out.println(e);
                }
            }
        }, "Shutdown-thread"));
    }
}