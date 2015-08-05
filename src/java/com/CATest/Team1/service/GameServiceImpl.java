/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.CATest.Team1.service;

import com.CATest.Team1.Model.Game;
import com.CATest.Team1.Model.User;
import java.util.HashMap;

/**
 *
 * @author MZN
 */
@javax.enterprise.context.ApplicationScoped
public class GameServiceImpl implements GameService {
    private final HashMap<String, Game> games = new HashMap<>();
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
    
}
