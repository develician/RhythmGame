package tests;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*; // File∂ßπÆø°
import java.util.*;

public class JFileChooserDemo extends JFrame implements ActionListener{

    // πˆ∆∞
    JButton openButton;
    JButton saveButton;

    // πˆ∆∞ ¥„¿ª ∆«≥⁄
    JPanel buttonPanel;
    JTextArea fileInfo; // ∆ƒ¿œ ¡§∫∏ ∫∏ø©¡ÿ¥Ÿ.

    public JFileChooserDemo() {
        super("∆ƒ¿œ ø≠±‚: JFileChooser ø¨Ω¿!!");
        //openButton = new JButton(new ImageIcon("22.gif"));  // πˆ∆∞ ∞¥√º ª˝º∫
        //saveButton  = new JButton(new ImageIcon("233.jpg"));
        openButton = new JButton("OPEN");  // πˆ∆∞ ∞¥√º ª˝º∫
        saveButton  = new JButton("SAVE");
        buttonPanel = new JPanel();  // ∆«≥⁄ ∞¥√º ª˝º∫
        fileInfo = new JTextArea();

        buttonPanel.add(openButton);
        buttonPanel.add(saveButton);

        add(buttonPanel,BorderLayout.NORTH);
        add(new JScrollPane(fileInfo));

        setSize(400,500);
        setVisible(true);

        openButton.addActionListener(this);
        saveButton.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e){
        JFileChooser chooser = new JFileChooser();
        if(e.getSource() == openButton){
            int result = chooser.showOpenDialog(this); // ¥≠∑∂¿ª∂ß int«¸¿∏∑Œ ∏Æ≈œ
            if(result == JFileChooser.APPROVE_OPTION){ // ø≠±‚ ¥≠∑∂¿ª ∂ß
                File file = chooser.getSelectedFile();
                fileInfo.append("∆ƒ¿œ∏Ì"+file.getName()+"\n");
                fileInfo.append("∆ƒ¿œ∞Ê∑Œ : "+file.getPath()+"\n");
                fileInfo.append("∆ƒ¿œ ªÁ¿Ã¡Ó:"+file.length()+"\n");

                Calendar date = Calendar.getInstance(); // new ∏¯«‘ : getInstance∑Œ æÚæÓø»
                date.setTimeInMillis(file.lastModified()); // file.lastModified() ∏∂¡ˆ∏∑ ºˆ¡§¿œ¿ª æÚæÓ ø√ºˆ ¿÷¿Ω.

                int year = date.get(Calendar.YEAR);
                int month = date.get(Calendar.MONTH)+1;
                int day = date.get(Calendar.DAY_OF_MONTH);

                fileInfo.append("¿€º∫≥Ø¬• :"+year+"≥‚"+month+"ø˘"+day+"¿œ");

            }
        }else if(e.getSource() == saveButton){
            int result = chooser.showSaveDialog(this);
            if(result == JFileChooser.APPROVE_OPTION){
                fileInfo.append(chooser.getSelectedFile().getName()+"¿˙¿Â «œø¥Ω¿¥œ¥Ÿ."+"\n");
            }
        }
    }

    public static void main(String[] args) {
        new JFileChooserDemo();
    }

}
