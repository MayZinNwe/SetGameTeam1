package com.CATest.Team1.Servlet;

import com.CATest.Team1.Model.Card;
import com.CATest.Team1.Model.CardOnTable;
import com.CATest.Team1.Model.Game;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.servlet.http.HttpServlet;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

@Path("/cardsOnTable")
public class TableServlet extends HttpServlet {

    @GET
    @Produces("application/json")
    @Path("/getTableCards")
    public JsonObject showTableCards(@Context UriInfo info) {
        CardOnTable cardOnTable = null;
        String id = info.getQueryParameters().getFirst("id");
        if (id != null) {
            Long gameId = Long.parseLong(id);
            if (AppConfig.games.containsKey(gameId)) {
                Game game = AppConfig.games.get(gameId);
                cardOnTable = game.getCardOnTable();
                cardOnTable.showOnTable(false);
            }

        } else {
            cardOnTable = new CardOnTable();
            cardOnTable.showOnTable(true);
        }
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
