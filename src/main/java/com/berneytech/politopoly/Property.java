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
public class Property implements Space{
    int location;
    String name;
    String type;
    int[] rent;
    int price;
    int buildings; //0=rent,1=1 house, etc. 5=hotel
    int owned;
    Player owner;
    int houseCost;
    
    public Property(int location, String name, String type, int price, int[] rent, int houseCost){
    this.location=location;
    this.name=name;
    this.type=type;
    this.price=price;
    this.buildings=0;
    this.owned=0;
    this.rent=rent;
    this.houseCost=houseCost;
    }
    public String getName(){
        return name;
    }
    public int getLocation() {
        return location;
    }
    public int getRent(){
        return rent[buildings];
    }
    
    public void landedOn(Player player) {
        if (owned==1){
            payRent(owner);
        }
        else{
            buyDialogue(player);
        }
    }
    public int compareTo(Space x){
        if (type.charAt(0)<x.getType().charAt(0))
            return -1;
        else if (type.charAt(0)==x.getType().charAt(0))
            return 0;
        return 1;
        
    }
    public int isOwned(){
        return owned;
    }
    void payRent(Player player){
        int paidrent=rent[buildings];
        player.setBalance(-paidrent);
        owner.setBalance(paidrent);
        Mechanics.setPlayer(owner);
        Mechanics.setPlayer(player);
        out.println(player+" pays "+owner+" $"+rent[buildings]+" for rent");
    }
    void buyDialogue(Player player){
        List<Player> players= new ArrayList(Mechanics.getPlayers());
        
        out.println("Would "+player+" like to buy "+name+" for "+price+"? [yes/no]");
        boolean decision=decision();
        if (decision && player.getBalance()>=price){
                owned=1;
                owner=player;
                player.setBalance(-price);
                player.addSpace(this);
                player.updateBuildings(type);
                Mechanics.setPlayer(player);
        }
        else{
            if (!(player.getBalance()>=price))
                out.println("stop being poor.");
            if (Mechanics.getNumPlayers()==2){
                out.println("Would the other player like to buy "+name+" for "+price+"? [yes/no]");
                int indexOne=Mechanics.getPlayerIndex(player);
                decision=decision();
                if (indexOne==0){
                    if(players.get(1).getBalance()>price&&decision){
                        owned=1;
                        players.get(1).setBalance(-price);
                        players.get(1).addSpace(this);
                        owner=players.get(1);
                        Mechanics.setPlayer(players.get(1));
                    }
                    else{
                        out.println("I'm sorry you're poor");
                    }
                }
                if (indexOne==1){
                    if(players.get(0).getBalance()>price&&decision){
                        owned=1;
                        players.get(0).setBalance(-price);
                        players.get(0).addSpace(this);
                        owner=players.get(0);
                        Mechanics.setPlayer(players.get(0));
                    }
                    else{
                        out.println("I'm sorry you're poor");
                    }
                }
            }
            else{
                beginAuction();
            }
        }
    }
    public void setBuildings(int x){
        buildings=x;  
    }
    boolean decision(){
        Scanner keyboard= new Scanner(System.in);
        String a= keyboard.nextLine();
        if (a.equals("yes")||a.equals("no"))
            return a.equals("yes");
        else{
            out.println("Not a valid input. Please type yes or no.");
            return decision();
        }
    }
    int bid(){
        Scanner keyboard=new Scanner(System.in);
        return keyboard.nextInt();
    }
    void beginAuction(){
        List<Player> players= new ArrayList<Player>(Mechanics.getPlayers());
        int maxBid=0;
        Player winner=players.get(0);
        for (int x=0; x<players.size(); x++){
            out.println("Would "+players.get(x)+" like to bid?");
            if (decision()){
                out.print("Enter bid amount: ");
                int bid=bid();
                if (bid>maxBid&&bid<=players.get(x).getBalance()){
                    maxBid=bid;
                    winner=players.get(x);
                }
                else{
                    out.println("IT'S FOR THE CHURCH HONEY. NEXT!!!");
                    players.remove(x);
                    x--;
                }
            }
            else{
                players.remove(x);
                x--;
            }
            
            if (x==players.size()-1){
            x-=players.size();
            }
        }
        out.println(winner+" won "+name+" for $"+maxBid);
        winner.setBalance(-maxBid);
        winner.addSpace(this);
        Mechanics.setPlayer(winner);
    }
    public String toString(){
        return "["+type+"] "+name;
    }

    @Override
    public String getType() {
        return type;
    }

    public int getBuildings(){
        return buildings;
    }

    @Override
    public int getHouseCost() {
        return houseCost;
    }
    
    
}
