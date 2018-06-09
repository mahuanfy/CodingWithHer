package com.thoughtworks.logic;

import org.junit.Assert;
import org.junit.Test;

public class MainFuctionTest {

    MainFuction mainFuction = new MainFuction();


    @Test
    public void setN() {
    }

    @Test
    public void initMatrix() {
        mainFuction.initMatrix();
    }

    @Test
    public void setMatrix() {
        mainFuction.setMatrix();
    }

    @Test
    public void get_num_where_x_1_y_1() {
        int[][] n = {{1, 0, 0}, {0, 1, 0}, {1, 1, 1}};
        int num = mainFuction.getNum(1, 1, n);
        Assert.assertEquals(4, num);
    }

    @Test
    public void get_num_where_x_2_y_2() {
        int[][] n = {{1, 0, 0},
                {0, 1, 0},
                {1, 1, 1}};
        int num = mainFuction.getNum(2, 2, n);
        Assert.assertEquals(2, num);
    }

    @Test
    public void changeState() {
    }

    @Test(timeout = 1000)
    public void check() {
        mainFuction.check();
    }

    @Test
    public void printWhole() {
        mainFuction.printWhole();

    }

    @Test(timeout = 5000)
    public void main() throws InterruptedException {
        initMatrix();
        printWhole();
        while (true) {
            check();
            setMatrix();
            printWhole();
            Thread.sleep(1000);
        }
    }
}
