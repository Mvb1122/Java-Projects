package classes.beings;

public class Cat extends Being {
    // Look at the Person class first! Then look at this one.

    public String gender;
    String breed;

    public Cat(String name) {
        super(name);
    }

    public Cat(String name, String breed) {
        super(name);
        this.breed = breed;
    }

    public Cat(String name, String breed, String Gender) {
        super(name);
        this.breed = breed;

        // Just in case somebody passes in an improperly capitalized gender,
        // this code capitalizes the first letter and then puts sets the cat's gender to it.
        String[] genderArr = Gender.split("");
        String capitalizedGender = genderArr[0].toUpperCase();
        for (int i = 1; i < genderArr.length; i++) {
            capitalizedGender += genderArr[i];
        }

        this.gender = capitalizedGender;
    }

    public void pet() {
        // Make a new string array representing all of the options for petting the cat.
        String[] options = new String[] {
                "I slapped " + this.name + " so hard " + getPronoun().toLowerCase() + " became a pancake.",
                "I pet " + this.name + ".",
                "I laughed at " + this.name + " because " + getPronoun().toLowerCase() + " looked dumb.",
                "I called " + this.name + " ugly."
        };

        // Generate a random number between 0 and the length of the options array.
        int randNum;
        do {
            // Sometimes, when casting a number to int, it can get casted to -1, so
            // I have to regenerate it until it's not -1.
            randNum = (int) Math.round(Math.random() * options.length - 1);
        } while (randNum < 0);

        System.out.println(options[randNum]);
    }

    private String getPronoun() {
        // This is a private method, which means that only this class itself can access it.

        if (this.gender != null) {
            if (this.gender.equals("Male")) {
                return "He";
            } else if (this.gender.equals("Female")) {
                return "She";
            } else {
                return "They";
            }
        } else {
            return "They";
        }

    }
}
