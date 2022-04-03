package by.epam.jwd.task02;

import java.util.Stack;

//производит арифметические вычисления (вычисляет результат выражения вида Reverse Polish Notation)
public class MathCalcLogic {

    public Double calculateRPN(String rpn) {
        String operand = new String();
        Stack<Double> stack = new Stack<>();

        for (int i = 0; i < rpn.length(); i++) {
            if (rpn.charAt(i) == ' ') {
                continue;
            }
            if (ExpressionToReversePolishNotation.getPriority(rpn.charAt(i)) == 0) {
                while (rpn.charAt(i) != ' ' && ExpressionToReversePolishNotation.getPriority(rpn.charAt(i)) == 0) {
                    operand += rpn.charAt(i++);
                    if (i == rpn.length()) {
                        break;
                    }
                }
                stack.push(Double.parseDouble(operand));
                operand = new String();
            }
            if (ExpressionToReversePolishNotation.getPriority(rpn.charAt(i)) > 1) {

                double a = stack.pop();
                double b = stack.pop();

                if (rpn.charAt(i) == '+') {
                    stack.push(b + a);
                }
                if (rpn.charAt(i) == '-') {
                    stack.push(b - a);
                }
                if (rpn.charAt(i) == '/') {
                    if (a == 0) {
                        throw new RuntimeException("Divided by zero!");
                    }
                    stack.push(b / a);
                }
                if (rpn.charAt(i) == '*') {
                    stack.push(b * a);
                }
            }
        }
        return stack.pop();
    }

}
