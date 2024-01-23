package org.alexis.menus;

import org.alexis.Editor;
import org.alexis.TextArea;

import javax.swing.*;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.undo.UndoManager;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditMenu extends JMenu {
    private final JMenuItem undo = new JMenuItem("Undo    Ctrl+Z");
    private final JMenuItem redo = new JMenuItem("Redo");
    private final JMenuItem cut = new JMenuItem("Cut    Ctrl+X");
    private final JMenuItem copy = new JMenuItem("Copy    Ctrl+C");
    private final JMenuItem paste = new JMenuItem("Paste    Ctrl+V");
    private final JMenuItem delete = new JMenuItem("Delete");
    private final UndoManager edits = new UndoManager();
    static String savedText;

    public EditMenu() {
        this.setText("Edit");
        addMenuItems();
        addTextAreaEdits();
        undoText();
        redoText();
        deleteText();
        cutText();
        pasteText();
        copyText();
    }

    public void addMenuItems() {
        JMenuItem[] items = {undo,redo,cut,copy,paste,delete};
        for (JMenuItem item : items) {
            this.add(item); //adds each menu item to the current instance
        }
    }

    public void undoText() {
        undo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    edits.undo(); //undo text in text area
                } catch (Exception ignored) {
                }
            }
        });
    }
    public void redoText() {
        redo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    edits.redo(); //redo text in text area
                } catch (Exception ignored) {
                }
            }
        });
    }

    public void deleteText() {
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Editor.textArea.replaceSelection(""); //replaces selected text with empty string
            }
        });
    }

    public void cutText() {
        cut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                savedText = Editor.textArea.getSelectedText();
                Editor.textArea.replaceSelection(""); //deletes the text from text area
                JTextField text = new JTextField();
                text.setBackground(Color.yellow);
                text.setText(savedText);
                text.setTransferHandler(new TransferHandler("text"));
                text.setDragEnabled(true);
                MenuBar.clipboard.getContentPane().add(text);
                MenuBar.clipboard.getContentPane().revalidate();
                MenuBar.clipboard.getContentPane().repaint();
            }
        });
    }

    public void copyText() {
        copy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                savedText = Editor.textArea.getSelectedText();
                JTextField text = new JTextField();
                text.setBackground(Color.yellow);
                text.setText(savedText);
                text.setTransferHandler(new TransferHandler("text"));
                text.setDragEnabled(true); //allows to drag text from this component
                MenuBar.clipboard.getContentPane().add(text);
                MenuBar.clipboard.getContentPane().revalidate();
                MenuBar.clipboard.getContentPane().repaint();
            }
        });
    }

    public void pasteText() {
        paste.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Editor.textArea.append(savedText);
            }
        });
    }


    public void addTextAreaEdits() {
        Editor.textArea.getDocument().addUndoableEditListener(new UndoableEditListener() {
            @Override
            public void undoableEditHappened(UndoableEditEvent e) { //method called each time an edit is made to text area
                edits.addEdit(e.getEdit()); //adds each edit to the undo manager
            }
        });
    }

}
