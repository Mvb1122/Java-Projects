package com.company;
// ^ This tells JVM to read this code when it starts.

import classes.beings.*;
// ^ This tells Java to import all of the classes in the classes.beings folder.

public class Main {

    public static void main(String[] args) {
        // This section covers packages and classes, go to each class's file for more information.
        int humanNum = (int) Math.floor(Math.random() * 999999);
        Person me = new Person("Human Number " + humanNum);
        // ^ You can then access a class's constructor via its class name.
        me.kill();
        // ^ Once you've instantiated the class (made a variable for it), you can access its methods with a dot.

        String randomCatName = "Max";
        Cat kiddy = new Cat(randomCatName, "Tabby", "Male");
        //                   ^ You can also pass variables into a constructor.

        for (int i = 0; i < 10; i++) {
            kiddy.pet();
            // Like any other statement, a method can usually be run within a loop or any other group.
        }

        System.out.println("There is " + Being.getPopulation() + " living being(s).");
        // Static methods are referenced by calling them on the class's name.
            // Why does this output 1 even though I haven't declared a single being?
                // It's because both the Person and Cat classes extend being,
                // which means that their constructors reflect the one held within
                // the Being class, so population is always increased by one
                // when one of its extending classes run its constructor.

    }
}
