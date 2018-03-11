package code.jam.y2016.round1b;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class C2016AB {

    public static void main(String[] args) throws Exception {
        new C2016AB().solveTests("C");
    }
    
    private void solveTests(String testName) throws Exception {
        solveTest(testName + "-test.in", testName + "-test.out");
        solveTest(testName + "-small-attempt0.in", testName + "-small-attempt0.out");
        solveTest(testName + "-small-attempt1.in", testName + "-small-attempt1.out");
        //solveTest(testName + "-large.in", testName + "-large.out");
    }
    
    private void sortRareAtTop(List titles) {
        final Map occurencesLeft = new HashMap();
        final Map occurencesRight = new HashMap();
        for (Iterator iterator = titles.iterator(); iterator.hasNext();) {
            final String title = (String)iterator.next();
            final String left = title.split(" ")[0];
            final String right = title.split(" ")[1];
            Long occursLeft = (Long)occurencesLeft.get(left);
            if (occursLeft==null) {
                occursLeft = new Long(1);
            } else {
                occursLeft = new Long(occursLeft.longValue() + 1);
            }
            Long occursRight = (Long)occurencesRight.get(left);
            if (occursRight==null) {
                occursRight = new Long(1);
            } else {
                occursRight = new Long(occursRight.longValue() + 1);
            }
            occurencesLeft.put(left, occursLeft);
            occurencesRight.put(right, occursRight);
        }
        Collections.sort(titles, new Comparator() {
            public int compare(Object a, Object b) {
                final String leftA = ((String)a).split(" ")[0];
                final String leftB = ((String)b).split(" ")[0];
                int result = ((Long)occurencesLeft.get(leftA)).compareTo((Long)occurencesLeft.get(leftB));
                if (result!=0) return result;
                result = leftA.compareTo(leftB);
                if (result!=0) return result;
                final String rightA = ((String)a).split(" ")[1];
                final String rightB = ((String)b).split(" ")[1];
                result = ((Long)occurencesRight.get(rightA)).compareTo((Long)occurencesRight.get(rightB));
                if (result!=0) return result;
                return rightA.compareTo(rightB);
            }
        });
    }
    
    private void solveTest(String in, String out) throws Exception {
        final String filename = getClass().getResource(in).getFile().replaceAll("%20", " ");
        File file = new File(filename);
        Scanner scanner = new Scanner(file);
        FileWriter writter = new FileWriter(new File(file.getParent(), out));
        System.out.println("solving " + in);
        solveTest(scanner, writter);
        writter.flush();
    }
    
    public void solveTest(Scanner scanner, FileWriter writer) throws Exception {
        final int cases = scanner.nextInt(); scanner.nextLine();
        for (int i = 0; i < cases; i++) {
            final int titlesCount = scanner.nextInt(); scanner.nextLine();
            List titles = new ArrayList();
            for (int j = 0; j < titlesCount; j++) {
                titles.add(scanner.nextLine());
            }
            writer.write("Case #" + (i+1) + ": ");
            writer.write(solveFor(titles));
            writer.write("\n");
        }
    }
    
    private String solveFor(List titles) {
        System.out.println(titles);
        sortRareAtTop(titles);
        System.out.println(titles);
        final Set lefts = new HashSet();
        final Set rights = new HashSet();
        int fakesCount = 0;
        for (Iterator iterator = titles.iterator(); iterator.hasNext();) {
            String[] title = ((String)iterator.next()).split(" ");
            final String left = title[0];
            final String right = title[1];
            if (lefts.contains(left) && rights.contains(right)) {
                fakesCount++;
            }
            lefts.add(left);
            rights.add(right);
        }
        return String.valueOf(fakesCount);
    }
    
}
