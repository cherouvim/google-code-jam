package code.jam.y2016.round1b;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class A2016AB {

    public static void main(String[] args) throws Exception {
        new A2016AB().solveTests("A");
    }
    
    private void solveTests(String testName) throws Exception {
        solveTest(testName + "-test.in", testName + "-test.out");
        solveTest(testName + "-small-attempt0.in", testName + "-small-attempt0.out");
        solveTest(testName + "-small-attempt1.in", testName + "-small-attempt1.out");
        solveTest(testName + "-small-attempt2.in", testName + "-small-attempt2.out");
        solveTest(testName + "-large.in", testName + "-large.out");
    }
    
    private void solveTest(String in, String out) throws Exception {
        final String filename = getClass().getResource(in).getFile().replaceAll("%20", " ");
        File file = new File(filename);
        Scanner scanner = new Scanner(file);
        FileWriter writter = new FileWriter(new File(file.getParent(), out));
        System.out.println("solving " + in);
        solveTest(scanner, writter);
        writter.flush();
    }
    
    public void solveTest(Scanner scanner, FileWriter writer) throws Exception {
        final int cases = scanner.nextInt(); scanner.nextLine();
        for (int i = 0; i < cases; i++) {
            writer.write("Case #" + (i+1) + ": ");
            writer.write(solveFor(scanner.nextLine()));
            writer.write("\n");
        }
    }
    
    private static final class Solver {
        private char digit;
        private char finder;
        private String lettersForRemoval;
        private Solver(char digit, char finder, String remover) {
            this.digit = digit;
            this.finder = finder;
            this.lettersForRemoval = remover;
        }
    }
    
    private long count(String haystack, char needle) {
        long count = 0;
        for(int i = 0, length = haystack.length(); i < length; i++) {
            if(haystack.charAt(i) == needle) {
                count++;
            }
        }
        return count;
    }
    
    private String removeOnce(String haystack, String needle) {
        for (int i = 0, length = needle.length(); i < length; i++) {
            haystack = haystack.replaceFirst(String.valueOf(needle.charAt(i)), "");
        }
        return haystack;
    }
    
    private static final Solver[] solvers = new Solver[] {
        new Solver('0', 'Z', "ZERO"),
        new Solver('6', 'X', "SIX"),
        new Solver('2', 'W', "TWO"),
        new Solver('7', 'S', "SEVEN"),
        new Solver('5', 'V', "FIVE"),
        new Solver('8', 'G', "EIGHT"),
        new Solver('3', 'T', "THREE"),
        new Solver('4', 'R', "FOUR"),
        new Solver('1', 'O', "ONE"),
        new Solver('9', 'I', "NINE")
    };
    
    private String solveFor(String number) {
        final List resultNumbers = new ArrayList();
        for (int i = 0; i < solvers.length; i++) {
            final Solver solver = solvers[i];
            final long count = count(number, solver.finder);
            if (count>0) {
                for (int j = 0; j < count; j++) {
                    resultNumbers.add(String.valueOf(solver.digit));
                    number = removeOnce(number, solver.lettersForRemoval);
                }
            }
        }
        Collections.sort(resultNumbers);
        StringBuilder result = new StringBuilder();
        for (Iterator iterator = resultNumbers.iterator(); iterator.hasNext();) {
            String num = (String)iterator.next();
            result.append(num);
        }
        System.out.println(result);
        return result.toString();
    }
    
}
