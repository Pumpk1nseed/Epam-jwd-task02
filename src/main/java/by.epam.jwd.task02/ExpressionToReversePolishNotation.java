package by.epam.jwd.task02;

import java.util.Stack;

//преобразует выражение пользователя в выражение типа Reverse Polish Notation
public class ExpressionToReversePolishNotation {

    public String expressionToRPN(String str) {
        String current = "";
        Stack<Character> stack = new Stack<>();

        //текущий приоритет символа
        int priority;
        for (int i = 0; i < str.length(); i++) {
            priority = getPriority(str.charAt(i));

            if (priority == 0) {
                current += str.charAt(i);
            }
            if (priority == 1) {
                stack.push(str.charAt(i));
            }
            if (priority > 1) {
                //отделяем числа друг от друга пробелом
                current += ' ';
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
                //удаляем из стека символ '('
                stack.pop();
            }
        }
        while (!stack.empty()) {
            current += stack.pop();
        }
        return current;
    }

    /*
     *определяет приоритет символа арифметического выражения
     *если символ не относится к символам арифметических выражений, возвращает нуль)
     */
    protected static int getPriority(char token) {
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
