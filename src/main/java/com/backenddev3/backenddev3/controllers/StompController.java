package com.backenddev3.backenddev3.controllers;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.util.*;

import com.backenddev3.backenddev3.models.Bullet;
import com.backenddev3.backenddev3.models.Player;

@Controller
public class StompController {

    private List<Player> playerList = new ArrayList<>();
    
    @MessageMapping("/new-player")
    @SendTo("/destroy/players")
    public List<Player> newPlayer(String username) {

        System.out.println(username + " received");
        System.out.println(playerList.size());
        if (playerList.size() == 4) {
            return playerList;
        }

        int playerNumber = playerList.size() + 1;

        switch(playerNumber) {
            case 1: {
                Player player = new Player(username, 1, true, "blue", 0, 10, true, 0);
                playerList.add(player);
                System.out.println(playerList.size());
                return playerList;
            }
            case 2: {
                Player player = new Player(username, 2, false, "red", 19, 5, true, 0);
                playerList.add(player);
                System.out.println(playerList.size());
                return playerList;
            }
            case 3: {
                Player player = new Player(username, 3, false, "green", 19, 10, true, 0);
                playerList.add(player);
                System.out.println(playerList.size());
                return playerList;
            }
            case 4: {
                Player player = new Player(username, 4, false, "yellow", 19, 15, true, 0);
                playerList.add(player);
                return playerList;
            }
            default:
                return playerList;
        }

    }

    @MessageMapping("/new-round")
    @SendTo("/destroy/players")
    public List<Player> updatePlayersForNewRound(Player oldPlayer) {
        switch(oldPlayer.getPlayerNumber()) {
            case 1: {
                for(int i = 0; i < playerList.size(); i++) {
                    if (playerList.get(i).getUsername().equals(oldPlayer.getUsername())) {
                        playerList.remove(i);
                    }
                }
               
                Player player = new Player(oldPlayer.getUsername(), 2, false, oldPlayer.getColour(), 19, 5, true, oldPlayer.getScore());
                playerList.add(player);
                System.out.println(playerList.size());
                return playerList;
            }
            case 2: {
                for(int i = 0; i < playerList.size(); i++) {
                    if (playerList.get(i).getUsername().equals(oldPlayer.getUsername())) {
                        playerList.remove(i);
                    }
                }
                
                Player player = new Player(oldPlayer.getUsername(), 3, false,  oldPlayer.getColour(), 19, 10, true, oldPlayer.getScore());
                playerList.add(player);
                System.out.println(playerList.size());
                return playerList;
            }
            case 3: {
                for(int i = 0; i < playerList.size(); i++) {
                    if (playerList.get(i).getUsername().equals(oldPlayer.getUsername())) {
                        playerList.remove(i);
                    }
                }
                
                Player player = new Player(oldPlayer.getUsername(), 4, false,  oldPlayer.getColour(), 19, 15, true, oldPlayer.getScore());
                playerList.add(player);
                System.out.println(playerList.size());
                return playerList;
            }
            case 4: {
                for(int i = 0; i < playerList.size(); i++) {
                    if (playerList.get(i).getUsername().equals(oldPlayer.getUsername())) {
                        playerList.remove(i);
                    }
                }
                
                Player player = new Player(oldPlayer.getUsername(), 1, true,  oldPlayer.getColour(), 0, 10, true, oldPlayer.getScore());
                playerList.add(player);
                return playerList;
            }
            default:
                return playerList;
        }
    }
    
    @MessageMapping("/update-player-movement")
    @SendTo("/destroy/players")
    public List<Player> updatePlayerMovement(Player updatedPlayer) {
        
        System.out.println(updatedPlayer.getY() + ", " + updatedPlayer.getUsername());
        for(Player player : playerList) {
            if (player.getUsername().equals(updatedPlayer.getUsername().toString())) {
                player.setY(updatedPlayer.getY());
                player.setActive(updatedPlayer.isActive());
                System.out.println("we got here! " + player.getUsername());
                return playerList;
            }
        }
        return playerList;
        
    }

    @MessageMapping("/new-bullet")
    @SendTo("/destroy/bullets") 
    public Bullet newBullet(Bullet bullet) {
        System.out.println("fire!");
        return bullet;
    }

    @MessageMapping("/game-end")
    @SendTo("/destroy/players")
    public List<Player> emptyPlayerList() {
        playerList.clear();
        return playerList;
        
    }

}
