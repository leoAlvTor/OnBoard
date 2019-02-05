package vista;

import javafx.application.Application;
import vista.logIn.StartUpTest;

public class Main {
    public static void main(String[] args) {
        new Thread(() -> Application.launch(StartUpTest.class)).start();
    }
}
