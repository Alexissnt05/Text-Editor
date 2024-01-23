package org.alexis.menus;

import org.alexis.Editor;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileMenu extends JMenu {
    private final JMenuItem open = new JMenuItem("Open");
    private final JMenuItem save = new JMenuItem("Save");

    public FileMenu() { //adds menu items to the FileMenu as a drop-down window
        this.setText("File"); //sets the menu name
        this.add(open);
        this.add(save);
        openFile();
        saveFile();
    }
    public void openFile() {
        JFileChooser fileChooser = new JFileChooser(); //prompts user to choose file or directory
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Only txt files", "txt");
        fileChooser.addChoosableFileFilter(filter); //Makes the fileChooser only see files with .txt extension
        open.addActionListener(new ActionListener() { //action listener for when clicking on the menu item
            @Override
            public void actionPerformed(ActionEvent e) {
                //response stores whether a file has been selected or not
                //fileChooser.showOpenDialog returns a response (0 or 1)
                int response = fileChooser.showOpenDialog(null); //lets you select a file to open
                if (response == JFileChooser.APPROVE_OPTION) { //if response is equal to 0
                    File file = new File(fileChooser.getSelectedFile().getAbsolutePath()); //contains path of selected file
                    try {
                        Scanner scanner = new Scanner(file); //scanner to read from a file
                        while (scanner.hasNext()) {
                            Editor.textArea.append(scanner.nextLine() + "\n");
                        }
                    } catch (FileNotFoundException ignored) {
                    }
                }
            }
        });
    }
    public void saveFile() {
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int response = fileChooser.showSaveDialog(null); //lets you select a file to save
                File file = fileChooser.getSelectedFile();
                if (response == JFileChooser.APPROVE_OPTION) {
                    try (FileWriter fw = new FileWriter(file)) { //automatically closes the resource
                        fw.write(Editor.textArea.getText());
                    } catch (IOException ignored) {
                    }
                }
            }
        });
    }

}
