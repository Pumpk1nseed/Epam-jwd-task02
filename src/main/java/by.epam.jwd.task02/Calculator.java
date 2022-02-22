package by.epam.jwd.task02;

//https://www.youtube.com/watch?v=6AMp0zH7x7M

import java.util.Stack;

public class Calculator {

    public static String ExpressionToRPN(String expression) {
        String current = "";
        Stack<Character> stack = new Stack<>();

        int priority; //текущий приоритет нашего символа
        for (int i = 0; i < expression.length(); i++) {
            priority = getPriority(expression.charAt(i));

            if (priority == 0) {
                current += expression.charAt(i);
            }
            if (priority == 1) {
                stack.push(expression.charAt(i));
            }
            if (priority > 1) {
                current += ' '; // если пробела не будет, то наш RPN будет передавать слитную строку, чтобы отделять 22 от 222
                //пока стек не пустой, запихиваем в current элемент, пока не встретится символ с приоритетом, меньшим текущего
                while (!stack.empty()) {
                    //берем их стэка наш верхний символ
                    if (getPriority(stack.peek()) >= priority) {
                        current += stack.pop(); // pop передает в current верхний элемент и одновременно удаляет его из стека
                    } else {
                        break;
                    }
                }
                stack.push(expression.charAt(i));
            }

            if(priority == -1){
                current += ' ';
                /*проходимся по самому стеку и выкидываем в current все элементы до тех пор,
                 *пока не встреится символ открывающейся скобки (приоритет 1)*/
                while(getPriority(stack.peek()) != 1) {
                    current += stack.pop();
                }
                stack.pop(); //удаляем из стека символ '('
            }
        }
        while(!stack.empty()){
            current += stack.pop();
        }
        return current;
    }

    //предусмотреть деление на нуль
    public static Double RPNToAnswer(String rpn) {
        String operand = new String();//если будем использовать большие числа, нам нужно будет проходиться по строке в поисках пробела (и пока не найдет пробле, это будет число)
        Stack<Double> stack = new Stack<>();

        for (int i = 0; i < rpn.length(); i++){
            if(getPriority(rpn.charAt(i)) == ' '){
                continue;
            }
            if (getPriority(rpn.charAt(i)) == 0) {
                //слизать число и засунуть в операнд
                while (rpn.charAt(i) != ' ' && getPriority(rpn.charAt(i)) == 0) {
                    operand += rpn.charAt(i++);
                    if (i == rpn.length()) {
                        break;
                    }
                    stack.push(Double.parseDouble(operand));
                    operand = new String();
                }
            }
                if(getPriority(rpn.charAt(i)) > 1) {
                    //берем 2 верхних числа
                    double a = stack.pop();
                    double b = stack.pop();

                    if(rpn.charAt(i) == '+'){
                        stack.push(b+a);
                    }
                    if(rpn.charAt(i) == '-'){
                        stack.push(b-a);
                    }
                    if(rpn.charAt(i) == '/'){
                        stack.push(b/a);
                    }
                    if(rpn.charAt(i) == '*'){
                        stack.push(b*a);
                    }
            }
        }
        return stack.pop();
    }

    //определяет приоритет символа арифе=метического выражения
    private static int getPriority(char token) {
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
