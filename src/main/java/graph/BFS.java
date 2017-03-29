package graph;

import java.util.LinkedList;
import java.util.Scanner;

/**
 * Created by Administrator on 2017/3/27.
 */
public class BFS {
    static int n = 7;
    static int[][] data;
    static boolean[] visited;
    public static void main(String[] args) {
        Scanner cin = new Scanner(System.in);
        while (cin.hasNext()) {
            int numLines = cin.nextInt();
            data = new int[n][n];
            visited = new boolean[n];

            int start, end;
            for (int i = 0; i < numLines; i++) {
                start = cin.nextInt();
                end = cin.nextInt();
                data[start][end] = 1;
                data[end][start] = 1;
            }
            BFSTraverse();
        }
    }

    private static void BFSTraverse() {
        LinkedList<Integer> traverses = new LinkedList<Integer>();

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {

                visited[i] = true;
                System.out.printf(i + " ");
                traverses.addLast(i);

                while (!traverses.isEmpty()) {
                    System.out.println(traverses);
                    int current = traverses.removeFirst();

                    for (int j = 0; j < n; j++) {
                        if (data[current][j] == 1 && !visited[j])   {
                            visited[j] = true;
                            System.out.printf(j + " ");
                            traverses.addLast(j);
                        }
                    }
                }
            }
        }

//        //version_1
//        for (int i = 0; i < n; i++) {
//            if (!visited[i]) {
//                traverses.addLast(i);
//                while (!traverses.isEmpty()) {
//                    System.out.println(traverses);
//
//                    int current = traverses.removeFirst();
//                    if (!visited[current]) {
//                        visited[current] = true;
//                        System.out.printf(current + " ");
//                    }
//
//                    for (int j = 0; j < n; j++) {
//                        if (data[current][j] == 1 && !visited[j])   traverses.addLast(j);
//                    }
//                }
//            }
//        }
    }
}
