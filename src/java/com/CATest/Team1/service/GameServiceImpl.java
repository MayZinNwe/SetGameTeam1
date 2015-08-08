/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.CATest.Team1.service;

import com.CATest.Team1.Model.Game;
import com.CATest.Team1.Model.User;
import java.util.Collection;
import java.util.HashMap;
import javax.enterprise.context.ApplicationScoped;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

/**
 *
 * @author MZN
 */
@ApplicationScoped
public class GameServiceImpl implements GameService{
    private final HashMap<String, Game> games = new HashMap<>();
    private final HashMap<String, User> users= new HashMap<>();
    
    public GameServiceImpl(){
        
    }
    @Override
    public boolean addUser(User user) {
        if(!users.containsKey(user.getUserName())){
            users.put(user.getUserName(), user);
            return true;
        }        
        return false;
    }

    @Override
    public boolean isValid(User user) {
        if(users.containsKey(user.getUserName())){
            return users.get(user.getUserName()).getUserPassword().equalsIgnoreCase(user.getUserPassword());
        }
        return false;
    }
 

    @Override
    public Collection<User> getAllUsers() {
        return users.values();
    }
    
    @Override
    public Game createGame(User user) {
        Game game = new Game(user);
        games.put(game.getId(), game);
        return game;
    }

   
    @Override
    public Game getGame(String id) {
        return games.get(id);
    }
    
    @Override
    public JsonObject toJson(){
        JsonObjectBuilder gamesObject = Json.createObjectBuilder();
        JsonArrayBuilder gameList = Json.createArrayBuilder();
        JsonArrayBuilder onlineUserList = Json.createArrayBuilder();
        for(Game game : games.values()){
            gameList.add(game.toJson());
        }
        for(User user : users.values()){
            JsonObjectBuilder userObject = Json.createObjectBuilder();
            userObject.add("username", user.getUserName());
            onlineUserList.add(userObject.build());
        }
        gamesObject.add("games", gameList);
        gamesObject.add("users", onlineUserList);
        
        return gamesObject.build();
    }
    
}
