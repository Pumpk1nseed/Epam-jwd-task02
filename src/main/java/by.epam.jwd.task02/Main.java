package by.epam.jwd.task02;

/*
 *Создать однопоточное консольное приложение.
 *Общие требования к заданию:
 *Данные в приложение приходят в виде строки, которая содержит натуральные числа,
 *знаки четырех арифметических действий (сложение, вычитание, умножение, деление) и скобки.
 *Вычислите значение выражения.
 */

public class Main {
    public static void main(String[] args) {
        String str = "2/2";
        boolean flag = Validator.validateStringForCalculator(str);
        System.out.println(flag);
        //if (flag){
            //System.out.println(Calculator.ExpressionToRPN(str));
            //System.out.println(Calculator.RPNToAnswer(Calculator.ExpressionToRPN(str)));
        //}
    }

}
