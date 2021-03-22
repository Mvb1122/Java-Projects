public class Main {
    public static void main(String[] args) {
        System.out.println("Variable Example:");
        int variableName = 8; // Integer variables can't have decimals.
        double variableTwo = 8.2; // Double variables can have decimals.
        String variableThree = "8 + 8.2 is. . .";
        System.out.print(variableThree); // print lets you put text at the end of the line.
        System.out.println(variableName + variableTwo); // println just appends "\n" to the end of the line.
        // Or well, it should. But IntelliJ doesn't want me to do it so whatever.
        // You can also concatenate strings like in ES6. You're not able to do it using `${}` though.
        String part1 = "Hello "; // Needs a space.
        String part2 = "Gamers."; // Javac also throws errors if you don't use semicolons at the end.
        System.out.println(part1 + part2);
    }
}
