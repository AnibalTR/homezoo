package neumont.edu.csc150.c.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PersonalZooView {

    private BufferedReader userIn;

    public PersonalZooView() {
        userIn = new BufferedReader(new InputStreamReader(System.in));
    }

    public void displayMainMenu() {
        System.out.println("Welcome to Personal Zoo!!!\r\n"+"Press 1 to Login\r\n" +"Press 2 to Sign up\r\n" +"To exit press 0\r\n");
    }

    public String promptUserEntryInput(String userinput) throws IOException {
        showMessage("Enter input");
        return readString(1);
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

    public int readInt(int min, int max) throws IOException {
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

    public String getStringInput() throws IOException {
        return userIn.readLine();
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
