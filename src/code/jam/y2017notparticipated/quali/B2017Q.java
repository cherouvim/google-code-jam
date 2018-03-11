package code.jam.y2017notparticipated.quali;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class B2017Q {

    public static void main(String[] args) throws Exception {
        new B2017Q().solveTests();
    }
    
    private void solveTests() throws Exception {
        solveTest("B-test.in", "B-test.out");
//        solveTest("B-small-practice.in", "B-small-practice.out");
    }
    
    private void solveTest(String in, String out) throws Exception {
        final String filename = getClass().getResource(in).getFile().replaceAll("%20", " ").replaceAll("build/classes", "src");
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
            final String line = solveFor(scanner.nextLine()) + "\n";
            writer.write(line);
            System.out.print(line);
        }
    }
    
    private static String NINES = "999999999999999999999999999999999999999999";
    private String solveFor(String number) {
        // 1. if number length is 1, return number
        if (number.length()==1) return number;
        
        // 2. if all numbers are the same, return number
        if (number.matches("(\\d)\\1+")) return number; // http://stackoverflow.com/a/6508030/72478
        
        // 3. if number is 11110* then return 9999
        if (number.matches("[1]+0\\d*")) return number + " " + NINES.substring(0, number.length() - 1);

        String result = "";
        // 4. otherwise, iterate, copy and -1 and 99.. on drop
        for (int i = 0; i < number.length(); i++) {
            char current = number.charAt(i);
            // next character with "hack" failsafe last next to "9" for corrent append of final element at the end of loop
            char next = i==number.length()-1?
                            "9".charAt(0):
                            number.charAt(i+1);
            // ascii numbers for numbers are 48 (0) to 57 (9) so we can simply compare chars
            if (current>next) {
                result += (char)(current - 1);
                result += NINES.substring(0, number.length() - i - 1);
                break;
            }
            result += current;
        }
        return number + " " + result;
    }
    
}
