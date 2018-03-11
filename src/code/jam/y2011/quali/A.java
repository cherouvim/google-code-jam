package code.jam.y2011.quali;

import code.jam.util.GoogleCodeJamSkeleton;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class A extends GoogleCodeJamSkeleton {

    public static void main(String[] args) throws Exception {
        new A().run();
    }
    
    public void solveTest(Scanner scanner, FileWriter writer) throws Exception {
        final int cases = scanner.nextInt(); scanner.nextLine();
        for (int i = 0; i < cases; i++) {
            final int commandsCount = scanner.nextInt();
            List commands = new ArrayList(commandsCount);
            for (int j = 0; j < commandsCount; j++) {
                commands.add(new RobotPosition(scanner.next(), Integer.valueOf(scanner.nextInt())));
            }
            if (i>0) writer.write("\n");
            writer.write("Case #" + (i+1) + ": ");
            writer.write(solve(commands));
        }
    }
    
    private String solve(List commands) {
        RobotPosition robotO = new RobotPosition("O", 1);
        RobotPosition robotB = new RobotPosition("B", 1);
        long movesCount = 0;
        while (!commands.isEmpty()) {
            boolean consumedO = handle(robotO, commands);
            boolean consumedB = handle(robotB, commands);
            if (consumedO && consumedB) {
                throw new RuntimeException("error?");
            }
            if (consumedO || consumedB) {
                commands.remove(0);
            }
            movesCount++;
        }
        return String.valueOf(movesCount);
    }
    
    private boolean handle(RobotPosition robot, List remainingCommands) {
        int nextPosition = nextPosition(robot, remainingCommands);
        if (nextPosition==-1) {
            // this robot is done
            return false;
        }
        if (nextPosition==robot.position && ((RobotPosition)remainingCommands.get(0)).robot.equals(robot.robot)) {
            // push button
            return true;
        }
        if (nextPosition==robot.position && !((RobotPosition)remainingCommands.get(0)).robot.equals(robot.robot)) {
            // wait
            return false;
        }
        if (nextPosition>robot.position) {
            // move forward
            robot.position++;
            return false;
        }
        if (nextPosition<robot.position) {
            // move backward
            robot.position--;
            return false;
        }
        throw new RuntimeException("cannot get here?");
    }
    
    private int nextPosition(RobotPosition robot, List commands) {
        for (Iterator it = commands.iterator(); it.hasNext();) {
            RobotPosition robotPosition = (RobotPosition)it.next();
            if (robotPosition.robot.equals(robot.robot)) {
                return robotPosition.position;
            }
        }
        return -1;
    }
    
    private static class RobotPosition {
        String robot;
        int position;
        private RobotPosition(String robot, int position) {
            this.robot = robot;
            this.position = position;
        }
        public String toString() {
            return "robot " + robot;
        }
    }
    
}
