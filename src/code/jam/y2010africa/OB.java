package code.jam.y2010africa;

import java.io.File;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.Scanner;

public class OB {
    
    private static String solve(int townsCount, int office, int[][] employees) {
        int[] townCars = new int[townsCount];
        for (int i = 0; i < townCars.length; i++) {
            try {
                townCars[i] = office == i
                        ? 0 : // skip town where employees can go to work on foot
                        getCarsFor(employees, i);
            } catch (IllegalArgumentException e) {
                return "IMPOSSIBLE";
            }
        }
        return toString(townCars);
    }
    
    private static int getCarsFor(int[][] employees, int town) {
        int employeesCountForTown = 0;
        for (int i = 0; i < employees.length; i++) {
            if (employees[i][0]==town) {
                employeesCountForTown++;
            }
        }
        int[] employeeCapacitiesForTown = new int[employeesCountForTown];
        int index = 0;
        for (int i = 0; i < employees.length; i++) {
            if (employees[i][0]==town) {
                employeeCapacitiesForTown[index] = employees[i][1];
                index++;
            }
        }
        Arrays.sort(employeeCapacitiesForTown);
        return getCapacitiesFor(employeeCapacitiesForTown);
    }
    
    private static int getCapacitiesFor(int[] employeeCapacitiesForTown) {
        int top = 0;
        int bottom = employeeCapacitiesForTown.length-1;
        int cars = 0;
        while(top<=bottom) {
            if (employeeCapacitiesForTown[bottom]==0) {
                throw new IllegalArgumentException("cannot drive himself");
            }
            cars++;
            employeeCapacitiesForTown[bottom]--; // drive himself
            while(employeeCapacitiesForTown[bottom]>0) {
                employeeCapacitiesForTown[bottom]--; // drive another person
                top++;
            }
            bottom--;
        }
        return cars;
    }

    private static String toString(int[] numbers) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < numbers.length; i++) {
            if (i>0) result.append(' ');
            result.append(Integer.toString(numbers[i]));
        }
        return result.toString();
    }
    
    private static void solveTest(String in, String out) throws Exception {
        Scanner s = new Scanner(new File(in));
        FileWriter w = new FileWriter(new File(out));
        final int cases = s.nextInt(); s.nextLine();
        for (int i = 0; i < cases; i++) {
            final int townsCount = s.nextInt();
            final int office = s.nextInt() - 1; s.nextLine();
            final int employeesCount = s.nextInt(); s.nextLine();
            final int[][] employees = new int[employeesCount][2];
            for (int j = 0; j < employeesCount; j++) {
                final int employeeTown = s.nextInt();
                final int employeeDriveCapacity = s.nextInt();
                employees[j][0] = employeeTown-1;
                employees[j][1] = employeeDriveCapacity;
            }
            final String result = solve(townsCount, office, employees);

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
