package code.jam.y2016.quali;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class C2016Q {

    public static void main(String[] args) throws Exception {
        new C2016Q().solveTests();
    }
    
    private void solveTests() throws Exception {
        solveTest("C-test.in", "C-test.out");
        solveTest("C-small-attempt0.in", "C-small-attempt0.out");
        //solveTest("C-large.in", "C-large.out");
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
        System.out.println(cases);
        for (int i = 0; i < cases; i++) {
            writer.write("Case #" + (i+1) + ":\n");
            writer.write(solve(scanner.nextInt(), scanner.nextInt()));
            if (scanner.hasNext()) scanner.nextLine();
        }
    }
    
    private String solve(int coinLength, int coins) {
        StringBuilder result = new StringBuilder();
        int coinsProduced = 0;
        System.out.println(coinLength + " " +coins);
        for(long i=0, maxSize = (long)Math.pow(2, coinLength-2); i<maxSize; i++) {
            final String coin = getJamCoin(coinLength, i);
            if (isValid(coin)) {
                System.out.println("found valid");
                coinsProduced++;
                result.append(coin).append(" ");
                result.append(getNonTrivialDivisors(coin));
                result.append("\n");
                if (coinsProduced==coins) break;
            }
        }
        return result.toString();
    }
    
    private boolean isValid(String coin) {
        for (int i = 2; i <= 10; i++) {
            long coinValue = Long.parseLong(coin, i);
            if (isPrime(coinValue)) return false;
        }
        return true;
    }
    
    private String getNonTrivialDivisors(String coin) {
        StringBuilder result = new StringBuilder();
        for (int i = 2; i <= 10; i++) {
            long coinValue = Long.parseLong(coin, i);
            for (int j = 2; j < coinValue-1; j++) {
                if (coinValue%j==0) {
                    result.append(j).append(' ');
                    break;
                }
            }
        }
        return result.toString().trim();
    }
    
    private String getJamCoin(int length, long index) {
        StringBuilder result = new StringBuilder(length);
        result.append('1');
        String core = Long.toBinaryString(index);
        if (core.length()>length-2) {
            core = core.substring(core.length() - (length - 2));
        }
        // pad left
        for(int i=0; i<length-2-core.length(); i++) {
            result.append('0');
        }
        result.append(core);
        result.append('1');
        return result.toString();
    }

    private static boolean isPrime(long number) {
        if (number%2==0) return false;
        for(int i=3; i*i<=number; i+=2) if(number%i==0) return false;
        return true;
    }
    
}
