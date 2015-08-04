package com.CATest.Team1.Servlet;

import com.CATest.Team1.Model.Card;
import com.CATest.Team1.Model.CardOnDeck;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.servlet.http.HttpServlet;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/cards")
public class GameServlet extends HttpServlet {
//

    @GET
    @Produces("application/json")
    @Path("/getAllCards")
    public JsonObject showAllCards() {
        CardOnDeck cardOnDeck = new CardOnDeck();
        cardOnDeck.getCards();
        JsonObjectBuilder results = Json.createObjectBuilder();
        JsonArrayBuilder cards = Json.createArrayBuilder();
        int i = 0;
        for (Card card : cardOnDeck.gameCards) {
            cards.add(card.toJson());
            i++;
        }
        
        results.add("cards", cards.build());
        return results.build();
    }
    
    @GET
    @Produces("application/json")
    @Path("/getShuffleCards")
    public JsonObject showShuffleCards() {
        CardOnDeck cardOnDeck = new CardOnDeck();
        cardOnDeck.getCards().shuffleCards();
        JsonObjectBuilder results = Json.createObjectBuilder();
        JsonArrayBuilder cards = Json.createArrayBuilder();
        int i = 0;
        for (Card card : cardOnDeck.gameCards) {
            cards.add(card.toJson());
            i++;
        }
        
        results.add("cards", cards.build());
        return results.build();
    }

//    @GET
//    @Produces("application/json")
//    @Path("/getAllCards")
//    public JsonObject getCards() {
//        //Note: this println is checking input data type (string or invalid int)
//        System.out.println(">>> cid = " + 1);
//        Card card = new Card("a", "b", "c", 1);
//        card.setImageUrl("d");
//        if (null == card) {
//            throw new WebApplicationException(Response.Status.NOT_FOUND);
//        }
//        return (card.toJson());
//
//    }
}
