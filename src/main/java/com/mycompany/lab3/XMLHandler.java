package com.mycompany.lab3;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

public class XMLHandler extends BaseHandler {

    private List<Monster> monsterList;

    @Override
    public void handle(String fileName) {
        if (fileName.endsWith(".xml")) {
            monsterList = parseXMLFile(fileName);
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
    public static List<Monster> parseXMLFile(String fileName) {
        List<Monster> tempMonsterList = new ArrayList<>();
        Monster monster = null;
        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        ArrayList<String> immunitiesList = new ArrayList<>();
        try {
            XMLEventReader reader = xmlInputFactory.createXMLEventReader(new FileInputStream(fileName));
            while (reader.hasNext()) {
                XMLEvent xmlEvent = reader.nextEvent();
                if (xmlEvent.isStartElement()) {
                    StartElement startElement = xmlEvent.asStartElement();
                    if (startElement.getName().getLocalPart().equals("monster")) {
                        monster = new Monster();
                        Attribute categoryAttr = startElement.getAttributeByName(new QName("category"));
                        if (categoryAttr != null) {
                            monster.setCategory(categoryAttr.getValue());
                        }
                    } else if (startElement.getName().getLocalPart().equals("description")) {
                        xmlEvent = reader.nextEvent();
                        monster.setDescription(xmlEvent.asCharacters().getData());
                    } else if (startElement.getName().getLocalPart().equals("danger")) {
                        xmlEvent = reader.nextEvent();
                        monster.setDanger(Integer.parseInt(xmlEvent.asCharacters().getData()));
                    } else if (startElement.getName().getLocalPart().equals("location")) {
                        xmlEvent = reader.nextEvent();
                        monster.setLocation(xmlEvent.asCharacters().getData());
                    } else if (startElement.getName().getLocalPart().equals("first_mentioned")) {
                        xmlEvent = reader.nextEvent();
                        monster.setFirstMention(xmlEvent.asCharacters().getData());
                    } else if (startElement.getName().getLocalPart().equals("height")) {
                        xmlEvent = reader.nextEvent(); // Переходим к следующему событию
                        if (xmlEvent.isCharacters()) { // Проверяем, является ли событие текстовым
                            String heightValue = xmlEvent.asCharacters().getData().trim();
                            monster.setHeight(heightValue.isEmpty() ? "не измерим" : heightValue);
                        } else {
                            monster.setHeight("не измерим"); // Значение по умолчанию, если нет текста
                        }
//                        monster.setHeight(xmlEvent.asCharacters().getData() instanceof String ? (String) xmlEvent.asCharacters().getData() : "не измерим");
                    } else if (startElement.getName().getLocalPart().equals("weight")) {
                        xmlEvent = reader.nextEvent();
                        if (xmlEvent.isCharacters()) {
                            String weightValue = xmlEvent.asCharacters().getData().trim();
                            monster.setWeight(weightValue.isEmpty() ? "не указан" : weightValue);
                        } else {
                            monster.setWeight("не указан");
                        }
//                        monster.setWeight(xmlEvent.asCharacters().getData() instanceof String ? (String) xmlEvent.asCharacters().getData() : "не осязаем");
                    } else if (startElement.getName().getLocalPart().equals("vulnerability")) {
                        xmlEvent = reader.nextEvent();
                        if (xmlEvent.isCharacters()) {
                            String vulnerabilityValue = xmlEvent.asCharacters().getData().trim();
                            monster.setVulnerability(vulnerabilityValue.isEmpty() ? "не указан" : vulnerabilityValue);
                        } else {
                            monster.setVulnerability("не указан");
                        }
//                        monster.setVulnerability(xmlEvent.asCharacters().getData() instanceof String ? (String) xmlEvent.asCharacters().getData() : " ");
                    } //immune
                    else if (startElement.getName().getLocalPart().equals("immunity")) {
                        xmlEvent = reader.nextEvent();
                        immunitiesList.add(xmlEvent.asCharacters().getData()); // Добавляем каждый иммунитет
                    } //active
                    else if (startElement.getName().getLocalPart().equals("active")) {
                        xmlEvent = reader.nextEvent();
                        monster.setActive(xmlEvent.asCharacters().getData());
                    } //recipe
                    else if (startElement.getName().getLocalPart().equals("recipe")) {
                        xmlEvent = reader.nextEvent();
                        monster.setRecipe(xmlEvent.asCharacters().getData());
                    } //time
                    else if (startElement.getName().getLocalPart().equals("time")) {
                        xmlEvent = reader.nextEvent();
                        monster.setTime(xmlEvent.asCharacters().getData());
                    } //efficiency
                    else if (startElement.getName().getLocalPart().equals("efficiency")) {
                        xmlEvent = reader.nextEvent();
                        monster.setEfficiency(xmlEvent.asCharacters().getData());
                    }
                }
                if (xmlEvent.isEndElement()) {
                    EndElement endElement = xmlEvent.asEndElement();
                    if (endElement.getName().getLocalPart().equals("monster")) {
                        monster.setImmune(immunitiesList); // Сохраняем список иммунитетов
                        immunitiesList = new ArrayList<>();
                        tempMonsterList.add(monster);
                    }
                }
            }
        } catch (FileNotFoundException | XMLStreamException ex) {
            ex.printStackTrace();
        }
        return tempMonsterList;
    }

}
