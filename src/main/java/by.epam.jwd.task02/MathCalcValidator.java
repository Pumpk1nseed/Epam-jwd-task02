package by.epam.jwd.task02;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 *Проверяет входное выражение на соответствие требованиям приложения: строка, которая содержит натуральные числа,
 *знаки четырех арифметических действий (сложение, вычитание, умножение, деление) и скобки.
 */
public class MathCalcValidator {

    public void validateStringForCalculator(String str) {
        checkNull(str);
        checkPresenceOfNumbers(str);
        checkNaturalNumbers(str);
        checkUnknownSymbols(str);
        checkBrackets(str);
        checkLogicOfExpression(str);
    }

    //проверяет строку на null
    public void checkNull(String str) {
        if (str == null) {
            throw new NullPointerException();
        }
    }

    //проверяет строку на наличие цифр
    public void checkPresenceOfNumbers(String str) {
        Pattern number = Pattern.compile("[\\d]");
        Matcher mNumber = number.matcher(str);

        if (!mNumber.find()) {
            throw new RuntimeException("It's nothing to calculate!");
        }
    }

    //проверяет, являются ли числа в строке натуральными
    public void checkNaturalNumbers(String str) {
        boolean doubleNumbers = findDouble(str);
        boolean negativeNumbers = findNegativeNumbers(str);
        boolean zero = findZero(str);

        if (doubleNumbers || negativeNumbers || zero) {
            throw new RuntimeException("Numbers must be natural!");
        }
    }

    //находит вещественные числа
    public boolean findDouble(String str) {
        Pattern doubleNumbers = Pattern.compile("[\\d][\\.\\,][\\d]");
        Matcher mDoubleNumbers = doubleNumbers.matcher(str);

        if (mDoubleNumbers.find()) {
            return true;
        } else {
            return false;
        }
    }

    //находит отрицательные числа
    public boolean findNegativeNumbers(String str) {
        Pattern negativeNumbers = Pattern.compile(".[^\\d][\\-][\\d]");
        Matcher mNegativeNumbers = negativeNumbers.matcher(str);

        if (mNegativeNumbers.find() || (str.charAt(0) == '-' && Character.isDigit(str.charAt(1)))) {
            return true;
        } else {
            return false;
        }
    }

    //находит числа, равные нулю
    public boolean findZero(String str) {
        Pattern number = Pattern.compile("[\\d]+");
        Matcher mNumber = number.matcher(str);

        while (mNumber.find()) {
            if (Integer.parseInt(mNumber.group()) == 0) {
                return true;
            }
        }
        return false;
    }

    //проверяет строку на наличие неизвестных для нашего калькулятора символов
    public void checkUnknownSymbols(String str) {
        Pattern unknown = Pattern.compile("[^\\d\\s\\+\\-\\*\\/\\(\\)]");
        Matcher mUnknown = unknown.matcher(str);

        if (mUnknown.find()) {
            throw new RuntimeException("Unknown symbol(s) here!");
        }
    }

    //проверяет скобки (количество и порядок)
    public void checkBrackets(String str) {
        int check = 0;
        for (int i = 0; i < str.length(); i++) {
            if (check < 0) {
                throw new RuntimeException("Check brackets!");
            }
            String symbol = str.substring(i, i + 1);
            if (symbol.equals("(")) {
                check++;
                continue;
            }
            if (symbol.equals(")"))
                check--;
        }
        if (check != 0) {
            throw new RuntimeException("Check brackets!");
        }
    }

    //проверет логику выражения (подряд идущие знаки, корректность знаков в начале/конце строки)
    public void checkLogicOfExpression(String str) {
        boolean twoSignInRow = findTwoSignInRow(str);
        boolean incorrectStart = findIncorrectStart(str);
        boolean incorrectFinish = findIncorrectFinish(str);

        if (twoSignInRow || incorrectStart || incorrectFinish) {
            throw new RuntimeException("Please check the signs of arithmetic operations... Can't calculate!");
        }
    }

    //находит два подряд стоящих знака арифметических действий либо скобок
    public boolean findTwoSignInRow(String str) {
        Pattern twoSignInRow = Pattern.compile("[\\+\\-\\*\\/\\(][\\+\\-\\*\\/]");
        Pattern twoBracketsInRow = Pattern.compile("[(][)]");//две скобки подряд

        Matcher mTwoSignInRow = twoSignInRow.matcher(str);
        Matcher mTwoBracketsInRow = twoBracketsInRow.matcher(str);

        if (mTwoSignInRow.find() || mTwoBracketsInRow.find()) {
            return true;
        } else {
            return false;
        }
    }

    //находит знак арифметического выражения в начале строки
    public boolean findIncorrectStart(String str) {
        char startSymbol = str.charAt(0);
        if (startSymbol == '*' || startSymbol == '/' || startSymbol == '-' || startSymbol == '+') {
            return true;
        } else {
            return false;
        }
    }

    //находит знак арифметического выражения в конце строки
    public boolean findIncorrectFinish(String str) {
        char endSymbol = str.charAt(str.length() - 1);
        if (endSymbol == '*' || endSymbol == '/' || endSymbol == '+' || endSymbol == '-') {
            return true;
        } else {
            return false;
        }
    }
}