/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.lab3;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nsoko
 */
public class Controller {
    static List<Monster> monsterCollection = new ArrayList<>();
    static XMLHandler xml = new XMLHandler();
    static YAMLHandler yml = new YAMLHandler();
    static JSONHandler json = new JSONHandler();
    
    public static void setHandlerChain(String file) {
        xml.setNext(yml);
        yml.setNext(json);
        System.out.println("попытка обработать файл " + file);
        xml.handle(file);
    }
    public static List<Monster> getXMLCollection(){
        return xml.getMonsterList();
    }
    public static List<Monster> getYAMLCollection(){
        return yml.getMonsterList();
    }
    public static List<Monster> getJSONCollection(){
        return json.getMonsterList();
    }
    public static void setMonsters(){
        if(getXMLCollection() != null){
            
        }
    }
    public static List<Monster> getMonsters(){
        return monsterCollection;
    }
    public static List<Monster> getMonsters(String chosenFilePath){
        if(chosenFilePath.endsWith(".xml")){
            System.out.println("внутри контроллера " +xml.getMonsterList());
            return getXMLCollection();
        } else if(chosenFilePath.endsWith(".yml")){
            System.out.println("внутри контроллера " + yml.getMonsterList());
            return getYAMLCollection();
        } else if(chosenFilePath.endsWith(".json")){
            System.out.println("внутри контроллера " +json.getMonsterList());
            return getJSONCollection();
        }
        return null;
    }
    
}
