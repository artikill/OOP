import java.util.Arrays;

public class Task2 {
    public static String repeat(String word, int n) {
        String newWord = " ";

        for(int i = 0; i < word.length(); ++i) {
            for(int j = 0; j < n; ++j) {
                newWord = newWord + word.charAt(i);
            }
        }

        return newWord;
    }

    public static int differenceMaxMin(int[] mass) {
        int max = -2147483648;
        int min = 2147483647;
        int[] var3 = mass;
        int var4 = mass.length;

        for(int var5 = 0; var5 < var4; ++var5) {
            int value = var3[var5];
            min = Math.min(min, value);
            max = Math.max(max, value);
        }

        return max - min;
    }

    public static boolean isAvgWhole(int[] mass) {
        double sum = 0;
        int[] var3 = mass;
        int var4 = mass.length;

        for(int var5 = 0; var5 < var4; ++var5) {
            int value = var3[var5];
            sum += value;
        }

        return sum % mass.length <1;
    }

    public static int[] cumulativeSum(int[] mass) {
        for(int i = 1; i < mass.length; ++i) {
            mass[i] += mass[i - 1];
        }

        return mass;
    }

    public static int getDecimalPlaces(String number) {
        return number.indexOf(46) != 0 ? number.length() - number.indexOf(46) - 1 : 0;
    }

    public static int Fibonacci(int n) {
        switch(n) {
            case 0:
            case 1:
                return 1;
            default:
                return Fibonacci(n - 2) + Fibonacci(n - 1);
        }
    }

    public static boolean isValid(String index) {
        int buf = 0;

        for(int i = 0; i < index.length(); ++i) {
            if (!Character.isDigit(index.charAt(i))) {
                ++buf;
            } else if (index.charAt(i) == ' ') {
                ++buf;
            } else if (index.length() != 5) {
                ++buf;
            }
        }

        return buf == 0;
    }

    public static boolean isStrangePair(String word1, String word2) {
        return word1.charAt(0) == word2.charAt(word2.length() - 1) && word2.charAt(0) == word1.charAt(word1.length() - 1);
    }

    public static boolean isPrefix(String word, String pref) {
        pref = pref.substring(0, pref.length() - 1);
        return word.startsWith(pref);
    }

    public static boolean isSuffix(String word, String suf) {
        suf = suf.substring(1);
        return word.endsWith(suf);
    }

    public static int boxSeq(int shag) {
        if (shag == 0) {
            return 0;
        } else {
            int count = 0;

            for(int i = 0; i < shag; ++i) {
                if (i % 2 != 0) {
                    --count;
                } else {
                    count += 3;
                }
            }

            return count;
        }
    }
    public static void main(String[] args) {
        System.out.println(repeat("Deck", 4));
        System.out.println(differenceMaxMin(new int[]{10, 4, 1, 4, -10, -50, 32, 21}));
        System.out.println(isAvgWhole(new int[]{1, 5, 6}));
        System.out.println(Arrays.toString(cumulativeSum(new int[]{3, 3, -2, 408, 3, 3})));
        System.out.println(getDecimalPlaces("431114.84112380"));
        System.out.println(Fibonacci(7));
        System.out.println(isValid("15006"));
        System.out.println(isStrangePair("sparkling", "groups"));
        System.out.println(isPrefix("automation", "auto-"));
        System.out.println(isSuffix("arachnophobia", "-phobia"));
        System.out.println(boxSeq(3));
    }
}
