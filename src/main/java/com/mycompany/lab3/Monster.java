/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.lab3;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;

/**
 *
 * @author nsoko
 */
public class Monster {
    private String source;
    private String category;
    private String description;
    private int danger;
    private String location;
    @JsonProperty("first_mentioned")
    private String firstMentioned;
    private String height;
    private String weight;
    private String vulnerability;
    private ArrayList<String> immune;
    private String active;
    private String recipe;
    private String time;
    private String efficiency;
    public void setCategory(String category) {
        this.category = category;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    void setDanger(int danger) {
        this.danger = danger;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setFirstMention(String firstMention) {
        this.firstMentioned = firstMention;
    }

    public void setHeight(String height) {
        this.height = height;
    }
    public void setWeight(String weight){
        this.weight = weight;
    }

    public void setVulnerability(String vulnerability) {
        this.vulnerability = vulnerability;
    }
    public void setImmune(ArrayList<String> immune){
        this.immune = immune;
    }

    public void setActive(String active) {
        this.active = active;
    }
    public void setRecipe(String recipe){
        this.recipe = recipe;
    }
    public void setTime(String time){
        this.time = time;
    }
    public void setEfficiency(String efficiency){
        this.efficiency = efficiency;
    }       
    @Override
    public String toString() {
        return "Monster{" +
                "category='" + category + '\'' +
                ", description='" + description + '\'' +
                ", danger=" + danger +
                ", location='" + location + '\'' +
                ", firstMention='" + firstMentioned + '\'' +
                ", height=" + height +
                ", weight=" + weight +
                ", vulnerability='" + vulnerability + '\'' +
                ", immune=" + immune +
                ", active='" + active + '\'' +
                ", recipe='" + recipe + '\'' +
                ", time='" + time + '\'' +
                ", efficiency='" + efficiency + '\'' +
                '}';
    }
    
}
