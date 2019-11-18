package com.example.thebarapp;

public class Review {
    int id;
    int barID;
    int rating;
    String drink;
    String food;
    String atmosphere;
    String overall;

    public void Review(int id, int barID, int rating, String drink, String food, String atmosphere, String overall){
        setId(id);
        setBarID(barID);
        setRating(rating);
        setDrink(drink);
        setFood(food);
        setAtmosphere(atmosphere);
        setOverall(overall);
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

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getDrink() {
        return drink;
    }

    public void setDrink(String drink) {
        this.drink = drink;
    }

    public String getFood() {
        return food;
    }

    public void setFood(String food) {
        this.food = food;
    }

    public String getAtmosphere() {
        return atmosphere;
    }

    public void setAtmosphere(String atmosphere) {
        this.atmosphere = atmosphere;
    }

    public String getOverall() {
        return overall;
    }

    public void setOverall(String overall) {
        this.overall = overall;
    }
}
