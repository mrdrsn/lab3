/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.lab3;

import java.util.List;

/**
 *
 * @author nsoko
 */
public class Lab3 {

    public static void main(String[] args) {
// Укажите путь к вашему XML-файлу
        String fileName = "C:\\Users\\nsoko\\OneDrive\\Desktop\\бибукс\\6 сем\\лаба 3 смирнов\\monsters.xml";

        // Вызов парсера
        List<Monster> monsters = XMLHandler.parseXMLfile(fileName);

        // Вывод результатов на экран
        for (Monster monster : monsters) {
            System.out.println(monster);
        }

    }
}
