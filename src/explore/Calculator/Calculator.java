package explore.Calculator;

import java.util.*;

/**
 * https://github.com/Lee-W/Java-Calculator/blob/master/src/main/java/leew/Cal.java
 */
public class Calculator {
    private static final String[] BINARY_OPERATOR = {"+", "-", "*", "/", "%", "^"};
    private static final Map<String, Integer> OP_PRIORITY;
    static {
        Map<String, Integer> opMap = new HashMap<>();
        opMap.put("^", 2);
        opMap.put("*", 3);
        opMap.put("/", 3);
        opMap.put("%", 3);
        opMap.put("+", 3);
        opMap.put("-", 3);
        OP_PRIORITY = Collections.unmodifiableMap(opMap);
    }

//    public static Double calculate (Deque<String> equation) {
//        List<String> postfixEquation = infixToPostfix(equation);
//
//        if ()
//    }


//    private static List<String> infixToPostfix(Deque<String> equation) {
//
//    }
}