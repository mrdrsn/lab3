package com.mycompany.lab3;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.error.YAMLException;

public class YAMLHandler extends BaseHandler implements Handler {

    @Override
    public void handle(String fileName) {
        if (fileName.endsWith(".yml")) {
            List<Monster> monsterList = parseYAMLFile(fileName);
            monsterList.forEach(System.out::println);
        } else {
            System.out.println("текущий обработчик не может прочитать файл... передаю запрос следующему...");
            super.handle(fileName);
        }
    }

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
                monster.setHeight(monsterData.get("height") instanceof String ? (String) monsterData.get("height") : "не измерим");
                monster.setWeight(monsterData.get("weight") instanceof String ? (String) monsterData.get("weight") : "не осязаем");
                monster.setVulnerability(monsterData.get("vulnerability") instanceof String ? (String) monsterData.get("vulnerability") : " ");
                monster.setImmune((ArrayList<String>) monsterData.get("immune"));
                monster.setActive((String) monsterData.get("active"));
                monster.setRecipe((String) monsterData.get("recipe"));
                monster.setTime((String) monsterData.get("time"));
                monster.setEfficiency((String) monsterData.get("efficiency"));
                monsterList.add(monster);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (YAMLException e) {
            System.out.println("это не yml файл");
        }
        return monsterList;
    }
}
