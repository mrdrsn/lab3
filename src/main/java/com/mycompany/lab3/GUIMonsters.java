package com.mycompany.lab3;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.io.File;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GUIMonsters extends JFrame {
    private String chosenFilePath;
    public GUIMonsters() {
        JPanel startPanel = new JPanel();
        JButton startButton = new JButton("Выбрать файл для импорта");
        JButton exitButton = new JButton("Выйти из программы");

        startButton.addActionListener((ActionEvent e) -> {
            chosenFilePath = startProgram();
            addMainFrame();
        });
        exitButton.addActionListener((ActionEvent e) -> {
            System.exit(0);
        });
        startPanel.setLayout(new BoxLayout(startPanel, BoxLayout.Y_AXIS));
        startPanel.add(startButton, BorderLayout.NORTH);
        startPanel.add(exitButton, BorderLayout.SOUTH);
        add(startPanel);
        setBounds(400, 150, 400, 300);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    private void addMainFrame(){
        JFrame mainFrame = new JFrame("Информация о монстрах");
        mainFrame.setBounds(400, 150, 800, 600);
        mainFrame.setVisible(true);
        mainFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    private String startProgram() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir"))); //вообще не понимаю че мне тут нужно написать
        int result = fileChooser.showOpenDialog(GUIMonsters.this);
        String filePath = null;
        if (result == JFileChooser.APPROVE_OPTION) {
            filePath = fileChooser.getSelectedFile().getAbsolutePath();
            System.out.println("Выбранный файл: " + filePath);
            addMainFrame();
        } else {
            System.out.println("Файл не выбран.");
        }
        return filePath;
    }
}
