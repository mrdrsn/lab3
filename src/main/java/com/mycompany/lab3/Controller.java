/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.lab3;

/**
 *
 * @author nsoko
 */
public class Controller {

    public static void setHandlerChain(String[] fileArray) {
        XMLHandler xml = new XMLHandler();
        YAMLHandler yml = new YAMLHandler();
        JSONHandler json = new JSONHandler();
        xml.setNext(yml);
        yml.setNext(json);
        for (String file : fileArray) {
            System.out.println("попытка обработать файл " + file);
            xml.handle(file);
        }
    }
}