
package com.mycompany.lab3;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

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

    public static void importButtonDesign(JPanel backgroundPanel, JPanel cardPanel, JButton button, JButton button2) {

        backgroundPanel.setLayout(new BoxLayout(backgroundPanel, BoxLayout.Y_AXIS));
        backgroundPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button2.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        setButtonDesign(button,"#062935");
        setButtonDesign(button2,"#062935");
        cardPanel.setPreferredSize(new Dimension(400, 300));

        backgroundPanel.add(cardPanel);
        backgroundPanel.add(Box.createVerticalStrut(10));
        backgroundPanel.add(button);
        backgroundPanel.add(Box.createVerticalStrut(10));
        backgroundPanel.add(button2);
        backgroundPanel.add(Box.createVerticalStrut(10));
    }
    public static void setButtonDesign(JButton button, String colorCode){
        button.setMaximumSize(new Dimension(300, 45));
        button.setBackground(Color.decode(colorCode));
        button.setForeground(Color.WHITE);
        Font customFont = CustomFontLoader.loadCustomFont(35, "ST-Brigantina-free.otf");
        button.setFont(customFont);
    }

    public static JScrollPane monsterInfoDesign(Monster selectedMonster) {
        // Создаем панель с GridBagLayout для точного размещения компонентов
        JPanel detailsPanel = new JPanel(new GridBagLayout());
        JScrollPane scrollPane = new JScrollPane(detailsPanel);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Отступы между элементами

        addEditableComponent(detailsPanel, "Категория: ", selectedMonster.getCategory(), gbc, 0, selectedMonster::setCategory);
        addEditableComponent(detailsPanel, "Описание: ", selectedMonster.getDescription(), gbc, 1, selectedMonster::setDescription);
        addEditableComponent(detailsPanel, "Опасность: ", String.valueOf(selectedMonster.getDanger()), gbc, 2, value -> {
            try {
                selectedMonster.setDanger(Integer.parseInt(value));
            } catch (NumberFormatException e) {
                System.err.println("Ошибка преобразования опасности в число: " + value);
            }
        });
        addEditableComponent(detailsPanel, "Место обитания: ", selectedMonster.getLocation(), gbc, 3, selectedMonster::setLocation);
        addEditableComponent(detailsPanel, "Первое упоминание: ", selectedMonster.getFirstMention(), gbc, 4, selectedMonster::setFirstMention);
        addEditableComponent(detailsPanel, "Рост: ", selectedMonster.getHeight(), gbc, 5, selectedMonster::setHeight);
        addEditableComponent(detailsPanel, "Вес: ", selectedMonster.getWeight(), gbc, 6, selectedMonster::setWeight);
        addEditableComponent(detailsPanel, "Уязвимость: ", selectedMonster.getVulnerability(), gbc, 7, selectedMonster::setVulnerability);
        addEditableComponent(detailsPanel, "Иммунитет: ", selectedMonster.getImmune(), gbc, 8, value -> {
            selectedMonster.setImmune(new ArrayList<>(List.of(value.split(","))));
        });
        addEditableComponent(detailsPanel, "Активность: ", selectedMonster.getActive(), gbc, 9, selectedMonster::setActive);
        addEditableComponent(detailsPanel, "Рецепт: ", selectedMonster.getRecipe(), gbc, 10, selectedMonster::setRecipe);
        addEditableComponent(detailsPanel, "Эффективность: ", selectedMonster.getEfficiency(), gbc, 11, selectedMonster::setEfficiency);

        detailsPanel.setPreferredSize(new Dimension(400, 500));
        return scrollPane;
    }

    // Метод для добавления редактируемого компонента с привязкой к полю монстра
    private static void addEditableComponent(JPanel panel, String label, String initialValue, GridBagConstraints gbc, int gridy, java.util.function.Consumer<String> setter) {
        gbc.gridx = 0; // Все компоненты размещаются в одном столбце
        gbc.gridy = gridy; // Устанавливаем строку для текущего компонента
        gbc.fill = GridBagConstraints.HORIZONTAL; // Компонент растягивается по горизонтали
        gbc.weightx = 1.0; // Компонент занимает все доступное пространство по горизонтали

        JTextArea textArea = createEditableTextArea(label + initialValue);
        textArea.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateValue();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateValue();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateValue();
            }

            private void updateValue() {
                try {
                    String text = textArea.getText();
                    // Убираем метку перед значением
                    String value = text.replace(label, "").trim();
                    setter.accept(value); // Вызываем сеттер для обновления данных
                } catch (Exception ex) {
                    System.err.println("Ошибка при обновлении значения: " + ex.getMessage());
                }
            }
        });

        panel.add(textArea, gbc);
    }

    private static JTextArea createEditableTextArea(String text) {
        JTextArea textArea = new JTextArea(text);
        textArea.setLineWrap(true); // Включаем перенос строк
        textArea.setWrapStyleWord(true); // Перенос по словам (не по символам)
        textArea.setEditable(true); // Делаем текстовое поле редактируемым
        textArea.setOpaque(false); // Включаем непрозрачность
        textArea.setFocusable(true); // Разрешаем фокусировку
        Font customFont = CustomFontLoader.loadCustomFont(16, "BukyVede-Regular.ttf");
        textArea.setFont(customFont.deriveFont(Font.BOLD)); // Устанавливаем шрифт
        return textArea;
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
