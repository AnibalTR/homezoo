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
                    signUp();
                    break;
            }
        } while (!exitRequested);
    }

    private void signUp() throws IOException {
//        User newUser = new User();
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
        User newUser = new User(userName, epw, 100.00, new ArrayList<Environment>(), new ArrayList<Food>());
        saveText(newUser);
    }

    private void login() throws IOException {
        boolean userRequestedExit = false;
        boolean userNamePasses = false;
        boolean userPWPasses = false;
        boolean credentialsMatch = false;
        int minNameLen = 3;
        String userName = "";
        String password;
        User returnUser;

        do {

            while (!userNamePasses) {
                personalZooUI.showMessage(String.format("Please enter a username with a minimum of %d characters\r\nTo exit type 'exit'", minNameLen));
                userName = personalZooUI.readString(minNameLen);

                if (userName.toLowerCase() == "exit") {
                    userRequestedExit = true;
                }

                userNamePasses = searchEntry(userName);
                if (!userNamePasses){
                    personalZooUI.showError("We could not find that user, please enter a different username");
                }
            }

            do {
                personalZooUI.showMessage(String.format("Please enter a password with a minimum of %d characters\r\n" +
                        "To exit type 'exit'", minNameLen));
                password = personalZooUI.readString(3);

                if (password.toLowerCase() == "exit") {
                    userRequestedExit = true;
                }

                returnUser = loadJournal(userName);

                if (returnUser != null) {
                    credentialsMatch = returnUser.getPassword().equals(encryptor.encrypt(password));
                } else {
                    personalZooUI.showError("No users found with these credentials, please check username and password");
                }
            } while (!credentialsMatch);

        } while (!userRequestedExit || !credentialsMatch);





//        boolean userDoesNotExist;
//        boolean credentialsMatch = false;
//        String userName;
//        String password = "";
//        int minNameLen = 3;
//        User returnUser;
//        do{
//            personalZooUI.showMessage(String.format("Please enter a username with a minimum of %d characters", minNameLen));
//            userName = personalZooUI.readString(minNameLen);
//            userDoesNotExist = searchEntry(userName);
//            if (!userDoesNotExist){
//                personalZooUI.showError("We could not find that user, please enter a different username");
//            }
//            while (userDoesNotExist) {
//                personalZooUI.showMessage(String.format("Please enter a password with a minimum of %d characters", minNameLen));
//                password = personalZooUI.readString(3);
//                returnUser = loadJournal(userName);
//            }
//            if (returnUser != null) {
//                credentialsMatch = returnUser.getPassword() == encryptor.encrypt(password);
//            } else {
//                personalZooUI.showError("No users found with these credentials, please check username and password");
//            }
//        }while(!userDoesNotExist && !credentialsMatch);
//        System.out.println(returnUser.getEnvironments().size());
//        System.out.println(returnUser.getFood().size());
//        System.out.println("success");
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
