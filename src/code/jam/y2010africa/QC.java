package code.jam.y2010africa;

import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class QC {
    
    private static final Map KEYS = new HashMap();
    static {
        KEYS.put("a", "2");
        KEYS.put("b", "22");
        KEYS.put("c", "222");
        KEYS.put("d", "3");
        KEYS.put("e", "33");
        KEYS.put("f", "333");
        KEYS.put("g", "4");
        KEYS.put("h", "44");
        KEYS.put("i", "444");
        KEYS.put("j", "5");
        KEYS.put("k", "55");
        KEYS.put("l", "555");
        KEYS.put("m", "6");
        KEYS.put("n", "66");
        KEYS.put("o", "666");
        KEYS.put("p", "7");
        KEYS.put("q", "77");
        KEYS.put("r", "777");
        KEYS.put("s", "7777");
        KEYS.put("t", "8");
        KEYS.put("u", "88");
        KEYS.put("v", "888");
        KEYS.put("w", "9");
        KEYS.put("x", "99");
        KEYS.put("y", "999");
        KEYS.put("z", "9999");
        KEYS.put(" ", "0");
    }
    
    private static String solve(String text) {
        StringBuilder result = new StringBuilder();
        String lastKeys = "";
        for (int i = 0; i < text.length(); i++) {
            final char c = text.charAt(i);
            final String keys = (String)KEYS.get(Character.toString(c));
            if (i>0 && keys.charAt(0)==lastKeys.charAt(0)) result.append(' ');
            result.append(keys);
            lastKeys = keys;
        }
        return result.toString();
    }
    
    public static void main(String[] args) throws Exception {
        Scanner s = new Scanner(new File("C-large-practice.in"));
        FileWriter w = new FileWriter(new File("C-large-practice.out"));
        final int cases = s.nextInt(); s.nextLine();
        for (int i = 0; i < cases; i++) {
            final String text = s.nextLine();
            final String result = solve(text);

            if (i>0) w.write("\n");
            w.write("Case #" + (i+1) + ": ");
            w.write(result);
        }
        w.flush();
    }
    
    
}
