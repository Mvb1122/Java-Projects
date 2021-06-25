package classes.beings;

public class Being {
    public boolean living;
    public String name;
    // We declare the fields for the class at the start of the class in order to allow us to assign them later.
    public static int population;
    // Static variables hold the same value across all instances of a class.

    public Being(String name) {
        // This is a constructor, it returns the Being object which holds the living and name fields.
        this.name = name;
        this.living = true;

        population++;
    }

    public void kill() {
        // This is a method that returns void-- it doesn't return anything.

        this.living = false;

        population--;

        System.out.println("Killing " + this.name + "!");
    }

    public static int getPopulation() {
        // This is a static method, which means that it has the same result weather it's called on an instance of this class
        // or the class itself.
        return population;
    }

    public void setName(String name) {
        this.name = name;
    }
}
