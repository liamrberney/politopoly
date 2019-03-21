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
    int houseCost;
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

     void electionDialogue() {
        List<Player> players= new ArrayList<>(Mechanics.getPlayers());
        int[] donations=new int[Mechanics.getNumPlayers()];
        out.println("Election time! All unexpired superpac donations are worth 10x");
        for (int x=0; x<players.size(); x++){
            out.print("How much will "+players.get(x).getName() + " contribute?: ");
            int contribution=contribution();
            donations[x]=players.get(x).getSuperBalance()+contribution;
            players.get(x).setBalance(-contribution);
            out.println("\n\n\n\n\n\n");
            Mechanics.setPlayers((ArrayList<Player>) players);
        }
        int max=0;
        int maxIndex=0;
        for (int x=0; x<donations.length;x++){
            if (donations[x]>max){
                max=donations[x]; maxIndex=x;
                
            }
        }
        out.println(players.get(maxIndex).getName()+" wins the election!");
        winnerDialogue(players.get(maxIndex));
    }

    private void winnerDialogue(Player player) {
        out.println("What kind of bill would you like to create: \n"
                + "redistribution [r], taxation [tax] , building maintenance [bm], railroad regulation [rr], \n"
                + "railroad deregulation [rd] , property forclosure [p], low income housing subsidies [hs], or tarrifs[t]?");
        switch (bill()){
            case "r":
                redistributionDialouge();
                break;
            case "tax":
                taxDialogue(player);
                break;
            case "bm":
                maintenanceDialogue();
                break;
            case "rr":
                regulationDialogue();
                break;
            case "rd":
                deregulationDialogue();
                break;
            case "pf":
                forclosureDialogue();
                break;
            case "hs":
                subsidiesDialogue();
                break;
            case "t":
                tarrifsDialogue();
                break;
            default:
                out.println("Sorry, your answer sucked.");
                winnerDialogue(player);
                
                
                
        }
    }
    
    String bill(){
        Scanner keyboard= new Scanner(System.in);
        try {
        return keyboard.nextLine();}
        catch(Exception e){
            out.println("Sorry, your answer sucked.");
            return bill();
        }
    }

    private void redistributionDialouge() {
        List<Player> players= new ArrayList<>(Mechanics.getPlayers());
        int total=0;
        for (Player player:players){
            total+=player.getBalance();
        }
        for (Player player:players){
            player.changeBalance(total/players.size());
            Mechanics.setPlayer(player);
        }
    }

    private void taxDialogue(Player winner) {
        List<Player> players= new ArrayList<>(Mechanics.getPlayers());
        for (int x=0; x<players.size();x++){
            if (players.get(x).equals(winner)){
                 players.remove(x);
            }
        }
        int total=0;
        for (Player player: players){
            int tax=(int)(.2*player.getBalance());
            total+=tax;
            player.setBalance(-tax);
            Mechanics.setPlayer(player);
        }
        winner.setBalance(total);
        Mechanics.setPlayer(winner);
        
    }

    private void maintenanceDialogue() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void regulationDialogue() {
        out.println("Railroad tickets got halved in price!");
        Mechanics.regulate();
    }

    private void deregulationDialogue() {
        out.println("Railroad tickets doubled in price!");
        Mechanics.deregulate();
    }

    private void forclosureDialogue() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void subsidiesDialogue() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void tarrifsDialogue() {
        out.println("You decided to start a trade war with China. Market crashes by 50%");
        List<Player> players= new ArrayList<>(Mechanics.getPlayers());
        for (Player player:players){
            player.marketCrash();
            Mechanics.setPlayer(player);
        }
    }

    @Override
    public int getBuildings() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getHouseCost() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void increaseBuildings() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getPrice() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
