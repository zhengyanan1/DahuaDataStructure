/**
 * Created by Administrator on 2017/3/15.
 */
public class Test {
    public static void main(String[] args) {
        System.out.println("Test.main():Hello World!!!");


        StringBuilder sb = new StringBuilder("abcabcabc");
        System.out.println(sb.indexOf("abc",0));
        System.out.println(sb.substring(0,2));
    }
}
