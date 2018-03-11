package code.jam.y2010africa;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class QB {
    
    private static String reverse(String line) {
        StringBuilder result = new StringBuilder(line.length());
        String[] words = line.split("\\s");
        for (int i = 0; i < words.length; i++) {
            if (i>0) result.insert(0, ' ');
            result.insert(0, words[i]);
        }
        return result.toString();
    }
    
    public static void main(String[] args) throws Exception {
        Scanner s = new Scanner(new File("input.txt"));
        FileWriter w = new FileWriter(new File("output.txt"));
        final int cases = s.nextInt(); s.nextLine();
        for (int i = 0; i < cases; i++) {
            final String line = s.nextLine();
            final String result = reverse(line);
            if (i>0) w.write("\n");
            w.write("Case #" + (i+1) + ": ");
            w.write(result);
        }
        w.flush();
    }
    
    
}
