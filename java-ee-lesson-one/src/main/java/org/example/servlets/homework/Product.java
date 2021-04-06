package org.example.servlets.homework;

public class Product {
    private int id;
    private String title;
    private float cost;

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public float getCost() {
        return cost;
    }

    @Override
    public String toString() {
        return "Продукт: " +
                "id=" + id +
                ", название='" + title + '\'' +
                ", стоимость=" + cost;
    }
}
