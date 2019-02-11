/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.berneytech.politopoly;

import static java.lang.System.out;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author bernelia000
 */
public class Mechanics {
    public Board board;
    public static List<Player> players;
    public Mechanics(){
        players=new ArrayList<>();
        out.print("How many players?: ");
        int y=getNumber();
        for(int x=0;x<y;x++){
            out.print("Player "+x+" name: ");
            players.add(new Player(getPlayerName()));
        }
        Board.initializeBoard();
        for (int x=0;x<players.size();x++){
            players.get(x).beginTurn();
            if (x==players.size()-1)
                x-=players.size();
        }
    }
    public int getNumber(){
        for(;;){
            try{
                Scanner keyboard = new Scanner(System.in);
                return keyboard.nextInt();
            }
            catch (InputMismatchException e){
                out.println("That ain't a number boi");
            }
        }   
    }
    public static List<Player> getPlayers(){
        return players;
    }
    public static void setPlayers(ArrayList<Player> updatedPlayers){
        players=updatedPlayers;
    }
    public static void setPlayer(Player player){
        players.set(getPlayerIndex(player),player);
    }
    public String getPlayerName(){
        Scanner keyboard = new Scanner(System.in);
        return keyboard.nextLine();
    }
    static int getNumPlayers(){
        return players.size();
    }
    static int getPlayerIndex(Player player){
       for (int x=0; x<getNumPlayers(); x++){
           if (players.get(x).equals(player))
               return x;
       }
       return -1;
    }
}
