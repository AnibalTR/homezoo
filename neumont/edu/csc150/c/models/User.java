package neumont.edu.csc150.c.models;

import java.util.List;

public class User {
    private String userName;
    private String Password;
    private List<Environment> environments;
    private double money;
    private List<Food> food;

    public User() {}

    public User(String userName, String password, double money, List<Environment> environments, List<Food> food) {
        this.userName = userName;
        Password = password;
        this.environments = environments;
        this.money = money;
        this.food = food;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public List<Environment> getEnvironments() {
        return environments;
    }

    public void setEnvironments(List<Environment> environments) {
        this.environments = environments;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public List<Food> getFood() {
        return food;
    }

    public void setFood(List<Food> food) {
        this.food = food;
    }

    private void validateNotNull(Object parm, String name) {
        if(parm == null) {
            throw new IllegalArgumentException(
                    String.format("%s cannot be null", name)
            );
        }
    }

    private String iterateSerializeEnvironments() {
        String buildStr = "";
        for (Environment e : environments) {
            buildStr += e.serialize();
        }
        return buildStr;
    }

    private List<Environment> deserializeEnvironment(String serializedEnvironment) {
        String[] pieces = serializedEnvironment.split(",");

        for (int i = 0; i < pieces.length; i++) {
            if (pieces[i].contains("<")) {
                String[] newPetArr = pieces[i].split("<");
//                String name, AnimalTypes.AllAnimals animalType, Colors color, int hunger, int attention, int messiness, int stars, int lastFeedingTime, int lastPlayTime, int lastCleaning, int lastSleepTime, boolean isAsleep)
                environments.add(new Environment(new Pet(newPetArr[0], AnimalTypes.AllAnimals.valueOf(newPetArr[1]), AnimalTypes.Colors.valueOf(newPetArr[2]), Integer.parseInt(newPetArr[3]), Integer.parseInt(newPetArr[4]), Integer.parseInt(newPetArr[5]), Integer.parseInt(newPetArr[6]), Integer.parseInt(newPetArr[7]), Integer.parseInt(newPetArr[8]), Integer.parseInt(newPetArr[9]), Integer.parseInt(newPetArr[10]), true));
            } else {
                for (int j = 0; j < AnimalTypes.AllAnimals.values().length; j++) {
                    if (pieces[i] == AnimalTypes.AllAnimals.values()[j].toString()) {
                        environments.add(new Environment(null, AnimalTypes.AllAnimals.values()[j]));
                    }
                }
            }
        }
    }

    private String iterateSerializeFood() {
        String buildStr = "";
        for (Food f : food) {
            buildStr += f.serialize();
        }
        return buildStr;
    }

    public String serialize(){
        return String.format("%s|%s|%d|%s|%s|%s",
                this.getUserName(),
                this.getPassword(),
                this.getMoney(),
                this.iterateSerializeEnvironments(),
                this.iterateSerializeFood());
    }

    public void deserialize(String line) {
        String[] pieces = line.split("\\|");
        this.setUserName(pieces[0].trim());
        this.setPassword(pieces[1].trim());
        this.setMoney(Double.parseDouble(pieces[2].trim()));

    }
}
