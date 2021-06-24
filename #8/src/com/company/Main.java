package com.company;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        System.out.println("\nGroups: ");
        // Groups, they let you group statements. In JS, you could replicate this with an if (true) {code-here} statement.
            // They don't do anything different from running them outside of a group.
            // Although they do have block-scope, so be aware of that.
        {
            System.out.println("This is in a group.");
        }

        System.out.println("\ninstanceof: ");
        // instanceof it's a keyword that lets you determine if an object is an instance of another class.
        String whatIsThisAnInstanceOf = "I am an Integer.";
        if (whatIsThisAnInstanceOf instanceof String) {
            System.out.println(whatIsThisAnInstanceOf + " is an instance of the String class.");
        } else {
            // The getClass() method returns the class that the object it's used on is an instance of.
            System.out.println(whatIsThisAnInstanceOf + " is an instance of the " + whatIsThisAnInstanceOf.getClass());
        }

        System.out.println("\nUnary minus: ");
        // Unary -
            // In java, unary - negates the number it's attached to, like this:
        int number = 3;
        System.out.println(-number); // Prints -1, -number also has the value of -1.

        System.out.println("\nPost and Pre Increment/Decrement: ");
        // Post and pre increment/decrement
        {
            // In java, we have post and pre decrement:
                number++; // returns the value of number, then increases the value of number
                ++number; // increases the value of the number, then returns it.
            // You might be wondering, what's the difference?
                // System.out.println(number++); prints 3
                // System.out.println(++number); prints 4 (Without the previous statement.)
            // So in theory, we can do a calculation like this:
            {
                int a = 2;
                int v = ++a + ++a * ++a; // v holds the value 23.
                //       3  +  4  *  5 = 23
                System.out.println(v);
            }

            // Whereas, using post-increment:
            {
                int a = 2;
                int v = a++ + a++ * a++; // v holds the value 14.
                //       2  + 3   * 4  = 14
                System.out.println(v);
            }

            // Here's something cool you can do:
            {
                int[] integerArray = new int[]{1, 0, 0};
                int i = 1;
                integerArray[i++] = integerArray[i] + 1;
                // Stores the value of (index 3 + 1) in index 2
                System.out.println(Arrays.toString(integerArray));
            }
        }

        // Other conditionals, Boolean AND, OR, and XOR
        System.out.println("\nOther Conditionals: ");
        {
            System.out.println("\nBoolean AND: ");
            // AND
            {
                // Boolean AND, &, is exactly like && except that it always evaluates both operators,
                // whereas the normal && only evaluates the first operator, if the first one is false.

                // This is only really useful when you're using operators with side effects, like this:
                int i = 1;
                if (++i == 1 & ++i == 2) {
                    // Even though this code block isn't run, it still increases the value of i by 2, for the pre-increments
                    // in the conditional.
                    System.out.println("i has a value of " + i);
                }
                System.out.println("i has a value of " + i);

            }

            {
                System.out.println("\nBoolean OR: ");
                // Boolean OR, |, is exactly like the normal || except it always evaluates both operators,
                // Unlike the normal version, which only evaluates the second operator if the first one is false.
                int i = 1;

                // if (++i == 3 || ++i == 4) {}
                // If the above code were to be run, the resulting value of i would be 2.
                if (++i == 3 | ++i == 4) {
                }
                // The resulting value of i is 3.
                System.out.println("i has a value of " + i);
            }

            {
                System.out.println("\nBoolean XOR: ");
                // Boolean XOR, ^, is an XOR gate, which is only active if **only one of** its inputs is true, which
                // means that it always evaluates both of its inputs.
                int i = 3;
                // if (++i == 3 || ++i == 4) {}
                // If this code were to run, the resultant value of i would be 3.

                System.out.println(i);
                if (i++ == 3 ^ ++i == 5) {
                    // This code won't run, since both of its operators are true.
                }
                // HOWEVER, since both of the operators were evaluated, i holds the value of 5.
            }
        }

        System.out.println("\nBitwise Operators: (Nothing's printed in this section)");
        // Bitwise operators
        {
            // They directly manipulate the bits that make up a number.

            // Bitwise complement (~)
            {
                // Changes 1 to 0 and 0 to 1
                byte b = 12; // b holds the value of 0001100
                byte c = (byte) ~b; // c now holds the value of -13.
                // This is because b inverted is 1110011, which is read as -13 by java.
                // The actual value of c is 1111111111110011, since that tells java to read it as -13.

            }

            // Bitwise AND (&)
            {
                // Sets the returned bit to 1 if it's 1 in both the operators
                byte a = 10; // 00001010
                byte b = 7; // 00000111
                byte c = (byte) (a & b); // 00000111, which is 2 in decimal.
            }
            // Bitwise OR (|)
            {
                // Sets the returned bit to 1 if its 1 in either of the operators, 0 if it's a 0 bit in both of them.
                byte a = 10; // 00001010
                byte b = 7; // 00000111
                byte c = (byte) (a | b); // 00001111 or 15 in decimal.
            }

            // Bitwise XOR (^)
            {
                // Sets the returned bit to 1 only if it's 1 in one of the operators. 0 if it's 1 in both or neither.
                byte a = 10; // 00001010
                byte b = 7; // 00000111
                byte c = (byte) (a ^ b); // 00001101 or 13 in decimal.
            }

            // For the bitwise shift operations, if the number of bytes to shift exceeds the number of bits specified by
                // its class, it's discarded.
                // For the byte class, it has 8 functional bits.

            // Bitwise left shift (<<)
            {
                // Shifts the bits of the first operand left by the number of places specified by the second operand.
                // Effectively, this multiplies the first number by 2 to the power of the second number.
                byte a = 10; // 00001010
                byte b = 1; // 00000001
                byte c = (byte) (a << b); // 00010100, which is 20 in decimal.
            }

            // Bitwise right shift (>>) (Signed)
            {
                // Shifts the bits of the first operand right by the number of places specified by the second operand.
                // Effectively, this divides the first number by 2 to the power of the second number.
                {
                    byte a = 10; // 00001010
                    byte b = 1; // 00000001
                    byte c = (byte) (a >> b); // 00000101, which is 5 in decimal.
                }

                // If the first number is negative, 1 is shifted into overlapped bits, whereas positive numbers get 0 shifted in.
                // Don't ask me what the numerical equivalent to this is. I have no idea. Go ask Wikipedia.
                    // Do I think that there will ever come a use for a Negative Bitwise left shift? No.
                {
                    byte a = -10; // 11110110
                    byte b = 1; // 00000001
                    byte c = (byte) (a >> b); // 11111011, which is -5 in decimal.
                }
            }

            // Bitwise right shift (>>>) (Unsigned)
            {
                // Shifts the bits of the first operand right by the number of places specified by the second operand.
                // No, I have no idea what this is used for.

                byte a = -10; // 11110110
                byte b = 1; // 00000001
                byte c = (byte) (a >>> b); // 01111011, which is 123 in decimal.
            }

        }

        System.out.println("\nAssignment Operators: (Nothing's printed in this section)");
        // Assignment Operators
        {
            // Assigns an operator to itself operated by a second operator.

            // Arithmetic Operators
            {
                float i = 3;
                i += 1; // i = 4
                i *= 2; // i = 8
                i -= 2; // i = 6
                i /= 2; // i = 2
                i %= 2; // i = 0
            }

            // Bitwise operators
            {
                byte i = 1; // 00000001
                i &= 3; // 00000001 & 00000011 -> 00000001 which is decimal 1.
                i |= 3; // 00000001 | 00000011 -> 00000011 which is decimal 3.
                i ^= 5; // 00000011 | 00000101 -> 00000100 which is decimal 4.
            }

            // Shift operators
            {
                byte i = -3; // 11111101
                i <<= 1; // 11111101 << 1 -> 11111011 which is decimal -5.
                i >>= 1; // 11111110 >> 1 -> 11111101 which is decimal -3.
                i >>>= 1; // 11111101 >>> 1 -> 01111110 which is decimal 126.
            }

            // Quiz problem: (Solve it if ya want.)
            byte a = 1;
            byte b = 1;
             int c = (++a << b) + 5;
            // System.out.println(c); // Prints the square root of the square root of 6561.
        }

        System.out.println("\nLambda Expressions: ");
        // Lambda Expressions
        {
            // Lambda expressions are used to define anonymous functions and are effectively
                // the same as arrow functions in javascript. You'll know when to use it
                // when your IDE tells you that you can, although you might remember its usage.
            // They look like this: (They cannot be used as statements-- THEY MUST BE USED AS PARAMETERS FOR OTHER FUNCTIONS.
            /*
            () -> {
                System.out.println("Hello?");
            };
             */

            // Here's an example, creating an anonymous Runnable object to start a new thread with:
            Thread thread = new Thread(() -> {
                System.out.println("Hello from the second thread.");
            });
            thread.start();
            System.out.println("Hello from the main thread.");
        }
    }
}