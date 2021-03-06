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
            System.out.println("\"" + whatIsThisAnInstanceOf + "\" is an instance of the " + whatIsThisAnInstanceOf.getClass());
        }

        System.out.println("\nUnary minus: ");
        // Unary -
        // In java, unary - negates the number it's attached to, like this:
        int number = 3;
        System.out.println(-number); // Prints -3, -number also has the value of -3.
        // (It doesn't store the value, like in the increment/decrement operators.)

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
                System.out.println("v has a value of " + v);
            }

            // Whereas, using post-increment:
            {
                int a = 2;
                int v = a++ + a++ * a++; // v holds the value 14.
                //       2  + 3   * 4  = 14
                System.out.println("v has a value of " + v);
            }

            // Here's something cool you can do:
            {
                int[] integerArray = new int[]{1, 0, 3};
                int i = 1;
                integerArray[i++] = integerArray[i] + 1;
                // Stores the value of (the value of index 3 + 1) in index 2
                System.out.println(Arrays.toString(integerArray)); // Prints [1, 4, 3]
                // Value of index 3: 3
                // 3 + 1 = 4
                // integerArray[2] is set to 4
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

                System.out.println("i has a value of " + i); // Prints 3.

            }

            {
                System.out.println("\nBoolean OR: ");
                // Boolean OR, |, is exactly like the normal || except it always evaluates both operators,
                // Unlike the normal version, which only evaluates the second operator if the first one is false.
                int i = 1;

                // if (++i == 3 || ++i == 4) {}
                // If the above code were to be run, the resulting value of i would be 2.
                if (++i == 3 | ++i == 4) {}
                // The resulting value of i is 3.
                System.out.println("i has a value of " + i); // Prints 3
            }

            {
                System.out.println("\nBoolean XOR: ");
                // Boolean XOR, ^, is an XOR gate, which is only active if **only one of** its inputs is true, which
                // means that it always evaluates both of its inputs.
                int i = 3;
                // if (++i == 3 || ++i == 4) {}
                // If this code were to run, the resultant value of i would be 3.

                if (i++ == 3 ^ ++i == 5) {
                    // This code won't run, since both of its operators are true.
                }
                // HOWEVER, since both of the operators were evaluated, i holds the value of 5.

                System.out.println("i has a value of " + i); // Prints 5
            }
        }

        System.out.println("\nBitwise Operators: ");
        // Bitwise operators
        {
            // They directly manipulate the bits that make up a number.

            // Bitwise complement (~)
            {
                // Changes 1 to 0 and 0 to 1
                byte b = 12; // b holds the value of 0001100
                byte c = (byte) ~b; // c now holds the value of -13.
                System.out.println("c has a value of " + c);
                // This is because b inverted is 1110011, which is read as -13 by java.
                // The actual value of c is 1111111111110011, since that tells java to read it as -13.

            }

            // Bitwise AND (&)
            {
                // Sets the returned bit to 1 if it's 1 in both the operators
                byte a = 10; // 00001010
                byte b = 7; // 00000111
                byte c = (byte) (a & b); // 00000111, which is 2 in decimal.
                System.out.println("c has a value of " + c);
            }
            // Bitwise OR (|)
            {
                // Sets the returned bit to 1 if its 1 in either of the operators, 0 if it's a 0 bit in both of them.
                byte a = 10; // 00001010
                byte b = 7; // 00000111
                byte c = (byte) (a | b); // 00001111 or 15 in decimal.
                System.out.println("c has a value of " + c);
            }

            // Bitwise XOR (^)
            {
                // Sets the returned bit to 1 only if it's 1 in one of the operators. 0 if it's 1 in both or neither.
                byte a = 10; // 00001010
                byte b = 7; // 00000111
                byte c = (byte) (a ^ b); // 00001101 or 13 in decimal.
                System.out.println("c has a value of " + c);
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
                System.out.println("c has a value of " + c);
            }

            // Bitwise right shift (>>) (Signed)
            {
                // Shifts the bits of the first operand right by the number of places specified by the second operand.
                // Effectively, this divides the first number by 2 to the power of the second number.
                {
                    byte a = 10; // 00001010
                    byte b = 1; // 00000001
                    byte c = (byte) (a >> b); // 00000101, which is 5 in decimal.
                    System.out.println("c has a value of " + c);
                }

                // If the first number is negative, 1 is shifted into overlapped bits, whereas positive numbers get 0 shifted in.
                // Don't ask me what the numerical equivalent to this is. I have no idea. Go ask Wikipedia.
                // Do I think that there will ever come a use for a Negative Bitwise left shift? No.
                {
                    byte a = -10; // 11110110
                    byte b = 1; // 00000001
                    byte c = (byte) (a >> b); // 11111011, which is -5 in decimal.
                    System.out.println("c has a value of " + c);
                }
            }

            // Bitwise right shift (>>>) (Unsigned)
            {
                // Shifts the bits of the first operand right by the number of places specified by the second operand.
                // No, I have no idea what this is used for.

                byte a = -10; // 11110110
                byte b = 1; // 00000001
                byte c = (byte) (a >>> b); // 01111011, which is 123 in decimal.

                // However, in Java, which uses 16 bit 2-signed complement numbers, it's read as -5.
                System.out.println("c has a value of " + c);
            }

        }

        System.out.println("\nAssignment Operators: ");
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
                i %= 2; // i = 1
                System.out.println("i has a value of " + i); // Prints 1
            }

            // Bitwise operators
            {
                byte i = 1; // 00000001
                i &= 3; // 00000001 & 00000011 -> 00000001 which is decimal 1.
                i |= 3; // 00000001 | 00000011 -> 00000011 which is decimal 3.
                i ^= 5; // 00000011 ^ 00000101 -> 00000110 which is decimal 6.
                System.out.println("i has a value of " + i); // Prints 6
            }

            // Shift operators
            {
                byte i = -3; // 11111101
                i <<= 1; // 11111101 << 1 -> 11111011 which is decimal -5.
                i >>= 1; // 11111110 >> 1 -> 11111101 which is decimal -3.
                i >>>= 1; // 11111101 >>> 1 -> 01111110 which is decimal 126.

                // However, java reads this as -2, since we did a signed shift operation on a negative value, so...
                System.out.println("i has a value of " + i); // Prints -2
            }

            // Quiz problem: (Solve it if ya want.)
            byte a = 1;
            byte b = 1;
            int c = (++a << b) + 5;
            System.out.println("The solution to the quiz problem is " + c); // Prints the square root of the square root of 6561.
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

        // Named statements:
        System.out.println("\nNamed statements: ");
        {
            // You can put a name and then a colon before a statement (group or otherwise) to name it.
            namedStatement:
            {
                // This statement is named "namedStatement"
            }
            // Example, a named loop:
            namedLoop:
            for (int i = 0; i < 10; i++) {
                print:
                System.out.println("This is in a loop, and this run statement is named print.");
                break namedLoop;
            }
        }

        // Final Variables:
        {
            // Final variables tell Java that a variable's value is not allowed to change.
            final String finalString = "This string's value can't be changed.";
            final int funnyNumber = 69; // This value will never change.
        }

        // Empty Statements:
        System.out.println("\nEmpty Statements: ");
        {
            ;
            // That is an empty statement. It does nothing. Just press enter instead of doing that.

            if (whatIsThisAnInstanceOf.equals("42069"));
            // That's also another empty statement.

            // One useful use is something like this:
            int a = 0;
            for (int i = 0; i < 10; i++, a++);
            System.out.println(a);

            /*
                What does the above code represent?
                Well, I'm increasing the value of a by the value of i, but not by more than 10.
             */
        }

        // Switch Statements:
        System.out.println("\nSwitch Statements: ");
        {
            int randomNumber = (int) Math.round(Math.random() * 5); // Generate a random number between 0 and 5.

            switch (randomNumber) {
                case 1: // Case statements can direct to the same piece of code, you just put the redirected ones above the
                    // used one and leave the statement empty.
                case 2: System.out.println("The number is 1 or 2."); break;
                case 3: for (int i = randomNumber; randomNumber < randomNumber * 2; i++) {
                    System.out.println("You can also use statements within cases.");
                    break;
                } break;
                case 4: {
                    System.out.println("Normally, people use groups as the statement within a case, like this.");
                    break;
                }
                default: System.out.println("The random number was " + randomNumber); break;
            }

        }

        // Other kinds of loops:
        System.out.println("\nOther Loops: ");
        {
            // While:
            // This loop runs the same statement over-and-over until the conditional evaluates to false.
            System.out.println("\n\tWhile Loop: ");
            {
                int i = 0;
                while (i < 2) {
                    i++;
                    System.out.println("\t\tThe value of i is " + i);
                }
            }

            // Do While:
                // This loop reruns the same code until the conditional is false, although it always runs at least once.
            System.out.println("\n\tDo While Loop: ");
            {
                int i = 0;
                do {
                    System.out.println("\t\tThe value of i is " + ++i);
                } while (i != 3);
            }

            // foreach:
                // This loop reruns the same code on each element of an array.
                // However, one limit of it is that you do not know the index of the element in question,
                // Well, what does that mean? If you have a loop that you want to exclude the last element from,
                // then you can't do it.
            System.out.println("\n\tforeach Loop: ");
            {
                // Example, add all of the numbers in a list:
                int[] i = new int[] {1, 2, 3};
                int countedNumber = 0;
                for(int numberInLoop : i) countedNumber += numberInLoop;
                System.out.println("\t\tThe total value of " + Arrays.toString(i) + " is " + countedNumber);
            }

            // continue statement:
                // Skips the rest of the currently running loop and starts the next one.
            System.out.println("\n\tcontinue Statement:");
            {
                int a = 3;
                for (int i = 0; i <= a; i++) {
                    if (i != a) {System.out.println("\t\tThe value of i is not equal to a."); continue;} else {System.out.println("\t\ti is equal to a."); break;}
                }
            }

        }

        // Synchronised Statement:
        System.out.println("\nSynchronised: ");
        {
            // Since Java was meant to be multithreaded, you can use synchronised statements to ensure that
            // you don't corrupt anything.

            // Here's two examples, one with Synchronised and one without it.
            with:
            {
                System.out.println("\n\tWith Synchronised: ");
                int[] i = new int[]{0}; // Declare i as a single element array, since Synchronised needs an object or array.
                for (int b = 0; b < 10; b++) {
                    // Create 10 new threads, each of which immediately tries to increase i[0] by one.
                    int finalB = b;
                    Thread c = new Thread(() -> {
                        synchronized (i) {
                            i[0]++;
                        }
                        System.out.println("\t\tThread #" + finalB + " finished. The value of i is " + i[0]);
                    });
                    c.start();
                }

                // You can see, that due to the staggering with starting threads, each of the threads prints a different total,
                // However, the eventual total is correct, which is all that really matters.
            }

            // Wait for 1 second for the other threads to complete.
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Now, without:
            without:
            {
                System.out.println("\n\tWithout Synchronised: ");
                int[] i = new int[]{0}; // Declare i as a single element array, since Synchronised needs an object or array.
                for (int b = 0; b < 10; b++) {
                    // Create 10 new threads, each of which immediately tries to increase i[0] by one.
                    int finalB = b;
                    Thread c = new Thread(() -> {
                        i[0]++;
                        System.out.println("\t\tThread #" + finalB + " finished. The value of i is " + i[0]);
                    });
                    c.start();

                    // You can see that, due to the thread's staggered start, the value of i[0] is not always 10 once the
                    // operation completes, although you can also run into the problem that i[0] or the whole i array corrupts,
                    // Due to it being constantly read and written.
                }
            }
        }

        // Wait for 1 second for the other threads to complete.
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        // Try {} catch () {}:
        System.out.println("\n\tTry/Catch Statements:");
        {
            // Try-Catch is a way of trying to do something, and catching any error it may throw,
            // For example, here's a way of looping through an array, and breaking the loop when
            // a certain element or index is reached.
            String[] stringArray = new String[] {"Hello", "I am", "a string element.", "but I'm not printed."};
            int i = 0;
            while (true) {
                try {
                    if (i == 3) {
                        break;
                    }
                    System.out.println("\t\t" + stringArray[i]);
                    i++;
                } catch (ArrayIndexOutOfBoundsException e) {
                    // When you try to get an element from an index that doesn't exist throws
                    // an ArrayIndexOutOfBoundsException. You catch it by declaring it.
                    System.out.println("\t\tThere's no element for index number " + i + "!");
                    break;
                } finally {
                    // Finally actions are run after the try/catch parts are run, even if an error
                    // is thrown.
                    System.out.println("\t\t\tLooped.");
                }
            }
        }

        // Assert statements
        {
            // They basically do nothing if you're not a professional Java dev, (working for Oracle), so don't worry about it.
            System.out.println("\n\tAssertions:");
            int i = (int) Math.floor(Math.random() * 5);
            assert i == 3;
            System.out.println("\t\ti = " + i + " when it should be three. I'm complaining.");
        }



    }

}