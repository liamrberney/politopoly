/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.berneytech.politopoly;

/**
 *
 * @author bernelia000
 */
public class financialAccount {
    int turns; 
    int amount;
    public financialAccount(int x){
        turns=4;
        amount=x;
    }
    void updateTurns(){
        turns--;
    }
    int getTurns(){
        return turns;
    }
    int getAmount(){
        return amount;
    }
    void setBalance(int amount){
        this.amount=amount;
    }
    void marketCrash(){
        amount/=2;
    }
}
