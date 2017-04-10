package graph.traverse;

import java.util.Scanner;

/**
 * Created by Administrator on 2017/3/27.
 */
public class DFS {
    static int n = 5;
    static int[][] data;
    static boolean[] visited;
    public static void DFSTraverse(){
        for (int i = 0; i < n; i++) {
            if (!visited[i])    DFS(i);
        }
    }
    public static void DFS(int i){
        visited[i] = true;
        System.out.printf(i+" ");

        for (int j = 0; j < n; j++) {
            if (data[i][j] == 1 && !visited[j]) DFS(j);
        }
    }
    public static void main(String[] args){
        Scanner cin = new Scanner(System.in);
        while (cin.hasNext()){
            int numLines = cin.nextInt();
            data = new int[n][n];
            visited = new boolean[n];

            int start,end;
            for (int i = 0; i < numLines; i++) {
                start = cin.nextInt();
                end = cin.nextInt();
                data[start][end] = 1;
                data[end][start] = 1;
            }
            DFSTraverse();
        }
    }
}
