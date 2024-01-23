package org.alexis;

import javax.swing.*;
import java.awt.*;

public class TextArea extends JTextArea {

    public TextArea() {
        setDefaultFont();
        this.setLineWrap(true);
    }

    public void setDefaultFont() {
        Font defaultFont = new Font("Lucida Console",Font.PLAIN,20);
        this.setFont(defaultFont); //sets the default font for the current instance
    }
}
