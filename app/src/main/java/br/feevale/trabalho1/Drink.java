package br.feevale.trabalho1;

public class Drink {

    private String name;
    private int volume;
    private boolean isAlcoolic;
    private double price;
    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public boolean isAlcoolic() {
        return isAlcoolic;
    }

    public void setAlcoolic(boolean alcoolic) {
        isAlcoolic = alcoolic;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
