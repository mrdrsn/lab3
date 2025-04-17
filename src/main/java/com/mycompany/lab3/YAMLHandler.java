/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.lab3;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.yaml.snakeyaml.Yaml;
//import org.apache.commons.exec.ExecuteException;
import org.yaml.snakeyaml.error.YAMLException;

/**
 *
 * @author nsoko
 */
public class YAMLHandler {

    public static List<Monster> parseYAMLFile(String fileName) {
        List<Monster> monsterList = new ArrayList<>();
        try {
            Yaml yaml = new Yaml();

            FileInputStream inputStream = new FileInputStream(fileName);
            Map<String, Object> yamlData = yaml.load(inputStream);

            List<Map<String, Object>> monstersData = (List<Map<String, Object>>) yamlData.get("monsters");

            for (Map<String, Object> monsterData : monstersData) {
                Monster monster = new Monster();
                monster.setCategory((String) monsterData.get("category"));
                monster.setDescription((String) monsterData.get("description"));
                monster.setDanger(((Number) monsterData.get("danger")).intValue());
                monster.setLocation((String) monsterData.get("location"));
                monster.setFirstMention((String) monsterData.get("first_mentioned"));
                monster.setHeight(monsterData.get("height") instanceof Double ? (Double) monsterData.get("height") : 0.0);
                monster.setWeight(monsterData.get("weight") instanceof Integer ? (Integer) monsterData.get("weight") : 0);
                monster.setVulnerability(monsterData.get("vulnerability") instanceof String ? (String) monsterData.get("vulnerability") : " ");
                monster.setImmune((ArrayList<String>) monsterData.get("immune"));
                monster.setActive((String) monsterData.get("active"));
                monster.setRecipe((String) monsterData.get("recipe"));
                monster.setTime((int) monsterData.get("time"));
                monster.setEfficiency((String) monsterData.get("efficiency"));
                monsterList.add(monster);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (YAMLException e){
            System.out.println("это не yml файл");
        }
        return monsterList;
    }
}
