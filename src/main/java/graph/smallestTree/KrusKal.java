package graph.smallestTree;

import java.util.*;

/**
 * Created by Administrator on 2017/3/28.
 */
public class KrusKal {
    public static int n = 9;
    public static int[][] data = new int[][]{
            {0,10,-1,-1,-1,11,-1,-1,-1},
            {10,0,18,-1,-1,-1,16,-1,12},
            {-1,18,0,22,-1,-1,-1,-1,8},
            {-1,-1,22,0,20,-1,24,16,21},
            {-1,-1,-1,20,0,26,-1,7,-1},
            {11,-1,-1,-1,26,0,17,-1,-1},
            {-1,16,-1,24,-1,17,0,19,-1},
            {-1,-1,-1,16,7,-1,19,0,-1},
            {-1,12,8,21,-1,-1,-1,-1,0},

    };
    public static void main(String[] args){
        ArrayList<Edge> edges = new ArrayList<Edge>(15);
        for (int i = 0; i < n; i++)
            for (int j = i + 1; j < n; j++)
                if (data[i][j] != -1)   edges.add(new Edge(i,j,data[i][j]));

        int[] kind = new int[n];
        edges.sort(new Comparator<Edge>() {
            public int compare(Edge o1, Edge o2) {
                return o1.weight - o2.weight;
            }
        });

        int count = 0;
        for (int i = 0; i < n; i++) kind[i] = 0;
        for (Edge e : edges)    System.out.println(e);
        for (Edge e : edges){
            int n,m;
            n = getKind(kind,e.start);
            m = getKind(kind,e.end);
            System.out.println("***"+e);
            System.out.println(n + ";" + m);
            if (n != m){
                kind[n] = m;
                System.out.println(e);
                count += e.weight;
            }
            System.out.println(Arrays.toString(kind));
            System.out.println();
        }
        System.out.println(count);


        //version_1:O(e * n)
//        for (int i = 0; i < n; i++) kind[i] = i;
//        int count = 0;
//        for (Edge e : edges)    System.out.println(e);
//        for (Edge e : edges){
//            if (kind[e.start] != kind[e.end]){
//                count += e.weight;
//                System.out.printf(e.weight + " ;" + e.start + ";" + e.end);
//
//                for (int i = 0; i < n; i++) {
//                    if (kind[i] == kind[e.end]) {
//                        kind[i] = kind[e.start];
//                    }
//                }
//                System.out.println(Arrays.toString(kind));
//            }
//        }
//        System.out.println(count);
    }
    public static int getKind(int[] kinds,int key){
        while (kinds[key] > 0){
            key = kinds[key];
        }
        return key;
    }
}
class Edge{
    int start;
    int end;
    int weight;

    public Edge(int start, int end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Edge{" +
                "start=" + start +
                ", end=" + end +
                ", weight=" + weight +
                '}';
    }
}
