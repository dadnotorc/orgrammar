/*
Easy
#Simulation
 */
package lintcode;

/**
 * 1730 · Spreadsheet Notation Conversion
 *
 * You need to simulate a spreadsheet, like the naming convention of ExcelExcel, to convert the index into a string.
 * There is a integer number index, let 1 = A, 2 = B ... 26 = Z, 27 = AA, ... 702 = ZZ, the max number of every row is 702.
 * When index > 702, you need to line break, and restart to numbering, like 703 = A, 705 = C.
 * For every 702702 numbers, you need to line break, and restart to numbering.
 * The string after converting for index is the rowindex add its numbering.
 * Finally 1 = 1A, 2 = 1B, 3 = 1C, 26 = 1Z, 27 = 1AA, 702 = 1ZZ, 705 = 2C, 1404 = 2ZZ。
 * Such as 705 is at the 2th row，numbered CC，so after convert it is 2C。
 *
 * 1 <= index <= 10 ^ 18
 *
 * Sample Input1: 3
 * Sample Output1: 1C
 *
 * Sample Input2: 28
 * Sample Output2: 1AB
 */
public class _1730_SpreadsheetNotationConversion {

    /**
     * int/long 转 char 要注意
     * 1. 前面加 (char)
     * 2. 后面的运算要用 () 包裹住
     */
    public String convert(long index) {
        index -= 1; // 换成 0-based index
        StringBuilder sb = new StringBuilder();

        // 计算 row index
        long rowIndex = 1 + index / 702; // +1 是因为第一排从 1 开始, index/702 可以等于 0
        sb.append(rowIndex);

        // 计算 第一个字母的偏移
        index %= 702;
        long first = index / 26;
        if (first != 0) { // == 0 就跳过不加
            sb.append((char) ('A' - 1 + first)); // 这里要 -1, 是因为从 空 开始, 下一位才是 A, 最后到 Z
        }

        // 计算 第二个字母的偏移
        index %= 26;
        sb.append((char) ('A' + index)); // 这里不能 -1, 因为是从 A 开始到 Z

        return sb.toString();
    }
}
