package neumont.edu.csc150.c.controller;

import neumont.edu.csc150.c.models.*;
import neumont.edu.csc150.c.view.PersonalZooView;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PersonalZooController {
    private final static String usersFolder = "Users";
    private PersonalZooView personalZooUI;
    private Encryption encryptor;
    private Date date;
    private Store store;
    private User newUser;
    private int day;

    public PersonalZooController() {
        loadComponents();
        File folder = new File(usersFolder);
        if(!folder.exists())
            folder.mkdir();
    }

    public void start() throws IOException {
        boolean exitRequested = false;
        do {
            personalZooUI.displayMainMenu();
            int selection = personalZooUI.getUserSelection(0, 2);
            switch(selection){
                case 0:
                    personalZooUI.showMessage("Exiting...");
//                    return;
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
                boolean petTimeUpdated = false;
                boolean petCleanUpdated = false;
                boolean petFeedUpdated = false;
                Pet pet = e.getPet();
                long now = date.getTime();
                long lastPlayTime = pet.getLastPlayTime();
                long lastCleaningTime = pet.getLastCleaning();
                long lastFeedingTime = pet.getLastFeedingTime();

                do {
                    if (lastPlayTime < now - (day / 48)) {
                        pet.setAttention(pet.getAttention() - 1);
                        lastPlayTime += day / 48;
                    } else {
                        petTimeUpdated = true;
                    }
                    if (lastCleaningTime < now - (day / 182)) {
                        pet.setMessiness(pet.getMessiness() - 1);
                        lastCleaningTime += day / 182;
                    } else {
                        petFeedUpdated = true;
                    }
                    if (lastFeedingTime < now - (day / 1000)) {
                        pet.setHunger(pet.getHunger() - 1);
                        lastFeedingTime += day / 1000;
                    } else {
                        petCleanUpdated = true;
                    }
                } while (!petTimeUpdated && !petCleanUpdated && !petFeedUpdated);
                if (pet.getLastSleepTime() % day < day / 2) {
                    pet.setAsleep(false);
                    pet.setLastSleepTime(now - (pet.getLastSleepTime() % (day / 2)));
                }
                else
                    pet.setAsleep(true);
                pet.setStars((pet.getHunger() + pet.getMessiness() + pet.getAttention()) / 6);
            }
        }
    }

    private void play() throws IOException {
        boolean leaveGameMenu = false;
        do{
            personalZooUI.displayUserInfo(newUser.getUserName(), newUser.getMoney());
            personalZooUI.showMessage("=~=~=~=~ Main Menu ~=~=~=~=");
            personalZooUI.displayPlayMenu();
            int userInput = personalZooUI.getUserSelection(0, 4);
            switch(userInput) {
                case 0:
                    personalZooUI.showMessage("Saving and Exiting...");
                    saveText(newUser);
                    leaveGameMenu = true;
                    System.exit(0);
                    break;
                case 1:
                    goToStore();
                    break;
                case 2:
                    viewInventory(leaveGameMenu);
                    break;
                case 3:
                    managePets();
                    break;
                case 4:
                    saveText(newUser);
                    leaveGameMenu = true;
                    break;
            }
        }while(!leaveGameMenu);
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
        if (pets.size() > 0) {
            for (int i = 0; i < pets.size(); i++) {
                personalZooUI.showMessage(String.format("[%d] %s : %s",
                        i + 1, pets.get(i).getName(), pets.get(i).getAnimalType()));
            }
            int selection = personalZooUI.getUserSelection(1, pets.size());
            managePet(pets.get(selection - 1));
        }
        else {
            personalZooUI.showMessage("You currently have no pets");
        }

        return true;
    }

    private void managePet(Pet pet) throws IOException {
        if(!pet.isAsleep()) {
            personalZooUI.showMessage(pet.toString());
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
                    cleanPet(pet);
                    break;
                case 0:
                    break;
            }
        } else {
            personalZooUI.showMessage(String.format("%s is asleep, come back in a while\r\n", pet.getName()));
        }
    }

    private void cleanPet(Pet pet) {
        if (pet.getLastPlayTime() + day / 12 > date.getTime()) {
            personalZooUI.showMessage(String.format("%s's environment is not dirty", pet.getName()));
        } else if (pet.getMessiness() < 10) {
            pet.setMessiness(pet.getMessiness() + 1);
            pet.setLastCleaning(date.getTime());
            personalZooUI.showMessage(String.format("You cleaned %s's cage", pet.getName()));
        } else {
            personalZooUI.showMessage(String.format("%s's environment is not dirty", pet.getName()));
        }
    }

    private void playPet(Pet pet) {
        if (pet.getLastPlayTime() + day / 24 > date.getTime()) {
            personalZooUI.showMessage(String.format("You need to wait to play with %s", pet.getName()));
        } else if (pet.getAttention() < 10) {
            pet.setAttention(pet.getAttention() + 1);
            pet.setLastPlayTime(date.getTime());
            personalZooUI.showMessage(String.format("You played with %s", pet.getName()));
        } else {
            personalZooUI.showMessage(String.format("%s is all happy", pet.getName()));
        }
    }

    private void feedPet(Pet pet) throws IOException {
        List<Food> food = new ArrayList<>();
//        personalZooUI.showMessage(String.format("%s : %s", pet.getName(), pet.getAnimalType()));
        for (int i = 0; i < newUser.getFood().size(); i++) {
            Food petFood = newUser.getFood().get(i);
            if(pet.getAnimalType().toString().equals(petFood.getFoodType().toString()))
                food.add(petFood);
        }
        if (food.size() < 1)
            personalZooUI.showMessage(String.format("You do not currently have food for %s", pet.getName()));
        else if (pet.getLastFeedingTime() + day / 8 > date.getTime())
            personalZooUI.showMessage(String.format("%s is not currently hungry", pet.getName()));
        else {
            for (int i = 0; i < food.size(); i++) {
                personalZooUI.showMessage(String.format("[%d] %s", i + 1, food.get(i).toString()));
            }
            int selection = personalZooUI.getUserSelection(1, food.size());
            for (int i = 0; i < newUser.getFood().size(); i++) {
                if (newUser.getFood().get(i).getFoodType().toString().equals(food.get(selection - 1).getFoodType().toString()) && newUser.getFood().get(i).getAmountOfFood() == food.get(selection - 1).getAmountOfFood()) {
                    newUser.getFood().remove(i);
                    pet.setLastFeedingTime(date.getTime());
                    pet.setHunger(pet.getHunger() + food.get(selection - 1).getAmountOfFood());
                }
            }
        }
    }

    private void viewInventory(boolean exit) throws IOException {
        do{
            personalZooUI.showMessage("======= Inventory =======");
            personalZooUI.displayViewInventoryMenu();
            int userInput = personalZooUI.getUserSelection(0, 2);
            switch (userInput) {
                case 0:
                    exit = true;
                    break;
                case 1:
                    viewPetStats();
                    break;
                case 2:
                    viewFoodSupply();
                    break;
            }
        }while(!exit);

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
        personalZooUI.showMessage("=~=~=~=~ Animals ~=~=~=~=");
        for (int i = 0; i < newUser.getEnvironments().size(); i++) {
            if (newUser.getEnvironments().get(i).getPet() == null) {
                personalZooUI.showMessage(String.format("[%d] Environment Suitable for : %s\r\n", i + 1, newUser.getEnvironments().get(i).getAnimalsCage()));
            } else {
                personalZooUI.showMessage(String.format("[%d] %s\r\n", i + 1, newUser.getEnvironments().get(i).getPet().toString()));
            }
        }
        personalZooUI.showMessage("Press Enter to Exit");
        String selection = personalZooUI.readString(0);
        if (selection.equals("")){
            return;
        }
    }

    private void goToStore() throws IOException {
        boolean exitingStore = false;
        do {
            int selection = 0;
            personalZooUI.showMessage("=~=~=~=~ Store ~=~=~=~=");
            personalZooUI.displayStoreMainMenu();
            selection = personalZooUI.getUserSelection(0, 4);
            switch (selection) {
                case 0:
                    exitingStore = true;
                    break;
                case 1:
                case 2:
                case 3:
                    mainStoreMenuSelector(exitingStore, selection);
                    break;
                case 4:
                    sellPet();
                    break;
            }

        } while(!exitingStore);
    }

    private void sellPet() throws IOException {
        personalZooUI.showMessage("======= Pets you're Selling =======");
        Pet sellPet = null;
        int petAmount = -1;
        for (int i = 0; i < newUser.getEnvironments().size(); i++) {
            if (newUser.getEnvironments().get(i).getPet() != null) {
                sellPet = newUser.getEnvironments().get(i).getPet();
                int sellPrice = store.sellPet(sellPet.getAnimalType(),sellPet.getStars());
                personalZooUI.showMessage((String.format("[%d] %s - Type: %s - Sell Price: %d" , i + 1,sellPet.getName(),sellPet.getAnimalType(),sellPrice)));
                petAmount = i;
            }
        }
        personalZooUI.showMessage("Which pet would you like to sell? Or press 0 to exit");
        int selection = personalZooUI.getUserSelection(1, petAmount + 1);
        newUser.getEnvironments().remove(selection - 1);
        newUser.setMoney(newUser.getMoney() + store.getPriceOfPet(sellPet.getAnimalType()));
        personalZooUI.showMessage("-$"+store.getPriceOfPet(sellPet.getAnimalType()));
        saveText(newUser);
    }

    private void mainStoreMenuSelector(boolean exit, int currentDirectory) throws IOException {
        do {
            personalZooUI.showMessage("=~=~=~=~ Animal Class ~=~=~=~=");
            personalZooUI.displayStoreSubMenu();
            int selection = personalZooUI.getUserSelection(0, 3);
            switch (selection) {
                case 0:
                    break;
                case 1:
                    displayReptileClass(exit, currentDirectory);
                    break;
                case 2:
                    displayMammalClass(exit, currentDirectory);
                    break;
                case 3:
                    displayBirdClass(exit, currentDirectory);
                    break;
            }
            exit=true;
        } while (!exit);
    }

    private void displayBirdClass(boolean exit, int currentDirectory) throws IOException {
        do {
            personalZooUI.showMessage("=~=~=~=~ Birds ~=~=~=~=");
            personalZooUI.displayBirdClass();
            int selection = personalZooUI.getUserSelection(0, AnimalTypes.BirdSpecies.values().length);

            switch(selection) {
                case 0:
                    break;
                case 1:
                    displayMiscBirds(exit, currentDirectory);
                    break;
                case 2:
                    displayParrots(exit, currentDirectory);
                    break;
            }
            exit = true;
        } while(!exit);
    }

    private void displayParrots(boolean exit, int currentDirectory) throws IOException {
        do{
            personalZooUI.showMessage("======= Parrots =======");
            personalZooUI.displayParrotsClass();
            int selection = personalZooUI.getUserSelection(0, AnimalTypes.ParrotSpecies.values().length);
            AnimalTypes.AllAnimals selectedAnimal = null;
            if (selection != 0){
                selectedAnimal = AnimalTypes.AllAnimals.valueOf(AnimalTypes.ParrotSpecies.values()[selection - 1].toString());
                buyProduct(exit, currentDirectory, selectedAnimal);
            }
            exit =true;
        }while(!exit);
    }

    private void displayMiscBirds(boolean exit, int currentDirectory) throws IOException {
        do{
            personalZooUI.showMessage("======= Misc. Birds =======");
            personalZooUI.displayMiscBirdClass();
            int selection = personalZooUI.getUserSelection(0, AnimalTypes.MiscBirdSpecies.values().length);
            AnimalTypes.AllAnimals selectedAnimal = null;
            if (selection != 0){
                selectedAnimal = AnimalTypes.AllAnimals.valueOf(AnimalTypes.MiscBirdSpecies.values()[selection - 1].toString());
                buyProduct(exit, currentDirectory, selectedAnimal);
            }
            exit =true;
        }while(!exit);

    }

    private void displayMammalClass(boolean exit, int currentDirectory) throws IOException {
        do {
            personalZooUI.showMessage("=~=~=~=~ Mammals ~=~=~=~=");
            personalZooUI.displayMammalClass();
            int selection = personalZooUI.getUserSelection(0, AnimalTypes.MammalSpecies.values().length);

            switch(selection) {
                case 0:
                    break;
                case 1:
                    displayRodents(exit, currentDirectory);
                    break;
                case 2:
                    displayMiscMammals(exit, currentDirectory);
                    break;
                case 3:
                    displayDogs(exit, currentDirectory);
                    break;
                case 4:
                    displayFeline(exit, currentDirectory);
                    break;
            }
            exit = true;
        } while(!exit);
    }

    private void displayFeline(boolean exit, int currentDirectory) throws IOException {
        do{
            personalZooUI.showMessage("======= Cats =======");
            personalZooUI.displayFelineClass();
            int selection = personalZooUI.getUserSelection(0, AnimalTypes.FelineSpecies.values().length);
            AnimalTypes.AllAnimals selectedAnimal = null;
            if (selection != 0){
                selectedAnimal = AnimalTypes.AllAnimals.valueOf(AnimalTypes.FelineSpecies.values()[selection - 1].toString());
                buyProduct(exit, currentDirectory, selectedAnimal);
            }
            exit =true;
        }while(!exit);
    }

    private void displayDogs(boolean exit, int currentDirectory) throws IOException {
        do{
            personalZooUI.showMessage("======= Dogs =======");
            personalZooUI.displayDogClass();
            int selection = personalZooUI.getUserSelection(0, AnimalTypes.DogSpecies.values().length);
            AnimalTypes.AllAnimals selectedAnimal = null;
            if (selection != 0){
                selectedAnimal = AnimalTypes.AllAnimals.valueOf(AnimalTypes.DogSpecies.values()[selection - 1].toString());
                buyProduct(exit, currentDirectory, selectedAnimal);
            }
            exit =true;
        }while(!exit);
    }

    private void displayMiscMammals(boolean exit, int currentDirectory) throws IOException {
        do{
            personalZooUI.showMessage("======= Misc. Mammals =======");
            personalZooUI.displayMiscMammalClass();
            int selection = personalZooUI.getUserSelection(0, AnimalTypes.MiscMammalSpecies.values().length);
            AnimalTypes.AllAnimals selectedAnimal = null;
            if (selection != 0){
                selectedAnimal = AnimalTypes.AllAnimals.valueOf(AnimalTypes.MiscMammalSpecies.values()[selection - 1].toString());
                buyProduct(exit, currentDirectory, selectedAnimal);
            }
            exit =true;
        }while(!exit);
    }

    private void displayRodents(boolean exit, int currentDirectory) throws IOException {
        do{
            personalZooUI.showMessage("======= Rodents =======");
            personalZooUI.displayRodentsClass();
            int selection = personalZooUI.getUserSelection(0, AnimalTypes.RodentSpecies.values().length);
            AnimalTypes.AllAnimals selectedAnimal = null;
            if (selection != 0){
                selectedAnimal = AnimalTypes.AllAnimals.valueOf(AnimalTypes.RodentSpecies.values()[selection - 1].toString());
                buyProduct(exit, currentDirectory, selectedAnimal);
            }
            exit =true;
        }while(!exit);
    }

    private void displayReptileClass(boolean exit, int currentDirectory) throws IOException {
        do {
            personalZooUI.showMessage("=~=~=~=~ Reptiles ~=~=~=~=");
            personalZooUI.displayReptileClass();
            int selection = personalZooUI.getUserSelection(0, AnimalTypes.ReptileSpecies.values().length);

            switch(selection) {
                case 0:
                    break;
                case 1:
                    displaySnakes(exit, currentDirectory);
                    break;
                case 2:
                    displayTurtles(exit, currentDirectory);
                    break;
                case 3:
                    displayLizards(exit, currentDirectory);
            }
            exit = true;
        } while(!exit);
    }

    private void displayLizards(boolean exit, int currentDirectory) throws IOException {
        do{
            personalZooUI.showMessage("======= Lizards =======");
            personalZooUI.displayLizardClass();
            int selection = personalZooUI.getUserSelection(0, AnimalTypes.LizardSpecies.values().length);
            AnimalTypes.AllAnimals selectedAnimal = null;
            if (selection != 0) {
                selectedAnimal = AnimalTypes.AllAnimals.valueOf(AnimalTypes.LizardSpecies.values()[selection - 1].toString());
                buyProduct(exit, currentDirectory, selectedAnimal);
            }
            exit =true;
        }while(!exit);
    }

    private void displayTurtles(boolean exit, int currentDirectory) throws IOException {
        do{
            personalZooUI.showMessage("======= Turtles =======");
            personalZooUI.displayTurtleClass();
            int selection = personalZooUI.getUserSelection(0, AnimalTypes.TurtleSpecies.values().length);
            AnimalTypes.AllAnimals selectedAnimal = null;
            if (selection != 0){
                selectedAnimal = AnimalTypes.AllAnimals.valueOf(AnimalTypes.TurtleSpecies.values()[selection - 1].toString());
                buyProduct(exit, currentDirectory, selectedAnimal);
            }
            exit =true;
        }while(!exit);
    }

    private void displaySnakes(boolean exit, int currentDirectory) throws IOException {
        do{
            personalZooUI.showMessage("======= Snakes =======");
            personalZooUI.displaySnakeClass();
            int selection = personalZooUI.getUserSelection(0, AnimalTypes.SnakeSpecies.values().length);
            AnimalTypes.AllAnimals selectedAnimal = null;
            if (selection != 0){
                selectedAnimal = AnimalTypes.AllAnimals.valueOf(AnimalTypes.SnakeSpecies.values()[selection - 1].toString());
                buyProduct(exit, currentDirectory, selectedAnimal);
            }
            exit = true;
        }while(!exit);
    }

    private void buyProduct(boolean exit, int currentDirectory, AnimalTypes.AllAnimals selectedAnimal) throws IOException {
        switch(currentDirectory) {
            case 1:
                buyPet(selectedAnimal);
                break;
            case 2:
                buyEnvironment(selectedAnimal);
                break;
            case 3:
                buyFood(selectedAnimal);
                break;
        }
    }

    private void buyFood(AnimalTypes.AllAnimals selectedAnimal) throws IOException {
        personalZooUI.showMessage(String.format("How much %s food would you like? (1 - 3)\r\n[0] Exit", selectedAnimal));
        int selection = personalZooUI.getUserSelection(0,3);
        int foodCost = store.getPriceOfPet(selectedAnimal)/10;
        int totalCost = foodCost*selection;
        switch(selection) {
            case 0:
                break;
            case 1:
            case 2:
            case 3:
                if(newUser.getMoney() - totalCost > 0){
                    List<Food> foodList;
                    Food food = store.buyFood(selection,selectedAnimal);
                    foodList = newUser.getFood();
                    foodList.add(food);
                    newUser.setFood(foodList);
                    newUser.setMoney(newUser.getMoney() - totalCost);
                }
                else{
                    personalZooUI.showMessage(String.format("you do not currently have enough money for %d %s food", selection,selectedAnimal));
                }
                break;
        }
    }

    private void buyEnvironment(AnimalTypes.AllAnimals selectedAnimal) throws IOException {
        int petCost = store.getPriceOfPet(selectedAnimal);
        int environmentCost = petCost / 2;
        if (newUser.getMoney() - environmentCost < 0) {
            personalZooUI.showMessage("You do not currently have the money needed to buy the %s environment");
        } else {
            newUser.setMoney(newUser.getMoney() - environmentCost);
            List<Environment> envList;
            Environment environment = new Environment(null, selectedAnimal);
            envList = newUser.getEnvironments();
            envList.add(environment);
            newUser.setEnvironments(envList);
        }
    }

    private void buyPet(AnimalTypes.AllAnimals selectedAnimal) throws IOException {
        boolean hasEnvironment = checkUserEnvironments(selectedAnimal);
        personalZooUI.showMessage(String.format("Are you sure you would like to purchase a %s for $%d?\r\n[1] yes\r\n[0] no", selectedAnimal, store.getPriceOfPet(selectedAnimal)));
        int selection = personalZooUI.getUserSelection(0,1);
        int cost = store.getPriceOfPet(selectedAnimal);

        if (selection == 1 && hasEnvironment && newUser.getMoney() - cost > 0)
            getPet(selectedAnimal);
        else if (!hasEnvironment)
            personalZooUI.showMessage(String.format("You do not have the environment necessary for the %s", selectedAnimal));
        else if (newUser.getMoney() - cost < 0)
            personalZooUI.showMessage(String.format("You do not have enough money for the %s", selectedAnimal));
    }

    private boolean checkUserEnvironments(AnimalTypes.AllAnimals selectedAnimal) {
        boolean hasEnvironment = false;
        for (Environment e : newUser.getEnvironments())
            if (e.getAnimalsCage().toString().equals(selectedAnimal.toString()) && e.getPet() == null) {
                hasEnvironment = true;
            }
        return hasEnvironment;
    }

    private void getPet(AnimalTypes.AllAnimals selectedAnimal) throws IOException {

        personalZooUI.showMessage("======= Pick a Color for your Pet =======");
        personalZooUI.displayPetColors();
        int selection = personalZooUI.getUserSelection(1, AnimalTypes.Colors.values().length);
        int cost = store.getPriceOfPet(selectedAnimal);
        newUser.setMoney(newUser.getMoney() - cost);
        personalZooUI.showMessage(String.format("What would you like to name your %s?", selectedAnimal));
        String petName = personalZooUI.readString(3);
        Pet pet = store.buyPet(selectedAnimal, AnimalTypes.Colors.values()[selection - 1]);
        pet.setName(petName);
        List<Environment> env = newUser.getEnvironments();
        for (Environment e : env) {
            if (e.getAnimalsCage().toString().equals(pet.getAnimalType().toString()) && e.getPet() == null) {
                e.setPet(pet);
                break;
            }
        }
        newUser.setEnvironments(env);
    }

    private void signUp() throws IOException {
        personalZooUI.showMessage("=~=~=~=~ SignUp ~=~=~=~=");
        int minNameLen = 3;
        boolean userDoesNotExist;
        String userName;

        do{
            personalZooUI.showMessage(String.format("Please enter a username with a minimum of %d characters", minNameLen));
            userName = personalZooUI.readString(minNameLen);
            userDoesNotExist = searchEntry(userName);
            if (!userDoesNotExist){
                personalZooUI.showMessage("Username passed");
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
        personalZooUI.showMessage("=~=~=~=~ Login ~=~=~=~=\r\nTo exit type 'exit'\r\n");
        boolean userNamePasses = false;
        boolean userPWPasses = false;
        boolean credentialsMatch = false;
        int minNameLen = 3;
        String userName = "";
        String password;

        while (credentialsMatch == false) {
            while (userNamePasses == false) {
//                personalZooUI.showMessage(String.format("Please enter a username with a minimum of %d characters\r\nTo exit type 'exit'", minNameLen));
                personalZooUI.showMessage("Enter your Username");
                userName = personalZooUI.readString(minNameLen);
                if (userName.toLowerCase().equals("exit")) {
                    start();
                }
                userNamePasses = searchEntry(userName);
                if (!userNamePasses){
                    personalZooUI.showError("We could not find that user, please enter a different username");
                }
            }
            while (userPWPasses == false) {
//                personalZooUI.showMessage(String.format("Please enter a password with a minimum of %d characters\r\nTo exit type 'exit'", minNameLen));
                personalZooUI.showMessage("Enter your Password");
                password = personalZooUI.readString(3);
                if (password.toLowerCase().equals("exit")) {
                    start();
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
        File file = new File(usersFolder, user.getUserName());
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

    public void loadComponents() {
        personalZooUI = new PersonalZooView();
        encryptor = new Encryption();
        date = new Date();
        store = new Store();
        day = 86000000;
    }
}
