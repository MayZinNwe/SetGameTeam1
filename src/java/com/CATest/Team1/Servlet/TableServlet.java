package com.CATest.Team1.Servlet;

import com.CATest.Team1.Model.Card;
import com.CATest.Team1.Model.CardOnTable;
import com.CATest.Team1.Model.Game;
import com.CATest.Team1.Model.SetEngine;
import com.CATest.Team1.service.GameService;
import com.CATest.Team1.service.GameServiceImpl;
import javax.ejb.Stateless;
import javax.inject.Inject;
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
@Stateless
public class TableServlet extends HttpServlet {

    @Inject
    GameServiceImpl gameService;

    @GET
    @Produces("application/json")
    @Path("/getTableCards")
    public String showTableCards(@Context UriInfo info) {
        CardOnTable cardOnTable = null;
        String id = info.getQueryParameters().getFirst("id");
        if (id != null) {
            Game game = (gameService.getGame(id));
            cardOnTable = game.getCardOnTable();
            cardOnTable.showOnTable(false);
        } else {
            cardOnTable = new CardOnTable();
            cardOnTable.showOnTable(true);
        }
        //cardOnDeck.getCards();
        JsonObjectBuilder results = Json.createObjectBuilder();
        JsonArrayBuilder cards = Json.createArrayBuilder();
        JsonArrayBuilder setCards = Json.createArrayBuilder();
        if (id != null) {
            Game game = (gameService.getGame(id));
            cardOnTable = game.getCardOnTable();
            cardOnTable.showOnTable(false);
        }
        for (Card card : cardOnTable.tableCard) {
            cards.add(card.toJson());
        }
        for (Card card : cardOnTable.setGameCard) {
            if (card != null) {
                setCards.add(card.toJson());
            }
        }
        results.add("cards", cards.build());
        results.add("setCards", setCards);
        return results.build().toString();
    }

    @GET
    @Produces("application/json")
    @Path("/checkTableCards")
    public String checkTableCards(@Context UriInfo info) {
        CardOnTable cardOnTable = null;
        String gameId = info.getQueryParameters().getFirst("id");
        int cardId1 = Integer.parseInt(info.getQueryParameters().getFirst("card1").toString());
        int cardId2 = Integer.parseInt(info.getQueryParameters().getFirst("card2").toString());
        int cardId3 = Integer.parseInt(info.getQueryParameters().getFirst("card3").toString());
        JsonObjectBuilder results = Json.createObjectBuilder();
        JsonArrayBuilder cards = Json.createArrayBuilder();
        JsonArrayBuilder setCards = Json.createArrayBuilder();

        boolean valid = false;
        boolean success = true;
        // Call Service to check logic
        String id = info.getQueryParameters().getFirst("id");
        if (id != null) {
            Game game = (gameService.getGame(id));
            cardOnTable = game.getCardOnTable();
            Card card1 = cardOnTable.getCardOnTable(cardId1);
            Card card2 = cardOnTable.getCardOnTable(cardId2);
            Card card3 = cardOnTable.getCardOnTable(cardId3);
            if (card1 != null && card2 != null & card3 != null) {
                SetEngine setEngine = new SetEngine();
                valid = setEngine.isSet(card1, card2, card3);
                if (valid == true) {
                    //Remove the 3 cards and Replace the 3 cards at the same place
                    cardOnTable.setNewCardOnTable(card1);
                    cardOnTable.setNewCardOnTable(card2);
                    cardOnTable.setNewCardOnTable(card3);

                    for (Card card : cardOnTable.setGameCard) {
                        if (card != null) {
                            setCards.add(card.toJson());

                        } else {
                            success = false;
                        }
                    }
                } else {
                    for (Card card : cardOnTable.setGameCard) {
                        if (card != null) {
                            setCards.add(card.toJson());

                        } else {
                            success = false;
                        }
                    }
                }
            }
        }

        for (Card card : cardOnTable.tableCard) {
            cards.add(card.toJson());
        }
        results.add("setCards", setCards.build());
        results.add("status", Boolean.toString(valid));
        results.add("won", Boolean.toString(success));
        results.add("cards", cards.build());
        return results.build().toString();
    }

}
