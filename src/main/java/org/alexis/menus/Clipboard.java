package org.alexis.menus;

import javax.swing.*;
import java.awt.*;

public class Clipboard extends JFrame {
    public Clipboard() {
        createWindow();
    }
    public void createWindow() {
        this.setTitle("Clipboard");
        this.setIconImage(new ImageIcon("src/main/resources/clipboard.png").getImage());
        this.setSize(220,300);
        this.setLocation(500,265);
        this.getContentPane().setBackground(Color.yellow);
        this.setLayout(new BoxLayout(this.getContentPane(),BoxLayout.Y_AXIS)); //arranges components vertically
    }

}
