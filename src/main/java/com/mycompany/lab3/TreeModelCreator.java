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
        myMonsterTree.setBackground(new Color(0, 0, 0, 60)); 
        myMonsterTree.setOpaque(false); 

        myMonsterTree.setCellRenderer(new CustomTreeCellRenderer());

        JScrollPane scrollPane = new JScrollPane(myMonsterTree);

        scrollPane.setBackground(Color.BLACK); 

        scrollPane.getViewport().setBackground(new Color(0, 0, 0, 60)); 
        scrollPane.getViewport().setOpaque(false); 

        return scrollPane;
    }

    public static void addFileRoot(JScrollPane monsterScroll, String fileName) {
        Controller.setHandlerChain(fileName);
        JTree monsterTree = (JTree) monsterScroll.getViewport().getView();
        if (isNodeExists(monsterTree, fileName)) {
            return;
        }

        String fileNodeType = null;
        List<Monster> monsters = null;

        if (fileName.endsWith(".xml")) {
            fileNodeType = "XML File";
            monsters = Controller.getXMLCollection(); 
        } else if (fileName.endsWith(".yml") || fileName.endsWith(".yaml")) {
            fileNodeType = "YAML File";
            monsters = Controller.getYAMLCollection(); 
        } else if (fileName.endsWith(".json")) {
            fileNodeType = "JSON File";
            monsters = Controller.getJSONCollection(); 
        }

        if (fileNodeType == null || monsters == null) {
            System.out.println("Не удалось определить тип файла или загрузить монстров.");
            return;
        }

        DefaultMutableTreeNode fileNode = new DefaultMutableTreeNode(fileNodeType);

        for (Monster monster : monsters) {
            DefaultMutableTreeNode monsterNode = new DefaultMutableTreeNode(monster);
            fileNode.add(monsterNode);
        }

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

        @Override
        public Component getTreeCellRendererComponent(JTree tree, Object value, boolean isSelected, boolean expanded,
                boolean leaf, int row, boolean hasFocus) {
            super.getTreeCellRendererComponent(tree, value, isSelected, expanded, leaf, row, hasFocus);

            setFont(CustomFontLoader.loadCustomFont(20, "BukyVede-Regular.ttf"));

            if (isSelected) {
                setBackgroundSelectionColor(new Color(0, 0, 0, 0)); 
                setTextSelectionColor(Color.decode("#ab4da6")); 
            } else {
                setBackgroundNonSelectionColor(new Color(0, 0, 0, 0)); 
                setForeground(Color.WHITE);
            }

            setOpaque(false); 

            return this;
        }
    }
}
