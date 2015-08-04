package com.CATest.Team1.Model;

import javax.json.Json;
import javax.json.JsonObject;
import javax.persistence.Id;

public class Card  {

    private String Shading;
    private String Symbol;
    private String Color;
    private int Number;
    private String imageUrl;
    @Id
    private Long id;

    public String getShading() {
        return Shading;
    }

    public void setShading(String Shading) {
        this.Shading = Shading;
    }

    public String getSymbol() {
        return Symbol;
    }

    public void setSymbol(String Symbol) {
        this.Symbol = Symbol;
    }

    public String getColor() {
        return Color;
    }

    public void setColor(String Color) {
        this.Color = Color;
    }

    public int getNumber() {
        return Number;
    }

    public void setNumber(int Number) {
        this.Number = Number;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    
    public Card() {
    }

    public Card(String Color, String Symbol, String Shading, int Number) {
        this.Color = Color;
        this.Symbol = Symbol;
        this.Shading = Shading;
        this.Number = Number;
    }

     public JsonObject toJson(){
        return(Json.createObjectBuilder()
                .add("imageUrl", imageUrl)
                .build());
    }
     
    @Override
    public String toString() {
        return "Card{" + "Shading=" + Shading + ", Symbol=" + Symbol + ", Color=" + Color + ", Number=" + Number + '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    

}
