package top.yms.recent.code2;

import java.util.Arrays;

public class Code21 {
    int maxSumPossible = -1;
    int minSumPossible = -1;
    int [] theSumTotal = null;
    final int theDiceNum = 6;
    int len = -1;

    private void doFunction(int sum, int i) {
        if (i == 0) {
            theSumTotal[sum - minSumPossible]++;
            return;
        } else {
            for (int j = 1; j <= theDiceNum; j++) {
                doFunction(sum+j,i-1);
            }
        }
    }

    public double[] dicesProbability(int n) {
        if (n<1) return null;

        maxSumPossible = n*theDiceNum;
        minSumPossible = n;
        len = maxSumPossible-minSumPossible+1;
        double [] theResult = new double[len];
        theSumTotal = new int[len];
        doFunction(0,n);

        double totalOccu = Math.pow(theDiceNum,n);
        for (int i = 0; i < len; i++) {
            theResult[i] = theSumTotal[i] / (totalOccu);
        }
        System.out.println(Arrays.toString(theSumTotal));

        return theResult;
    }

    public static void main(String[] args) {
        Code21 code21 = new Code21();
        int n = 3;
        double[] theDices = code21.dicesProbability(n);

        for (int i = 0; i < (n* code21.theDiceNum) - code21.minSumPossible+1; i++) {
            System.out.println("点数【"+(i+ code21.minSumPossible)+"】出现概率："+ (theDices[i] * 100.0d)+"%");
        }

//        System.out.println(Arrays.toString(theDices));
    }

}

