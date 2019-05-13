package br.feevale.trabalho1;

public class Sale {

    private long id;
    private long client;
    private long food;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getClient() {
        return client;
    }

    public void setClient(long client) {
        this.client = client;
    }

    public long getFood() {
        return food;
    }

    public void setFood(long food) {
        this.food = food;
    }

    public long getDrink() {
        return drink;
    }

    public void setDrink(long drink) {
        this.drink = drink;
    }

    private long drink;


}
