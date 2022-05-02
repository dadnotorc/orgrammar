package leetcode;

import java.util.ArrayList;

/**
 * 65. Valid Number
 * Hard
 *
 * A valid number can be split up into these components (in order):
 * 1. A decimal number or an integer.
 * 2. (Optional) An 'e' or 'E', followed by an integer.
 *
 * A decimal number can be split up into these components (in order):
 * 1. (Optional) A sign character (either '+' or '-').
 * 2. One of the following formats:
 *    a) One or more digits, followed by a dot '.'. 例如 3.
 *    b) One or more digits, followed by a dot '.', followed by one or more digits. 例如 3.1
 *    c) A dot '.', followed by one or more digits. 例如 .1
 *
 * An integer can be split up into these components (in order):
 * 1. (Optional) A sign character (either '+' or '-').
 * 2. One or more digits.
 *
 * For example, all the following are valid numbers:
 * ["2", "0089", "-0.1", "+3.14", "4.", "-.9", "2e10", "-90E3", "3e+7", "+6e-1", "53.5e93", "-123.456e789"],
 *
 * while the following are not valid numbers:
 * ["abc", "1a", "1e", "e3", "99e2.5", "--6", "-+3", "95a54e53"].
 *
 * Given a string s, return true if s is a valid number.
 *
 * Example 1:
 * Input: s = "0"
 * Output: true
 *
 * Example 2:
 * Input: s = "e"
 * Output: false
 *
 * Example 3:
 * Input: s = "."
 * Output: false
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 20
 * s consists of only English letters (both uppercase and lowercase), digits (0-9), plus '+', minus '-', or dot '.'.
 */
public class _65_Valid_Number {

    /*
    使用 if else
     */
    public boolean isNumber(String s) {
        s = s.trim();

        boolean pointSeen = false; // 小数点
        boolean eSeen = false; // 科学计数法 e
        boolean numberSeen = false;
        boolean numberAfterE = true;

        for(int i = 0; i < s.length(); i++) {
            if('0' <= s.charAt(i) && s.charAt(i) <= '9') {
                numberSeen = true;
                numberAfterE = true;
            } else if(s.charAt(i) == '.') {
                if(eSeen || pointSeen) {
                    return false;
                }
                pointSeen = true;
            } else if(s.charAt(i) == 'e' || s.charAt(i) == 'E') {
                if(eSeen || !numberSeen) {
                    return false;
                }
                numberAfterE = false;
                eSeen = true;
            } else if(s.charAt(i) == '-' || s.charAt(i) == '+') {
                if(i != 0 && s.charAt(i - 1) != 'e') {
                    return false;
                }
            } else {
                return false;
            }
        }

        return numberSeen && numberAfterE;
    }


    /*
    使用 design pattern, 而不直接使用 if,  else if 的原因
    1. 不方便以后的扩展, 比如 extend to support hex number, such as 0x12ab; 或者罗马数字 I, II, IV 等
    2. 不方便 debug
     */

    // 参考 https://leetcode.com/problems/valid-number/discuss/23977/A-clean-design-solution-By-using-design-pattern

    public boolean isNumber_design_pattern(String s) {
        NumberValidate nv = new NumberValidator();
        return nv.validate(s);
    }



    interface NumberValidate {
        boolean validate(String s);
    }

    static abstract class NumberValidateTemplate implements NumberValidate {
        public boolean validate(String s) {
            if (isEmptyString(s)) {
                return false;
            }

            String processedString = removeSpaceAndSignChar(s);
            if (isEmptyString(processedString)) {
                return false;
            }

            return doValidate(processedString);
        }

        private boolean isEmptyString(String s) {
            return s == null || s.equals("");
        }

        private String removeSpaceAndSignChar(String s) {
            String str = s.trim();

            if (str.charAt(0) == '+' || str.charAt(0) == '-') {
                str = str.substring(1);
            }

            return str;
        }

        protected abstract boolean doValidate(String s);
    }


    class NumberValidator implements NumberValidate {

        private ArrayList<NumberValidate> validators = new ArrayList<>();

        public NumberValidator() {
            addValidators();
        }

        private void addValidators() {
            validators.add(new IntegerValidate());
            validators.add(new ScientificNotationValidate());
            validators.add(new FloatValidate());

            // 扩展可加入 HexValidate 之类的
        }

        @Override
        public boolean validate(String s) {
            for (NumberValidate nv : validators) {
                if (nv.validate(s)) {
                    return true;
                }
            }

            return false;
        }
    }

    class IntegerValidate extends NumberValidateTemplate {

        @Override
        protected boolean doValidate(String s) {
            // 这里可以考虑检查是否以 0 开头

            for (int i = 0; i < s.length(); i++) {
                if (!Character.isDigit(s.charAt(i))) {
                    return false;
                }
            }

            return true;
        }
    }

    class ScientificNotationValidate extends NumberValidateTemplate {

        @Override
        protected boolean doValidate(String s) {
            if (s.length() < 3) { return false; } // 科学计数法要求 e 前后必须有数字

            s = s.toLowerCase();

            int pos = s.indexOf("e");
            if (pos == -1) { return false; }

            String left = s.substring(0, pos);
            String right = s.substring(pos + 1);

            return validatePart(left, true) && validatePart(right, false);
        }

        private boolean validatePart(String s, boolean isLeft) {
            if (s == null || s.equals("")) { return false; }

            if (s.startsWith(" ") || s.endsWith(" ")) { return false; }

            NumberValidate integerValidate = new IntegerValidate();
            if (!integerValidate.validate(s)) { return false; }

            if (isLeft) {
                NumberValidate floatValidate = new FloatValidate();
                return floatValidate.validate(s);
            }

            return true;
        }
    }

    class FloatValidate extends NumberValidateTemplate {

        @Override
        protected boolean doValidate(String s) {
            if (s.length() < 2) { return false; } // 浮点数要求 . 前后至少有一位数

            int pos = s.indexOf(".");
            if (pos == -1) { return false; }

            String left = s.substring(0, pos);
            String right = s.substring(pos + 1);

            return validatePart(left) && validatePart(right);
        }

        private boolean validatePart(String s) {
            if (s == null || s.equals("")) { return false; }

            if (s.startsWith(" ") || s.endsWith(" ")) { return false; }

            NumberValidate integerValidate = new IntegerValidate();

            return integerValidate.validate(s);
        }
    }






}
