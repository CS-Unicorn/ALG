package com.advancex.sparsearray;

public class Demo {
    public static void main(String[] args) {
        /**
         * 创建一个二维数组 11*11
         *  0表示没有棋子，1表示黑子，2表示蓝子
         */
        int[][] chessArr = new int[11][11];
        // 初始条件 (1,2) 为一个黑子，(2,3)为一个蓝子
        chessArr[1][2] = 1;
        chessArr[2][3] = 2;
        chessArr[4][5] = 2;
        // 输出原始的二维数组
        System.out.println("原始二维数组：");
        for (int[] row : chessArr) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }

        System.out.println("============================");

        // 遍历二维数组
        // 输出并记录非0元素
        int num = 0;
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (chessArr[i][j] != 0) {
                    num++;
                    System.out.println("chessArr[" + i + "][" + j + "] = " + chessArr[i][j]);
                }
            }
        }
        System.out.println("num = " + num);

        System.out.println("============================");

        // 创建对应的稀疏数组
        /**\
         * 稀疏数组：
         * 第一行存储二维数组的行数 列数 非0元素 的个数
         * 其下依次存放非0元素的行列信息
         */
        // 行数表示有多少个非0值，列存储 行数 列数 元素值
        int[][] sparseArr = new int[num + 1][3];
        // 给稀疏数组赋初值
        sparseArr[0][0] = 11; // 第一列存二维数组的行数
        sparseArr[0][1] = 11; // 第二列存二维数组的列数
        sparseArr[0][2] = num; // 第三列存二位数组中非0元素的个数

        // 遍历二维数组，将非0元素存放到sparseArr中
        int count = 0; // 用来记录是第几个非0数据
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (chessArr[i][j] != 0) {
                    count++; // 每存入一个非0元素，count就自增
                    sparseArr[count][0] = i; // 将行 i 存入第1列
                    sparseArr[count][1] = j; // 将列 j 存入第2列
                    sparseArr[count][2] = chessArr[i][j]; // 将元素的值存入第3列
                }
            }
        }


        // 输出稀疏数组
        /*for (int[] ints : sparseArr) {
            for (int anInt : ints) {
                System.out.printf("%d\t", anInt);
            }
            System.out.println();
        }*/
        System.out.println("输出稀疏数组（三元组）：");
        for (int i = 0; i < sparseArr.length; i++) {
            System.out.printf("%5d  %5d  %5d\n", sparseArr[i][0], sparseArr[i][1], sparseArr[i][2]);
        }
        System.out.println("============================");


        /**
         *  将稀疏数组转回二维数组
         *  1、先读取第一行，获取二维数组的行列个数
         *  2、将稀疏数组第一行之后的数据即为二维数组中对应的元素
         */

        // 读取第一行，获取二维数组的行列个数
        int[][] chessArr2 = new int[sparseArr[0][0]][sparseArr[0][1]];
        // 输出恢复后的二维数组,  此时尚未赋值
        System.out.println("输出恢复后尚未赋值的稀疏数组：");
        for (int[] ints : chessArr2) {
            for (int anInt : ints) {
                System.out.print(anInt + "\t");
            }
            System.out.println();
        }


        // 将稀疏数组第一行之后的数据即为二维数组中对应的元素
        // 从稀疏数组的第二行开始遍历
        for (int i = 1; i < sparseArr.length; i++) {
            chessArr2[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];
        }

        System.out.println("============================");
        System.out.println("恢复后的稀疏数组：");
        for (int[] ints : chessArr2) {
            for (int anInt : ints) {
                System.out.print(anInt + "\t");
            }
            System.out.println();
        }


    }
}