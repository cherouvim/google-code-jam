package code.jam.y2010africa;

import java.io.File;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.Scanner;

/*
 * http://code.google.com/codejam/contest/dashboard?c=438101#s=p2
 */
public class OC {
    
    private static String solve(int shouldPass, long[] passed) {
        assert shouldPass <= passed.length;
        long maxPasses = 0;
        while(true) {
            Arrays.sort(passed);
            if (passed[passed.length - shouldPass]==0) {
                return "" + maxPasses;
            }
            long passes = passed[passed.length - shouldPass]/shouldPass;
            if (passes==0) {
                passes=1;
            }
            for (int i = 0; i < shouldPass; i++) {
                passed[passed.length-1-i]-=passes;
            }
            maxPasses+=passes;
        }
    }
    
    private static void solveTest(String in, String out) throws Exception {
        Scanner s = new Scanner(new File(in));
        FileWriter w = new FileWriter(new File(out));
        final int cases = s.nextInt(); s.nextLine();
        for (int i = 0; i < cases; i++) {
            final int tests = s.nextInt();
            final int shouldPass = s.nextInt();
            final long[] passed = new long[tests];
            for (int j = 0; j < tests; j++) {
                passed[j] = s.nextLong();
            }
            s.nextLine();
            
            final String result = solve(shouldPass, passed);

            if (i>0) w.write("\n");
            w.write("Case #" + (i+1) + ": ");
            w.write(result);
        }
        w.flush();
    }
    
    private static void solveTests(String testName) throws Exception {
        solveTest(testName + "small.in", testName + "small.out");
        solveTest(testName + "large.in", testName + "large.out");
    }
    
    public static void main(String[] args) throws Exception {
        solveTests(getClassName());
    }
    
    private static String getClassName() {
        return new Object() {}.getClass().getEnclosingClass().getSimpleName(); // returns "Foo" for Foo.class
    }

}
