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
        Card a= Board.getCommunityChest((int)Math.random()*17);
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
                case "REPAIRS":
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
        throw new UnsupportedOperationException("Not supported yet."); 
        
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
