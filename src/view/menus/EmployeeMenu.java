package view.menus;

import com.sun.javafx.css.Style;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class EmployeeMenu extends Application{

    private TextField txtUser;

    private PasswordField passwordField;

    private Button btnInvoices, btnInventory, btnReports;

    private GridPane grid;

    @Override
    public void start(Stage stage) {

        Scene scene = new Scene(addGridPane(), 550, 500);

        stage.setTitle("Employee Menu");

        stage.setScene(scene);
        stage.show();

    }

    public GridPane addGridPane() {
        grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(12);

        Image invoiceIcon = new Image(getClass().getResourceAsStream("icons/invoice.png"));
        btnInvoices = new Button("", new ImageView(invoiceIcon));
        btnInvoices.setTooltip(new Tooltip("Create a new Invoice"));

        Image inventoryIcon = new Image(getClass().getResourceAsStream("icons/inventory.png"));
        btnInventory = new Button("", new ImageView(inventoryIcon));
        btnInventory.setTooltip(new Tooltip("Access to the inventory"));

        Image reportIcon = new Image(getClass().getResourceAsStream("icons/report.png"));
        btnReports = new Button("", new ImageView(reportIcon));
        btnReports.setTooltip(new Tooltip("Create a new Sales Report"));

        grid.add(btnInvoices, 0, 0);
        grid.add(btnInventory, 1,1);
        grid.add(btnReports, 2,2);

        return this.grid;
    }

}
