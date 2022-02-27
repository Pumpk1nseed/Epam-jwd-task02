package by.epam.jwd.task02;

/*
 *Создать однопоточное консольное приложение.
 *Общие требования к заданию:
 *Данные в приложение приходят в виде строки, которая содержит натуральные числа,
 *знаки четырех арифметических действий (сложение, вычитание, умножение, деление) и скобки.
 *Вычислите значение выражения.
 */

public class MathCalculator {
    public double calculate(String str) {
        MathCalcValidator validator = new MathCalcValidator();
        MathCalcLogic logic = new MathCalcLogic();

        validator.validateStringForCalculator(str);
        double result = logic.calculateExpression(str);

        return result;
    }
}


