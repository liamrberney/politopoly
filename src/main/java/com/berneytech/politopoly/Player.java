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
        balance+=x;
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
}
