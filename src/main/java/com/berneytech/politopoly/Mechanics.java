 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.berneytech.politopoly;

import java.awt.EventQueue;
import static java.lang.System.out;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import javax.swing.JFrame;

/**
 *
 * @author bernelia000
 */
public class Mechanics {
    public Board board;
    public static List<Player> players;
    public static int railroadMultiplier;
    public static GUI gameBoard;
    public static ImageComponent die1;
    public static ImageComponent die2;
    public Mechanics(){
        //GUI a=new GUI("C:\\Users\\bernelia000\\Documents\\NetBeansProjects\\politopoly\\src\\Dice\\dice 2.png");
        die1=new ImageComponent("C:\\Users\\bernelia000\\Documents\\NetBeansProjects\\politopoly\\src\\bitmap.png",0,0,50,"die1");
        die2=new ImageComponent("C:\\Users\\bernelia000\\Documents\\NetBeansProjects\\politopoly\\src\\bitmap.png",0,50,50,"die2");
        gameBoard=new GUI();
        ImageComponent boardImage = new ImageComponent("C:\\Users\\bernelia000\\Documents\\NetBeansProjects\\politopoly\\src\\bitmap.png",100,0,700,"gameBoard");
        gameBoard.addToFrame(boardImage);
        gameBoard.addToFrame(die1);
        gameBoard.addToFrame(die2);
        railroadMultiplier=1;
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
    static void regulate(){
        railroadMultiplier*=.5;
    }
    static GUI getGUI(){
        return gameBoard;
    }
    static void deregulate(){
        railroadMultiplier*=2;
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
