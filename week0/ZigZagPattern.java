package week0;

public class ZigZagPattern {
    public static void printZigZagPattern(String s, int numRows) {
        if (numRows <= 1) {
            System.out.println(s);
            return;
        }

        StringBuilder[] rows = new StringBuilder[numRows];
        for (int i = 0; i < numRows; i++) {
            rows[i] = new StringBuilder();
        }

        int row = 0;
        boolean down = true;

        for (char c : s.toCharArray()) {
            rows[row].append(c);
            if (row == 0) {
                down = true;
            } else if (row == numRows - 1) {
                down = false;
            }
            row += down ? 1 : -1;
        }

        for (StringBuilder stringBuilder : rows) {
            System.out.println(stringBuilder);
        }
    }

    public static void main(String[] args) {
        printZigZagPattern("ZOHOOISHIRING", 3);
    }
}
