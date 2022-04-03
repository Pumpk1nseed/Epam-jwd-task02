package by.epam.jwd.task02;

/*
 *Однопоточное консольное приложение.
 *Данные в приложение приходят в виде строки, которая содержит натуральные числа,
 *знаки четырех арифметических действий (сложение, вычитание, умножение, деление) и скобки.
 *Приложение вычисляет значение выражения.
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


