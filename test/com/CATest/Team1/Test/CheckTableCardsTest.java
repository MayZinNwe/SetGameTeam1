/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.CATest.Team1.Test;

import com.CATest.Team1.Model.Card;
import com.CATest.Team1.Model.CardOnTable;
import com.CATest.Team1.Model.Game;
import com.CATest.Team1.Model.SetEngine;
import com.CATest.Team1.Model.User;
import com.CATest.Team1.service.GameServiceImpl;
import org.junit.*;

public class CheckTableCardsTest {

    CardOnTable card = new CardOnTable();

    public CheckTableCardsTest() {

    }

    @Test
    public void getPossibleSetCart() {
        GameServiceImpl gameService = new GameServiceImpl();
        Game game = gameService.createGame(new User("tester"));
        CardOnTable cardOnTable = game.getCardOnTable();
        Card[] originalCards = cardOnTable.tableCard;

        Card card1 = cardOnTable.getCardOnTable(originalCards[0].getId());
        Card card2 = cardOnTable.getCardOnTable(originalCards[1].getId());
        Card card3 = cardOnTable.getCardOnTable(originalCards[2].getId());
        if (card1 != null && card2 != null & card3 != null) {
            cardOnTable.setNewCardOnTable(card1);
            cardOnTable.setNewCardOnTable(card2);
            cardOnTable.setNewCardOnTable(card3);
        }
        Assert.assertTrue("Correct", card1.getId()!=cardOnTable.tableCard[0].getId());
        Assert.assertTrue("Correct", card2.getId()!=cardOnTable.tableCard[1].getId());
        Assert.assertTrue("Correct", card3.getId()!=cardOnTable.tableCard[2].getId());
    }

}
