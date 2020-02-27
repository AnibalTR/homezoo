package neumont.edu.csc150.c.models;

import java.util.List;

public class User {
    private String userName;
    private String Password;     
    private List<Environment> environments;
    private double money;
    private List<Food> food;

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

    private void validateNotNull(Object parm, String name) {
        if(parm == null) {
            throw new IllegalArgumentException(
                    String.format("%s cannot be null", name)
            );
        }
    }

    public String serialize(){
        return String.format("%s|%s",
                this.getUserName(), this.getPassword());
    }

    public void deserialize(String line) {
        String[] pieces = line.split("\\|");

        this.setUserName(pieces[0].trim());

    }
}
