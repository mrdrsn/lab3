package com.mycompany.lab3;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.util.List;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;

public class TreeModelCreator {

    public static JScrollPane createMonsterTree() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Список загруженных монстров");
        JTree myMonsterTree = new JTree(root);
        myMonsterTree.setPreferredSize(new Dimension(300, 600));
        myMonsterTree.setBackground(new Color(0, 0, 0, 60)); // Полупрозрачный фон для дерева
        myMonsterTree.setOpaque(false); // Отключаем непрозрачность

        // Устанавливаем собственный рендерер для ячеек дерева
        myMonsterTree.setCellRenderer(new CustomTreeCellRenderer());

        // Создаем JScrollPane
        JScrollPane scrollPane = new JScrollPane(myMonsterTree);
//        scrollPane.setPreferredSize(new Dimension(300, 600));

        // Устанавливаем полупрозрачный фон для JScrollPane
        scrollPane.setBackground(Color.BLACK); // Полупрозрачный фон
//        scrollPane.setOpaque(false); // Отключаем непрозрачность для JScrollPane

        // Устанавливаем полупрозрачный фон для Viewport (области просмотра)
        scrollPane.getViewport().setBackground(new Color(0, 0, 0, 60)); // Полупрозрачный фон
        scrollPane.getViewport().setOpaque(false); // Отключаем непрозрачность для Viewport

        return scrollPane;
    }

    public static void addFileRoot(JScrollPane monsterScroll, String fileName) {
        Controller.setHandlerChain(fileName);
        JTree monsterTree = (JTree) monsterScroll.getViewport().getView();
        if (isNodeExists(monsterTree, fileName)) {
            return;
        }

        // Определяем тип файла по расширению
        String fileNodeType = null;
        List<Monster> monsters = null;

        if (fileName.endsWith(".xml")) {
            fileNodeType = "XML File";
            monsters = Controller.getXMLCollection(); // Получаем монстров из XML
        } else if (fileName.endsWith(".yml") || fileName.endsWith(".yaml")) {
            fileNodeType = "YAML File";
            monsters = Controller.getYAMLCollection(); // Получаем монстров из YAML
        } else if (fileName.endsWith(".json")) {
            fileNodeType = "JSON File";
            monsters = Controller.getJSONCollection(); // Получаем монстров из JSON
        }

        if (fileNodeType == null || monsters == null) {
            System.out.println("Не удалось определить тип файла или загрузить монстров.");
            return;
        }

        // Создаем корневой узел для файла
        DefaultMutableTreeNode fileNode = new DefaultMutableTreeNode(fileNodeType);

        // Добавляем дочерние узлы для каждого монстра
        for (Monster monster : monsters) {
            DefaultMutableTreeNode monsterNode = new DefaultMutableTreeNode(monster);
            fileNode.add(monsterNode);
        }

        // Обновляем модель дерева
        DefaultTreeModel model = (DefaultTreeModel) monsterTree.getModel();
        DefaultMutableTreeNode mainRoot = (DefaultMutableTreeNode) model.getRoot();
        model.insertNodeInto(fileNode, mainRoot, mainRoot.getChildCount());
        model.reload(mainRoot);
    }

    private static boolean isNodeExists(JTree tree, String nodeName) {
        TreeModel model = tree.getModel();
        DefaultMutableTreeNode root = (DefaultMutableTreeNode) model.getRoot();
        return searchNode(root, nodeName);
    }

    private static boolean searchNode(DefaultMutableTreeNode node, String nodeName) {
        if (node.getUserObject().toString().equals(nodeName)) {
            return true;
        }
        for (int i = 0; i < node.getChildCount(); i++) {
            DefaultMutableTreeNode child = (DefaultMutableTreeNode) node.getChildAt(i);
            if (searchNode(child, nodeName)) {
                return true;
            }
        }
        return false;
    }

    private static class CustomTreeCellRenderer extends DefaultTreeCellRenderer {

//        private final Font customFont = new Font("Arial", Font.BOLD, 14); // Кастомный шрифт
        @Override
        public Component getTreeCellRendererComponent(JTree tree, Object value, boolean isSelected, boolean expanded,
                boolean leaf, int row, boolean hasFocus) {
            super.getTreeCellRendererComponent(tree, value, isSelected, expanded, leaf, row, hasFocus);

            // Устанавливаем кастомный шрифт
            setFont(CustomFontLoader.loadCustomFont(20, "BukyVede-Regular.ttf"));

            // Настройка цветов
            if (isSelected) {
                setBackgroundSelectionColor(new Color(0, 0, 0, 0)); // Прозрачный фон при выделении
                setTextSelectionColor(Color.decode("#ab4da6")); // Цвет текста при выделении
            } else {
                setBackgroundNonSelectionColor(new Color(0, 0, 0, 0)); // Прозрачный фон для неактивных узлов
                setForeground(Color.WHITE); // Цвет текста для неактивных узлов
            }

            setOpaque(false); // Отключаем непрозрачность для компонента

            return this;
        }
    }
}
