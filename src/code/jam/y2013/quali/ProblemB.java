package code.jam.y2013.quali;

import java.io.*;
import java.util.StringTokenizer;

public class ProblemB {

    private static String PATH = "F:\\dev\\projects\\code-jam-2012\\src\\code\\jam\\y2013\\quali";
    File inputFile = new File(PATH, "B-large.in");
    File outputFile = new File(PATH, "B-large.out");
    BufferedReader in;
    PrintWriter out;
    StringTokenizer st = new StringTokenizer("");

    public static void main(String[] args) throws Exception {
        new ProblemB().solve();
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
        final int height = nextInt();
        final int width = nextInt();
        final int[][] data = new int[height][width];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                data[y][x] = nextInt();
            }
        }
        // for each cell
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (!isPossibleCellWithLawnmower(data, x, y)) {
                    return "NO";
                }
            }
        }
        return "YES";
    }
    
    private boolean isPossibleCellWithLawnmower(int[][] data, int x, int y) {
        return isCellGreaterOrEqualThanLine(data, y, data[y][x]) || isCellGreaterOrEqualThanColumn(data, x, data[y][x]);
    }
    
    private boolean isCellGreaterOrEqualThanLine(int[][] data, int lineIndex, int cellValue) {
        for (int i = 0; i < data[0].length; i++) {
            if (cellValue<data[lineIndex][i]) return false;
        }
        return true;
    }
    
    private boolean isCellGreaterOrEqualThanColumn(int[][] data, int columnIndex, int cellValue) {
        for (int i = 0; i < data.length; i++) {
            if (cellValue<data[i][columnIndex]) return false;
        }
        return true;
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
