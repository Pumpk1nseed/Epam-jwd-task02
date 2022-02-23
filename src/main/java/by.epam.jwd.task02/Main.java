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
        String str = "5/0";
        boolean isCorrect = Validator.validateStringForCalculator(str);

        if (isCorrect) {
            System.out.println(Calculator.calculateExpression(str));
        }

    }
}


