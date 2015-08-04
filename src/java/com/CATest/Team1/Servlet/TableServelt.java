package com.CATest.Team1.Servlet;

import com.CATest.Team1.Model.Card;
import com.CATest.Team1.Model.CardOnTable;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.servlet.http.HttpServlet;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/cardsOnTable")
public class TableServelt extends HttpServlet {
    
      @GET
    @Produces("application/json")
    @Path("/getTableCards")
    public JsonObject showTableCards()  {
        
        CardOnTable cardOnTable = new CardOnTable();
        cardOnTable.showOnTable();
        //cardOnDeck.getCards();
        JsonObjectBuilder results = Json.createObjectBuilder();
        JsonArrayBuilder cards = Json.createArrayBuilder();
        int i = 0;
        for (Card card : cardOnTable.tableCard) {
            cards.add(card.toJson());
            i++;
        }
        
        results.add("cards", cards.build());
        return results.build();
    }
    
//    @GET
//    @Produces("application/json")
//    @Path("/getShuffleCards")
//    public JsonObject showShuffleCards() {
//        CardOnDeck cardOnDeck = new CardOnDeck();
//        cardOnDeck.getCards().shuffleCards();
//        JsonObjectBuilder results = Json.createObjectBuilder();
//        JsonArrayBuilder cards = Json.createArrayBuilder();
//        int i = 0;
//        for (Card card : cardOnDeck.gameCards) {
//            cards.add(card.toJson());
//            i++;
//        }
//        
//        results.add("cardsontable", cards.build());
//        return results.build();
//    }
    
}
