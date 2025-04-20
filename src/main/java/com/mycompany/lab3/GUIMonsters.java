package com.mycompany.lab3;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

public class GUIMonsters extends JFrame {

    private String chosenFilePath;
    private final List<String> fileArray = new ArrayList<>();
    private JScrollPane monsterScrollPane;
    private final JPanel treePanel = new JPanel();
    private JPanel monsterInfoPanel = new JPanel();

    public GUIMonsters() {
        super("Лабораторная работа 3");
        BackgroundPanel backgroundPanel = new BackgroundPanel("red trees.jpg");
        setContentPane(backgroundPanel);

        JButton startButton = new JButton("Выбрать файл для импорта");
        JButton exitButton = new JButton("Выйти из программы");

        startButton.addActionListener((ActionEvent e) -> {
            startProgram();
        });
        exitButton.addActionListener((ActionEvent e) -> {
            System.exit(0);
        });

        GUIDesign.startFrameDesign(backgroundPanel, startButton, exitButton);
        setBounds(600, 300, 750, 400);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void addMainFrame() {
        JFrame mainFrame = new JFrame("Информация о монстрах");
        BackgroundPanel backgroundPanel = new BackgroundPanel("pixel cemetery.jpg");
        JPanel cardPanel = GUIDesign.createCardPanel();

        treePanel.setOpaque(false);
        cardPanel.add(treePanel, "TREE_PANEL");
        cardPanel.add(monsterInfoPanel, "MONSTER_PANEL");

        JButton addMoreButton = new JButton("Импортировать еще один файл");
        addMoreButton.addActionListener((ActionEvent e) -> {
            startProgram();
            GUIDesign.showCard(cardPanel, "TREE_PANEL");
        });

        treePanel.setBackground(Color.red);
        monsterInfoPanel.setBackground(Color.CYAN);
        monsterInfoPanel.setOpaque(false);

        monsterScrollPane = TreeModelCreator.createMonsterTree();
        TreeModelCreator.addFileRoot(monsterScrollPane, chosenFilePath);
        treePanel.add(monsterScrollPane);
        JTree tree = (JTree) monsterScrollPane.getViewport().getView();
        tree.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
                    if (selectedNode != null && selectedNode.getUserObject() instanceof Monster) {
                        Monster selectedMonster = (Monster) selectedNode.getUserObject();
                        System.out.println(selectedMonster.toString());
                        try {
                            monsterInfo(cardPanel, selectedMonster);
                        } catch (MalformedURLException ex) {
                            Logger.getLogger(GUIMonsters.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            }
        });
        GUIDesign.importButtonDesign(backgroundPanel, cardPanel, addMoreButton);

        mainFrame.setContentPane(backgroundPanel);
        mainFrame.setBounds(600, 250, 750, 500);
        mainFrame.setVisible(true);
        mainFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void startProgram() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));

        int result = fileChooser.showOpenDialog(GUIMonsters.this);
        String filePath = null;

        if (result == JFileChooser.APPROVE_OPTION) {
            filePath = fileChooser.getSelectedFile().getAbsolutePath();
            System.out.println("Выбранный файл: " + filePath);

            if (this.chosenFilePath == null) {
                this.chosenFilePath = filePath;
                fileArray.add(filePath);
                addMainFrame();
            } else {
                if (filePath.equals(this.chosenFilePath) || fileArray.contains(filePath)) {
                    System.out.println("Ошибка: файл уже был импортирован.");
                } else {
                    this.chosenFilePath = filePath;
                    fileArray.add(filePath);
                    TreeModelCreator.addFileRoot(monsterScrollPane, filePath);
                }
            }
        } else {
            System.out.println("Файл не выбран.");
        }
    }

    private void monsterInfo(JPanel cardPanel, Monster selectedMonster) throws MalformedURLException {
        monsterInfoPanel = (JPanel) cardPanel.getComponent(1);
        monsterInfoPanel.removeAll();
        JButton backToTreeButton = new JButton("Вернуться к выбору монстра");
        backToTreeButton.addActionListener((ActionEvent a) -> {
            GUIDesign.showCard(cardPanel, "TREE_PANEL");
        });
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setPreferredSize(new Dimension(600, 300));

        JPanel detailsPanel = GUIDesign.monsterInfoDesign(selectedMonster);

        String imagePath = selectedMonster.getImagePath();
        if (imagePath != null) {
            ImageIcon monsterImageIcon = new ImageIcon(new URL(imagePath));
            Image scaledImage = monsterImageIcon.getImage().getScaledInstance(200, 300, Image.SCALE_SMOOTH);
            JLabel imageLabel = new JLabel(new ImageIcon(scaledImage));
            mainPanel.add(imageLabel, BorderLayout.EAST); 
        } else {
            JLabel noImageLabel = new JLabel("Изображение отсутствует");
            mainPanel.add(noImageLabel, BorderLayout.EAST);
        }
        mainPanel.add(detailsPanel, BorderLayout.WEST);
        monsterInfoPanel.add(backToTreeButton);
        monsterInfoPanel.add(mainPanel, BorderLayout.CENTER);
        monsterInfoPanel.revalidate();
        monsterInfoPanel.repaint();
        GUIDesign.showCard(cardPanel, "MONSTER_PANEL");
    }
}
