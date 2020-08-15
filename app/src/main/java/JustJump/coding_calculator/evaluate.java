package JustJump.coding_calculator;

import java.util.ArrayList;
import java.util.List;

public class evaluate
{
    public static String evaluate(String expression) {
        char[] tokens = expression.toCharArray();
        List<String> list = new ArrayList<>();

        String s = "";

        String operator = "";
        String firstNum = "";
        String secondNum = "";

        boolean operationOnQueue = false;

        for (int i = 0; i < tokens.length; i++) {
            if (Character.isDigit(tokens[i])) {
                s += Character.toString(tokens[i]);
            } else {
                list.add(s);
                list.add(Character.toString(tokens[i]));

                if (operationOnQueue) {
                    operationOnQueue = false;
                    secondNum = s;

                    list.set(list.lastIndexOf(firstNum), eval(firstNum, operator, secondNum));
                    list.remove(list.lastIndexOf(operator));
                    list.remove(list.lastIndexOf(secondNum));
                }

                if (tokens[i] == '*' || tokens[i] == '/') {
                    operationOnQueue = true;

                    operator = Character.toString(tokens[i]);
                    firstNum = list.get(list.lastIndexOf(operator) - 1);
                }

                s = "";
            }

            if (i == tokens.length - 1 && s.length() > 0) {
                list.add(s);

                if (list.get(list.size() - 2).equals("*") || list.get(list.size() - 2).equals("/")) {
                    firstNum = list.get(list.size() - 3);
                    operator = list.get(list.size() - 2);
                    secondNum = list.get(list.size() - 1);

                    list.set(list.size() - 3, eval(firstNum, operator, secondNum));
                    list.remove(list.size() - 2);
                    list.remove(list.size() - 1);
                }
            }
        }


        while (list.size() > 1) {
            firstNum = list.get(0);
            operator = list.get(1);
            secondNum = list.get(2);

            list.set(0, eval(firstNum, operator, secondNum));
            list.remove(2);
            list.remove(1);
        }

        return list.get(0);
    }

    public static String eval(String a, String operator, String b) {
        double r = 0;

        switch (operator) {
            case "/":
                r += Double.parseDouble(a) / Double.parseDouble(b);
                break;
            case "*":
                r += Double.parseDouble(a) * Double.parseDouble(b);
                break;
            case "-":
                r += Double.parseDouble(a) - Double.parseDouble(b);
                break;
            case "+":
                r += Double.parseDouble(a) + Double.parseDouble(b);
                break;
        }

        return Double.toString(r);
    }
}
