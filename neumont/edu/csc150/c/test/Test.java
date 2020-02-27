package neumont.edu.csc150.c.test;

import neumont.edu.csc150.c.controller.PersonalZooController;
import neumont.edu.csc150.c.models.AnimalTypes;
import neumont.edu.csc150.c.models.Encryption;

import java.io.File;
import java.io.IOException;

public class Test {
    public static void main(String[] args) throws IOException {
        Encryption enc = new Encryption();

        String hello = enc.encrypt("abcdefghijklmnopqrstuvwxyz");

        System.out.println(hello);

        String dec = enc.decrypt(hello);
        System.out.println(dec);

//        public void menuTest(AnimalTypes) {
//
//        }

        for (int i = 0; i < AnimalTypes.AnimalSpecies.values().length; i++) {
            System.out.println(AnimalTypes.AnimalSpecies.values()[i]);
        }

//        PersonalZooController pzc = new PersonalZooController();
//        pzc.run();

        File folder = new File("Users");
        File[] files = folder.listFiles();

        for (File file : files) {
            System.out.println(file.toString().substring(6).contentEquals("test" + ".txt"));
        }
    }
}
