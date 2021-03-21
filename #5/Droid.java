public class Droid {
  int batteryLevel;
  String name;

  public Droid(String droidName)  {
    name = droidName;
    batteryLevel = 100;
  }

  // toString() is run automatically when you print a variable.
  public String toString() {
    return "Hello, my name is " + name + ".";
  }

  public String performTask(String task) {
    batteryLevel -= 10;
    return name + " is performing " + task;
  }

  public String readBattery() {
    return name + " has " + batteryLevel + " percent remaining.";
  }

  public String transferEnergyFrom(Droid instance) {
    instance.batteryLevel -= 10;
    batteryLevel += 10;
    return "Battery transfer from " + instance.name + " to " + name + " complete.";
  }

  public static void main(String [] args) {
    Droid Codey = new Droid("Codey");
    System.out.println(Codey);
    System.out.println(Codey.performTask("beeping"));
    System.out.println(Codey.performTask("booping"));
    System.out.println(Codey.readBattery());
    Droid BatterySacrifice = new Droid("batsac");
    System.out.println(Codey.transferEnergyFrom(BatterySacrifice));
    System.out.println(Codey.readBattery());
    System.out.println(Codey.transferEnergyFrom(BatterySacrifice));
    System.out.println(Codey.readBattery());
    System.out.println(BatterySacrifice.readBattery());
  }
}