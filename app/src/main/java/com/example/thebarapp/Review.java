package com.example.thebarapp;


import com.google.firebase.firestore.Exclude;

public class Review {

    int food;
    int drink;
    int atmosphere;
    String comment;
    String documentId;

    public Review(int food,String comment, int drink, int atmosphere ) {
        this.food = food;
        this.drink = drink;
        this.atmosphere = atmosphere;
        this.comment = comment;
    }

    public Review() {
    }
    @Exclude
    public String getDocumentId(){
        return documentId;
    }
    public void setDocumentId(String documentId){
        this.documentId = documentId;
    }

    public int getFood() {
        return food;
    }



    public int getDrink() {
        return drink;
    }



    public int getAtmosphere() {
        return atmosphere;
    }



    public String getComment() {
        return comment;
    }




}
