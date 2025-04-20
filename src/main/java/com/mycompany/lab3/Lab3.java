/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.lab3;

import java.io.IOException;
import java.util.List;
import javax.swing.SwingUtilities;

/**
 *
 * @author nsoko
 */
public class Lab3 {

    public static void main(String[] args) throws IOException {
//        String[] fileArray = new String[3];
//        fileArray[0] = "C:\\Users\\nsoko\\OneDrive\\Desktop\\бибукс\\6 сем\\лаба 3 смирнов\\monsters.xml";
//        fileArray[1] = "C:\\Users\\nsoko\\OneDrive\\Desktop\\бибукс\\6 сем\\лаба 3 смирнов\\monsters.yml";
//        fileArray[2] = "C:\\Users\\nsoko\\OneDrive\\Desktop\\бибукс\\6 сем\\лаба 3 смирнов\\monsters.json";
////        String fileName = "C:\\Users\\Nastya\\Desktop\\monsters.xml";
////        String fileName = "C:\\Users\\Nastya\\Desktop\\monsters.yml";
//        XMLHandler xml = new XMLHandler();
//        YAMLHandler yml = new YAMLHandler();
//        JSONHandler json = new JSONHandler();
//        xml.setNext(yml);
//        yml.setNext(json);
//        for(String file: fileArray){
//            System.out.println("попытка обработать файл " +file);
//            xml.handle(file);
        SwingUtilities.invokeLater(() -> new GUIMonsters());
    }
}
