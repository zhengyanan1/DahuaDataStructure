package search;

/**
 * Created by Administrator on 2017/4/1.
 */
public class FibonaqiSearch {
    public static int[] F = new int[]{0,1,1,2,3,5,8,13,21,34};
    static int fibonaqiSearch(int[] a,int length,int key){
        int left = 0,right = length - 1;
        int k = 0;
        while (length > F[k] - 1)   k++;

        for (int i = length; i < F[k] - 1; i++) {
            a[i] = a[right];
        }//高位补齐

//        System.out.println("-------" + (F[k] - 1));
//        int count = 0;
        while (left <= right){
//            count++;
            int mid = left + F[k - 1] - 1;
//            System.out.println(k + ";" + mid);
            if (a[mid] < key){
                left = mid + 1;
                k = k - 2;
            }
            else if (a[mid] > key){
                right = mid - 1;
                k = k - 1;
            }
            else {
//                System.out.println(mid + "********" + count);
                if (mid >= length - 1)  return length - 1;
                else                    return mid;
            }
        }

        return -1;
    }
    public static void main(String[] args){
        int[] a = new int[20];
        a[0] = 0;
        a[1] = 1;
        a[2] = 16;
        a[3] = 24;
        a[4] = 35;
        a[5] = 47;
        a[6] = 59;
        a[7] = 62;
        a[8] = 73;
        a[9] = 88;
        a[10] = 99;

//        for (int i = 0; i < 20; i++) {
//            System.out.println(fibonaqiSearch(a,11,i));
//        }
        System.out.println(fibonaqiSearch(a,11,62));
    }
}
