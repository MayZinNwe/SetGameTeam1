/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.CATest.Team1.service;

import com.CATest.Team1.Model.Game;
import com.CATest.Team1.Model.User;

/**
 *
 * @author MZN
 */
public interface GameService {

    /**
     *
     * @param user
     * @return
     */
    public Game createGame(User user);
    
    public Game getGame(String id);
}
