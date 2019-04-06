package code.jam.y2012.quali;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class ProblemA {

    private static String PATH = "F:\\dev\\projects\\code-jam-2012\\src\\code\\jam\\y2012\\quali";
    File inputFile = new File(PATH, "A-small-attempt0.in");
    File outputFile = new File(PATH, "A-small.out");
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
    
    Map mapper = new HashMap();
    {
        mapper.put("a", "y");
        mapper.put("b", "h");
        mapper.put("c", "e");
        mapper.put("d", "s");
        mapper.put("e", "o");
        mapper.put("f", "c");
        mapper.put("g", "v");
        mapper.put("h", "x");
        mapper.put("i", "d");
        mapper.put("j", "u");
        mapper.put("k", "i");
        mapper.put("l", "g");
        mapper.put("m", "l");
        mapper.put("n", "b");
        mapper.put("o", "k");
        mapper.put("p", "r");
        mapper.put("q", "z");
        mapper.put("r", "t");
        mapper.put("s", "n");
        mapper.put("t", "w");
        mapper.put("u", "j");
        mapper.put("v", "p");
        mapper.put("w", "f");
        mapper.put("x", "m");
        mapper.put("y", "a");
        mapper.put("z", "q");
    }

    void solve(int testCase) throws IOException {
        String line = "";
        int c = nextChar();
        while (c!=10 && c!=-1) {
            String replacement = (String)mapper.get(Character.toString((char)c));
            line += replacement==null?(char)c:replacement;
            c = nextChar();
        }
        print("Case #" + testCase + ": " + line);
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
