/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.CATest.Team1.Model;

import java.util.Calendar;
import java.util.HashMap;

/**
 *
 * @author MZN
 */
public class UsersOnGame {
    private HashMap<String,User> onlineUsers= new HashMap<String,User>();
    private HashMap<String,UserScore> usersScore = new HashMap<String,UserScore>();

    public UsersOnGame() {
    }
    
    public UserScore addOnlineUser(User user){
        UserScore userScore;
        if(!onlineUsers.containsKey(user.getUserName())){
            
            onlineUsers.put(user.getUserName(),user);
        }
        if(!usersScore.containsKey(user.getUserName())){
            userScore = new UserScore(user.getUserName());
            userScore.startTime = Calendar.getInstance().getTimeInMillis();
            usersScore.put(user.getUserName(), userScore);
        }else{
            userScore=  usersScore.get(user.getUserName());   
        }
        return userScore;
    }
    
    public boolean removeOnlineUser(User user){
        if(onlineUsers.containsKey(user.getUserName())){
            onlineUsers.remove(user.getUserName());
            return true;
        }
        if(usersScore.containsKey(user.getUserName())){
            UserScore userScore = usersScore.get(user.getUserName());
            long endTime = Calendar.getInstance().getTimeInMillis();
            long startTime = userScore.getStartTime();
            long duration = endTime-startTime/1000;
            userScore.setDuration(duration);
            user.setTotalTimePlayed(user.getTotalTimePlayed()+duration);
            user.setTotalScore(user.getTotalScore()+userScore.getScore());
        }
        return false;
    }
    
    public boolean addScore(User user){
        if(usersScore.containsKey(user.getUserName())){
            UserScore userScore =usersScore.get(user.getUserName());
            userScore.setScore(userScore.getScore()+1);
            return true;
        }
        return false;
    }

    public HashMap<String, User> getOnlineUsers() {
        return onlineUsers;
    }

    public HashMap<String, UserScore> getUsersScore() {
        return usersScore;
    }
}
