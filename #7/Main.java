import java.util.Arrays;
//  See line 9
import java.util.ArrayList;
//  See line 36
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


        /*
        Part 2: Arraylists.

        They're basically Arrays in Java; they can be resized and all, BUT they can only have one type of data.
        You also have to use Integer when declaring them; not int.

        jkjk about the one data type. If you initialize them with <assortment> they can hold whatever you want, like in JS.
        (But otherwise, you *have* to only have one kind of data. <assortment> does not work with normal Arrays.)
         */

        ArrayList<Integer> resizeableNumbers = new ArrayList<>(); // This makes a new ArrayList called "resizeableNumbers."

        resizeableNumbers.add(1); // 0
        resizeableNumbers.add(2); // 1
        resizeableNumbers.add(3); // 2
        resizeableNumbers.add(4); // 3
                                  // 4
        resizeableNumbers.add(6); // 5
        resizeableNumbers.add(7); // 6
        resizeableNumbers.add(8); // 7
        resizeableNumbers.add(9); // 8
        resizeableNumbers.add(10);// 9

        // There, resizableNumbers now contains the same info as numbersOneThroughTen. But wait, I made a mistake.
        System.out.println("Value of resizeableNumbers (before replacement): " + resizeableNumbers); // You can print ArrayLists without anything funky.

        resizeableNumbers.add(4, 5); // Fix my mistake by adding the number 5 at index 4.

        System.out.println("Value of resizeableNumbers (after replacement): " + resizeableNumbers);

        // .size(); is like .length(); for arrays, but for ArrayLists.
        System.out.println("Size of resizeableNumbers: " + resizeableNumbers.size());

        // Getting items from an ArrayList has us use .get(); instead of square brackets. []
        System.out.println("Item in the 2nd index of resizeableNumbers: " + resizeableNumbers.get(2));

        // You can ".set();" items in an ArrayList via .set().
        resizeableNumbers.set(5, 69420);
        System.out.println("List after being .set();: " + resizeableNumbers);

        // Removing Items:
        // NOTE: You can also use .remove(); with a string as a parameter. It will remove the
        // first incident of that string being used.
        resizeableNumbers.remove(5);
        resizeableNumbers.add(5, 6);
        System.out.println("List after .remove(5) and .add(5, 6): " + resizeableNumbers);

        // Finding index:
        int indexOfFive = resizeableNumbers.indexOf(5);
        System.out.println("The index of \"5\" is: " + indexOfFive);

        // Assortment ArrayLists
        ArrayList assortmentArrayList = new ArrayList<>(3);
        assortmentArrayList.add("Hello");
        assortmentArrayList.add(3);
        assortmentArrayList.add('c'); // char
        System.out.println("Assortment Array List: " + assortmentArrayList);
    }
}
