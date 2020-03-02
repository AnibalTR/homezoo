package neumont.edu.csc150.c.controller;

import neumont.edu.csc150.c.models.Encryption;
import neumont.edu.csc150.c.models.Environment;
import neumont.edu.csc150.c.models.Food;
import neumont.edu.csc150.c.models.User;
import neumont.edu.csc150.c.view.PersonalZooView;

import java.io.*;
import java.util.ArrayList;

public class PersonalZooController {
    private final static String usersFolder = "Users";
    private PersonalZooView personalZooUI = new PersonalZooView();
    private Encryption encryptor = new Encryption();
    private User newUser;

    public PersonalZooController() {
        File folder = new File(usersFolder);
        if(!folder.exists()){
            folder.mkdir();
        }
    }

    public void run() throws IOException {
        boolean exitRequested = false;
        do {
            personalZooUI.displayMainMenu();
            int selection = personalZooUI.getUserSelection(0, 2);
            switch(selection){
                case 0:
                    personalZooUI.showMessage("Exiting...");
                    exitRequested = true;
                    break;
                case 1:
                    login();
                    play();
                    break;
                case 2:
                    signUp();
                    play();
                    break;
            }
        } while (!exitRequested);
    }

    private void play() throws IOException {
        personalZooUI.displayPlayMenu();
        int userInput = personalZooUI.getUserSelection(0, 3);

        switch(userInput) {
            case 0:
                saveText(newUser);
                personalZooUI.showMessage("Saving and Exiting...");
                System.exit(0);
                break;
            case 1:
                goToStore();
                break;
            case 2:
                viewInventory();
                break;
            case 3:
//                managePets();
                break;
        }
    }

    private boolean viewInventory() throws IOException {
        personalZooUI.displayViewInventoryMenu();
        int userInput = personalZooUI.getUserSelection(0, 2);

        switch (userInput) {
            case 0:
                return true;
            case 1:
                viewPetStats();
                break;
            case 2:
                viewFoodSupply();
                break;
        }
        return true;
    }

    private void viewFoodSupply() {

        personalZooUI.showMessage("========Food Supply======");
        for (int i = 0; i < newUser.getFood().size(); i++) {
            personalZooUI.showMessage(String.format("[%d] %s", i+1, newUser.getFood().get(i).toString()));
        }
    }

    private void viewPetStats() {
        personalZooUI.showMessage("========Animals======");
        for (int i = 0; i < newUser.getEnvironments().size(); i++) {
            if (newUser.getEnvironments().get(i).getPet() == null) {
                personalZooUI.showMessage(String.format("[%d] Environment Suitable for : %s", i + 1, newUser.getEnvironments().get(i).getAnimalsCage()));
            } else {
                personalZooUI.showMessage(String.format("[%d] %s", i + 1, newUser.getEnvironments().get(i).getPet().toString()));
            }
        }
    }

    private void goToStore() {

    }

    private void signUp() throws IOException {
        int minNameLen = 3;
        boolean userDoesNotExist;
        String userName;

        do{
            personalZooUI.showMessage(String.format("Please enter a username with a minimum of %d characters", minNameLen));
            userName = personalZooUI.readString(minNameLen);
            userDoesNotExist = searchEntry(userName);
            if (!userDoesNotExist){
                personalZooUI.showMessage("User created");
            } else {
                personalZooUI.showError("Username is taken");
            }
        }while(userDoesNotExist);
        personalZooUI.showMessage(String.format("Please enter a password with a minimum of %d characters", minNameLen));
        String password = personalZooUI.readString(minNameLen);
        String epw = encryptor.encrypt(password);
        newUser = new User(userName, epw, 100.00, new ArrayList<Environment>(), new ArrayList<Food>());
        saveText(newUser);
    }

    private void login() throws IOException {
        boolean userNamePasses = false;
        boolean userPWPasses = false;
        boolean credentialsMatch = false;
        int minNameLen = 3;
        String userName = "";
        String password;

        while (credentialsMatch == false) {
            while (userNamePasses == false) {
                personalZooUI.showMessage(String.format("Please enter a username with a minimum of %d characters\r\nTo exit type 'exit'", minNameLen));
                userName = personalZooUI.readString(minNameLen);

                if (userName.toLowerCase().equals("exit")) {
                    return;
                }

                userNamePasses = searchEntry(userName);
                if (!userNamePasses){
                    personalZooUI.showError("We could not find that user, please enter a different username");
                }
            }

            while (userPWPasses == false) {
                personalZooUI.showMessage(String.format("Please enter a password with a minimum of %d characters\r\n" +
                        "To exit type 'exit'", minNameLen));
                password = personalZooUI.readString(3);
                System.out.println(userName);
                if (password.toLowerCase().equals("exit")) {
                    return;
                }

                newUser = loadJournal(userName);

                if (newUser != null) {
                    userPWPasses = newUser.getPassword().equals(encryptor.encrypt(password));
                } else {
                    personalZooUI.showError("No users found with these credentials, please check username and password");
                }
            }
            credentialsMatch = true;
        }
    }

    public void saveText(User user) throws FileNotFoundException {
        File file = new File(usersFolder,user.getUserName());
        PrintStream outFile = new PrintStream(file + ".txt");
        try{
            outFile.println(user.serialize());
        }
        finally {
            outFile.close();
        }
    }

    public User loadJournal(String userFileName) throws IOException {
        BufferedReader inFile = new BufferedReader(new InputStreamReader(new FileInputStream("Users/" + userFileName + ".txt")));
        try {
            String content = "";
            while (inFile.ready()){
                content += inFile.readLine() + System.lineSeparator();
                if(!content.trim().isEmpty()){
                    User p = new User();
                    p.deserialize(content);
                    return p;
                }
            }
        }
        finally {
            inFile.close();
        }
        return null;
    }

    public boolean searchEntry(String userName) throws IOException {
        File folder = new File(usersFolder);
        File[] files = folder.listFiles();

        for (File file : files) {
            if (file.toString().substring(6).contentEquals(userName + ".txt")) {
                return true;
            }
        }
        return false;
    }
}
