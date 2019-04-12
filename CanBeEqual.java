import javax.script.ScriptEngineManager;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class CanBeEqual {

    static Set<String> сharacters = new HashSet<String>();
    static Set<String> sortNumbers = new HashSet<String>();
    static ArrayList<StringBuilder> withoutBrackets = new ArrayList<StringBuilder>();
    static ArrayList<StringBuilder> withBrackets = new ArrayList<StringBuilder>();

    public static void main(String[] args) throws Exception {
        int[] nums = new int[] {0,2,6,9};
        canBeEqualTo24(nums);
    }

    public static boolean canBeEqualTo24(int[] nums) {

        boolean b=false;
        long result=0;
        final char o = '(';
        final char c = ')';
        String numbers = "";
        for (int n : nums)
            numbers += String.valueOf(n);
        permutation("", numbers);
        characterVariations();

        for (String s : sortNumbers) {
            for (String symb : сharacters) {
                withoutBrackets.add(new StringBuilder().append(s, 0, 1)
                        .append(symb, 0, 1)
                        .append(s, 1, 2)
                        .append(symb, 1, 2)
                        .append(s, 2, 3)
                        .append(symb, 2, 3)
                        .append(s, 3, 4)
                );
            }
        }

        StringBuilder qwe;
        for (StringBuilder strBld : withoutBrackets) {
            qwe = new StringBuilder(strBld);
            withBrackets.add(qwe.insert(7, c).insert(4, o));
            qwe = new StringBuilder(strBld);
            withBrackets.add(qwe.insert(3, c).insert(0, o));
            qwe = new StringBuilder(strBld);
            withBrackets.add(qwe.insert(5, c).insert(0, o));
            qwe = new StringBuilder(strBld);
            withBrackets.add(qwe.insert(7, c).insert(2, o));
            qwe = new StringBuilder(strBld);
            withBrackets.add(qwe.insert(7, c).insert(4, o).insert(3, c).insert(0, o));
            qwe = new StringBuilder(strBld);
            withBrackets.add(qwe.insert(5, c).insert(2, o));
            qwe = new StringBuilder(strBld);
            withBrackets.add(qwe.insert(5, c).insert(3, c).insert(0, o).insert(0, o));
            qwe = new StringBuilder(strBld);
            withBrackets.add(qwe.insert(7, c).insert(7, c).insert(4, o).insert(2, o));
            qwe = new StringBuilder(strBld);
            withBrackets.add(qwe.insert(5, c).insert(5, c).insert(2, o).insert(0, o));
            qwe = new StringBuilder(strBld);
            withBrackets.add(qwe.insert(7, c).insert(5, c).insert(2, o).insert(2, o));
        }

        System.out.println(withBrackets.toString());

        for (StringBuilder stringBuilder : withBrackets) {
            try {
                Object obj = new ScriptEngineManager().getEngineByName("JavaScript").eval(stringBuilder.toString());
                if(obj instanceof  Integer){
                    result = ((Integer)obj).longValue();
                }
                if (obj instanceof Double){
                    result = Math.round(((Double)obj).doubleValue());
                }
                if (result==24) {
                    System.out.println(stringBuilder.toString()+" = 24");
                    b=true;
                    break;
                }
            }
            catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
        System.out.println(b);
        return b;
    }

    private static void permutation(String prefix, String str) {

        int n = str.length();
        if (n == 0) {
            sortNumbers.add(prefix);
        } else {
            for (int i = 0; i < n; i++)
                permutation(prefix + str.charAt(i), str.substring(0, i) + str.substring(i + 1, n));
        }
    }

    private static void permutationСharacters(String prefix, String str) {

        int n = str.length();
        if (n == 0) {
            сharacters.add(prefix);
        } else {
            for (int i = 0; i < n; i++)
                permutationСharacters(prefix + str.charAt(i), str.substring(0, i) + str.substring(i + 1, n));
        }
    }

    private static void characterVariations() {
        final String operators = "+-*/";
        String s = "";
        for (int i = 3; i > 0; i--) {
            for (int n = 3; n >= 0; n--) {
                for (int m = i; m > 0; m--) {
                    s = s + String.valueOf(operators.charAt(n));
                }
            }
        }
        for (int q = 0; q < 21; q++) {
            permutationСharacters("", s.substring(q, q + 3));
        }
    }

}

