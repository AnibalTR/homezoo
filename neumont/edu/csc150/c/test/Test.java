package neumont.edu.csc150.c.test;

import neumont.edu.csc150.c.models.AnimalTypes;
import neumont.edu.csc150.c.models.Encryption;

public class Test {
    public static void main(String[] args) {
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
    }
}
