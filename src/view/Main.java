package view;


import javafx.application.Application;
import view.logIn.LogIn;

public class Main {
    public static void main(String[] args) {
        new Thread(() -> Application.launch(LogIn.class)).start();

       // MongoDBExamples mongoDBConection = new MongoDBExamples();

    }
}
