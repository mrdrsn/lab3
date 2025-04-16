/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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

/**
 *
 * @author nsoko
 */
public class XMLHandler {

    public static List<Monster> parseXMLfile(String fileName) {
        List<Monster> monsterList = new ArrayList<>();
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
                        xmlEvent = reader.nextEvent();
                        double heightValue;
                        try {
                            heightValue = Double.parseDouble(xmlEvent.asCharacters().getData());
                        } catch (ClassCastException ex) {
                            heightValue = 0.0;
                        }
                        monster.setHeight(heightValue);
//                        monster.setHeight(Double.parseDouble(xmlEvent.asCharacters().getData()));
                    } else if (startElement.getName().getLocalPart().equals("weight")) {
                        xmlEvent = reader.nextEvent();
                        int weightValue;
                        try{
                        weightValue = Integer.parseInt(xmlEvent.asCharacters().getData());
                        } catch(ClassCastException ex){
                            weightValue = 0;
                        }
                        monster.setWeight(weightValue);
//                        monster.setWeight(Integer.parseInt(xmlEvent.asCharacters().getData()));
                    } else if (startElement.getName().getLocalPart().equals("vulnerability")) {
                        xmlEvent = reader.nextEvent();
                        String vulnerabilityValue;
                        try{
                           vulnerabilityValue = xmlEvent.asCharacters().getData();
                        } catch(ClassCastException ex){
                           vulnerabilityValue = " ";
                        }
                        monster.setVulnerability(vulnerabilityValue);
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
                        monster.setTime(Integer.parseInt(xmlEvent.asCharacters().getData()));
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
                        monsterList.add(monster);
                    }
                }
            }
        } catch (FileNotFoundException | XMLStreamException ex) {
            ex.printStackTrace();
        }
        return monsterList;
    }

}
