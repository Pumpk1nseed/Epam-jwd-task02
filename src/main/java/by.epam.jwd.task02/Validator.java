package by.epam.jwd.task02;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

//исправить:
// 2.натуральные числа проверка

public class Validator {

    public static boolean validateStringForCalculator(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        if (!checkPresenceOfNumbers(str) || !checkUnknownSymbols(str)
                || !checkBrackets(str) || !checkLogicOfExpression(str)) {
            showErrorOnConsole(str);
            return false;
        } else {
            return true;
        }
    }

    //выводит на консоль тип ошибки проверяемого выражения
    public static void showErrorOnConsole(String str) {
        if (!checkPresenceOfNumbers(str)) {
            printMessage("Please check you expression... It's nothing to calculate!");
        }
        if (!checkUnknownSymbols(str)) {
            printMessage("Please check your expression... Unknown symbol(s) here!");
        }
        if (!checkBrackets(str)) {
            printMessage("Please check brackets!");
        }
        if (!checkLogicOfExpression(str)) {
            printMessage("Please check your expression... Can't calculate!");
        }
    }

    //проверяет строку на наличие цифр
    public static boolean checkPresenceOfNumbers(String str) {
        Pattern number = Pattern.compile("[\\d]");
        Matcher mNumber = number.matcher(str);

        if (!mNumber.find()) {
            return false;
        } else {
            return true;
        }
    }

    //проверяет строку на наличие неизвестных для нашего калькулятора символов
    public static boolean checkUnknownSymbols(String str) {
        Pattern letter = Pattern.compile("[^\\d\\s\\+\\-\\*\\/\\(\\)]");
        Matcher mLetter = letter.matcher(str);

        if (mLetter.find()) {
            return false;
        } else {
            return true;
        }
    }

    //проверяет скобки (количество и порядок)
    public static boolean checkBrackets(String str) {
        int check = 0;
        for (int i = 0; i < str.length(); i++) {
            if (check < 0) {
                return false;
            }
            String symbol = str.substring(i, i + 1);
            if (symbol.equals("(")) {
                check++;
                continue;
            }
            if (symbol.equals(")"))
                check--;
        }
        if (check == 0) {
            return true;
        } else {
            return false;
        }
    }

    //проверет логику выражения (подряд идущие знаки, начало/конец строки)
    public static boolean checkLogicOfExpression(String str) {
        boolean twoSignInRow = findTwoSignInRow(str);
        boolean incorrectStart = findIncorrectStart(str);
        boolean incorrectFinish = findIncorrectFinish(str);

        if (twoSignInRow || incorrectStart || incorrectFinish) {
            return false;
        } else {
            return true;
        }

    }

    //проверяет, являются ли числа в строке натуральными
    public static void checkNaturalNumbers(String str) {

    }

    //находит два подряд стоящих знака арифметических действий либо скобок
    public static boolean findTwoSignInRow(String str) {
        Pattern twoSignInRow = Pattern.compile("[\\+\\-\\*\\/][\\+\\-\\*\\/]");//два знака подряд
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
    public static boolean findIncorrectStart(String str) {
        if (str.charAt(0) == '*' || str.charAt(0) == '/') {
            return true;
        } else {
            return false;
        }
    }

    //находит знак арифметического выражения в конце строки
    public static boolean findIncorrectFinish(String str) {
        if (str.charAt(str.length() - 1) == '*' || str.charAt(str.length() - 1) == '/'
                || str.charAt(str.length() - 1) == '+' || str.charAt(str.length() - 1) == '-') {
            return true;
        } else {
            return false;
        }
    }

    public static void printMessage(String message) {
        System.out.println(message);
    }
}
