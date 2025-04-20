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

    public String getCategory() {
        return this.category;
    }

    public String getDescription() {
        return this.description;
    }

    public int getDanger() {
        return this.danger;
    }

    public String getLocation() {
        return this.location;
    }

    public String getFirstMention() {
        return this.firstMentioned;
    }

    public String getHeight() {
        return this.height;
    }

    public String getWeight() {
        return this.weight;
    }

    public String getVulnerability() {
        return this.vulnerability;
    }

    public String getImmune() {
        if (immune == null || immune.isEmpty()) {
            return "Нет иммунитетов"; // Возвращаем сообщение, если список пуст
        }
        return String.join(", ", immune); // Объединяем элементы через запятую
    }

    public String getActive() {
        return this.active;
    }

    public String getRecipe() {
        return this.recipe;
    }

    public String getTime() {
        return this.time;
    }

    public String getEfficiency() {
        return this.efficiency;
    }

    public String getImagePath() {
        String imageName = category.toLowerCase() + ".jpg"; // Приводим к нижнему регистру для единообразия

        java.net.URL resourceUrl = getClass().getClassLoader().getResource(imageName);

        if (resourceUrl == null) {
            System.err.println("Изображение не найдено: " + imageName);
            return null; // Возвращаем null, если файл не найден
        }

        return resourceUrl.toString();
    }

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

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public void setVulnerability(String vulnerability) {
        this.vulnerability = vulnerability;
    }

    public void setImmune(ArrayList<String> immune) {
        this.immune = immune;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public void setRecipe(String recipe) {
        this.recipe = recipe;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setEfficiency(String efficiency) {
        this.efficiency = efficiency;
    }

    @Override
    public String toString() {
        return this.category;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
