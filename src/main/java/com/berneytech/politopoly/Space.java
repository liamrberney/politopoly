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
public interface Space {
    int getLocation();
    int isOwned();
    void landedOn(Player player);
    String getType();
    String getName();
    int getRent();
    int compareTo(Space x);
    void setBuildings(int x);
    int getBuildings();
    int getHouseCost();
    void increaseBuildings();
}
