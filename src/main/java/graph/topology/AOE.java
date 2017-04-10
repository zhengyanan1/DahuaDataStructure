package graph.topology;

import javax.sound.sampled.Line;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * Created by Administrator on 2017/3/29.
 */
public class AOE {
    public static int n = 14;
    public static int[][] data;
    public static void main(String[] args){
        Scanner cin = new Scanner(System.in);
        while (cin.hasNext()){
            data = new int[n][n];

            int x,y,numLines = cin.nextInt();
            for (int i = 0; i < numLines; i++){
                x = cin.nextInt();
                y = cin.nextInt();
                data[x][y] = 1;
            }

            int[] in = new int[n];
            for (int j = 0; j < n; j++) {
                int inCount = 0;
                for (int i = 0; i < n; i++)
                    if (data[i][j] == 1)    inCount++;
                in[j] = inCount;
            }

            LinkedList<Integer> helper = new LinkedList<Integer>();
            for (int i = 0; i < n; i++) if (in[i] == 0) helper.addLast(i);

            int count = 0;
            while (!helper.isEmpty()){
                int cur = helper.removeLast();
                System.out.printf("%d->",cur);
                count++;
//                System.out.println(helper);
                for (int i = 0; i < n; i++) {
                    if (data[cur][i] == 1){
                        in[i]--;
                        if (in[i] == 0) helper.addLast(i);
                    }
                }
            }
            System.out.println();
            if (count == n) System.out.println("YES");
            else            System.out.println("NO!");
        }
    }
}
/**input:
 20
 0 4
 0 5
 0 11
 1 2
 1 4
 1 8
 2 5
 2 6
 2 9
 3 2
 3 13
 4 7
 5 8
 5 12
 6 5
 8 7
 9 10
 9 11
 10 13
 12 9
 */