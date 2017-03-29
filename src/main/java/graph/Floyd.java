package graph;

import java.util.Arrays;

/**
 * Created by Administrator on 2017/3/29.
 */
public class Floyd {
    public static int INFINITY = 65535;
    public static int n = 9;
    public static int[][] data = new int[][]{
            {0,1,5,INFINITY,INFINITY,INFINITY,INFINITY,INFINITY,INFINITY},
            {1,0,3,7,5,INFINITY,INFINITY,INFINITY,INFINITY},
            {5,3,0,INFINITY,1,7,INFINITY,INFINITY,INFINITY},
            {INFINITY,7,INFINITY,0,2,INFINITY,3,INFINITY,INFINITY},
            {INFINITY,5,1,2,0,3,6,9,INFINITY},
            {INFINITY,INFINITY,7,INFINITY,3,0,INFINITY,5,INFINITY},
            {INFINITY,INFINITY,INFINITY,3,6,INFINITY,0,2,7},
            {INFINITY,INFINITY,INFINITY,INFINITY,9,5,2,0,4},
            {INFINITY,INFINITY,INFINITY,INFINITY,INFINITY,INFINITY,7,4,0}
    };
    public static void main(String[] args){
        int[][] d = new int[n][n];
        int[][] p = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                d[i][j] = data[i][j];
                p[i][j] = j;
            }
        }
        //初始化

        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (d[i][j] > d[i][k] + d[k][j]){
                        d[i][j] = d[i][k] + d[k][j];
                        p[i][j] = p[i][k];
                    }
                }
            }
        }


        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.printf("%3d",d[i][j]);
            }
            System.out.println();
        }
        System.out.println("******");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.printf("%3d",p[i][j]);
            }
            System.out.println();
        }
        showPaths(d,p);

    }
    public static void showPaths(int[][] d,int[][] paths){
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                System.out.println("v" + i +"->v" + j + ":" + d[i][j]);

                System.out.printf("v" + i + " ");
                int k = paths[i][j]; //获得第一个路径顶点下标
                while (k != j){
                    System.out.printf("v" + k + " ");
                    k = paths[k][j];
                }
                System.out.printf("v" + j + "\n");
            }
            System.out.println();
        }
    }
}
