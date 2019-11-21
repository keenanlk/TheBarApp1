package com.example.thebarapp;


public class Review {

    int food;
    int drink;
    int atmosphere;
    String comment;

    public void Review(int food, String comment, int drink, int atmosphere){
        this.food = food;
        this.drink = drink;
        this.atmosphere = atmosphere;
        this.comment = comment;
    }

    public Review() {
    }

    public int getFood() {
        return food;
    }

    public void setFood(int food) {
        this.food = food;
    }

    public int getDrink() {
        return drink;
    }

    public void setDrink(int drink) {
        this.drink = drink;
    }

    public int getAtmosphere() {
        return atmosphere;
    }

    public void setAtmosphere(int atmosphere) {
        this.atmosphere = atmosphere;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }


}
