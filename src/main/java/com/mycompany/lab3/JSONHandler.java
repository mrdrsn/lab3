package com.mycompany.lab3;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class JSONHandler extends BaseHandler implements Handler {
    private List<Monster> monsterList;

    @Override
    public void handle(String fileName) {
        if (fileName.endsWith(".json")) {
            monsterList = parseJSONFile(fileName);
            monsterList.forEach(System.out::println);
        } else {
            System.out.println("текущий обработчик не может прочитать файл... передаю запрос следующему...");
            super.handle(fileName);
        }
    }
    @Override
    public List<Monster> getMonsterList(){
        return this.monsterList;
    }
    public static List<Monster> parseJSONFile(String fileName) {
        ObjectMapper mapper = new ObjectMapper();
        MonstersWrapper wrapper = null;
        try {
            wrapper = mapper.readValue(new File(fileName), MonstersWrapper.class);
            for (Monster monster : wrapper.getMonsters()) {
                monster.setSource("json");
                System.out.println(monster);
            }
        } catch (IOException e) {
        }
        return wrapper.getMonsters();
    }
}
