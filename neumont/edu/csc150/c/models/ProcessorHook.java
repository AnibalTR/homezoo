package neumont.edu.csc150.c.models;

import neumont.edu.csc150.c.controller.PersonalZooController;
import java.io.FileNotFoundException;

class PetChecker implements Runnable {



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
