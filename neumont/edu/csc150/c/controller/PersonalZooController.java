package neumont.edu.csc150.c.controller;

import neumont.edu.csc150.c.models.*;
import neumont.edu.csc150.c.view.PersonalZooView;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PersonalZooController {
    private final static String usersFolder = "Users";
    private PersonalZooView personalZooUI = new PersonalZooView();
    private Encryption encryptor = new Encryption();
    private Date date = new Date();

    private User newUser;

    public PersonalZooController() {
        File folder = new File(usersFolder);
        if(!folder.exists()){
            folder.mkdir();
        }
    }

    public void start() throws IOException {

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
                    checkTimePassed();
                    play();
                    break;
                case 2:
                    signUp();
                    play();
                    break;
            }
        } while (!exitRequested);
    }

    private void checkTimePassed() {
        for (Environment e : newUser.getEnvironments()) {
            if (e.getPet() == null) {
                continue;
            } else {
                boolean petUpdated = false;
                Pet pet = e.getPet();
                long now = date.getTime();
                int day = 86000000;
                long lastPlayTime = pet.getLastPlayTime();
                long lastCleaningTime = pet.getLastCleaning();
                long lastFeedingTime = pet.getLastFeedingTime();

                do {
                    if (lastPlayTime < now - day) {
                        pet.setAttention(pet.getAttention() - 1);
                        lastPlayTime -= day;
                    } else if (lastCleaningTime < now - day) {
                        pet.setMessiness(pet.getMessiness() - 1);
                        lastCleaningTime -= day;
                    } else if (lastFeedingTime < now - day) {
                        pet.setHunger(pet.getHunger() - 1);
                        lastFeedingTime -= day;
                    } else if (pet.getLastSleepTime() % day > day / 2) {
                        pet.setAsleep(!pet.isAsleep());
                    } else {
                        petUpdated = true;
                    }
                } while (!petUpdated);
                pet.setLastSleepTime(now - pet.getLastSleepTime() % (day / 2));
            }
        }
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
                managePets();
                break;
        }
    }

    private boolean managePets() throws IOException {

        List<Pet> pets = new ArrayList<>();
        for (int i = 0; i < newUser.getEnvironments().size(); i++) {
            if (newUser.getEnvironments().get(i).getPet() == null) {
                continue;
            } else {
                pets.add(newUser.getEnvironments().get(i).getPet());
            }
        }

        for (int i = 0; i < pets.size(); i++) {
            personalZooUI.showMessage(String.format("[%d] %s : %s",
                    i + 1, pets.get(i).getName(), pets.get(i).getAnimalType()));
        }

        int selection = personalZooUI.getUserSelection(1, pets.size());

        managePet(pets.get(selection - 1));

        return true;
    }

    private void managePet(Pet pet) throws IOException {
        personalZooUI.showMessage(pet.toString());
        if(!pet.isAsleep()) {
            personalZooUI.displayCaringMenu(pet.getName());
            int selection = personalZooUI.getUserSelection(0,3);
            switch(selection){
                case 1:
                    feedPet(pet);
                    break;
                case 2:
                    playPet(pet);
                    break;
                case 3:
                    break;
                case 0:
                    break;
            }
        } else {
            personalZooUI.showError(String.format("%s is asleep, come back in a while",pet.getName()));
        }
    }

    private void playPet(Pet pet) {
        if (pet.getAttention() < 10) {
            pet.setAttention(pet.getAttention() + 1);
//            pet.setLastPlayTime(LocalDate.);
        }
    }

    private void feedPet(Pet pet) throws IOException {
        List<Food> food = new ArrayList<>();
        personalZooUI.showMessage(String.format("%s : %s", pet.getName(), pet.getAnimalType()));
        for (int i = 0; i < newUser.getFood().size(); i++) {
            Food petFood = newUser.getFood().get(i);
            if(pet.getAnimalType().equals(petFood.getFoodType())){
                food.add(petFood);
            }
        }

        if (food.size() < 1)
            personalZooUI.showMessage("You do not currently have food for this pet");
        else {
            for (int i = 0; i < food.size(); i++) {
                personalZooUI.showMessage(String.format("[%d] %s", i + 1, newUser.getFood().get(i).toString()));
            }
            int selection = personalZooUI.getUserSelection(1, food.size());

            for (int i = 0; i < newUser.getFood().size(); i++) {
                if (newUser.getFood().get(i).toString().equals(food.get(selection))) {
                    newUser.getFood().remove(i);
                    pet.eat(food.get(selection));
                }
            }
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

    private void viewFoodSupply() throws IOException {

        personalZooUI.showMessage("========Food Supply======");
        for (int i = 0; i < newUser.getFood().size(); i++) {
            personalZooUI.showMessage(String.format("[%d] %s", i+1, newUser.getFood().get(i).toString()));
        }
        personalZooUI.showMessage("Press enter to Exit");
        personalZooUI.readString(0);
    }

    private void viewPetStats() throws IOException {
        personalZooUI.showMessage("========Animals======");
        for (int i = 0; i < newUser.getEnvironments().size(); i++) {
            if (newUser.getEnvironments().get(i).getPet() == null) {
                personalZooUI.showMessage(String.format("[%d] Environment Suitable for : %s", i + 1, newUser.getEnvironments().get(i).getAnimalsCage()));
            } else {
                personalZooUI.showMessage(String.format("[%d] %s", i + 1, newUser.getEnvironments().get(i).getPet().toString()));
            }
        }
        personalZooUI.showMessage("Press enter to Exit");
        personalZooUI.readString(0);
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

    public void serializeUser() throws FileNotFoundException {
        saveText(newUser);
    }
}
