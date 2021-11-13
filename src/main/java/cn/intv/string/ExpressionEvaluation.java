package cn.intv.string;

import java.util.Stack;

/**
 * 请写一个整数计算器，支持加减乘三种运算和括号。
 *
 * 数据范围：，保证计算结果始终在整型范围内
 *
 * "((1+3*2+7-9)-8)*2*2" ==> -12
 * "(2*(3-4))*5" ==> -10
 * */
public class ExpressionEvaluation {

    public static void main(String[] args) {
        System.out.println(solve("(2*(3-4))*5")); // -10
        System.out.println(solve("((1+3*2+7-9)-8)*2*2")); // -12
        System.out.println(solve("-11+13")); // 2
    }

    public static int solve(String s) {
        Stack<Integer> stack = new Stack<>();
        char operator = '+', c;
        int preStepNum = 0;
        for (int i = 0; i < s.length(); i++) {
            c = s.charAt(i);

            if (c >= '0' && c <= '9') {
                preStepNum = preStepNum * 10 + (c - '0');
            } else if (c == '(') {
                int cnt = 1; // '('的个数
                for (int j = i + 1; j < s.length(); j++) {
                    if (s.charAt(j) == '(') {
                        cnt++;
                    } else if (s.charAt(j) == ')') {
                        cnt--;
                    }

                    //
                    if (cnt == 0) {
                        preStepNum = solve(s.substring(i + 1, j));
                        i = j;
                        break;
                    }
                }
            }

            //当+-*/时
            if (c == '+' || c == '-' || c == '*' || c == '/' || i == s.length() - 1) {
                switch (operator) {
                    case '+':
                        stack.push(preStepNum);
                        break;
                    case '-':
                        stack.push(-1 * preStepNum);
                        break;
                    case '*':
                        stack.push(stack.pop() * preStepNum);
                        break;
                    case '/':
                        stack.push(stack.pop() / preStepNum);
                }

                preStepNum = 0;
                operator = c;
            }
        } // end for

        int ans = 0;
        while (!stack.isEmpty()) {
            ans += stack.pop();
        }

        return ans;
    }

    private static int doSvc(String s) {

        return 0;
    }
}
