package neumont.edu.csc150.c.models;

import neumont.edu.csc150.c.controller.PersonalZooController;
import java.io.FileNotFoundException;

public class ProcessorHook extends Thread {
    private PersonalZooController pc = new PersonalZooController();
    @Override
    public void run() {
        try {
//            pc.serializeUser();
            System.out.println("Hello");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
