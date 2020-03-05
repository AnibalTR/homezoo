package neumont.edu.csc150.c.models;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String userName;
    private String Password;
    private List<Environment> environments = new ArrayList<>();
    private double money;
    private List<Food> food = new ArrayList<>();

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

    private void deserializeEnvironment(String serializedEnvironment) {
        String[] pieces = serializedEnvironment.split(",");

        for (int i = 0; i < pieces.length; i++) {
            if (pieces[i].contains("<")) {
                String[] newPetArr = pieces[i].split("<");
                Pet pet = new Pet(newPetArr[0], AnimalTypes.AllAnimals.valueOf(newPetArr[1]), AnimalTypes.Colors.valueOf(newPetArr[2]), Integer.parseInt(newPetArr[3]), Integer.parseInt(newPetArr[4]), Integer.parseInt(newPetArr[5]), Integer.parseInt(newPetArr[6]), Long.parseLong(newPetArr[7]), Long.parseLong(newPetArr[8]), Long.parseLong(newPetArr[9]), Long.parseLong(newPetArr[10]), newPetArr[11].equals("true"));
                environments.add(new Environment(pet, pet.getAnimalType()));
            } else {
                for (int j = 0; j < AnimalTypes.AllAnimals.values().length; j++) {
                    if (pieces[i].equals(AnimalTypes.AllAnimals.values()[j].toString())) {
                        environments.add(new Environment(null, AnimalTypes.AllAnimals.values()[j]));
                    }
                }
            }
        }
    }

    private void deserializeFood(String serializedFood) {
        String[] pieces = serializedFood.split(",");

        for (int i = 0; i < pieces.length ; i++) {
            AnimalTypes.AllAnimals foodType = null;
            String[] foodPieces = pieces[i].split("<");
            for (int j = 0; j < AnimalTypes.AllAnimals.values().length; j++) {
                if(foodPieces[1].equals(AnimalTypes.AllAnimals.values()[j].toString())){
                    foodType = AnimalTypes.AllAnimals.values()[j];
                }
            }
            this.food.add(new Food(Integer.parseInt(foodPieces[0]), foodType));
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
        return String.format("%s|%s|%f|%s|%s|",
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

        if (pieces[3].length() > 1) {
            this.deserializeEnvironment(pieces[3].trim());
        }
        if (pieces[4].length() > 1) {
            this.deserializeFood(pieces[4].trim());
        }
    }
}
