package org.alexis.menus;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.translate.Language;
import com.google.cloud.translate.Translate;
import com.google.cloud.translate.TranslateOptions;
import com.google.cloud.translate.Translation;
import org.alexis.Editor;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;

public class TranslateMenu extends JFrame {
    private Translate translate;
    private final JComboBox<String> languages = new JComboBox<>();

    private final JLabel message = new JLabel();

    public TranslateMenu() {
        createWindow();
        setCredentials();
        addSupportedLanguages();
        addComboBox();
        translateText();
    }

    public void createWindow() {
        this.setTitle("Translate");
        this.setSize(220,100);
        this.setIconImage(new ImageIcon("src/main/resources/translation.png").getImage());
        this.getContentPane().setBackground(Color.WHITE);
        this.setLocation(1200,400);
        this.setLayout(new FlowLayout());
    }

    public void addSupportedLanguages() {
        languages.addItem("");
        for (Language l : translate.listSupportedLanguages()) {
            languages.addItem(l.getName()); //stores each supported language in languages combo box
        }
    }

    public void setCredentials() { //sets credential to translate instance
        try {
            //getApplicationDefault looks for environment variable GOOGLE_APPLICATION_CREDENTIALS
            GoogleCredentials credentials = GoogleCredentials.getApplicationDefault();
            translate = TranslateOptions.newBuilder().setCredentials(credentials).build().getService();
        } catch (IOException e) {
            System.out.println("No credentials found!");
        }
    }

    public void addComboBox() {
        this.getContentPane().add(languages);
    }

    public void translateText() {
        languages.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                String targetLanguage = "";
                if (e.getStateChange() == ItemEvent.SELECTED) { //returns selected or deselected
                    for (Language l : translate.listSupportedLanguages()) {
                        if (e.getItem().equals(l.getName())) {
                            targetLanguage = l.getCode();
                            break;
                        }
                    }
                    try {
                        Translation translation = translate.translate(Editor.textArea.getSelectedText(), Translate.TranslateOption.targetLanguage(targetLanguage));
                        String translatedText = translation.getTranslatedText();
                        Editor.textArea.replaceSelection(translatedText);
                        message.setText("");
                    } catch (Exception exception) {
                        message.setText("Try again!");
                        TranslateMenu.this.getContentPane().add(message);
                    }
                }
            }
        });
    }

}
