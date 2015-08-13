/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.CATest.Team1.Model;

/**
 *
 * @author MZN
 */
public class User {
    private String userName;
    private String userPassword;
    long totalScore;
    long totalTimePlayed;
    
    public User() {
    
    }
    
    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
    
    public User(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public long getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(long totalScore) {
        this.totalScore = totalScore;
    }

    public long getTotalTimePlayed() {
        return totalTimePlayed;
    }

    public void setTotalTimePlayed(long totalTimePlayed) {
        this.totalTimePlayed = totalTimePlayed;
    }
    
    @Override
    public String toString() {
        return "User{" + "userName=" + userName + '}';
    }
    
}
