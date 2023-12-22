package com.itjie.testdislocateImage;

import java.util.Random;

public class Test {
    public static void main(String[] args) {
        int[] array = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
        Random r = new Random();
        for (int i = 0; i < array.length; i++) {
            int index = r.nextInt(array.length);
            int temp = array[i];
            array[i] = array[index];
            array[index] = temp;
        }
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();

        //创建一个二维数组（4行4列）
        int[][] data = new int[4][4];

        //给二维数组添加数据
        //解法一
        for (int i = 0; i < array.length; i++) {
            //当i=0，i/4=0，i%4=0
            data[i / 4][i % 4] = array[i];
        }
       //解法二
        int index = 0;
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                data[i][j] = array[index];
                index++;
            }
        }
        //遍历二维数组
        for (int i = 0; i < data.length; i++) {
            // 遍历data数组，打印每一行的数据
            for (int j = 0; j < data[i].length; j++) {
                System.out.print(data[i][j] + " ");
            }
            System.out.println();
        }
    }
}
