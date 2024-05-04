package model;

public class Menu {
    private String kodeMenu;
    private String namaMenu;
    private double hargaMenu;
    private int stokMenu;

    public Menu(String kodeMenu, String namaMenu, double hargaMenu, int stokMenu) {
        this.kodeMenu = kodeMenu;
        this.namaMenu = namaMenu;
        this.hargaMenu = hargaMenu;
        this.stokMenu = stokMenu;
    }

    public String getKodeMenu() {
        return kodeMenu;
    }

    public void setKodeMenu(String kodeMenu) {
        this.kodeMenu = kodeMenu;
    }

    public String getNamaMenu() {
        return namaMenu;
    }

    public void setNamaMenu(String namaMenu) {
        this.namaMenu = namaMenu;
    }

    public double getHargaMenu() {
        return hargaMenu;
   }

    public void setHargaMenu(double hargaMenu) {
        this.hargaMenu = hargaMenu;
    }

    public int getStokMenu() {
        return stokMenu;
    }

    public void setStokMenu(int stokMenu) {
        this.stokMenu = stokMenu;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "kodeMenu='" + kodeMenu + '\'' +
                ", namaMenu='" + namaMenu + '\'' +
                ", hargaMenu=" + hargaMenu +
                ", stokMenu=" + stokMenu +
                '}';
    }
}