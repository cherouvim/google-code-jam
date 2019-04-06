package code.jam.y2013.round1c;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class ProblemB {

    private static String PATH = "F:\\dev\\projects\\code-jam-2012\\src\\code\\jam\\y2013\\round1c";
    File inputFile = new File(PATH, "B-small-attempt0.in");
    File outputFile = new File(PATH, "B-small.out");
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
        final int x = nextInt();
        final int y = nextInt();
        List queue = new ArrayList();
        bfs("", queue);
        while(true) {
            String path = (String)queue.remove(0);
            if (solves(path, x, y)) {
                return path;
            }
            if (path.length()>10) {
                return "***";
            }
            bfs(path, queue);
        }
    }
    
    boolean solves(String path, int x, int y) {
        int currentx = 0;
        int currenty = 0;
        int step = 1;
        for (int i = 0, length = path.length(); i < length; i++) {
            char direction = path.charAt(i);
            if (direction=='E') {
                currentx+=step;
            }
            if (direction=='S') {
                currenty-=step;
            }
            if (direction=='W') {
                currentx-=step;
            }
            if (direction=='N') {
                currenty+=step;
            }
            if (currentx==x && currenty==y) {
                return true;
            }
            step++;
        }
        return false;
    }
    
    void bfs(String path, List queue) {
        queue.add(path + "E");
        queue.add(path + "S");
        queue.add(path + "W");
        queue.add(path + "N");
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
