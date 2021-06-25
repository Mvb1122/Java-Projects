package classes.beings;

public class Person extends Being {
    public String gender;
    // This is an additional field, which doesn't replace any existing ones.

    public Person(String name) {
        // This is the default constructor, it only calls the Being constructor with
        // the name as a parameter.
        super(name);
    }

    public Person(String name, String gender) {
        // This is an example of class overloading-- when a class has more than one constructor.
        // What it does is that it calls the normal being constructor, and then
        // sets the gender to be equivalent to the second parameter.

        super(name);

        // Just in case somebody passes in an improperly capitalized gender,
        // this code capitalizes the first letter and then puts sets the person's gender to it.
        String[] genderArr = gender.split("");
        String capitalizedGender = genderArr[0].toUpperCase();
        for (int i = 1; i < genderArr.length; i++) {
            capitalizedGender += genderArr[i];
        }

        this.gender = capitalizedGender;
    }

    public void setGender(String gender) {
        // This is an instance method, which means that it can't be run on the class itself, and must
        // be run on an instance of this class.

        this.gender = gender;
    }

    // Now you can go to the Cat.java class.
}
