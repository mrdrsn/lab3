/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.lab3;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GUIDesign {

    public static void startFrameDesign(JPanel panel, JButton button1, JButton button2) {
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(115, 0, 0, 0));

        button1.setAlignmentX(Component.CENTER_ALIGNMENT);
        button2.setAlignmentX(Component.CENTER_ALIGNMENT);

        Dimension buttonSize = new Dimension(310, 50);
        button1.setMaximumSize(buttonSize);
        button2.setMaximumSize(buttonSize);

        button1.setBackground(Color.decode("#062935"));
        button1.setForeground(Color.WHITE);

        button2.setBackground(Color.decode("#062935"));
        button2.setForeground(Color.WHITE);

        Font customFont = CustomFontLoader.loadCustomFont(40, "ST-Brigantina-free.otf");
        button1.setFont(customFont);
        button2.setFont(customFont);

        panel.add(button1);
        panel.add(Box.createVerticalStrut(20));
        panel.add(button2);
    }
    
    public static void importButtonDesign(JPanel backgroundPanel,JPanel cardPanel, JButton button){
        
        backgroundPanel.setLayout(new BoxLayout(backgroundPanel, BoxLayout.Y_AXIS));
        backgroundPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        button.setMaximumSize(new Dimension(300,60));
        button.setBackground(Color.decode("#062935"));
        button.setForeground(Color.WHITE);
        Font customFont = CustomFontLoader.loadCustomFont(40, "ST-Brigantina-free.otf");
        button.setFont(customFont);
        
        cardPanel.setPreferredSize(new Dimension(400, 300));
        
        backgroundPanel.add(cardPanel);
        backgroundPanel.add(Box.createVerticalStrut(10));
        backgroundPanel.add(button);
        backgroundPanel.add(Box.createVerticalStrut(10));
    }
    public static JPanel monsterInfoDesign(Monster selectedMonster){
        JPanel detailsPanel = new JPanel(new GridLayout(12, 1));

        detailsPanel.add(new JLabel("Категория: " + selectedMonster.getCategory()));
        detailsPanel.add(new JLabel("Описание: " + selectedMonster.getDescription()));

        detailsPanel.add(new JLabel("Опасность: " + String.valueOf(selectedMonster.getDanger())));
        detailsPanel.add(new JLabel("Место обитания: " + selectedMonster.getLocation()));
        detailsPanel.add(new JLabel("Первое упоминание: " + selectedMonster.getFirstMention()));
        detailsPanel.add(new JLabel("Рост: " + selectedMonster.getHeight()));
        detailsPanel.add(new JLabel("Вес: " + selectedMonster.getWeight()));
        detailsPanel.add(new JLabel("Уязвимость: " + selectedMonster.getVulnerability()));
        detailsPanel.add(new JLabel("Иммунитет: " + selectedMonster.getImmune()));
        detailsPanel.add(new JLabel("Активность: " + selectedMonster.getActive()));
        detailsPanel.add(new JLabel("Рецепт: " + selectedMonster.getRecipe()));
        detailsPanel.add(new JLabel("Эффективность: " + selectedMonster.getEfficiency()));
        return detailsPanel;
    }
    public static JPanel createCardPanel() {
        JPanel cardPanel = new JPanel(new CardLayout());
        cardPanel.setOpaque(false);
        return cardPanel;
    }

    public static void addCard(JPanel cardPanel, JPanel panel, String name) {
        cardPanel.add(panel, name);
    }

    public static void showCard(JPanel cardPanel, String name) {
        CardLayout cardLayout = (CardLayout) cardPanel.getLayout();
        cardLayout.show(cardPanel, name);
    }
}
