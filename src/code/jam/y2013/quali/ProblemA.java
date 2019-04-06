package code.jam.y2013.quali;

import java.io.*;
import java.util.StringTokenizer;

public class ProblemA {

    private static String PATH = "F:\\dev\\projects\\code-jam-2012\\src\\code\\jam\\y2013\\quali";
    File inputFile = new File(PATH, "A-large.in");
    File outputFile = new File(PATH, "A-large.out");
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
        final String data = nextLine() + nextLine() + nextLine() + nextLine(); // hardcoded
        nextLine(); // empty
        // horizontal
        for (int i = 0; i < height; i++) {
            final String line = data.substring(i*width, i*width+width);
            if (line.matches("[XT]{4}")) return "X won";
            if (line.matches("[OT]{4}")) return "O won";
        }
        // diagonal
        if (data.matches("[XT]....[XT]....[XT]....[XT]")) return "X won";
        if (data.matches("[OT]....[OT]....[OT]....[OT]")) return "O won";
        if (data.matches("...[OT]..[OT]..[OT]..[OT]...")) return "O won";
        if (data.matches("...[OT]..[OT]..[OT]..[OT]...")) return "O won";
        
        // vertical
        if (data.matches(".*[XT]...[XT]...[XT]...[XT].*")) return "X won";
        if (data.matches(".*[OT]...[OT]...[OT]...[OT].*")) return "O won";

        if (data.contains(".")) {
            return "Game has not completed";
        } else {
            return "Draw";
        }
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
