package neumont.edu.csc150.c.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class  PersonalZooView {

    private BufferedReader userIn;

    public PersonalZooView() {
        userIn = new BufferedReader(new InputStreamReader(System.in));
    }

    public void displayMainMenu() throws IOException {
        showMessage("Welcome to Personal Zoo!!!\r\n"+"Press 1 to Login\r\n" +"Press 2 to Sign up\r\n" +"To exit press 0\r\n");
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


    /*// reptiles



//// snakes

////// ball python

////// corn snake

////// rosy boa



//// lizards

////// bearded dragon

////// leopard ghecko

////// iguanas



//// turtles

////// spiny softshell turtle

////// red eared slider

////// box turtle



// mammals



//// rodents

////// ferret

////// rat

////// skunk

////// hedgehog



//// misc mammals

////// fenic fox

////// raccoon

////// bear



//// dogs

////// english bernard

////// german shepard

////// yorksher terrier

////// pitbull

////// greyhound



//// felines

////// sphynx cat

////// persian

////// garfield

////// korean bobtail



// birds



//// parrots

////// scarlet macaw

////// grey parrot

////// pallid parrot



//// misc birds

////// crows

////// toucans

////// pidgeons*/
}
