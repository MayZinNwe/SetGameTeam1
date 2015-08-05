package com.CATest.Team1.Servlet;

import com.CATest.Team1.Model.Card;
import com.CATest.Team1.Model.CardOnDeck;
import com.CATest.Team1.Model.Game;
import com.CATest.Team1.Model.User;
import com.CATest.Team1.service.GameService;
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
// Ref : https://jersey.java.net/documentation/latest/jaxrs-resources.html#d0e2011

/**
 *
 * @author MZN
 */
@Path("/game")
@Stateless
public class GameServlet extends HttpServlet {
    
    @Inject
    GameService gameService ;
   
    @GET
    @Produces("application/json")
    @Path("/createNewGame")
    public JsonObject createNewGame() {
        Game game = gameService.createGame(new User("demo"));
        
        return game.toJson();
    }

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

    @GET
    @Produces(value = "application/json")
    @Path(value = "/openExistingGame")
    public JsonObject doOpenExistingGame(@Context UriInfo info ) {
        String id = info.getQueryParameters().getFirst("id");
        if(id!=null){
            gameService.getGame(id).toJson();
        }
        return null;
    }
}
