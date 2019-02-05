package view.logIn;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.CountDownLatch;

public class StartUpTest extends Application implements ActionListener {
    public static final CountDownLatch latch = new CountDownLatch(1);
    public static StartUpTest startUpTest = null;

    private TextField txtCedula, txtPassword;

    private GridPane grid;



    @Override
    public void start(Stage stage) {

        Scene scene = new Scene(addGridPane(), 300, 400);

        stage.setTitle("Log In");
        stage.setScene(scene);
        stage.show();


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

        Button ingresar = new Button("Ingresar");
        ingresar.setOnAction(event -> System.out.println("se precionno el boton ingresar"));

        Button limpiar = new Button("Limpiar");
        Button salir = new Button("Salir");

        Label lblName = new Label("User name:");
        Label lblPasswd = new Label("Password:");

        TextField txtUser = new TextField();
        PasswordField passwordField = new PasswordField();

        hbButtons.getChildren().addAll(ingresar, limpiar, salir);

        grid.add(lblName, 0, 0);
        grid.add(txtUser, 1, 0);
        grid.add(lblPasswd, 0, 1);
        grid.add(passwordField, 1, 1);
        grid.add(hbButtons, 0, 2, 2, 1);


        return this.grid;
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }
}