package code.jam.y2010africa;

import java.io.File;
import java.io.FileWriter;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class OA {
    
    private static String solve(int[] guestTickets) {
        Set orphanGuestTickets = new HashSet();
        for (int i = 0; i < guestTickets.length; i++) {
            final Integer guestTicket = Integer.valueOf(guestTickets[i]);
            if (orphanGuestTickets.contains(guestTicket)) {
                orphanGuestTickets.remove(guestTicket);
            } else {
                orphanGuestTickets.add(guestTicket);
            }
        }
        return orphanGuestTickets.toString().replaceAll("\\[|\\]", ""); // funky printing of Set's single element
    }
    
    private static void solveTest(String in, String out) throws Exception {
        Scanner s = new Scanner(new File(in));
        FileWriter w = new FileWriter(new File(out));
        final int cases = s.nextInt(); s.nextLine();
        for (int i = 0; i < cases; i++) {
            final int guestCount = s.nextInt(); s.nextLine();
            final int[] guestTickets = new int[guestCount];
            for (int j = 0; j < guestCount; j++) {
                final int price = s.nextInt();
                guestTickets[j] = price;
            }
            final String result = solve(guestTickets);

            if (i>0) w.write("\n");
            w.write("Case #" + (i+1) + ": ");
            w.write(result);
        }
        w.flush();
    }
    
    private static void solveTests(String testName) throws Exception {
        solveTest(testName + "small.in", testName + "small.out");
        solveTest(testName + "large.in", testName + "large.out");
    }
    
    public static void main(String[] args) throws Exception {
        solveTests(getClassName());
    }
    
    private static String getClassName() {
        return new Object() {}.getClass().getEnclosingClass().getSimpleName(); // returns "Foo" for Foo.class
    }
    
}
