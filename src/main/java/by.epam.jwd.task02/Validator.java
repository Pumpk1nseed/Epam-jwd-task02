package by.epam.jwd.task02;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {

    public static boolean isCorrect = true;

    public static boolean validateStringForCalculator(String str) {

        checkPresenceOfNumbers(str);
        checkUnknownSymbols(str);
        checkBrackets(str);
        checkLogicOfExpression(str);
        //checkNaturalNumbers(str);

        return isCorrect;
    }

    public static void checkPresenceOfNumbers(String str) {
        Pattern number = Pattern.compile("[\\d]");
        Matcher mNumber = number.matcher(str);

        if (str.isEmpty() || !mNumber.find()) {
            System.out.println("Please check you expression... It's nothing to calculate!");
            isCorrect = false;
        }
    }

    public static void checkUnknownSymbols(String str) {
        Pattern letter = Pattern.compile("[^\\d\\s\\+\\-\\*\\/\\(\\)]");
        Matcher mLetter = letter.matcher(str);

        if (mLetter.find()) {
            System.out.println("Please check your expression... Unknown symbol(s) here!");
            isCorrect = false;
        }
    }

    //как предусмотреть проверку )(?
    public static void checkBrackets(String str) {
        if (findNumOfSymbol(str, '(') != findNumOfSymbol(str, ')')) {
            System.out.println("Please check brackets!");
            isCorrect = false;
        }
    }

    //беда с end
    public static void checkLogicOfExpression(String str) {
        Pattern twoSignInRow = Pattern.compile("[\\+\\-\\*\\/\\(\\)][\\+\\-\\*\\/\\(\\)]"); //два знака подряд
        //Pattern start = Pattern.compile("//^\\*\\/\\)"); //знак, отличный от '+' и '-' в начале выражения
        //Pattern end = Pattern.compile("\\+\\-\\/\\*\\(/$"); //знак, отличный от ')' в конце выражения

        Matcher mTwoSignInRow = twoSignInRow.matcher(str);
       // Matcher mStart = start.matcher(str);
        //Matcher mEnd = end.matcher(str);

        if (mTwoSignInRow.find()) {
            System.out.println("Please check your expression... Can't calculate!");
            isCorrect = false;
        }
    }

    public static void checkNaturalNumbers(String str) {

    }

    public static int findNumOfSymbol(String str, char symbol) {
        int result = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == symbol) {
                result++;
            }
        }
        return result;
    }
}
