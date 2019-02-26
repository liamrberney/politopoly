/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.berneytech.politopoly;

import static java.lang.System.out;
import java.util.ArrayList;
import java.util.List;
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
                player.putInJail();
                break;
            case "ELECTION":
                electionDialogue();
                break;
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

    @Override
    public void setBuildings(int x) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    int contribution(){
        Scanner keyboard= new Scanner(System.in);
        try {
        return keyboard.nextInt();}
        catch(Exception e){
            out.println("Sorry, your answer sucked.");
            return contribution();
        }
    }

    private void electionDialogue() {
        List<Player> players= new ArrayList<Player>(Mechanics.getPlayers());
        int[] donations=new int[Mechanics.getNumPlayers()];
        out.println("Election time! All unexpired superpac donations are worth 10x");
        for (int x=0; x<players.size(); x++){
            out.print("How much will "+players.get(x).getName() + " contribute?: ");
            int contribution=contribution();
            donations[x]=players.get(x).getSuperBalance()+contribution;
            players.get(x).setBalance(-contribution);
            out.println("\n\n\n\n\n\n");
            Mechanics.setPlayers(players);
        }
        int max=0;
        for (int x=0; x<donations.length;x++){
            if (donations[x]>0
        }
    }
}
