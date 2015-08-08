package com.CATest.Team1.Model;

import java.util.Calendar;
import java.util.Date;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

public class Game {
    private String id;
    private Date createdDate;
    private User creator;
    private CardOnTable cardOnTable;

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

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }
    
    public Game(User creator) {
        id = Long.toString(Calendar.getInstance().getTimeInMillis());
        cardOnTable = new CardOnTable();
        createdDate = Calendar.getInstance().getTime();
        this.creator = creator;
    }

    public JsonObject toJson() {
        JsonObjectBuilder gameData = Json.createObjectBuilder();
        gameData.add("id", id);
        gameData.add("creator", creator.getUserName());
        gameData.add("date", createdDate.toString());
        return gameData.build();
    }

    @Override
    public String toString() {
        return "Game{" + "cardOnTable=" + cardOnTable + ", id=" + id + ", createdDate=" + createdDate + ", creator=" + creator + '}';
    }

}
