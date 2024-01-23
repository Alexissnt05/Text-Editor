package org.alexis;

import org.alexis.menus.MenuBar;

import javax.swing.*;
import java.awt.*;

public class Editor extends JFrame {
    private MenuBar menuBar;
    private JScrollPane scrollPane;
    public static TextArea textArea;

    public Editor() {
       createWindow();
       addTextArea();
       addScrollBars();
       addMenuBar();
    }

    private void createWindow() {
        this.setTitle("Text Editor");
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE); //program stops running when closing application
        this.setIconImage(new ImageIcon("src/main/resources/icon.png").getImage());
        this.setSize(500, 500);
        this.setLocationRelativeTo(null);// positions the window at the center of the screen
        this.setVisible(true);
    }

    private void addTextArea() {
        textArea = new TextArea();
        this.getContentPane().add(textArea); //getContentPane() returns content pane layer
    }

    private void addScrollBars() {
        /* Creates a scrollable view of the textArea component, and always displaying the vertical and horizontal
        bars */
        scrollPane = new JScrollPane(textArea, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setSize(new Dimension(50,50));
        this.getContentPane().add(scrollPane);
    }

    private void addMenuBar() {
        menuBar = new MenuBar();
        this.getContentPane().add(BorderLayout.NORTH,menuBar); //adds menuBar at the top of container (north region)
    }

}
