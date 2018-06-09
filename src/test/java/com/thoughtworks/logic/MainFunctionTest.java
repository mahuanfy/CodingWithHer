package com.thoughtworks.logic;

import org.junit.Assert;
import org.junit.Test;

public class MainFunctionTest {

    MainFunction mainFuction = new MainFunction();

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

    @Test(timeout = 1000)
    public void check() {
        mainFuction.check();
    }

    @Test
    public void printWhole() {
        mainFuction.printWhole();

    }
}
