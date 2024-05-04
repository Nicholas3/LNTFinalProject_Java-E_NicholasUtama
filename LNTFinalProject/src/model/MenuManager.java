package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MenuManager {
    private List<Menu> menuList;
    private Connection connection;

    public MenuManager() {
        this.menuList = new ArrayList<>();

        try {
        	connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/finalprojectbncc", "root", "");
            createTableIfNotExists();
            loadMenuData();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

        // Method to insert menu
        public void insertMenu(Menu newMenu) {
            menuList.add(newMenu);
            System.out.println("Menu inserted successfully.");

            try {
                String insertSQL = "INSERT INTO MENU (kodeMenu, namaMenu, hargaMenu, stokMenu) VALUES ('" +
                        newMenu.getKodeMenu() + "', '" +
                        newMenu.getNamaMenu() + "', " +
                        newMenu.getHargaMenu() + ", " +
                        newMenu.getStokMenu() + ")";
                Statement statement = connection.createStatement();
                statement.executeUpdate(insertSQL);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        // Method to update menu
        public void updateMenu(String kodeMenu, double newHarga, int newStok) {
            Iterator<Menu> iterator = menuList.iterator();
            while (iterator.hasNext()) {
                Menu menu = iterator.next();
                if (menu.getKodeMenu().equals(kodeMenu)) {
                    menu.setHargaMenu(newHarga);
                    menu.setStokMenu(newStok);
                    System.out.println("Menu updated successfully.");

                    try {
                        String updateSQL = "UPDATE MENU SET hargaMenu = " + newHarga + ", stokMenu = " + newStok +
                                " WHERE kodeMenu = '" + kodeMenu + "'";
                        Statement statement = connection.createStatement();
                        statement.executeUpdate(updateSQL);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                    return;
                }
            }
            System.out.println("Menu with kode " + kodeMenu + " not found.");
        }

        // Method to delete menu
        public void deleteMenu(String kodeMenu) {
            Iterator<Menu> iterator = menuList.iterator();
            while (iterator.hasNext()) {
                Menu menu = iterator.next();
                if (menu.getKodeMenu().equals(kodeMenu)) {
                    iterator.remove();
                    System.out.println("Menu deleted successfully.");

                    try {
                        String deleteSQL = "DELETE FROM MENU WHERE kodeMenu = '" + kodeMenu + "'";
                        Statement statement = connection.createStatement();
                        statement.executeUpdate(deleteSQL);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                    return;
                }
            }
            System.out.println("Menu with kode " + kodeMenu + " not found.");
        }

        // Create MENU table if not exists
        private void createTableIfNotExists() throws SQLException {
            String createTableSQL = "CREATE TABLE IF NOT EXISTS MENU (" +
                    "kodeMenu VARCHAR(10) PRIMARY KEY, " +
                    "namaMenu VARCHAR(255), " +
                    "hargaMenu DOUBLE, " +
                    "stokMenu INT)";
            Statement statement = connection.createStatement();
            statement.execute(createTableSQL);
        }

        // Method to load menu data from the database
        private void loadMenuData() throws SQLException {
            String selectSQL = "SELECT * FROM MENU";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(selectSQL);
            while (resultSet.next()) {
                String kodeMenu = resultSet.getString("kodeMenu");
                String namaMenu = resultSet.getString("namaMenu");
                double hargaMenu = resultSet.getDouble("hargaMenu");
                int stokMenu = resultSet.getInt("stokMenu");
                Menu menu = new Menu(kodeMenu, namaMenu, hargaMenu, stokMenu);
                menuList.add(menu);
            }
        }
    

    // Method to view all menus
    public List<Menu> viewMenu() {
        return menuList;
    }

    public void setMenuList(List<Menu> menuList) {
        this.menuList = menuList;
    }
}
