/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.berneytech.politopoly;

import static java.lang.System.out;
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
       
        switch (type){
            case "DONOTHING":
                break;
            case "COMMUNITYCHEST":
                Card.drawCommunityChest(player);
                break;
            case "CHANCE":
                Card.drawChance(player);
                break;
            case "TAX":
                if(name.equals("LUXURY TAX")){
                    player.setBalance(-75);
                }
                if(name.equals("INCOME TAX")){
                    out.println("Would you like to pay $200 or %10");
                    boolean decision=decision();
                    
                    if (decision){
                        player.setBalance(-200);
                    }
                    else{
                        player.setBalance((int) (-.1*player.getBalance()));
                    }
                }
                break;
            case "GOTOJAIL":
                player.setLocation(10);
            default:
                out.println("HMMMMMMMMMMMMMMMMMMMMMMMMMMMMM");
        Mechanics.setPlayer(player);
        }
   }
    boolean decision(){
        Scanner keyboard= new Scanner(System.in);
        String a= keyboard.nextLine();
        if (a.equals("$200")||a.equals("%10"))
            return a.equals("$200");
        else{
            out.println("Not a valid input. Please type $200 or %10.");
            return decision();
        }
    }
    public String getInput(){
        Scanner keyboard = new Scanner(System.in);
        return keyboard.nextLine();
    }
    public String getType() {
        return type;
    }

    @Override
    public int compareTo(Space x) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
