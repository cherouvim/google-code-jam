package code.jam.y2014.quali;

import java.io.*;
import java.util.StringTokenizer;

public class ProblemA {

    private static String PATH = "F:\\dev\\projects\\code-jam-2012\\src\\code\\jam\\y2014\\quali";
    File inputFile = new File(PATH, "A-small-attempt0.in");
    File outputFile = new File(PATH, "A-small-attempt0.out");
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
            print("Case #" + testCase + ": " + solve(testCase));
        }

        out.close();
    }
    
    String solve(int testCase) throws IOException {
        final int height = 4; // hardcoded
        final int width = 4; // hardcoded
        final int firstResponse = nextInt();
        String firstResponseLine = "";
        for (int row = 1; row <= 4; row++) { // hardcoded 4 rows
            String temp  = nextLine();
            if (firstResponse==row) {
                firstResponseLine = temp;
            }
        }
        final int secondResponse = nextInt();
        String secondResponseLine = "";
        for (int row = 1; row <= 4; row++) { // hardcoded 4 rows
            String temp  = nextLine();
            if (secondResponse==row) {
                secondResponseLine = temp;
            }
        }
        String firstResponseNumbers[] = firstResponseLine.split(" ");
        String secondResponseNumbers[] = secondResponseLine.split(" ");
        String correct = null;
        for (int i = 0; i < firstResponseNumbers.length; i++) {
            String number1 = firstResponseNumbers[i];
            for (int j = 0; j < secondResponseNumbers.length; j++) {
                String number2 = secondResponseNumbers[j];
                if (number1.equals(number2)) {
                    if (correct!=null) return "Bad magician!";
                    correct = number1;
                }
            }
        }
        return correct==null?"Volunteer cheated!":correct;
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
