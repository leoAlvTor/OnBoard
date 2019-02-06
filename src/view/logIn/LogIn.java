package view.logIn;

import controller.MongoConnection;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import view.menus.EmployeeMenu;

import java.util.concurrent.CountDownLatch;

public class LogIn extends Application{

    private TextField txtUser;

    private PasswordField passwordField;

    private Button logIn, clean, exit;

    private GridPane grid;

    private boolean flag;



    @Override
    public void start(Stage stage) {

        Scene scene = new Scene(addGridPane(), 300, 200);
        scene.getStylesheets().add(this.getClass().getResource("style.css").toExternalForm());

        stage.setTitle("Log In");
        stage.setScene(scene);
        stage.show();

        logIn.setOnAction(event -> checkUser(txtUser.getText(), passwordField.getText()));
        clean.setOnAction(event -> cleanText());
        exit.setOnAction(event -> System.exit(0));

    }

    public static void main(String[] args) {
        Application.launch(args);
    }

    public GridPane addGridPane() {
        grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(12);

        HBox hbButtons = new HBox();
        hbButtons.setSpacing(10.0);

        logIn = new Button("Log In");
        clean = new Button("Clean");
        exit = new Button("Exit");

        Label lblName = new Label("User name:");
        Label lblPasswd = new Label("Password:");

        txtUser = new TextField();
        passwordField = new PasswordField();

        hbButtons.getChildren().addAll(logIn, clean, exit);

        grid.add(lblName, 0, 0);
        grid.add(txtUser, 1, 0);
        grid.add(lblPasswd, 0, 1);
        grid.add(passwordField, 1, 1);
        grid.add(hbButtons, 1, 2, 2, 1);

        return this.grid;
    }

    public void checkUser(String user, String password){
        MongoConnection mongoConnection = new MongoConnection();
        Alert alertMsg = new Alert(Alert.AlertType.INFORMATION);

        if(mongoConnection.checkUsers(user,password)) {
            alertMsg.setTitle("Welcome");
            alertMsg.setHeaderText(null);
            alertMsg.setContentText("The information submitted is correct");
            alertMsg.showAndWait();
            Platform.runLater(() -> new EmployeeMenu().start(new Stage()));
            Stage stage1 = (Stage) logIn.getScene().getWindow();
            stage1.close();
        }else {
            alertMsg.setTitle("Incorrect data");
            alertMsg.setHeaderText(null);
            alertMsg.setContentText("The information submitted is incorrect, pleas check it");
            alertMsg.showAndWait();
            txtUser.requestFocus();
            cleanText();
            flag = false;
        }

    }

    public void cleanText(){
        passwordField.setText("");
        txtUser.setText("");
    }


}