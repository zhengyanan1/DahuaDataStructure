package search;

/**
 * Created by Administrator on 2017/4/1.
 */
public class InsertSearch {
    static int insertSearch(int[] a,int key){
        int count = 0;
        int left = 0,right = a.length - 1;
        while (left <= right){
            count++;
            double factor = (key - a[left])/(double)(a[right] - a[left]);
            int middle = left + (int) (factor * (right - left)) ;
            if (a[middle] == key)       {
//                System.out.println(count+"***");
                return middle;
            }
            else if (a[middle] > key)   right = middle - 1;
            else                        left = middle + 1;
        }
//        System.out.println(count+"***");
        return -1;
    }
    public static void main(String[] args){
        int[] a = new int[]{1,2,3,4,5,6,7,8,9,12,15,20,25};
        for (int i = 0; i < 20; i++) {
            System.out.println(insertSearch(a,i));
        }
    }
}
