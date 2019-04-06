package code.jam.y2012.round1b;

import java.io.*;
import java.util.StringTokenizer;

public class ProblemA {

    private static String PATH = "F:\\dev\\projects\\code-jam-2012\\src\\code\\jam\\y2012\\round1b";
    File inputFile = new File(PATH, "A-small.in.sample");
    File outputFile = new File(PATH, "A-small.out.sample");
    BufferedReader in;
    PrintWriter out;
    StringTokenizer st = new StringTokenizer("");

    public static void main(String[] args) throws Exception {
        new ProblemA().solve();
    }

    void solve() throws Exception {
        in = new BufferedReader(new FileReader(inputFile));
        out = new PrintWriter(outputFile);

        for (int testCase = 1, testCount = nextInt(); testCase <= testCount; testCase++) {
            solve(testCase);
        }

        out.close();
    }
    
    void solve(int testCase) throws IOException {
        final int[] judgeScores = new int[nextInt()];
        int totalJudgeScore = 0;
        for (int i = 0; i < judgeScores.length; i++) {
            judgeScores[i] = nextInt();
            totalJudgeScore+=judgeScores[i];
        }
        System.out.println(judgeScores[0] + " " + totalJudgeScore);
        String results = "";
        for (int i = 0; i < judgeScores.length; i++) {
            final double minPercentageForElimination = 1d/3;
            results += " " + Double.toString(minPercentageForElimination);
            
        }
        print("Case #" + testCase + ":" + results);
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
