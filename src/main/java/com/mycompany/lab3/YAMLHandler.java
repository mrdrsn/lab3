package com.mycompany.lab3;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.error.YAMLException;

public class YAMLHandler extends BaseHandler implements Handler {

    private List<Monster> monsterList;

    @Override
    public void handle(String fileName) {
        if (fileName.endsWith(".yml")) {
            monsterList = parseYAMLFile(fileName);
            monsterList.forEach(System.out::println);
        } else {
            System.out.println("текущий обработчик не может прочитать файл... передаю запрос следующему...");
            super.handle(fileName);
        }
    }

    @Override
    public List<Monster> getMonsterList() {
        return this.monsterList;
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
                monster.setSource("yaml");
                monsterList.add(monster);

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (YAMLException e) {
            System.out.println("это не yml файл");
        }
        System.out.println(monsterList);
        return monsterList;
    }

    public static void exportToYaml(List<Monster> monsters, String filePath) {
        DumperOptions options = new DumperOptions();
        options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK); 
        options.setPrettyFlow(true); 

        Yaml yaml = new Yaml(options);

        List<Map<String, Object>> monsterMaps = new ArrayList<>();
        for (Monster monster : monsters) {
            Map<String, Object> monsterMap = new LinkedHashMap<>();
            monsterMap.put("category", monster.getCategory());
            monsterMap.put("description", monster.getDescription());
            monsterMap.put("danger", monster.getDanger());
            monsterMap.put("location", monster.getLocation());
            monsterMap.put("first_mentioned", monster.getFirstMention());
            monsterMap.put("height", monster.getHeight());
            monsterMap.put("weight", monster.getWeight());
            monsterMap.put("vulnerability", monster.getVulnerability());
            monsterMap.put("immune", monster.getImmune()); 
            monsterMap.put("active", monster.getActive());
            monsterMap.put("recipe", monster.getRecipe());
            monsterMap.put("time", monster.getTime());
            monsterMap.put("efficiency", monster.getEfficiency());

            monsterMaps.add(monsterMap);
        }

        Map<String, Object> data = new LinkedHashMap<>();
        data.put("monsters", monsterMaps);

        try (FileWriter writer = new FileWriter(filePath)) {
            yaml.dump(data, writer);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Ошибка при записи YAML-файла.");
        }
    }
}
