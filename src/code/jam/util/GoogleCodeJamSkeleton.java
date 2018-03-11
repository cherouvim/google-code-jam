package code.jam.util;


import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

/**
 *
 * @author jc
 */
public abstract class GoogleCodeJamSkeleton {
    
    public abstract void solveTest(Scanner scanner, FileWriter writer) throws Exception;
    
    private void solveTest(String in, String out) throws Exception {
        final String filename = getClass().getResource(in).getFile().replaceAll("%20", " ");
        File file = new File(filename);
        Scanner scanner = new Scanner(file);
        FileWriter writter = new FileWriter(new File(file.getParent(), out));
        System.out.println("solving " + in);
        solveTest(scanner, writter);
        writter.flush();
    }
    
    private void solveTests(String testName) throws Exception {
        solveTest(testName + "-small.in", testName + "-small.out");
        solveTest(testName + "-large.in", testName + "-large.out");
    }
    
    public void run() throws Exception {
        solveTests(getClass().getSimpleName());
    }
    
    
}
