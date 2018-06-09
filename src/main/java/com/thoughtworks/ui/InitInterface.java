package com.thoughtworks.ui;

import com.thoughtworks.logic.MainFuction;

public class InitInterface {
    public static InitGame initDate() {
        MainFuction mainFuction = new MainFuction();

        int duration = 200;
        int totalNum = 200;

        mainFuction.initMatrix();
//        while (true) {
//            check();
//            setMatrix();
//            printWhole();
//            Thread.sleep(1000);
//        }
        InitGame initGame = new InitGame(mainFuction.getN(), mainFuction.getN(), duration, totalNum, mainFuction.matrix);
        return initGame;
    }

}
