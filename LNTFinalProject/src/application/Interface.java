package application;

import java.util.List;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.Menu;
import model.MenuManager;

public class Interface extends Application {

    private MenuManager menuManager = new MenuManager();
    private TextField kodeMenuField;
    private TextField namaMenuField;
    private TextField hargaMenuField;
    private TextField stokMenuField;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("PT Pudding Menu Management");

        // Create GridPane layout
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20, 20, 20, 20));
        grid.setVgap(10);
        grid.setHgap(10);

        // Add labels and text fields for Insert Menu Baru
        Label kodeMenuLabel = new Label("Kode Menu:");
        kodeMenuField = new TextField();
        kodeMenuField.setDisable(true);
        grid.add(kodeMenuLabel, 0, 0);
        grid.add(kodeMenuField, 1, 0);

        Label namaMenuLabel = new Label("Nama Menu:");
        namaMenuField = new TextField();
        grid.add(namaMenuLabel, 0, 1);
        grid.add(namaMenuField, 1, 1);

        Label hargaMenuLabel = new Label("Harga Menu:");
        hargaMenuField = new TextField();
        grid.add(hargaMenuLabel, 0, 2);
        grid.add(hargaMenuField, 1, 2);

        Label stokMenuLabel = new Label("Stok Menu:");
        stokMenuField = new TextField();
        grid.add(stokMenuLabel, 0, 3);
        grid.add(stokMenuField, 1, 3);

        // Add buttons
        Button viewMenuButton = new Button("View Menu");
        Button insertMenuButton = new Button("Insert Menu");
        Button updateMenuButton = new Button("Update Menu");
        Button deleteMenuButton = new Button("Delete Menu");
        grid.add(viewMenuButton, 0, 5);
        grid.add(insertMenuButton, 1, 5);
        grid.add(updateMenuButton, 2, 5);
        grid.add(deleteMenuButton, 3, 5);

        // Set action
        viewMenuButton.setOnAction(e -> {
        	List<Menu> menuList = menuManager.viewMenu();
            showMenuList(menuList);
        });

        insertMenuButton.setOnAction(e -> {
            String kodeMenu = generateRandomKodeMenu();
            String namaMenu = namaMenuField.getText();
            double hargaMenu = Double.parseDouble(hargaMenuField.getText());
            int stokMenu = Integer.parseInt(stokMenuField.getText());
            Menu newMenu = new Menu(kodeMenu, namaMenu, hargaMenu, stokMenu);
            menuManager.insertMenu(newMenu);
            kodeMenuField.setText(kodeMenu);
            namaMenuField.clear();
            hargaMenuField.clear();
            stokMenuField.clear();
        });

        updateMenuButton.setOnAction(e -> {
            String kodeMenu = kodeMenuField.getText();
            double newHarga = Double.parseDouble(hargaMenuField.getText());
            int newStok = Integer.parseInt(stokMenuField.getText());
            menuManager.updateMenu(kodeMenu, newHarga, newStok);
            namaMenuField.clear();
            hargaMenuField.clear();
            stokMenuField.clear();
        });

        deleteMenuButton.setOnAction(e -> {
            String kodeMenu = kodeMenuField.getText();
            menuManager.deleteMenu(kodeMenu);
            kodeMenuField.clear();
            namaMenuField.clear();
            hargaMenuField.clear();
            stokMenuField.clear();
        });

        // Set the scene
        Scene scene = new Scene(grid, 500, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    private void showMenuList(List<Menu> menuList) {
        Stage stage = new Stage();
        stage.setTitle("Menu List");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20, 20, 20, 20));
        grid.setVgap(10);
        grid.setHgap(10);

        Label kodeMenuLabel = new Label("Kode Menu");
        Label namaMenuLabel = new Label("Nama Menu");
        Label hargaMenuLabel = new Label("Harga Menu");
        Label stokMenuLabel = new Label("Stok Menu");
        grid.add(kodeMenuLabel, 0, 0);
        grid.add(namaMenuLabel, 1, 0);
        grid.add(hargaMenuLabel, 2, 0);
        grid.add(stokMenuLabel, 3, 0);

        int rowIndex = 1;
        for (Menu menu : menuList) {
            Label kodeMenu = new Label(menu.getKodeMenu());
            Label namaMenu = new Label(menu.getNamaMenu());
            Label hargaMenu = new Label(String.valueOf(menu.getHargaMenu()));
            Label stokMenu = new Label(String.valueOf(menu.getStokMenu()));
            grid.add(kodeMenu, 0, rowIndex);
            grid.add(namaMenu, 1, rowIndex);
            grid.add(hargaMenu, 2, rowIndex);
            grid.add(stokMenu, 3, rowIndex);
            rowIndex++;
        }

        // Set the scene
        Scene scene = new Scene(grid, 500, 300);
        stage.setScene(scene);
        stage.show();
    }
    
    private String generateRandomKodeMenu() {
        String randomKodeMenu = "PD-";
        for (int i = 0; i < 3; i++) {
            randomKodeMenu += (int) (Math.random() * 10);
        }
        return randomKodeMenu;
    }

    public static void main(String[] args) {
        launch(args);
    }
}