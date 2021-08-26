// Notes taken during AP CSA Class.

import java.util.Scanner;
public class APCSANotes {
    public static void notesOfAugust25() {
      // Most computers hold values in named storage locations; eg-- variables.

      // They’re initialized like
        // ClassName variableName;
      // Or like 
        // ClassName variableName = value;
      {
      // Alternatively, you can also make multiple at once:
        int a, b, c;
      }
      

      // There are a few primitive types:
        // int - an integer number: 1
        // double - a floating point number: 1.3
        // char - a single character: ‘a’
        // String - Not technically a primitive, but it is included by default and has a literal type: “hello world”


      // Naming Variables:
        // We use full words instead of singular letters to name a variable; eg: `canVolume` instead of `cv`.
        // Variables must start with a letter or an underscore.
        // They are case sensitive, so you can have `canVolume` and `cAnVolume` be different values.
      {
      // Interacting with variables:
        // We can do something like so:
      double total = 0; // Variables have to be declared before they are used.
      total = total + 1;
      // This increases total by one.
      }

      {
      // Variable Modifiers:
        // We can declare variables as being final, in order to lock them down to ensure that they don't change.
      final double pi = 3.14;
        // For instance, PI will always be 3.14, so it won't ever change.
      }

      // Types of Comments:
        // There are single line comments,

        /*
          Multi line comments.
        */

        /**
        *  and JavaDoc comments, which document your code for people 
        *  using it.
        */
    
      // Overflow errors:
        // Because of how numbers work, a computer can only store a number up
        // to a certain size. So then, when a value tries to go over its value,
        // it causes an "Overflow" error:
          // int x = 2147483648; 
        // This will cause an Overflow error, since int values can only hold up to 2147483647 or as low as -2147483648.
      
      {
      // Math
        // Java has an in-built math library:
        double b = Math.sqrt(100); // b = 10;
        // There are several methods, so I'm not going to cover them.

        // There's also some constants built in:
        final double pi2 = Math.PI; // pi = 3.14...

      // Casting
        // We can convert one conversable type to another via casting:
      int d = (int) 3.14; // i now holds 3
        // When casting from a double to an int, it doens't round, it just
        // cuts off the values.
      }

      // Scanner
        // Scanner is an in-built library used for 
        // getting user input, it's imported like:
        // import java.util.Scanner;
        // Which is put at the beginning of the program in order to tell the compiler to include the Scanner library.

      {
        // When we use it, we first have to initialize it:
        Scanner s = new Scanner(System.in);

        // Then, we can get input from it, like this:
        System.out.println("Enter a number.");
        int b = s.nextInt();
        System.out.println(b + " squared is " + Math.pow(b, 2));

      }
    }

    public static void notesOfAugust27() {
      // System.out.println("Fill this in, eventually.");
    }

    public static void main(String[] args) {
      notesOfAugust25();
      notesOfAugust27();
    }
}