import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Task4 {
    public Task4() {
    }

    public static String Bessy(int n, int k, String txt) {
        String[] text = txt.split(" ");
        txt = "";
        String finaltxt = "";

        for(int i = 0; i < n; ++i) {
            if (txt.length() + text[i].length() > k) {
                String var10000 = finaltxt.trim();
                finaltxt = var10000 + "\r\n" + text[i] + " ";
                txt = text[i];
            } else {
                finaltxt = finaltxt + text[i] + " ";
                txt = txt + text[i];
            }
        }

        return finaltxt.trim();
    }

    public static String[] split(String str) {
        List<String> list = new ArrayList();
        int f = 0;
        int i = 0;

        while(str.length() > 0) {
            if (str.charAt(i) == '(') {
                ++f;
            } else {
                --f;
            }

            if (f == 0) {
                list.add(str.substring(0, i + 1));
                str = str.substring(i + 1);
                i = 0;
            } else {
                ++i;
            }
        }

        return (String[])list.toArray(new String[list.size()]);
    }

    public static String toCamelCase(String str) {
        for(int i = 1; i < str.length(); ++i) {
            if (str.charAt(i) == '_') {
                str = str.substring(0, i) + str.substring(i + 1, i + 2).toUpperCase() + str.substring(i + 2, str.length());
            }
        }

        return str;
    }

    public static String toSnakeCase(String str) {
        return str.replaceAll("([A-Z])", "_$0").toLowerCase();
    }

    public static String overTime(double[] work) {
        double sum = 0.0D;
        if (17.0D - work[0] >= 0.0D) {
            sum += (17.0D - work[0]) * work[2];
        }

        if (work[1] - 17.0D >= 0.0D) {
            sum += (work[1] - 17.0D) * work[2] * work[3];
        }

        return "$" + String.valueOf(sum);
    }

    public static String BMI(String[] fat) {
        double ves = Double.parseDouble(fat[0].split(" ")[0]);
        double rost = Double.parseDouble(fat[1].split(" ")[0]);
        String out = " ";
        if (fat[0].contains("pounds")) {
            ves *= 0.45D;
        }

        if (fat[1].contains("inches")) {
            rost *= 0.0254D;
        }

        double BMI = (double)Math.round(ves / (rost * rost) * 10.0D) / 10.0D;
        if (BMI < 18.5D) {
            out = BMI + " Underweight";
        }

        if (BMI >= 18.5D && BMI <= 24.9D) {
            out = BMI + " Normal weight";
        }

        if (BMI > 25.0D) {
            out = BMI + " Overweight";
        }

        return out;
    }

    public static int bugger(int num) {
        int count;
        for(count = 0; num > 9; ++count) {
            int chnum;
            for(chnum = 1; num > 0; num /= 10) {
                chnum *= num % 10;
            }

            num = chnum;
        }

        return count;
    }

    public static String toStarShorthand(String str) {
        int count = 1;
        char let = str.charAt(0);
        String newStr = "";

        for(int i = 0; i < str.length(); ++i) {
            if (str.charAt(i) != let) {
                if (count != 1) {
                    newStr = newStr + let + "*" + count;
                } else {
                    newStr = newStr + let;
                }

                let = str.charAt(i);
                count = 1;
            } else {
                ++count;
            }
        }

        if (count != 1) {
            newStr = newStr + let + "*" + count;
        } else {
            newStr = newStr + let;
        }

        return newStr;
    }

    public static boolean doesRhyme(String str1, String str2) {
        str1 = str1.substring(str1.lastIndexOf(" ") + 1);
        str2 = str2.substring(str2.lastIndexOf(" ") + 1);
        String let = "aeiouyAEIOUY";
        String res1 = "";
        String res2 = "";

        int i;
        for(i = 0; i < str1.length(); ++i) {
            if (let.indexOf(str1.charAt(i)) != -1) {
                res1 = res1 + str1.charAt(i);
            }
        }

        for(i = 0; i < str2.length(); ++i) {
            if (let.indexOf(str2.charAt(i)) != -1) {
                res2 = res2 + str2.charAt(i);
            }
        }

        if (res1.toLowerCase().equals(res2.toLowerCase())) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean trouble(long a, long b) {
        String aa = Long.toString(a);
        String bb = Long.toString(b);
        int num = 0;

        int i;
        for(i = 2; i < aa.length(); ++i) {
            if (aa.charAt(i) == aa.charAt(i - 1) && aa.charAt(i) == aa.charAt(i - 2)) {
                num = aa.charAt(i);
            }
        }

        for(i = 0; i < bb.length(); ++i) {
            if (bb.charAt(i) == num && bb.charAt(i + 1) == num) {
                return true;
            }
        }

        return false;
    }

    public static int countUniqueBooks(String str, char c) {
        Map<Character, Integer> values = new HashMap();
        boolean start = true;

        for(int i = 0; i < str.length(); ++i) {
            if (str.charAt(i) == c && start) {
                ++i;

                for(; str.charAt(i) != c; ++i) {
                    Integer n = (Integer)values.get(str.charAt(i));
                    if (n == null) {
                        values.put(str.charAt(i), 1);
                    } else {
                        values.put(str.charAt(i), n + 1);
                    }
                }

                start = false;
            }

            if (str.charAt(i) == c) {
                start = true;
            }
        }

        return values.size();
    }

    public static void main(String[] args) {
        System.out.println("№1 " + Bessy(3, 2, "это международный договор "));
        System.out.println("№2 " + Arrays.toString(split("((()))(())()()(()())")));
        System.out.println("№3.1 " + toCamelCase("is_modal_open"));
        System.out.println("№3.2 " + toSnakeCase("isModalOpen"));
        double[] var10001 = new double[]{16.0D, 18.0D, 30.0D, 1.8D};
        System.out.println("№4 " + overTime(var10001));
        PrintStream var10000 = System.out;
        String[] var1 = new String[]{"55 kilos", "1.65 meters"};
        var10000.println("№5 " + BMI(var1));
        System.out.println("№6 " + bugger(999));
        System.out.println("№7 " + toStarShorthand("abbccc"));
        System.out.println("№8 " + doesRhyme("Sam I am!", "Green eggs and ham."));
        System.out.println("№9 " + trouble(666789L, 12345667L));
        System.out.println("№10 " + countUniqueBooks("AZYWABBCATTTA", 'A'));
    }
}

