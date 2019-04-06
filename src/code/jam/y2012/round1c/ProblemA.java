package code.jam.y2012.round1c;

import java.io.*;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class ProblemA {

    private static String PATH = "F:\\dev\\projects\\code-jam-2012\\src\\code\\jam\\y2012\\round1c";
    File inputFile = new File(PATH, "A-small-attempt4.in");
    File outputFile = new File(PATH, "A-small-attempt4.out");
    BufferedReader in;
    PrintWriter out;
    StringTokenizer st = new StringTokenizer("");

    public static void main(String[] args) throws Exception {
        new ProblemA().solve();
    }
    
    public static class Node {
        private int id;
        private int[] childrenIds;
        private Set reach = new HashSet();
        
        public Node() {
        }
        
        public int[] getChildrenIds() {
            return childrenIds;
        }
        
        public void setChildrenIds(int[] childrenIds) {
            this.childrenIds = childrenIds;
        }
        
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
        final Node[] nodes = new Node[nextInt()];
        for (int i = 0; i < nodes.length; i++) {
            Node node = new Node();
            node.id = i+1;
            final int[] childrenIds = new int[nextInt()];
            for (int j = 0; j < childrenIds.length; j++) {
                childrenIds[j] = nextInt();
            }
            node.setChildrenIds(childrenIds);
            nodes[i] = node;
        }
        print("Case #" + testCase + ": " + (solve(nodes)?"Yes":"No"));
    }
    
    boolean solve(Node[] nodes) {
        for (int i = 0; i < nodes.length; i++) {
            Node parent = nodes[i];
            for (int j = 0; j < parent.childrenIds.length; j++) {
                Node child = nodes[parent.childrenIds[j]-1];
                Set intersection = new HashSet(parent.reach);
                child.reach.add(Integer.valueOf(parent.id));
                intersection.retainAll(child.reach);
                if (!intersection.isEmpty()) {
                    return true;
                }
                child.reach.addAll(parent.reach);
            }
        }
        return false;
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
