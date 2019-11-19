package com.example.thebarapp;

public class Review {
    int id;



    int barID;
    int food;
    int drink;
    int atmosphere;
    String comment;

    public void Review(int id, int barID, int food, int drink, int atmosphere, String comment){
        this.barID = getBarID();
        this.id = getId();
        this.food = getFood();
        this.drink = getDrink();
        this.atmosphere = getAtmosphere();
        this.comment = getComment();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBarID() {
        return barID;
    }

    public void setBarID(int barID) {
        this.barID = barID;
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
