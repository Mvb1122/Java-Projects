package August;
import java.util.Scanner;

public class notes_Of_8_30_2021 {
  public static void runNotes() {
    // Since, for most of August, I've taken notes in the main file, I won't be transferring them over, but I'll be using this format for the future.

    // String Concatonation:
      // Strings can be added together like so:
      String firstName = "Micah";
      String lastName = "Bushman";
      String name = firstName + " " + lastName;
      System.out.println(name);

    // String Concatonation using user input:
    {
      Scanner s = new Scanner(System.in);
      System.out.println("Enter Part 1: ");
      String part1 = s.nextLine();
      System.out.println("Enter Part 2: ");
      String part2 = s.nextLine();
      System.out.println(part1 + part2);
    }

    // String substring();
    { // substring(int, int) is a method that returns a part of a string.
      String example = "0123456789";
      // The substring method accepts two parameters,
      // the first is the index to start at,
      // the second is the index to end at.
      String str = example.substring(3, 5);
      // str now holds "34".
    }

    // String charAt(int) method:
      // charAt(int) returns the character at an index in a string.
    {
      String example = "0123456789";
      char ltr = example.charAt(0);
      // ltr now holds '0'.
    }
    
    // String length() method:
    { // length returns the length of a string, starting at 1.
      String example = "0123456789";
      int length = example.length();
      System.out.println(length);
    }

    // Review of the Inheritance Hierarchies:
      // Superclass - A more generalized class.
      // Subclass   - A more specific class.

      // Example:
        // The "car" class extends "vehicle."
  }
}