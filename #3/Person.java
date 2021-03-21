public class Person {
    /*
        This is an example of a Java Class. They're kinda like returning objects
        from functions in the way that they work. As you can see, this "class" is called "example-Person",
        which is how we'll access it later.
    */
        int age; // Something you might want to know about a person is their age.
        String gender; // And their gender.

    // You don't *necessarily* have to declare the variables here, I just do it for convenience.

        public Person(int ageInput, String genderInput) {
            age = ageInput;
            gender = genderInput;
            /*
            This is an example of a behavior, it's like a function on an object in JS.
            */
        }

        public String change_gender(String newGender) {
            // ^ This means we're returning a string.
            String oldGender = gender;
            gender = newGender;
            return "Gender changed to " + gender + " from " + oldGender + ".";
            // You don't have to declare variable type when returning a value
            // since you did it at the start of the behavior.
        }
// Remember that you can click the green > to run the class directly.
        public static void main(String[] args) {
            Person Micah = new Person(15, "Male");
            System.out.println("Memory address of Micah: " + Micah);
            // Printing the value of a class results in its memory address.
            // Don't ask me why, idk.
            System.out.println("Gender of Micah: " + Micah.gender);
            System.out.println("Age of Micah: " + Micah.age);

            // Running a behavior:
            Micah.change_gender("Dead lol");
            // This does return a string, but it's going to be deleted here, since we don't have
            // something to deal with it.
            System.out.println("Value of Micah.gender: " + Micah.gender);

            // Since our behavior returns a value, we can do this:
            System.out.println(Micah.change_gender("Male"));
        }
}