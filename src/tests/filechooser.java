package tests;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class filechooser  implements ActionListener {
    private JFrame frm = new JFrame();
    private JFileChooser fileChooser = new JFileChooser();
    private JPanel northPanel = new JPanel();
    private JPanel centerPanel = new JPanel();
    private JButton openBtn = new JButton();
    private JButton saveBtn = new JButton();
    private JLabel fileLabel = new JLabel();
    private JTextField address = new JTextField();

    public filechooser()
    {
        //기본 컴포넌트 설정
        fileLabel.setText("파일 경로");
        address.setColumns(30);
        openBtn.setText("열기");
        saveBtn.setText("저장");

        //액션 리스너 장착
        openBtn.addActionListener(this);
        saveBtn.addActionListener(this);

        //각 패널 설정 및 컴포넌트 장착
        centerPanel.add(fileLabel);
        centerPanel.add(address);
        northPanel.setLayout(new GridLayout(0,2));
        northPanel.add(openBtn);
        northPanel.add(saveBtn);

        //프레임에 패널 장착
        frm.add(northPanel, "North");
        frm.add(centerPanel, "Center");

        //기본 프래임 셋팅
        frm.setTitle("JFileChooser 예제");
        frm.setLocation(120, 120);
        frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frm.pack();
        frm.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == openBtn)
        {
            int returnVal = fileChooser.showOpenDialog(frm);
            if( returnVal == JFileChooser.APPROVE_OPTION)
            {
                //열기 버튼을 누르면
                File file = fileChooser.getSelectedFile();
                address.setText(file.toString()+"을 오픈합니다");
            }
            else
            {
                //취소 버튼을 누르면
                address.setText("파일을 열지 못했습니다");
                System.out.println("취소합니다");
            }
        }
        else if(e.getSource() == saveBtn)
        {
            int returnVal = fileChooser.showSaveDialog(frm);
            if( returnVal == JFileChooser.APPROVE_OPTION)
            {
                //취소 버튼을 누르면
                File file = fileChooser.getSelectedFile();
                address.setText(file.toString()+"에 저장합니다");
            }
            else
            {
                //취소 버튼을 누르면
                address.setText("파일을 저장하지 못했습니다");
            }
        }
    }

    public static void main(String[] args)
    {
        filechooser my = new filechooser();
    }
}
