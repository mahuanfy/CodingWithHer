package com.thoughtworks.ui;

import com.thoughtworks.Main;
import com.thoughtworks.file.Data;
import com.thoughtworks.logic.MainFunction;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.concurrent.TimeUnit;

public class GameOfLifeFrame extends JFrame {
    MainFunction mainFunction = null;
    private JButton openBtn = new JButton("随机页面");
    private JButton startGameBtn = new JButton("暂停");
    private JButton staticGame = new JButton("静态Game");
    private JButton cycleGame = new JButton("周期Game");
    private JButton gliderGame = new JButton("滑翔机Game");
    private JButton symmetricGame = new JButton("对称Game");
    private JTextField totalNum = new JTextField("随机数的布局大小");
    private JTextField durationTextField = new JTextField("动画间隔时间");
    private boolean isStart = false;
    private boolean stop = false;
    private JPanel buttonPanel = new JPanel(new GridLayout(4, 2));
    private JPanel gridPanel = new JPanel();
    private JTextField[][] textMatrix;
    private static final int DEFAULT_DURATION = 200;
    private int duration = DEFAULT_DURATION;

    public GameOfLifeFrame() {
        setTitle("生命游戏");
        openBtn.addActionListener(new OpenPage());
        startGameBtn.addActionListener(new StartGameActioner());
        staticGame.addActionListener(new StaticGameActioner());
        cycleGame.addActionListener(new CycleGameActioner());
        gliderGame.addActionListener(new GliderGameActioner());
        symmetricGame.addActionListener(new SymmetricGameActioner());
        buttonPanel.add(openBtn);
        buttonPanel.add(startGameBtn);
        totalNum.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                totalNum.setText("");
            }
        });
        buttonPanel.add(totalNum);
        durationTextField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                durationTextField.setText("");
            }
        });
        buttonPanel.add(durationTextField);
        buttonPanel.add(staticGame);
        buttonPanel.add(cycleGame);
        buttonPanel.add(gliderGame);
        buttonPanel.add(symmetricGame);


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
                total = 20;
            }
            mainFunction = new MainFunction(total);
            startGameBtn.setText("开始游戏");
            mainFunction.initMatrix();
            initGridLayout();
            showMatrix();
            gridPanel.updateUI();
            new StartGameActioner().actionPerformed(e);
        }
    }

    private void showMatrix() {
        int[][] matrix = mainFunction.matrix;
        for (int y = 0; y < matrix.length; y++) {
            for (int x = 0; x < matrix[0].length; x++) {
                if (matrix[y][x] > 0) {
                    textMatrix[y][x].setBackground(Color.decode("#" +000000+ 72 * matrix[y][x] + 160 * matrix[y][x]));

                } else {
                    textMatrix[y][x].setBackground(Color.decode("#FFFFFF"));
                }
            }
        }
    }

    private void initGridLayout() {
        int rows = mainFunction.getN();
        int cols = mainFunction.getN();
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
                mainFunction.check();
                showMatrix();
                try {
                    TimeUnit.MILLISECONDS.sleep(duration);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    private class StaticGameActioner implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            mainFunction = new MainFunction(2, Data.startData);
            startGameBtn.setText("开始游戏");
            initGridLayout();
            showMatrix();
            gridPanel.updateUI();
            new StartGameActioner().actionPerformed(e);

        }
    }

    private class CycleGameActioner implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            mainFunction = new MainFunction(3, Data.cycleData);
            startGameBtn.setText("开始游戏");
            initGridLayout();
            showMatrix();
            gridPanel.updateUI();
            new StartGameActioner().actionPerformed(e);

        }
    }

    private class GliderGameActioner implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            mainFunction = new MainFunction(40, Data.gliderData);
            startGameBtn.setText("开始游戏");
            initGridLayout();
            showMatrix();
            gridPanel.updateUI();
            new StartGameActioner().actionPerformed(e);

        }
    }

    private class SymmetricGameActioner implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            mainFunction = new MainFunction(5, Data.symmetricData);
            startGameBtn.setText("开始游戏");
            initGridLayout();
            showMatrix();
            gridPanel.updateUI();
            new StartGameActioner().actionPerformed(e);
        }
    }
}
