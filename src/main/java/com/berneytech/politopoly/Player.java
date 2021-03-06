/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.berneytech.politopoly;

import static java.lang.System.out;
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;


public class Player {
    List<Space> spaces;
    List<Space> mortgagedSpaces;
    boolean inJail;
    int jailTurns;
    int balance;
    int location;
    String name;
    int dice;
    int getOutOfJailFreeCards;
    List<financialAccount> superBalances;
    List<financialAccount> investmumpts;
    public Player(String name){
        this.name=name;
        spaces= new ArrayList<>();
        location=0;
        balance=1500;
        getOutOfJailFreeCards=0;
        inJail=false;
        jailTurns=0;
        superBalances=new ArrayList<>();
        investmumpts=new ArrayList<>();
        mortgagedSpaces=new ArrayList<>();
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
    
    public void marketCrash(){
         for (int x=0; x<investmumpts.size();x++){
            investmumpts.get(x).marketCrash();
        }
    }
    public void beginTurn(){
        for (int x=0; x<superBalances.size();x++){
            superBalances.get(x).updateTurns();
            if (superBalances.get(x).getTurns()<=0){
                out.println("Superpac donation of "+superBalances.get(x).getAmount()+" has expired for "+name);
                superBalances.remove(x);     
            }
        }
        for (int x=0; x<investmumpts.size();x++){
            investmumpts.get(x).updateTurns();
            investmumpts.get(x).setBalance((int)(investmumpts.get(x).getAmount()*1.09));
            if (investmumpts.get(x).getTurns()==0){
                out.println(name+" sells his stonks for "+ investmumpts.get(x).getAmount());
                balance+=investmumpts.get(x).getAmount();
                investmumpts.remove(x);     
            }
        }
        if (inJail){
            if (jailTurns==0){
                setBalance(-50);
                inJail=false; beginTurn();
            }
            else{
                out.println("Would "+name+" like to pay $50 to get out of jail? [yes]/[no]");
                if (decision()){
                    inJail=false;  jailTurns=0; beginTurn();
                }
                else{
                    boolean isDoubles=diceRoll();
                    if (isDoubles){
                        inJail=false; jailTurns=0;beginTurn();
                    }
                    else{
                        jailTurns--;
                    }   
                }
            }
            
        
            
        }
        int beforeRoll=location;
        boolean isDoubles=diceRoll();
        if (location<beforeRoll){
            out.println("You have passed go. Collect $200");
            balance+=200;
        }
        out.println(name+" rolls a "+dice+" and lands on "+Board.getSpace(location).getName());
        
        Board.getSpace(location).landedOn(this);
        if (isDoubles){
            out.println("You rolled doubles. Roll again");
            beginTurn();
        }
        else{
            boolean turnEnded=false;
            while(!turnEnded){
                out.print("Would "+name+" like to build[build], donate to superpac[superpac], \n"
                        + "invest[invest], mortgage[mortgage], unmortgage[unmortage], play the lottery[lottery], get stats[stats], or end turn[end]:");
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
                    case "lottery":
                        lotteryTicket();
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
        
    }
    void putInJail(){
        inJail=true;
        jailTurns=3;
    }
    int getSuperBalance(){
        int x=0;
        for (financialAccount a:superBalances){
            x+=a.getAmount();
        }
        return x*10;
    }
    public void lotteryTicket(){
        if (balanceCheck()){
        balance-=100;
        if (rollDice()==7 && rollDice()==7 && rollDice()==7){
            balance+=100*100; out.println("Congratulations! You've won $10 THOUSAND dollars");
        }
        else{
            out.println("Better luck next time");
        }
        }
        else{
            out.println("stop being poor");
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
    void setGetOutOfJailFree(int x){
        getOutOfJailFreeCards+=1;
    }
    void passGo(){
        balance+=200;
    }
    void setBalance(int x){
        balance=balance+x;
    }
    boolean balanceCheck(){
        return balance>0;
    }
    void setLocation(int x){
        location=x;
    }
    int getLocation(){
        return location;
    }
    boolean diceRoll(){
        int a= ThreadLocalRandom.current().nextInt(1, 6 + 1);
        int b= ThreadLocalRandom.current().nextInt(1, 6 + 1);
        
        Mechanics.die1.setImage("C:\\Users\\bernelia000\\Documents\\NetBeansProjects\\politopoly\\src\\Dice\\dice "+a+".png");
        Mechanics.die2.setImage("C:\\Users\\bernelia000\\Documents\\NetBeansProjects\\politopoly\\src\\Dice\\dice "+b+".png");
        Mechanics.gameBoard.frame.setVisible(true);
        dice=a+b;
        out.println(dice);
        if(location+dice>39)
            location+=dice-40;
        else
            location+=dice;
        return a==b;
    }
    boolean equals(){
        return  balance==balance &&
                location== location &&
                name.equals(name);
    }
    public String toString(){
        return name;
    }
    public void changeBalance(int balance){
        this.balance=balance;
    }
    public int rollDice(){
        int a= ThreadLocalRandom.current().nextInt(1, 6 + 1);
        int b= ThreadLocalRandom.current().nextInt(1, 6 + 1);
        return a+b;
    }
    public void buildDialogue() {
        out.println("On what color would you like to build?"
                + "\n [brown],[lightblue],[purple],[orange],[red],[yellow],[green],[blue]");
        String type=typeDecision();
        boolean buildable=false;
        int houseCost=0;
        for (Space a: spaces)
            if (a.getType().equals(type)&& a.getBuildings()>=1){
                buildable=true;
                houseCost=a.getHouseCost();
            }
        if (buildable){
            out.println("How many houses would you like to build? [Hotel=5 houses]");
            int x=getNumber();
            int currentBuildings=0;
            for (int y=0; y<spaces.size();y++){
                if (spaces.get(y).getType().equals(type)){
                    if (spaces.get(y).getBuildings()>=2){
                        currentBuildings+=(spaces.get(y).getBuildings()-1); 
                    }
                }
            }
            if (type.equals("brown")||type.equals("blue")){
                if (x+currentBuildings>=10){
                    out.println("Sorry, you can't build that many houses here. You currently have "+currentBuildings+" houses\n"
                            + "on two properties and can build up to "+(10-currentBuildings)+" more." );
                }
                else{
                    int cost = x*houseCost;
                    if (balance > cost){
                        balance -= cost;
                        int toppokiHouses=0;
                        List<Space> targets= new ArrayList<>();
                        for (int y=0; y<spaces.size();y++){
                            if (spaces.get(y).getType().equals(type)){
                                int houseNumber=spaces.get(y).getBuildings()-1;
                                if (houseNumber>=toppokiHouses){
                                    toppokiHouses=houseNumber;
                                    targets.add(spaces.get(y));
                                    spaces.remove(y);
                                }
                                else{
                                    targets.add(0,spaces.get(y));
                                    spaces.remove(y);
                                }
                            }
                        }
                        int y=0;
                        for (; x>0; x--){
                            targets.get(y).increaseBuildings();
                            y++;
                            if (y==2){y=0;}
                        }
                        for (Space a: targets){
                            spaces.add(a);
                        }
                    }
                }
            }
            else{
                if (x+currentBuildings>=15){
                    out.println("Sorry, you can't build that many houses here. You currently have "+currentBuildings+" houses\n"
                            + "on three properties and can build up to "+(15-currentBuildings)+" more." );
                }
                else{
                    int cost = x*houseCost;
                    if (balance > cost){
                        balance -= cost;
                        int toppokiHouses=0;
                        List<Space> targets= new ArrayList<>();
                        for (int y=0; y<spaces.size();y++){
                            if (spaces.get(y).getType().equals(type)){
                                int houseNumber=spaces.get(y).getBuildings()-1;
                                if (houseNumber>=toppokiHouses){
                                    toppokiHouses=houseNumber;
                                    targets.add(spaces.get(y));
                                    spaces.remove(y);
                                }
                                else{
                                    targets.add(0,spaces.get(y));
                                    spaces.remove(y);
                                }
                            }
                        }
                        int y=0;
                        for (; x>0; x--){
                            targets.get(y).increaseBuildings();
                            y++;
                            if (y==3){y=0;}
                        }
                        for (Space a: targets){
                            spaces.add(a);
                        }
                    }
                }
            }
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

    private void superpacDialogue() {
        out.println("How much money are you donating? (Worth 10x @ Elections for 4 turns)");
        int x=donation();
        if (balance-x>0){
        superBalances.add(new financialAccount(x));
        balance-=x;
        }
        else{
            out.println("stop being poor");
        }
        
    }
    String typeDecision(){
        Scanner keyboard= new Scanner(System.in);
        String str= keyboard.nextLine();
        if (str.equals("brown")||str.equals("lightblue")||str.equals("purple")||
                str.equals("orange")||str.equals("red")||str.equals("yellow")||
                str.equals("green")||str.equals("blue"))
            return str;
        out.println("Sorry, your answer sucked. Try again");
        return typeDecision();
        
    }
       
    int donation(){
        Scanner keyboard= new Scanner(System.in);
        try {
        return keyboard.nextInt();}
        catch(Exception e){
            out.println("Sorry, your answer sucked.");
            return donation();
        }
    }
    String getName(){
        return name;
    }

    private void investDialogue() {
        out.println("How much money are you investing this turn (9% intrest compounded every turn for 4 turns.");
        int x=donation();
        if (balance-x>0){
        investmumpts.add(new financialAccount(x));
        balance-=x;
        }
        else{
            out.println("stop being poor");
        }
    }
    public String nameDecision(){
        Scanner keyboard= new Scanner(System.in);
        return keyboard.nextLine();
      
    }
    public void sellBuildings(String name){
        
    }

    public void unmortgageDialogue() {
        out.println("What property would you like to unmortgage?");
        String name=nameDecision();
        for (int x=0; x<mortgagedSpaces.size(); x++){
            if (mortgagedSpaces.get(x).getName().equals(name)){
                balance-=mortgagedSpaces.get(x).getPrice()*.5;
                spaces.add(mortgagedSpaces.get(x));
                updateBuildings(mortgagedSpaces.get(x).getType());
                mortgagedSpaces.remove(x);
                x--;  
                out.println("Unmortgage of "+name+" successful");
            }
        }
    }

    public void mortgageDialogue() {
        out.println("What property would you like to mortgage? \n Remember to sell any houses before mortgaing a property!");
        String name=nameDecision();
        for (int x=0; x<spaces.size(); x++){
            if (spaces.get(x).getName().equals(name)){
                balance+=spaces.get(x).getPrice()*.5;
                mortgagedSpaces.add(spaces.get(x));
                spaces.remove(x);
                updateBuildings(mortgagedSpaces.get(mortgagedSpaces.size()-1).getType());
                x--;  
                out.println("Mortgage of "+name+" successful");
            }
        }
    }

    private void statsDialogue() {
        out.println("Balance: "+balance);
        out.println("Properties: ");
        Collections.sort(spaces, (a, b) -> a.compareTo(b));
        for (Space a:spaces){
            out.println(a);
        }
        out.println("Mortgaged Properties: ");
        Collections.sort(mortgagedSpaces, (a, b) -> a.compareTo(b));
        for (Space a:mortgagedSpaces){
            out.println(a);
        }
        out.println("Number of get out of jail free cards: "+getOutOfJailFreeCards);
    }
    void updateBuildings(String type){
        int x=0;
        if (type.equals("BROWN")||type.equals("BLUE")||type.equals("RAILROAD")){
            if (type.equals("RAILROAD")){
                x=-1;
                for (int y=0; y<spaces.size();y++){
                    if (type.equals("RAILROAD")){
                        x++; //spaces.get(y).setBuildings(x);
                    }
                    
                }
            }
            else{
                for (int y=0; y<spaces.size(); y++){
                    int q=0;
                    if (spaces.get(y).getType().equals(type)){
                        q++;
                        if (q==2){
                            x++;
                        }
                    }
                }
            }
        }
        else{
            for (int y=0; y<spaces.size();y++){
                int q=0;
                if (spaces.get(y).getType().equals(type)){
                    q++;
                    if (q==3){
                        x++;
                    }
                }
            }          
        }
        
        if (type.equals("BROWN")||type.equals("BLUE")||type.equals("RAILROAD")){
            if (type.equals("RAILROAD")){
                for (int y=0; y<spaces.size();y++){
                    if (type.equals("RAILROAD")){
                        spaces.get(y).setBuildings(x);
                    }
                    
                }
            }
            else{
                for (int y=0; y<spaces.size(); y++){
                    int q=0;
                    if (spaces.get(y).getType().equals(type)){
                        q++;
                        if (q==2){
                            spaces.get(y).setBuildings(x);
                        }
                    }
                }
            }
        }
        else{
            for (int y=0; y<spaces.size();y++){
                int q=0;
                if (spaces.get(y).getType().equals(type)){
                    q++;
                    if (q==3){
                        spaces.get(y).setBuildings(x);
                    }
                }
            }          
        }
    }
}

