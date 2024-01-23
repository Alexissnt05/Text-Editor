package org.alexis.menus;

import org.alexis.Editor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class FormatMenu extends JFrame {
    private final JLabel font = new JLabel("Font");
    private final JLabel size = new JLabel("Size");
    private final JComboBox<String> fontsComboBox = new JComboBox<>();
    private final JComboBox<String> sizeComboBox = new JComboBox<>();
    public FormatMenu() {
        createWindow();
        addComboBoxes();
        changeFont();
        changeFontSize();
    }

    public void createWindow() {
        this.setName("Format");
        this.getContentPane().setBackground(Color.white);
        this.setIconImage(new ImageIcon("src/main/resources/font.png").getImage());
        this.setLocation(1200,270);
        this.setSize(360,100);
        this.setLayout(new FlowLayout());
        this.setResizable(false);
    }

    public void addComboBoxes() {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment(); //returns graphics environment instance
        for (String font : ge.getAvailableFontFamilyNames()) {
            fontsComboBox.addItem(font); //adds each font family name to the fontsComboBox
        }
        for (int i = 1; i <= 100; i++) {
            sizeComboBox.addItem(String.valueOf(i));
        }
        this.add(font);
        this.add(fontsComboBox);
        this.add(size);
        this.add(sizeComboBox);
    }

    public void changeFont() {
        fontsComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    Editor.textArea.setFont(new Font(e.getItem().toString(),Font.PLAIN,Editor.textArea.getFont().getSize()));
                }
            }
        });
    }

    public void changeFontSize() {
        sizeComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    Editor.textArea.setFont(new Font(Editor.textArea.getFont().getFontName(),Font.PLAIN,Integer.parseInt(e.getItem().toString())));
                }
            }
        });
    }

}
