package com.CATest.Team1.Model;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

public class Game {
    private String id;
    private Date createdDate;
    private String creator;
    private CardOnTable cardOnTable;
    private String description;
    private int maximumPlayer;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getMaximumPlayer() {
        return maximumPlayer;
    }

    public void setMaximumPlayer(int maximumPlayer) {
        this.maximumPlayer = maximumPlayer;
    }

    public Game(){
        
    }
    public CardOnTable getCardOnTable() {
        return cardOnTable;
    }

    public void setCardOnTable(CardOnTable cardOnTable) {
        this.cardOnTable = cardOnTable;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }
    
    public Game(String creator) {
        id = Long.toString(Calendar.getInstance().getTimeInMillis());
        cardOnTable = new CardOnTable();
        createdDate = Calendar.getInstance().getTime();
        this.creator = creator;
    }

    public JsonObject toJson() {
        JsonObjectBuilder gameData = Json.createObjectBuilder();
        gameData.add("id", id);
        gameData.add("creator", creator);
        gameData.add("date", createdDate.toString());
        gameData.add("description", description);
        gameData.add("maximumPlayer", maximumPlayer);
        return gameData.build();
    }

    @Override
    public String toString() {
        return "Game{" + "id=" + id + ", createdDate=" + createdDate + ", creator=" + creator + ", cardOnTable=" + cardOnTable + ", description=" + description + ", maximumPlayer=" + maximumPlayer + '}';
    }

}
