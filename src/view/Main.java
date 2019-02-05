package view;

import controller.MongoDBConection;

public class Main {
    public static void main(String[] args) {
        //new Thread(() -> Application.launch(StartUpTest.class)).start();

        MongoDBConection mongoDBConection = new MongoDBConection();
    }
}
