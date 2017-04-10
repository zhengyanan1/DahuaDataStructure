package graph.shortest_path;

import java.util.Arrays;

/**
 * Created by Administrator on 2017/3/28.
 * 假设求v0 到其他所有点的最短路径
 */
public class Dijstra {
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
        int[] shortestL = new int[n];
        int[] prePath = new int[n];//到某点的最短路径的前驱
        boolean[] visited = new boolean[n];//表示是否已经求得到该点的最短路径
        for (int i = 0; i < n; i++) shortestL[i] = data[0][i];
        for (int i = 0; i < n; i++) prePath[i] = 0;
        visited[0] = true;

        int i = 1;
        while (i ++ < n){
            int min = INFINITY,cur = -1;
            for (int j = 0; j < n; j++) {
                if (!visited[j] && min > shortestL[j]){
                    cur = j;
                    min = shortestL[j];
                }
            }
            visited[cur] = true;

            for (int j = 0; j < n; j++) {
                if (!visited[j] && min + data[cur][j] < shortestL[j]) {
                    shortestL[j] = min + data[cur][j];
                    prePath[j] = cur;
                }
            }
        }
        System.out.println(Arrays.toString(shortestL));
        System.out.println(Arrays.toString(prePath));

    }
}
