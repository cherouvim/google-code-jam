package code.jam.y2013.round1b;

import code.jam.util.GoogleCodeJamSkeleton;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Collections;
import java.util.Scanner;

public class A extends GoogleCodeJamSkeleton {

    public static void main(String[] args) throws Exception {
        new A().run();
    }
    
    public void solveTest(Scanner scanner, FileWriter writer) throws Exception {
        final int cases = scanner.nextInt(); scanner.nextLine();
        for (int i = 0; i < cases; i++) {
            final long myMoteSize = scanner.nextLong();
            final int otherMotesCount = scanner.nextInt();
            scanner.nextLine();
            final List otherMotes = new ArrayList(otherMotesCount);
            for (int j = 0; j < otherMotesCount; j++) {
                otherMotes.add(Long.valueOf(scanner.nextLong()));
            }
            if (i>0) writer.write("\n");
            String result = "Case #" + (i+1) + ": " + solve(myMoteSize, otherMotes);
            System.out.println(result);
            writer.write(result);
        }
    }
    
    private long solve(long myMoteSize, List motes) {
        Collections.sort(motes);
        List bfs = new ArrayList();
        bfs.add(new ArrayList());
        while(!bfs.isEmpty()) {
            List operations = (List)bfs.remove(0);
            if (isSolvable(myMoteSize, motes, operations)) return operations.size();
            bfs(myMoteSize, motes, operations, bfs);
        }
        return -1; // cannot happen?
    }
    
    private void bfs(long myMoteSize, List sortedMotes, List operations, List bfsQueue) {
        List leftOperations = new ArrayList(operations);
        leftOperations.add("-");

        List rightOperations = new ArrayList(operations);
        rightOperations.add("+");

        bfsQueue.add(leftOperations);
        bfsQueue.add(rightOperations);
    }
    
    private boolean isSolvable(long myMoteSize, List sortedMotes, List operations) {
        operations = new ArrayList(operations);
        sortedMotes = new ArrayList(sortedMotes);
        for (int i = 0, length = sortedMotes.size(); i < length; i++) {
            long mote = ((Long) sortedMotes.get(i)).longValue();
            while (myMoteSize<=mote) {
                if (operations.isEmpty()) return false;
                String operation = (String)operations.remove(0);
                while(operation.equals("-")) {
                    length--;
                    if (i>=length) {
                        return true;
                    }
                    if (operations.isEmpty()) break;
                    operation = (String)operations.remove(0);
                }
                if (operation.equals("+")) {
                    sortedMotes.add(i, Long.valueOf(myMoteSize-1));
                    mote = ((Long) sortedMotes.get(i)).longValue();
                    length++;
                }
            }
            myMoteSize+=mote;
        }
        return true;
    }
    
}
