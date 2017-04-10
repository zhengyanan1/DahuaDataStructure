package sort;

import java.io.*;
import java.util.Arrays;
import java.util.Random;
import java.util.SortedSet;

/**
 * Created by Administrator on 2017/4/7.
 */
public class SortCollections {
    static void BubbleSort(int[] a){//冒泡排序
        int length = a.length;
        boolean unFinished = true;
        for (int i = 0; i < length - 1 && unFinished; i++) {
            unFinished = false;
            for (int j = 0; j < length - 1 - i; j++) {
                if (a[j] > a[j + 1])    {
                    swap(a,j,j + 1);
                    unFinished = true;
                }
            }
        }
    }

    static void SimpleSelectSort(int[] a){//简单选择
        int length = a.length;
        for (int i = 0; i < length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < length ; j++) {
                if (a[min] > a[j])  min = j;
            }

            if (i != min)   swap(a,i,min);
        }
    }

    static void InsertSort(int[] a){//直接插入
        int length = a.length;
        for (int i = 1; i < length; i++) {
            if (a[i - 1] > a[i]){
                int key = a[i],j;

                for (j = i - 1;j >= 0 && key < a[j]; j--)  a[j + 1] = a[j];
                a[j + 1] = key;
            }
        }
    }

    static void XiErSort(int[] a){//希尔排序
        int length = a.length,increment = length;

        while (increment > 1){
            increment = increment / 3 + 1;
            for (int i = increment; i < length; i++) {
                if (a[i] < a[i - increment]){
                    int t = a[i],j;
                    for (j = i - increment; j >= 0 && a[j] > t; j -= increment) {
                        a[j + increment] = a[j];
                    }
                    a[j + increment] = t;
                }
            }
        }
    }

    static void HeapSort(int[] a){//堆排序
        //建堆
        for (int i = a.length / 2 - 1; i >= 0 ; i--) {
            HeapAdjust(a,i,a.length - 1);
        }
        //排序
        for (int i = a.length - 1; i > 0; i--) {
            swap(a,0,i);
            HeapAdjust(a,0,i - 1);
        }

    }
    static void HeapAdjust(int[] a,int start ,int end){
        int largest;
        int left = start * 2 + 1,right = start * 2 + 2;
        if (left <= end && a[start] < a[left])      largest = left;
        else                                        largest = start;

        if (right <= end && a[largest] < a[right])    largest = right;

        if (largest != start){
            swap(a,start,largest);
            HeapAdjust(a,largest,end);
        }
    }

    static void MergeSort(int[] a,int start,int end){
        if (start < end){
            int mid = (start + end) / 2;
            MergeSort(a,start,mid);
            MergeSort(a,mid + 1,end);
            Merge(a,start,mid,end);
        }
    }
    static void Merge(int[] a,int start,int middle,int end){
        int[] left,right;
        left = new int[middle - start + 2];
        right = new int[end - middle + 1];
        for (int i = start; i <= middle ; i++)      left[i - start] = a[i];
        for (int i = middle + 1; i <= end ; i++)    right[i - middle - 1] = a[i];
        left[left.length - 1] = Integer.MAX_VALUE;
        right[right.length - 1] = Integer.MAX_VALUE;

        int j = 0,k = 0;
        for (int i = start; i <= end ; i++) {
            if (left[j] <= right[k])    a[i] = left[j++];
            else                        a[i] = right[k++];
        }
    }

    static void MergeSortNew(int[] a){//非递归实现归并算法
        int k = 1;
        while (k < a.length){
            MergePass(a,k);
            k *= 2;
        }
    }
    static void MergePass(int[] a,int k){
        int i;
        for (i = 0; i + 2 * k - 1 < a.length; i += 2 * k) {
            Merge(a,i,i + k -1,i + 2 * k - 1);
        }

        //归并最后剩下的2个序列(一个长度为k,一个小于k)
        //如果剩一个小于k的序列，本轮轮空即可
        if (a.length - 1 > i + k - 1){
            Merge(a,i,i + k - 1,a.length - 1);
        }
    }

    static int count = 0;
    static void QuickSort(int[] a,int left,int right){
        /**version_1:
        if (left < right){
//            int x = new Random().nextInt(right - left + 1);
//            swap(a,right,x + left);
//随机化操作
            int middle = Partition(a,left,right);
            QuickSort(a,left,middle - 1);
            QuickSort(a,middle + 1,right);

        }
         */
        /** version_2*/
        while (left < right){
            int middle = Partition(a,left,right);
            QuickSort(a,left,middle - 1);
            left = middle + 1;
        }
    }
    static int Partition(int[] a,int left,int right){
        int key = a[right];
        int i = left - 1;
        for (int j = left; j < right; j++) {
            if (a[j] < key){
                swap(a,++i,j);
            }
        }
        swap(a,++i,right);
        return i;
    }

    static void swap(int[] a,int left,int right){
        if (left == right)  return;
        int t;
        t = a[left];
        a[left] = a[right];
        a[right] = t;

    }
    public static void main(String[] args) throws IOException{

        initData();
        BufferedReader cin = new BufferedReader(new InputStreamReader(
                new FileInputStream("test.txt")));
        int n = Integer.parseInt(cin.readLine());
        int[][] a = new int[10][n];
        for (int i = 0; i < n; i++) a[0][i] = Integer.parseInt(cin.readLine());
        cin.close();

        for (int i = 1; i < 10; i++)
            for (int j = 0; j < n; j++)     a[i][j] = a[0][j];

        System.out.println("source: " + Arrays.toString(a[0]));
        long start,end;

        start = System.currentTimeMillis();
        BubbleSort(a[1]);
        end = System.currentTimeMillis();
//        System.out.println("\n " + Arrays.toString(a[1]));
        System.out.printf("bubble:%d\n",(end - start));

        start = System.currentTimeMillis();
        SimpleSelectSort(a[2]);
        end = System.currentTimeMillis();
//        System.out.println("\n" + Arrays.toString(a[2]));
        System.out.printf("sim_se: %d\n",(end - start));

        start = System.currentTimeMillis();
        InsertSort(a[3]);
        end = System.currentTimeMillis();
//        System.out.println("\n" + Arrays.toString(a[3]));
        System.out.printf("insert: %d\n",(end - start));

        start = System.currentTimeMillis();
        XiErSort(a[4]);
        end = System.currentTimeMillis();
//        System.out.println("\n" + Arrays.toString(a[4]));
        System.out.printf("xi_er_: %d\n",(end - start));

        start = System.currentTimeMillis();
        HeapSort(a[5]);
        end = System.currentTimeMillis();
//        System.out.println("\n" + Arrays.toString(a[5]));
        System.out.printf("Heap__: %d\n",(end - start));

        start = System.currentTimeMillis();
        MergeSort(a[6],0,a[6].length - 1);
        end = System.currentTimeMillis();
//        System.out.println("\n" + Arrays.toString(a[6]));
        System.out.printf("Merge_: %d\n",(end - start));

        start = System.currentTimeMillis();
        MergeSortNew(a[7]);
        end = System.currentTimeMillis();
//        System.out.println("\n" + Arrays.toString(a[7]));
        System.out.printf("Me_New: %d\n",(end - start));


        start = System.currentTimeMillis();
        QuickSort(a[8],0,a[8].length - 1);
        end = System.currentTimeMillis();
//        System.out.println("\n" + Arrays.toString(a[8]));
        System.out.printf("QuickS: %d\n",(end - start));



        boolean flag = true;
        for (int i = 0; i < a[0].length; i++) {
            if (a[1][i] != a[2][i] || a[1][i] != a[3][i] || a[1][i] !=a[4][i]
                    || a[1][i] != a[5][i] || a[1][i] != a[6][i]
                    || a[1][i] != a[7][i] || a[1][i] != a[8][i]){
                flag = false;
                break;
            }
        }
        System.out.println(flag);

    }
    public static void initData()throws IOException{
       PrintWriter out = new PrintWriter("test.txt");
        out.println(200000);
        for (int i = 0; i < 200000; i++) {
            out.println(new Random().nextInt(100000));
        }
        out.close();
    }
}
