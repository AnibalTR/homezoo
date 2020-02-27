package neumont.edu.csc150.c.controller;

import neumont.edu.csc150.c.models.Encryption;
import neumont.edu.csc150.c.models.User;
import neumont.edu.csc150.c.view.PersonalZooView;

import java.io.*;
import java.time.LocalDate;

public class PersonalZooController {
    private final static String usersFolder = "Users";
    private PersonalZooView personalZooUI = new PersonalZooView();
    private Encryption encryptor = new Encryption();

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
                    break;
                case 2:
                    signup();
                    break;
            }
        } while (!exitRequested);
    }

    private void signup() throws IOException {
        User newUser = new User();
        int minNameLen = 3;
        boolean userExists;
        String userName;

        do{
            personalZooUI.showMessage(String.format("Please enter a username with a minimum of %d characters", minNameLen));
            userName = personalZooUI.readString(minNameLen);
            userExists = searchEntry(userName);
            if (!userExists){
                personalZooUI.showError("Username is already taken, please enter a different username");
            }
        }while(!userExists);
         newUser.setUserName(userName);
        personalZooUI.showMessage(String.format("Please enter a password with a minimum of %d characters", minNameLen));

        String password = personalZooUI.readString(minNameLen);
        String epw = encryptor.encrypt(password);
        newUser.setUserName(userName);
        newUser.setPassword(epw);
        saveText(newUser);
    }

    private void login() {
        User oldUser = new User();

    }

    private void enterCredentials() {

    }

    public void saveText(User user) throws FileNotFoundException {
        File file = new File(usersFolder,user.getUserName());
        PrintStream outFile = new PrintStream(file);
        try{
            outFile.println(user.serialize());
        }
        finally {
            outFile.close();
        }
    }

    public User loadJournal(String userFileName) throws IOException {
        BufferedReader inFile = new BufferedReader(new InputStreamReader(new FileInputStream(userFileName)));
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
