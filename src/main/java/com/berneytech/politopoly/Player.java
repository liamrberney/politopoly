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
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author bernelia000
 */
public class Player {
    List<Space> spaces;
    int balance;
    int location;
    String name;
    int dice;
    int getOutOfJailFreeCards;
    public Player(String name){
        this.name=name;
        spaces= new ArrayList<>();
        location=0;
        balance=1500;
        getOutOfJailFreeCards=0;
    }
    public void beginTurn(){
        diceRoll();
        out.println(name+" rolls a "+dice+" and lands on "+Board.getSpace(location).getName());
        Board.getSpace(location).landedOn(this);
        boolean turnEnded=false;
        while(!turnEnded){
            out.print("Would "+name+" like to build[build], donate to superpac[superpac], \n"
                    + "invest[invest], mortgage[mortgage], unmortgage[unmortage], get stats[stats], or end turn[end]:");
            String a=getInput();
            switch(a){
                case "build":
                    buildDialogue();
                    break;
                case "superpac":
                    superpacDialogue();
                    break;
                case "invest":
                    investDialogue();
                    break;
                case "mortgage":
                    mortgageDialogue();
                    break;
                case "unmortgage":
                    unmortgageDialogue();
                    break;
                case "end":
                    turnEnded=true;
                    break;
                case "stats":
                    statsDialogue();
                    break;
                default:
                    out.println("Sorry, your answer sucked. Try again.");
            }
        }
    }
    public String getInput(){
        Scanner keyboard = new Scanner(System.in);
        return keyboard.nextLine();
    }
    int getBalance(){
        return balance;
    }
    void addSpace(Space space){
        spaces.add(space);
    }
    void passGo(){
        balance+=200;
    }
    void setBalance(int x){
        balance=balance+x;
    }
    void diceRoll(){
        dice=(int) ThreadLocalRandom.current().nextInt(1, 6 + 1)+ThreadLocalRandom.current().nextInt(1, 6 + 1);
        out.println(dice);
        if(location+dice>39)
            location+=dice-40;
        else
            location+=dice;
    }
    boolean equals(){
        return  balance==balance &&
                location== location &&
                name.equals(name);
    }
    public String toString(){
        return name;
    }

    private void buildDialogue() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void superpacDialogue() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void investDialogue() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void unmortgageDialogue() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void mortgageDialogue() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void statsDialogue() {
        out.println("Balance: "+balance);
        out.println("Properties: ");
        for (Space a:spaces){
            out.println(a);
        }
        out.println("Number of get out of jail free cards: "+getOutOfJailFreeCards);
    }
}
