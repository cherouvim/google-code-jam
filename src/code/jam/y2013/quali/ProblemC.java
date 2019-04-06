package code.jam.y2013.quali;

import java.io.*;
import java.util.StringTokenizer;

public class ProblemC {

    private static String PATH = "F:\\dev\\projects\\code-jam-2012\\src\\code\\jam\\y2013\\quali";
    File inputFile = new File(PATH, "C-large-1.in");
    File outputFile = new File(PATH, "C-large-1.out");
    BufferedReader in;
    PrintWriter out;
    StringTokenizer st = new StringTokenizer("");

    public static void main(String[] args) throws Exception {
        new ProblemC().solve();
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
        final long min = nextLong();
        final long max = nextLong();
        int count = 0;
        //for (long i = min; i <= max; i++) {
            //if (isPalindrome(i) && hasPalindromeSquare(i)) count++;
        //}
        return String.valueOf(count);
    }
    
    private boolean isPalindrome(long i) {
        //String value = String.valueOf(i);
        //return new StringBuffer(value).reverse().toString().equals(value);
        return false;
    }
    
    private boolean hasPalindromeSquare(long i) {
        final long square = (long)Math.sqrt(i);
        return isPalindrome(square) && i==square*square;
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
