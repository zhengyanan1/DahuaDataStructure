import java.math.BigInteger;

/**
 * Created by Administrator on 2017/3/15.
 */
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}
public class Test {
    public static void change(TreeNode t){
        t = new TreeNode(10);
    }
    public static void main(String[] args) {
//        System.out.println("Test.main():Hello World!!!");
//
//
//        StringBuilder sb = new StringBuilder("abcabcabc");
//        System.out.println(sb.indexOf("abc",0));
//        System.out.println(sb.substring(0,2));

        TreeNode a = new TreeNode(1);
        change(a);
        System.out.println(a.val);
    }
}
