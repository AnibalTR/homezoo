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
    }
}
