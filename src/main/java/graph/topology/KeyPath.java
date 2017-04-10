package graph.topology;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * Created by Administrator on 2017/3/31.
 */
public class KeyPath {
    public static int n = 10;
    public static int[][] data;

    public static void main(String[] args){
        Scanner cin = new Scanner(System.in);
        while (cin.hasNext()){
            //初始化数据
            int numEdges = cin.nextInt();
            data = new int[n][n];
            for (int i = 0; i < numEdges; i++) {
                int start,end,value;
                start = cin.nextInt();
                end = cin.nextInt();
                value = cin.nextInt();
                data[start][end] = value;
            }

            int[] in = new int[n];//求顶点的入度
            initIndegree(data,in);

            int[] etv = new int[n];//顶点的最早开始时间
            LinkedList<Integer> saveOrder = new LinkedList<Integer>();//存储拓扑排序的栈
            TopoSort(data,in,etv,saveOrder);

            int[] ltv = new int[n];//顶点的最晚开始时间
            for (int i = 0; i < n; i++) ltv[i] = etv[n - 1];
            getLatestTimes(data,ltv,saveOrder);

            getKeyPath(etv,ltv,data);

        }
    }

    public static void getKeyPath(int[] etv,int[] ltv,int[][] data){
        int ete,lte;//ete活动最早开始时间,lte活动最晚开始时间
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (data[i][j] > 0){
                    ete = etv[i];
                    lte = ltv[j] - data[i][j];
                    if (ete == lte){
                        System.out.printf("<v%d,v%d>:%d-->",i,j,data[i][j]);
                    }

                }
            }
        }

    }
    public static void getLatestTimes(int[][] data,int[] ltv,LinkedList<Integer> saveOrder){
        while (!saveOrder.isEmpty()){
            int cur = saveOrder.removeLast();
            for (int i = 0; i < n; i++) {
                if (data[cur][i] > 0 && ltv[i] - data[cur][i] < ltv[cur]){
                    ltv[cur] = ltv[i] - data[cur][i];
                }
            }
        }
    }

    public static void initIndegree(int[][] data,int[] in){
        for (int i = 0; i < n; i++) {
            int inCount = 0;
            for (int j = 0; j < n; j++) {
                if (data[j][i] > 0) inCount++;
            }
            in[i] = inCount;
        }
        System.out.println(Arrays.toString(in));
    }


    /**
       @author zhengyanan
       @date 2017/3/31 @time 16:34
      求得各个顶点最早开始时间
    */
    public static void TopoSort(int[][]data, int[] in, int[] etv, LinkedList<Integer> saveOrder){
        for (int i = 0; i < n; i++)     etv[i] = 0;
        LinkedList<Integer> helper = new LinkedList<Integer>();
        for (int i = 0; i < n; i++) {
            if (in[i] == 0) helper.addLast(i);
        }

        int count = 0;//标记是否存在拓扑排序
        while (!helper.isEmpty()){
            int cur = helper.removeLast();
            saveOrder.addLast(cur);
            count++;

            for (int i = 0; i < n; i++) {
                if (data[cur][i] > 0)   {
                    in[i]--;
                    if (in[i] == 0) helper.addLast(i);

                    if (etv[cur] + data[cur][i] > etv[i]){
                        //求各顶点最早开始时间；PS:有邻接点才判断是否可以更新后面的最早开始时间
                        etv[i] = etv[cur] + data[cur][i];
                    }
                }
            }
        }
        System.out.println(Arrays.toString(etv));
        if (count == n) System.out.println("YES");
        else            System.out.println("NO!");
    }
}
/** input:
 13
 0 1 3
 0 2 4
 1 3 5
 1 4 6
 2 3 8
 2 5 7
 3 4 3
 4 6 9
 4 7 4
 5 7 6
 6 9 2
 7 8 5
 8 9 3

 */
