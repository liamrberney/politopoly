/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.berneytech.politopoly;

import static java.lang.System.out;
import java.util.List;

/**
 *
 * @author bernelia000
 */
public class Board {
    public static Space[] spaces;
    public static Card[] communityChest;
    public static Card[] chance;
    static void initializeBoard(){
        spaces=initializeSpaces();
        communityChest=initializeCommunityChest();
        chance=initializeChance();
        
    }

    public static Space[] initializeSpaces() {
        //int location, String name, String type, int price, int[] rent
        //int location, String name, String type
        Space[] spaces=new Space[40];
        spaces[0]= new otherSpace(0,"GO","DONOTHING");
        spaces[1]= new Property(1,"MEDITERANEAN AVENUE","BROWN",60,new int[]{2,4,10,20,90,160,250},50);
        spaces[2]= new otherSpace(2,"COMMUNITY CHEST","COMMUNITYCHEST");
        spaces[3]= new Property(3,"BALTIC AVENUE","BROWN",60,new int[]{4,8,20,60,180,320,450},50);
        spaces[4]= new otherSpace(4,"INCOME TAX","TAX");
        spaces[5]= new Property(5,"READING RAILROAD","RAILROAD",200,new int[]{25,50,100,200},0);
        spaces[6]= new Property(6,"ORIENTAL AVENUE","LIGHTBLUE",100,new int[]{6,12,30,90,270,400,550},50);
        spaces[7]= new otherSpace(7,"CHANCE","CHANCE");
        spaces[8]= new Property(8,"VERMONT AVENUE","LIGHTBLUE",100,new int[]{6,12,30,90,270,400,550},50);
        spaces[9]= new Property(9,"CONNECTICUT AVENUE","LIGHTBLUE",120,new int[]{8,16,40,100,300,450,600},50);
        spaces[10]= new otherSpace(10,"JAIL","DONOTHING");
        spaces[11]= new Property(11,"ST. CHARLES PLACE","PURPLE",140,new int[]{10,20,50,150,450,625,750},100);
        spaces[12]= new otherSpace(12,"ELECTIONS","ELECTIONS");
        spaces[13]= new Property(13,"STATES AVENUE","PURPLE",140,new int[]{10,20,50,150,450,625,750},100);
        spaces[14]= new Property(14,"VIRGINIA AVENUE","PURPLE",160,new int[]{11,22,60,180,500,700,900},100);
        spaces[15]= new Property(15,"PENNSYLVANIA RAILROAD","RAILROAD",200,new int[]{25,50,100,200},0);
        spaces[16]= new Property(16,"ST. JAMES PLACE","ORANGE",180,new int[]{14,28,70,200,550,750,950},100);
        spaces[17]= new otherSpace(17,"COMMUNITY CHEST","COMMUNITYCHEST");
        spaces[18]= new Property(18,"TENNESSEE AVENUE","ORANGE",180,new int[]{14,28,70,200,550,750,950},100);
        spaces[19]= new Property(19,"NEW YORK AVENUE","ORANGE",200,new int[]{16,32,80,220,600,800,1000},100);
        spaces[20]= new otherSpace(20,"FREE PARKING","DONOTHING");
        spaces[21]= new Property(21,"KENTUCKY AVENUE","RED",220,new int[]{18,36,90,250,700,875,1050},150);
        spaces[22]= new otherSpace(22,"CHANCE","CHANCE");
        spaces[23]= new Property(23,"INDIANA AVENUE","RED",220,new int[]{18,36,90,250,700,875,1050},150);
        spaces[24]= new Property(24,"ILLINOIS AVENUE","RED",240,new int[]{20,40,100,300,750,925,1100},150);
        spaces[25]= new Property(25,"B&O RAILROAD","RAILROAD",200,new int[]{25,50,100,200},0);
        spaces[26]= new Property(26,"ATLANTIC AVENUE","YELLOW",260,new int[]{22,44,110,330,800,975,1150},150);
        spaces[27]= new Property(27,"VENTNOR AVENUE","YELLOW",260,new int[]{22,44,110,330,800,975,1150},150);
        spaces[28]= new otherSpace(28,"MIDTERMS","ELECTIONS");
        spaces[29]= new Property(29,"MARVIN GARDENS","YELLOW",280,new int[]{24,48,120,360,850,1025,1200},150);
        spaces[30]= new otherSpace(30,"GO TO JAIL","GOTOJAIL");
        spaces[31]= new Property(31,"PACIFIC AVENUE","GREEN",300,new int[]{26,52,130,390,900,1100,1275},200);
        spaces[32]= new Property(32,"NORTH CAROLINA AVENUE","GREEN",300,new int[]{26,52,130,390,900,1100,1275},200);
        spaces[33]= new otherSpace(33,"COMMUNITY CHEST","COMMUNITYCHEST");
        spaces[34]= new Property(34,"PENNSYLVANIA AVENUE","GREEN",320,new int[]{28,56,150,450,1000,1200,1400},200);
        spaces[35]= new Property(35,"SHORT LINE","RAILROAD",200,new int[]{25,50,100,200},0);
        spaces[36]= new otherSpace(36,"CHANCE","CHANCE");
        spaces[37]= new Property(37,"PARK PLACE","BLUE",350,new int[]{35,70,175,500,1100,1300,1500},200);
        spaces[38]= new otherSpace(38,"LUXURY TAX","TAX");
        spaces[39]= new Property(39,"BOARDWALK","BLUE",400,new int[]{50,100,200,600,1400,1700,2000},200);
        return spaces;
    }
    
    public static Card[] initializeChance() {
        //String name, String type, String text, int sendTo, int rent
        Card[] chance=new Card[16];
        chance[0]=new Card("ADVANCETOGO","MOVEMENT","Advance to GO (Collect $200)",0,0);
        chance[1]=new Card("ADVANCETOILLINOIS","MOVEMENT","Advance to Illinois Ave. If you pass Go, collect $200.",24,0);
        chance[2]=new Card("ADVANCETOST.CHARLESPLACE","MOVEMENT","Advance to St. Charles Place. If you pass Go, collect $200.",11,0);
        chance[3]=new Card("ADVANCETONEARESTRAILROAD","RAILROAD","Advance token to the nearest Railroad and pay owner twice the rental to which he/she {he} is otherwise entitled.",-1,0);
        chance[4]=new Card("DIVIDEND","PAYMENT","Bank pays you dividend of $50.",-1,50);
        chance[5]=new Card("GETOUTOFJAILFREE","GETOUTOFJAILFREE","Get out of Jail Free. This card may be kept until needed, or traded/sold.",-1,0);
        chance[6]=new Card("GOBACK","GOBACK","Go back 3 spaces.",-3,0);
        chance[7]=new Card("JAIL","GOTOJAIL","Go to Jail. Go directly to Jail. Do not pass go. Do not collect $200",10,0);
        chance[8]=new Card("DOREPAIRS","REPAIRS","Make general repairs on all your property: For each house pay $25, For each hotel {pay} $100.",-1,0);
        chance[9]=new Card("POORTAX","PAYMENT","Bank pays you dividend of $50.",-1,50);
        chance[10]=new Card("RIDEREADING","MOVEMENT","Take a ride on the Reading. Advance token and} If you pass Go, collect $200.",5,0);
        chance[11]=new Card("ADVANCETOBOARDWALK","MOVEMENT","Take a walk on the Boardwalk. Advance to Boardwalk",39,0);
        chance[12]=new Card("CHAIRMANOFBOARD","REDISTRIBUTION","You have been elected Chairman of the Board. Pay each player $50.",-1,-50);
        chance[13]=new Card("BUILDINGANDLOANMATURES","PAYMENT","Your building and loan matures. Collect $150",-1,150);
        chance[14]=new Card("CROSSWORD","PAYMENT","You won a crossword competition. Collect $100",-1,100);
        return chance;
    }//Go Back Three {3} Spaces.
    
    public static Card[] initializeCommunityChest() {
        Card[] communityChest= new Card[17];
        communityChest[0]= new Card("ADVANCETOGO","MOVEMENT","Advance to GO (Collect $200)",0,0);
        communityChest[1]= new Card("BANK ERROR","PAYMENT","Bank error in your favor (Collect $200)",-1,200);  
        communityChest[2]= new Card("DOCTORFEE","PAYMENT","Doctor's fees. Pay $50",-1,-50); 
        communityChest[3]= new Card("STONK","PAYMENT","From sale of stock you get $50",-1,50); 
        communityChest[4]= new Card("GETOUTOFJAILFREE","GETOUTOFJAILFREE","Get out of Jail Free. This card may be kept until needed, or traded/sold.",-1,0);
        communityChest[5]= new Card("JAIL","GOTOJAIL","Go to Jail. Go directly to Jail. Do not pass go. Do not collect $200",10,0);
        communityChest[6]= new Card("OPERANIGHT","REDISTRIBUTION","Grand Opera Night. Collect $50 from every player for opening night seats.",-1,50);
        communityChest[7]= new Card("XMAS","PAYMENT","Xmas fund matures. Collect $100",-1,100);  
        communityChest[8]= new Card("INCOMEREFUND","PAYMENT","Income tax refund. Collect $20",-1,20);  
        communityChest[9]= new Card("BIRTHDAY","REDISTRIBUTION","It's your birthday. Collect $10 from every player",-1,10);
        communityChest[10]= new Card("LIFEINSURANCE","PAYMENT","Life insurance matures. Collect $100",-1,100);  
        communityChest[11]= new Card("HOSPITALFEE","PAYMENT","Hospital Fees. Pay $50",-1,50); 
        communityChest[12]= new Card("SCHOOLFEE","PAYMENT","School Fees. Pay $50",-1,50); 
        communityChest[13]= new Card("CONSULTANCY","PAYMENT","Recieve a $25 consultancy fee.",-1,25); 
        communityChest[14]= new Card("STREETREPAIRS","STREETREPAIRS","Make general repairs on all your property: For each house pay $40, For each hotel {pay} $115.",-1,0);
        communityChest[15]= new Card("BEAUTYCONTEST","PAYMENT","You have won second place in a beauty contest. Collect $10",-1,10); 
        communityChest[16]= new Card("INHERITANCE","PAYMENT","You inherit $100",-1,100); 
        return communityChest;
    }
    public static Space getSpace(int x){
        return spaces[x];
    }
    static Card getCommunityChest(int x){
        return communityChest[x];
    }
    public static Card getChance(int x){
        return chance[x];
    }
    public static void setSpace(Space space){
        spaces[space.getLocation()]=space;
    }

    
    
    
}
