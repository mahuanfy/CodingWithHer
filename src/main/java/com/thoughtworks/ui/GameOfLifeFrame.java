package com.thoughtworks.ui;

import com.thoughtworks.logic.MainFunction;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;

public class GameOfLifeFrame extends JFrame {
    MainFunction mainFuction = null;
    private JButton openBtn = new JButton("随机页面");
    private JButton startGameBtn = new JButton("暂停");
    private JLabel durationPromtLabel = new JLabel("动画间隔设置(ms为单位)");
    private JTextField totalNum = new JTextField();
    private JTextField durationTextField = new JTextField();
    private boolean isStart = false;
    private boolean stop = false;
    private JPanel buttonPanel = new JPanel(new GridLayout(2, 2));
    private JPanel gridPanel = new JPanel();
    private JTextField[][] textMatrix;
    private static final int DEFAULT_DURATION = 200;
    private int duration = DEFAULT_DURATION;

    public GameOfLifeFrame() {
        setTitle("生命游戏");
        openBtn.addActionListener(new OpenPage());
        startGameBtn.addActionListener(new StartGameActioner());
        buttonPanel.add(openBtn);
        buttonPanel.add(startGameBtn);
        buttonPanel.add(totalNum);
        buttonPanel.add(durationTextField);
        buttonPanel.setBackground(Color.WHITE);
        getContentPane().add("North", buttonPanel);
        this.setSize(1000, 1200);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    private class OpenPage implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            isStart = false;
            stop = true;
            int total;
            try {
                total = Integer.parseInt(totalNum.getText().trim());
            } catch (NumberFormatException e1) {
                total = 50;
            }
            mainFuction = new MainFunction(total);
            startGameBtn.setText("开始游戏");
            mainFuction.initMatrix();
            initGridLayout();
            showMatrix();
            gridPanel.updateUI();
            new StartGameActioner().actionPerformed(e);
        }
    }

    private void showMatrix() {
        int[][] matrix = mainFuction.matrix;
        for (int y = 0; y < matrix.length; y++) {
            for (int x = 0; x < matrix[0].length; x++) {
                if (matrix[y][x] > 0) {
                    textMatrix[y][x].setBackground(Color.BLACK);
                } else {
                    textMatrix[y][x].setBackground(Color.WHITE);
                }
            }
        }
    }

    /**
     * 创建显示的gridlayout布局
     */
    private void initGridLayout() {
        int rows = mainFuction.getN();
        int cols = mainFuction.getN();
        gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(rows, cols));
        textMatrix = new JTextField[rows][cols];
        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < cols; x++) {
                JTextField text = new JTextField();
                textMatrix[y][x] = text;
                gridPanel.add(text);
            }
        }
        add("Center", gridPanel);
    }

    private class StartGameActioner implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (!isStart) {
                try {
                    duration = Integer.parseInt(durationTextField.getText().trim());
                } catch (NumberFormatException e1) {
                    duration = DEFAULT_DURATION;
                }
                new Thread(new GameControlTask()).start();
                isStart = true;
                stop = false;
                startGameBtn.setText("暂停游戏");
            } else {
                stop = true;
                isStart = false;
                startGameBtn.setText("开始游戏");
            }
        }
    }

    private class GameControlTask implements Runnable {
        public void run() {
            while (!stop) {
                mainFuction.check();
                showMatrix();
                try {
                    TimeUnit.MILLISECONDS.sleep(duration);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}
