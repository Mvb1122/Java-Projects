package September;

public class notes_Of_9_2_2021 {
  public static void runNotes() {
    System.out.println("Reached Notes.");

    // Demonstrates class extension/inheiritance.
    a a = new a("a");
    b b = new b(a.member);

    // System.out.println(b.getMember()); // Outputs: "a"
  }
}

class a {
  public String member;

  public a(String mem) {member = mem;}

}

class b extends a {
  public b(String b) {super(b);}

  public String getMember() {
    return member;
  }
}