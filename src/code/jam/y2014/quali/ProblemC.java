package code.jam.y2014.quali;

import java.io.*;
import java.util.*;

public class ProblemC {

    private static String PATH = "F:\\dev\\projects\\code-jam-2012\\src\\code\\jam\\y2014\\quali";
    File inputFile = new File(PATH, "C-sample.in");
    File outputFile = new File(PATH, "C-sample.out");
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
            print("Case #" + testCase + ":");
            solve(testCase);
        }

        out.close();
    }
    
    void solve(int testCase) throws IOException {
        final int rows = nextInt();
        final int cols = nextInt();
        final int mines = nextInt();
        char grid[][] = new char[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                grid[i][j] = ' ';
            }
        }
        int cellsFilled = 0;
        fillGrid(grid, mines);
        printGrid(grid);
        // Impossible
    }
    
    private void fillGrid(char[][] grid, int mines) {
        int rows = grid.length;
        int cols = grid[0].length;
        int heightCovered = 0;
        int widthCovered = 0;
        while(true) {
            boolean directionDown = (rows - heightCovered)<=(cols - widthCovered);
            if (directionDown) {
                for (int i = heightCovered; i < rows; i++) {
                    if (mines==0) return;
                    grid[i][widthCovered] = '*';
                    mines--;
                }
                widthCovered++;
            } else {
                for (int i = widthCovered; i < cols; i++) {
                    if (mines==0) return;
                    grid[heightCovered][i] = '*';
                    mines--;
                }
                heightCovered++;
            }
        }
    }
    
    private void printGrid(char[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            String line = "";
            for (int j = 0; j < grid[i].length; j++) {
                line+=grid[i][j];
            }
            print(line);
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
