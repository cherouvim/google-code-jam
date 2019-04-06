package code.jam.y2014.quali;

import java.io.*;
import java.util.*;

public class ProblemD {

    private static String PATH = "F:\\dev\\projects\\code-jam-2012\\src\\code\\jam\\y2014\\quali";
    File inputFile = new File(PATH, "D-sample.in");
    File outputFile = new File(PATH, "D-sample.out");
    BufferedReader in;
    PrintWriter out;
    StringTokenizer st = new StringTokenizer("");

    public static void main(String[] args) throws Exception {
        new ProblemD().solve();
    }

    void solve() throws Exception {
        in = new BufferedReader(new FileReader(inputFile));
        out = new PrintWriter(outputFile);

        for (int testCase = 1, testCount = nextInt(); testCase <= testCount; testCase++) {
            print("Case #" + testCase + ": " + solve(testCase));
        }

        out.close();
    }
    
    String solve(int testCase) throws IOException {
        final int blocksCount = nextInt();
        Set naomiBlocks = new TreeSet();
        for (int i = 0; i < blocksCount; i++) {
            naomiBlocks.add(Double.valueOf(nextDouble()));
        }
        Set kenBlocks = new TreeSet();
        for (int i = 0; i < blocksCount; i++) {
            kenBlocks.add(Double.valueOf(nextDouble()));
            
        }
        final int warNaomiResult = getWarNaomiResult(new ArrayList(naomiBlocks), new ArrayList(kenBlocks));
        final int deceitfulWarNaomiResult = getDeceitfulWarNaomiResult(new ArrayList(naomiBlocks), new ArrayList(kenBlocks));
        return deceitfulWarNaomiResult + " " + warNaomiResult;
    }
    
    private int getWarNaomiResult(List naomiBlocks, List kenBlocks) {
        int naomiScore = 0;
        int kenScore = 0; // may not be needed
        while(!naomiBlocks.isEmpty()) {
            double naomiBlock = ((Double)naomiBlocks.remove(0)).doubleValue();
            double kenBlock = getKenBlock(naomiBlock, kenBlocks);
            if (naomiBlock>kenBlock) {
                naomiScore++;
            } else {
                kenScore++;
            }
        }
        return naomiScore;
    }
    
    private int getDeceitfulWarNaomiResult(List naomiBlocks, List kenBlocks) {
        int naomiScore = 0;
        int kenScore = 0; // may not be needed
        while(!naomiBlocks.isEmpty()) {
            double naomiRealBlock = ((Double)naomiBlocks.remove(0)).doubleValue();
//            double naomiDeceitfulBlock = naomiBlocks.size()>1?
//                    (((Double)kenBlocks.get(0)).doubleValue() + ((Double)kenBlocks.get(1)).doubleValue()) /2
//                    :
//                    naomiRealBlock;
            double naomiDeceitfulBlock = ((Double)kenBlocks.get(kenBlocks.size()-1)).doubleValue() - 0.00000000001d; // deceitful last
            double naomiTellBlock = naomiRealBlock > naomiDeceitfulBlock ? naomiRealBlock : naomiDeceitfulBlock;
            double kenBlock = getKenBlock(naomiTellBlock, kenBlocks);
            if (naomiRealBlock>kenBlock && naomiTellBlock<kenBlock) {
                System.out.println("ERROR");
            }
            if (naomiTellBlock>kenBlock) {
                naomiScore++;
            } else {
                kenScore++;
            }
        }
        return naomiScore;
    }
    
    private double getKenBlock(double naomiBlock, List kenBlocks) {
        for (Iterator it = kenBlocks.iterator(); it.hasNext();) {
            double kenBlock = ((Double)it.next()).doubleValue();
            if (kenBlock>naomiBlock) {
                it.remove();
                return kenBlock;
            }
        }
        return ((Double)kenBlocks.remove(0)).doubleValue(); // get smallest
    }
   
    private void print(String text) {
        out.println(text);
        System.out.println(text);
    }

    
    /**
     * helpers
     */
    String nextToken() throws IOException {
        while (!st.hasMoreTokens()) {
            st = new StringTokenizer(in.readLine());
        }
        return st.nextToken();
    }
    
    int nextChar() throws IOException {
        return in.read();
    }

    int nextInt() throws IOException {
        return Integer.parseInt(nextToken());
    }

    long nextLong() throws IOException {
        return Long.parseLong(nextToken());
    }

    double nextDouble() throws IOException {
        return Double.parseDouble(nextToken());
    }

    String nextLine() throws IOException {
        st = new StringTokenizer("");
        return in.readLine();
    }

    boolean EOF() throws IOException {
        while (!st.hasMoreTokens()) {
            String s = in.readLine();
            if (s == null) {
                return true;
            }
            st = new StringTokenizer(s);
        }
        return false;
    }
    
}
