package neumont.edu.csc150.c.controller;

import neumont.edu.csc150.c.model.User;
import neumont.edu.csc150.c.view.PersonalZooView;

import java.io.*;
import java.time.LocalDate;

public class PersonalZooController {
    private final static String usersFolder = "Users";
    private PersonalZooView personalZooUI = new PersonalZooView();
    private User newUser = new User();

    public PersonalZooController(){
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
                    exitRequested = true;
                    break;
                case 1:
                    break;
                case 2:
                    break;
                default:
                    System.out.println("Please choose from 0 - 2");
                    throw new RuntimeException("Invalid user selection  " + selection);
            }
        } while (!exitRequested);
    }

    public void saveText(LocalDate date, User entry) throws FileNotFoundException {
        File file = new File(usersFolder,date.toString());
        PrintStream outFile = new PrintStream(file);
        try{
            outFile.println(entry.serialize());
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

}
