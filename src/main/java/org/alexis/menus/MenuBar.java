package org.alexis.menus;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuBar extends JMenuBar {
    private final FileMenu fileMenu = new FileMenu();
    private final EditMenu editMenu = new EditMenu();
    private final JButton formatButton = new JButton("Format");
    private final JButton clipboardButton = new JButton("Clipboard");
    private final JButton translateButton = new JButton("Translate");
    static final Clipboard clipboard = new Clipboard();
    private final FormatMenu formatMenu = new FormatMenu();
    private final TranslateMenu translateMenu = new TranslateMenu();
    public MenuBar() {
        addMenus();
        showClipboard();
        showFormatMenu();
        showTranslateMenu();
    }
    private void addMenus() { //adds menus to the menu bar
        this.add(fileMenu);
        this.add(editMenu);
        this.add(clipboardButton);
        this.add(formatButton);
        this.add(translateButton);
    }

    private void showClipboard() {
        clipboardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clipboard.setVisible(true);
            }
        });
    }

    public void showFormatMenu() {
        formatButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                formatMenu.setVisible(true);
            }
        });
    }


    public void showTranslateMenu() {
        translateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                translateMenu.setVisible(true);
            }
        });
    }

}
