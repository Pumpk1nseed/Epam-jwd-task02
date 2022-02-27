package by.epam.jwd.task02;

import java.util.Stack;

public class MathCalcLogic {

    public double calculateExpression(String expression) {
        String rpn = expressionToRPN(expression);
        double result = rpnToResult(rpn);
        return result;
    }

    public String expressionToRPN(String str) {
        String current = "";
        Stack<Character> stack = new Stack<>();

        int priority; //текущий приоритет символа
        for (int i = 0; i < str.length(); i++) {
            priority = getPriority(str.charAt(i));

            if (priority == 0) {
                current += str.charAt(i);
            }
            if (priority == 1) {
                stack.push(str.charAt(i));
            }
            if (priority > 1) {
                current += ' '; //отделяем числа друг от друга пробелом
                //пока стек не пустой, добавляем в current элемент, пока не встретится символ с приоритетом, меньшим текущего
                while (!stack.empty()) {
                    if (getPriority(stack.peek()) >= priority) {
                        current += stack.pop();
                    } else {
                        break;
                    }
                }
                stack.push(str.charAt(i));
            }

            if (priority == -1) {
                current += ' ';
                //записываем в current все элементы до тех пор, пока не встретится символ открывающейся скобки (приоритет 1)
                while (getPriority(stack.peek()) != 1) {
                    current += stack.pop();
                }
                stack.pop(); //удаляем из стека символ '('
            }
        }
        while (!stack.empty()) {
            current += stack.pop();
        }
        return current;
    }

    public Double rpnToResult(String rpn) {
        String operand = new String();
        Stack<Double> stack = new Stack<>();

        for (int i = 0; i < rpn.length(); i++) {
            if (rpn.charAt(i) == ' ') {
                continue;
            }
            if (getPriority(rpn.charAt(i)) == 0) {
                //записываем числа в операнд
                while (rpn.charAt(i) != ' ' && getPriority(rpn.charAt(i)) == 0) {
                    operand += rpn.charAt(i++);
                    if (i == rpn.length()) {
                        break;
                    }
                }
                stack.push(Double.parseDouble(operand));
                operand = new String();
            }
            if (getPriority(rpn.charAt(i)) > 1) {

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

    //определяет приоритет символа арифметического выражения
    private int getPriority(char token) {
        if (token == '*' || token == '/') {
            return 3;
        } else if (token == '+' || token == '-') {
            return 2;
        } else if (token == '(') {
            return 1;
        } else if (token == ')') {
            return -1;
        } else {
            return 0;
        }
    }
}
