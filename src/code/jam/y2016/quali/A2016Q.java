package code.jam.y2016.quali;

import java.io.File;
import java.io.FileWriter;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class A2016Q {

    public static void main(String[] args) throws Exception {
        new A2016Q().solveTests("A");
    }
    
    private void solveTests(String testName) throws Exception {
        solveTest(testName + "-small-attempt0.in", testName + "-small-attempt0.out");
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
        final int cases = scanner.nextInt();
        for (int i = 0; i < cases; i++) {
            writer.write("Case #" + (i+1) + ": ");
            writer.write(solveFor(scanner.nextLong()) + "\n");
        }
    }
    
    private String solveFor(long countNumber) {
        long multiplier = 1;
        long lastNumber = -1;
        final Set seenNumbers = new HashSet();
        for(;;multiplier++) {
            long number = countNumber * multiplier;
            String numberAsString = Long.toString(number);
            for(int j=0, length=numberAsString.length(); j<length; j++) {
                seenNumbers.add(new Character(numberAsString.charAt(j)));
            }
            if (seenNumbers.size()==10) return Long.toString(number);
            if (lastNumber==number) return "INSOMNIA";
            lastNumber = number;
        }
    }
    
}
