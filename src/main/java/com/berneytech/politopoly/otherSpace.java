/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.berneytech.politopoly;

import java.util.Scanner;

/**
 *
 * @author bernelia000
 */
public class otherSpace implements Space{
    final int location;
    String type;
    String name;
    otherSpace(int location, String name, String type){
        this.location=location;
        this.type=type;
        this.name=name;
    }
    public int isOwned(){
        return 2;
    }
    public String getName(){
        return name;
    }
    public int getLocation() {
        return location;
    }
    public int getRent(){
        return 0;
    }

    public void landedOn(Player player) {
       /*if (type.equals("TAX")){
           if (location==4){
               out.println("Would you like to pay 10% or $200"){
               if (getInput().equals("10%")){
                   player.
               }
           }
           }
       }*/
    }
    public String getInput(){
        Scanner keyboard = new Scanner(System.in);
        return keyboard.nextLine();
    }
    public String getType() {
        return type;
    }
}
