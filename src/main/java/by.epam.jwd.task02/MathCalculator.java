package by.epam.jwd.task02;

/*
 *Однопоточное консольное приложение.
 *Данные в приложение приходят в виде строки, которая содержит натуральные числа,
 *знаки четырех арифметических действий (сложение, вычитание, умножение, деление) и скобки.
 *Вычисляет значение выражения.
 *Код организован виде библиотеки и создан jar-файл для возможности использования кода в другом проекте.
 */

public class MathCalculator {
    public double calculate(String str) {
        MathCalcValidator validator = new MathCalcValidator();
        ExpressionToReversePolishNotation rpn = new ExpressionToReversePolishNotation();
        MathCalcLogic logic = new MathCalcLogic();

        validator.validateStringForCalculator(str);
        double result = logic.calculateRPN(rpn.expressionToRPN(str));

        return result;
    }
}


