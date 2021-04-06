import java.util.Arrays;
//  See line 9


public class Main {

    public static void main(String[] args) {
        /*
	You might've noticed that ^ this is an array. In this case, it's an array of Strings called "args."
	In JavaScript, we have arrays which support multiple data types; strings and the such.
	HOWEVER, in Java, we don't. Only one type of data is allowed in an array.
	Additionally, we do have native Array support, technically, but you have
	to import the java.util.Arrays package in order to actually use them.

    Video of program running: https://youtu.be/AWXFQePIjVk
        */
        // args is the array of strings passed to our program when it's run.
        System.out.println("The arguments are:" + Arrays.toString(args));
        if (args[0].equals("true")) {
            System.out.println("Wow you did it, you successfully ran the program correctly.");
        } else {
            System.out.println("You have to run the program as \"java Main true\" in order for it to work correctly, idiot.");
        }

        // We can also make our own arrays.
        String[] stringArray = new String[3]; // This creates an empty array with 3 items.
        int[] numbersOneThroughTen = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10}; // This creates an array of 10 integers.
        System.out.println(stringArray); // This prints the memory address, something like: [Ljava.lang.String;@16d3586
        System.out.println(Arrays.toString(numbersOneThroughTen)); // [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
        // We *need* java.util.Arrays in order to print the arrays as human readable.
        // If I rewrote the program to not use java.util.Arrays, it'd be basically the same,
        // except I'd have to remove the print statements.



    }
}
