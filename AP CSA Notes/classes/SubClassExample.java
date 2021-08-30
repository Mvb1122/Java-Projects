package classes;

public class SubClassExample extends SuperClassExample {
  // Subclasses have all of the features of their superclasses, except they just add or replace parts.

  public SubClassExample(int i) {super(i);}

  public void increase() {
    member++;
  }

  public void decrease() {
    member--;
  }

  public int getMemebr() {
    return member;
  }
}