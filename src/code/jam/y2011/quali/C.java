package code.jam.y2011.quali;

import code.jam.util.GoogleCodeJamSkeleton;
import java.io.FileWriter;
import java.util.Scanner;

public class C extends GoogleCodeJamSkeleton {

    public static void main(String[] args) throws Exception {
        new C().run();
        System.out.println(5 ^ 6);
    }
    
    public void solveTest(Scanner scanner, FileWriter writer) throws Exception {
        final int cases = scanner.nextInt(); scanner.nextLine();
        for (int i = 0; i < cases; i++) {
            final int candiesCount = scanner.nextInt(); scanner.nextLine();
            final int[] candyWeights = new int[candiesCount];
            for (int j = 0; j < candiesCount; j++) {
                candyWeights[j] = scanner.nextInt();
            }
            if (i>0) writer.write("\n");
            writer.write("Case #" + (i+1) + ": ");
            writer.write(solve(candyWeights));
        }
    }
    
    private String solve(int[] candyWeights) {
        return "lol";
    }
    
}
