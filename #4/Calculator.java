public class Calculator {
  public Calculator() {
    // This is empty because it soley exists to hold the
    // add, subtract, multiply, divide, and modulo methods.
  }

  // Add
  public int add(int a, int b) {
    return a + b;
  }
  // Subtract
  public int subtract(int a, int b) {
    return a - b;
  }
  // Multiply
  public int multiply(int a, int b) {
    return a * b;
  }
  // Divide
  public int divide(int a, int b) {
    return a / b;
  }
  // Modulo (aka remainder)
  public int modulo(int a, int b) {
    return a % b;
  }

  public static void main(String [] args) {
    // Create a new calculator object
    Calculator myCalculator = new Calculator();
    // Print the result of using the 
    // add and subtract methods.
    System.out.println(myCalculator.add(5, 7));
    System.out.println(myCalculator.subtract(45, 11));
  }
}