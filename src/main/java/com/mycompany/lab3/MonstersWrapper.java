/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.lab3;

import java.util.List;

public class MonstersWrapper {
    private List<Monster> monsters;
    MonstersWrapper(){
        
    }
    MonstersWrapper(List<Monster> monsters) {
        this.monsters = monsters;
    }

    public List<Monster> getMonsters() {
        return monsters;
    }

    public void setMonsters(List<Monster> monsters) {
        this.monsters = monsters;
    }

    @Override
    public String toString() {
        return "MonstersWrapper{" +
                "monsters=" + monsters +
                '}';
    }
}