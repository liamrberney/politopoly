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
public class superBalance {
    int turns; 
    int amount;
    public superBalance(int x){
        turns=4;
        amount=x;
    }
    void updateTurns(){
        if (turns<=0){
            amount=0;
        }
        turns--;
    }
}
