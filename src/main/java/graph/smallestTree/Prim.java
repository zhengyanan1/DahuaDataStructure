package graph.smallestTree;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * Created by Administrator on 2017/3/27.
 */
public class Prim {
    public static int n = 9;
    public static int[][] data = new int[][]{
            {0,10,-1,-1,-1,11,-1,-1,-1},
            {10,0,18,-1,-1,-1,16,-1,12},
            {-1,-1,0,22,-1,-1,-1,-1,8},
            {-1,-1,22,0,20,-1,-1,16,21},
            {-1,-1,-1,20,0,26,-1,7,-1},
            {11,-1,-1,-1,26,0,17,-1,-1},
            {-1,16,-1,-1,-1,17,0,19,-1},
            {-1,-1,-1,16,7,-1,19,0,-1},
            {-1,12,8,21,-1,-1,-1,-1,0},

    };
    public static void main(String[] args) {
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++) {
                if (data[i][j] == -1)   data[i][j] = 65535;
            }
        System.out.println(Arrays.deepToString(data));
        LinkedList<Integer> reach = new LinkedList<Integer>();
        LinkedList<Integer> unReach = new LinkedList<Integer>();
        int total = 0;
        reach.add(0);
        for (int i = 1; i < n; i++) unReach.add(i);

        while (!unReach.isEmpty()) {
            int min = 65535, newNodeIndex = -1;
            System.out.println(reach);
            System.out.println(unReach);
            for (int i = 0; i < reach.size(); i++) {
                for (int j = 0; j < unReach.size(); j++) {
                    if (min > data[reach.get(i)][unReach.get(j)]) {
                        min = data[reach.get(i)][unReach.get(j)];
                        newNodeIndex = j;
                    }
                }
            }
            System.out.println(min + ";" + newNodeIndex);
            if (min < 65535) {
                total += min;
                reach.add(unReach.get(newNodeIndex));
                unReach.remove(newNodeIndex);
            }

        }
        System.out.println(total);
    }
}
