package code.jam.y2010africa;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class QA {
    
    private static String solve(int credit, int[] itemPrices) {
        for (int i = 0; i < itemPrices.length-1; i++) {
            for (int j = i+1; j < itemPrices.length; j++) {
                if (i==j) continue;
                if (itemPrices[i]+itemPrices[j]==credit) {
                    return "" + (i+1) + " " + (j+1);
                }
            }
        }
        return "no solution found!";
    }
    
    public static void main(String[] args) throws Exception {
        Scanner s = new Scanner(new File("input.txt"));
        FileWriter w = new FileWriter(new File("output.txt"));
        final int cases = s.nextInt(); s.nextLine();
        for (int i = 0; i < cases; i++) {
            final int credit = s.nextInt(); s.nextLine();
            final int itemsCount = s.nextInt(); s.nextLine();
            final int[] itemPrices = new int[itemsCount];
            for (int j = 0; j < itemsCount; j++) {
                final int price = s.nextInt();
                itemPrices[j] = price;
            }
            final String result = solve(credit, itemPrices);

            if (i>0) w.write("\n");
            w.write("Case #" + (i+1) + ": ");
            w.write(result);
        }
        w.flush();
    }
    
    
}
