/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.berneytech.politopoly;

import static java.lang.System.out;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author bernelia000
 */
public class Card {

    static void drawCommunityChest(Player player) {
        Card a= Board.getCommunityChest((int)(Math.random()*17));
        out.println(a.getName());
        out.println(a.getText());
        if (a.getSendTo()==-1){
            switch (a.getType()){
                case "PAYMENT":
                player.setBalance(a.getRent());
                break;
                case "REDISTRIBUTION":
                    List b= new ArrayList(Mechanics.getPlayers());
                case "GETOUTOFJAILFREE":
                    player.setGetOutOfJailFree(1);
                    break;
                case "STREETREPAIRS":
                    break;
                case "REPAIRS":
                    break;
                default:
                    out.println("HMMMM");
        }
        }
        else{
            if (!a.getType().equals("GOTOJAIL")){
                if (a.getSendTo()<player.getLocation()){
                    player.setBalance(200);
                }
                player.setLocation(a.getSendTo());
            }
            else{
                player.setLocation(10);
                player.putInJail();
            }
        }
    }
        

    static void drawChance(Player player) {
        Card a= Board.getChance((int)(Math.random()*15));
        out.println(a.getName());
        out.println(a.getText());
        if (a.getSendTo()==-1){
            switch (a.getType()){
                case "PAYMENT":
                player.setBalance(a.getRent());
                break;
                case "REDISTRIBUTION":
                    List<Player> b= new ArrayList(Mechanics.getPlayers());
                    player.setBalance(a.getRent()*(b.size()-1));
                    for (Player c : b){
                        c.setBalance(a.getRent()*-1);
                        Mechanics.setPlayer(c);
                    }
                    Mechanics.setPlayer(player);
                case "GETOUTOFJAILFREE":
                    player.setGetOutOfJailFree(1);
                    break;
                case "GOBACK":
                    player.setLocation(player.getLocation()-3);
                case "STREETREPAIRS":
                    break;
                case "REPAIRS":
                    break;
                case "MOVEMENT":
                    if (player.getLocation()<10)
                        player.setLocation(15);
                        else {
                                player.setLocation(25);
                                }
                    break;
                default:
                    out.println("HMMMM");
        }
        }
        else{
            if (!a.getType().equals("GOTOJAIL")){
                if (a.getSendTo()<player.getLocation()){
                    player.setBalance(200);
                }
                player.setLocation(a.getSendTo());           
                Board.getSpace(a.getSendTo()).landedOn(player);
            }
            else{
                player.setLocation(10);
                player.putInJail();
            }
        }
        Mechanics.setPlayer(player);
     
        
    }
    public  String name;
    public  String type;
    public  String text;
    public  int sendTo;
    public  int rent;
    public Card(String name, String type, String text, int sendTo, int rent){
    this.name=name;
    this.type=type;
    this.text=text;
    this.sendTo=sendTo;
    this.rent=rent;
    }
    String getName(){
        return name;
    }
    String getText(){
        return text;
    }
    String getType(){
        return type;
    }
    int getSendTo(){
        return sendTo;
    }
    int getRent(){
        return rent;
    }
}
