package code.jam.y2016.quali;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class B2017Q {

    public static void main(String[] args) throws Exception {
        new B2017Q().solveTests();
    }
    
    private void solveTests() throws Exception {
        solveTest("B-test.in", "B-test.out");
        solveTest("B-small-attempt0.in", "B-small-attempt0.out");
        solveTest("B-large.in", "B-large.out");
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
            writer.write(solveFastFor(scanner.nextLine()) + "\n");
        }
    }
    
    private int solveFastFor(String pancakeString) {
        int flips = 0;
        char current = '*';
        char previous = '*';
        for(int i=0, length = pancakeString.length(); i<length; i++) {
            current = pancakeString.charAt(i);
            if (i>0 && previous!=current) flips++;
            previous = current;
        }
        if (current=='-') flips++;
        return flips;
    }
    
//    following code solves with data structure representing pancakes. but this is not needed    
//    private String solveFor(String pancakeString) {
//        boolean[] pancake = toPancakeBoolean(pancakeString);
//        int flips = 0;
//        boolean goingForTheWin = pancake[0];
//        boolean goingForTheFlip = !pancake[0];
//            
//        for(int i=0; i<pancake.length; i++) {
//            //if (pancake[i]) 
//        }
//        System.out.println(pancakeString);
//        System.out.println(toPancakeString(toPancakeBoolean(pancakeString)));
//        
//        return "xxx" + pancakeString;
//    }
//    
//    private static boolean[] flipUpTo(boolean[] pancake, int pos) {
//        if (pos>=pancake.length) pos = pancake.length-1;
//        boolean[] result = new boolean[pancake.length];
//        for(int i=0; i<=pos; i++) {
//            result[i]=!pancake[pos-i];
//        }
//        for(int i=pos+1; i<pancake.length; i++) {
//            result[i]=pancake[i];
//        }
//        return result;
//    }
//    
//    private static boolean[] toPancakeBoolean(String pancake) {
//        boolean[] result = new boolean[pancake.length()];
//        for(int i=0, length=pancake.length(); i<length; i++) {
//            result[i]=pancake.charAt(i)=='+';
//        }
//        return result;
//    }
//    
//    private static String toPancakeString(boolean[] pancake) {
//        StringBuilder result = new StringBuilder(pancake.length);
//        for(int i=0; i<pancake.length; i++) {
//            result.append(pancake[i]?'+':'-');
//        }
//        return result.toString();
//    }
    
}
