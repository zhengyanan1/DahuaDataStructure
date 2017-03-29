package stack;

import javax.sound.sampled.Line;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * Created by Administrator on 2017/3/17.
 * 计算出一个四则运算中缀表达式的值：
 * 其中：数字均为整数，符号为 +,-,*,/,(,)
 */
public class ArithmeticUsingStack {
    public static void main(String[] args){
        Scanner cin = new Scanner(System.in);
        String test;
        while (cin.hasNext()){
            test = cin.nextLine();
            System.out.println(postfixToResult(infixToPostfix(test)));
        }
    }
    public static String infixToPostfix(String sorce){
        StringBuilder post = new StringBuilder();
        LinkedList<Character> helper = new LinkedList<Character>();
        int num = Integer.MIN_VALUE;
        for (int i = 0; i < sorce.length(); i++) {
            char c = sorce.charAt(i);
            if (c >= '0' && c <= '9'){
                num = 10 * num + (c - '0');
            }
            else{
                if (sorce.charAt(i - 1) >= '0' && sorce.charAt(i - 1) <= '9') {
                    post.append(num + " ");
                    num = 0;
                }
                switch (c){
                    case '+':
                    case '-':
                        while (!helper.isEmpty()){
                            if (helper.getLast() == '+' || helper.getLast() == '-'
                                    || helper.getLast() == '*' || helper.getLast() =='/')
                            post.append(helper.removeLast() + " ");
                            else break;
                        }
                        helper.addLast(c);
                        break;
                    case '*':
                    case '/':
                        while (!helper.isEmpty()){
                            if (helper.getLast() == '*' || helper.getLast() == '/')
                                post.append(helper.removeLast() + " ");
                            else break;
                        }
                        helper.addLast(c);
                        break;
                    case '(':
                        helper.addLast(c);
                        break;
                    case ')':
                        while (helper.getLast() != '('){
                            post.append(helper.removeLast() + " ");
                        }
                        helper.removeLast();
                        break;
                }

            }
        }
        post.append(num + " ");
        while (!helper.isEmpty())   post.append(helper.removeLast() + " ");
        post.substring(0,post.length() - 1);

        System.out.println(post);
        return post.toString();
    }
    public static int postfixToResult(String post){
        String[] data = post.split(" ");
        LinkedList<Integer> helper = new LinkedList<Integer>();
        for (int i = 0; i < data.length; i++) {
            if (data[i].charAt(0) >= '0' && data[i].charAt(0) <= '9'){
                helper.addLast(Integer.parseInt(data[i]));
            }
            else {
                int first,second;
                second = helper.removeLast();
                first = helper.removeLast();
                switch (data[i].charAt(0)){
                    case '+':
                        helper.addLast(first + second);
                        break;
                    case '-':
                        helper.addLast(first - second);
                        break;
                    case '*':
                        helper.addLast(first * second);
                        break;
                    case '/':
                        helper.addLast(first / second);
                        break;
                }
            }
        }

        return helper.removeLast();
    }
}
