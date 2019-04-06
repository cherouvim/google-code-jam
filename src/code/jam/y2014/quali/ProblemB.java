package code.jam.y2014.quali;

import java.io.*;
import java.util.StringTokenizer;

public class ProblemB {

    private static String PATH = "F:\\dev\\projects\\code-jam-2012\\src\\code\\jam\\y2014\\quali";
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
        final double cookiesToBuyFarm = nextDouble();
        final double cookiesPerSecondForFarm = nextDouble();
        final double targetCookies = nextDouble();
        System.out.println("trying " + testCase + " with " + targetCookies);
        double bestSecs = Double.MAX_VALUE;
        int farms = 0;
        while(true) {
            double secs = getSecondsForBuyingFarms(farms, 2.0d, cookiesToBuyFarm, cookiesPerSecondForFarm, targetCookies);
            if (secs>bestSecs) return String.valueOf(bestSecs);
            bestSecs = secs;
            farms++;
        }
    }
    
    private double getSecondsForBuyingFarms(int farmsToBeBought, double cookiesPerSecond, final double cookiesToBuyFarm, final double cookiesPerSecondForFarm, final double targetCookies) {
        double time = 0;
        for (int i = 0; i < farmsToBeBought; i++) {
            time+=cookiesToBuyFarm/cookiesPerSecond;
            cookiesPerSecond+=cookiesPerSecondForFarm;
        }
        return time + targetCookies / cookiesPerSecond;
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
