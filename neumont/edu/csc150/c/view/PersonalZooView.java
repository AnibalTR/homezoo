package neumont.edu.csc150.c.view;

import neumont.edu.csc150.c.models.AnimalTypes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class  PersonalZooView {

    private BufferedReader userIn;

    public PersonalZooView() {
        userIn = new BufferedReader(new InputStreamReader(System.in));
    }

    public void displayMainMenu() throws IOException {
        showMessage("======= Welcome to Your Personal Zoo =======" + "\r\n"+
                "\r\n"+"              Press 1 to Login" +
                "\r\n" +"             Press 2 to Sign up" +
                "\r\n" +"              To exit press 0\r\n");
    }

    public void displayPlayMenu() {
        showMessage("[1] Go To Store\r\n[2] View Inventory\r\n[3] Take Care of Pets\r\n[4] Sign Out\r\n[0] Save and Exit");
    }

    public void displayCaringMenu(String petName){
        showMessage(String.format("[1] Feed %s\r\n[2] Play With %s\r\n[3] Clean %s\r\n[0] Exit",petName));
    }

    public int getUserSelection(int min, int max) throws IOException {
        while (true) {
            String rawInput = userIn.readLine();
            try {
                int input = Integer.parseInt(rawInput);
                if (input < min || input > max) {
                    throw new NumberFormatException();
                }
                return input;
            } catch (NumberFormatException ex) {
                showError(String.format("You must enter an integer between %d and %d",
                        min, max));
            }
        }
    }

    public String readString(int minLength) throws IOException {
        while (true) {
            String input = userIn.readLine();
            if (input.length() < minLength) {
                showError("The name must be at least" + minLength + " characters");
            } else {
                return input;
            }
        }
    }

    public void showMessage(String msg) {
        System.out.println(msg);
    }

    public void showError(String msg) {
        System.err.println(msg);
    }

    public void displayViewInventoryMenu() {
        showMessage("[1] View Pets and Environments\r\n[2] View Food Supply\r\n[0] Back");
    }

    public void displayStoreMainMenu() {
        showMessage("[1] Buy Pet\r\n[2] Buy Environment\r\n[3] Buy Food\r\n[4] Sell Pet\r\n[0] Exit");
    }

    public void displayStoreSubMenu() {
        for (int i = 0; i < AnimalTypes.AnimalSpecies.values().length; i++) {
            showMessage(String.format("[%d] %s", i + 1, AnimalTypes.AnimalSpecies.values()[i]));
        }
        showMessage("[0] Exit");
    }

    public void displayBirdClass() {
        for (int i = 0; i < AnimalTypes.BirdSpecies.values().length; i++) {
            showMessage(String.format("[%d] %s", i + 1, AnimalTypes.BirdSpecies.values()[i]));
        }
        showMessage("[0] Exit");
    }

    public void displayMiscBirdClass() {
        for (int i = 0; i < AnimalTypes.MiscBirdSpecies.values().length; i++) {
            showMessage(String.format("[%d] %s", i + 1, AnimalTypes.MiscBirdSpecies.values()[i]));
        }
        showMessage("[0] Exit");
    }

    public void displayParrotsClass() {
        for (int i = 0; i < AnimalTypes.ParrotSpecies.values().length; i++) {
            showMessage(String.format("[%d] %s", i + 1, AnimalTypes.ParrotSpecies.values()[i]));
        }
        showMessage("[0] Exit");
    }

    public void displayMammalClass() {
        for (int i = 0; i < AnimalTypes.MammalSpecies.values().length; i++) {
            showMessage(String.format("[%d] %s", i + 1, AnimalTypes.MammalSpecies.values()[i]));
        }
        showMessage("[0] Exit");
    }

    public void displayRodentsClass() {
        for (int i = 0; i < AnimalTypes.RodentSpecies.values().length; i++) {
            showMessage(String.format("[%d] %s", i + 1, AnimalTypes.RodentSpecies.values()[i]));
        }
        showMessage("[0] Exit");
    }

    public void displayMiscMammalClass() {
        for (int i = 0; i < AnimalTypes.MiscMammalSpecies.values().length; i++) {
            showMessage(String.format("[%d] %s", i + 1, AnimalTypes.MiscMammalSpecies.values()[i]));
        }
        showMessage("[0] Exit");
    }

    public void displayDogClass() {
        for (int i = 0; i < AnimalTypes.DogSpecies.values().length; i++) {
            showMessage(String.format("[%d] %s", i + 1, AnimalTypes.DogSpecies.values()[i]));
        }
        showMessage("[0] Exit");
    }

    public void displayFelineClass() {
        for (int i = 0; i < AnimalTypes.FelineSpecies.values().length; i++) {
            showMessage(String.format("[%d] %s", i + 1, AnimalTypes.FelineSpecies.values()[i]));
        }
        showMessage("[0] Exit");
    }

    public void displayReptileClass() {
        for (int i = 0; i < AnimalTypes.ReptileSpecies.values().length; i++) {
            showMessage(String.format("[%d] %s", i + 1, AnimalTypes.ReptileSpecies.values()[i]));
        }
        showMessage("[0] Exit");
    }

    public void displaySnakeClass() {
        for (int i = 0; i < AnimalTypes.SnakeSpecies.values().length; i++) {
            showMessage(String.format("[%d] %s", i + 1, AnimalTypes.SnakeSpecies.values()[i]));
        }
        showMessage("[0] Exit");
    }

    public void displayTurtleClass() {
        for (int i = 0; i < AnimalTypes.TurtleSpecies.values().length; i++) {
            showMessage(String.format("[%d] %s", i + 1, AnimalTypes.TurtleSpecies.values()[i]));
        }
        showMessage("[0] Exit");
    }

    public void displayLizardClass() {
        for (int i = 0; i < AnimalTypes.LizardSpecies.values().length; i++) {
            showMessage(String.format("[%d] %s", i + 1, AnimalTypes.LizardSpecies.values()[i]));
        }
        showMessage("[0] Exit");
    }

    public void displayPetColors() {
        for (int i = 0; i < AnimalTypes.Colors.values().length; i++) {
            showMessage(String.format("[%d] %s", i + 1, AnimalTypes.Colors.values()[i]));
        }
    }
}
