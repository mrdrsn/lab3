/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.lab3;

import java.util.List;

/**
 *
 * @author nsoko
 */
public class Controller {

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
    
}
